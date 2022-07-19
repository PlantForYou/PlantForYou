package com.be1.plant4you.service;

import com.be1.plant4you.common.LikesId;
import com.be1.plant4you.common.ScrapId;
import com.be1.plant4you.domain.*;
import com.be1.plant4you.dto.request.PostRequest;
import com.be1.plant4you.dto.request.PostUpdateRequest;
import com.be1.plant4you.dto.response.PostResponse;
import com.be1.plant4you.dto.response.PostShortResponse;
import com.be1.plant4you.enumerate.PostCat;
import com.be1.plant4you.exception.DuplicateRequestException;
import com.be1.plant4you.exception.NoPrevRequestException;
import com.be1.plant4you.exception.NotMyPostException;
import com.be1.plant4you.exception.WrongPostIdException;
import com.be1.plant4you.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;
    private final ScrapRepository scrapRepository;

    public Page<PostShortResponse> getPostsByCat(PostCat cat, Pageable pageable) {
        return postRepository.findAllByCat(cat, pageable);
    }

    public Page<PostShortResponse> getPostsOrderByNew(Pageable pageable) {
        return postRepository.findAllOrderByCreatedDate(pageable);
    }

    public Page<PostShortResponse> getPostsOrderByLikes(Pageable pageable) {
        return postRepository.findAllOrderByLikes(pageable);
    }

    public Page<PostShortResponse> getMyPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByWriterId(userId, pageable);
    }

    public PostResponse getPost(Long userId, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
        return postRepository.findDtoById(userId, postId);
    }

    @Transactional
    public void upload(Long userId, PostRequest postRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Post post = Post.builder()
                    .cat(postRequest.getCat())
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .build();
            post.changeUser(user); //글 작성자

            postRepository.save(post);
        }
    }

    @Transactional
    public void modify(Long userId, Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> postOptional = postRepository.findById(postId);

        //글이 존재하면서, 해당 글이 현재 로그인한 이용자가 쓴 글일 경우에만 수정 가능
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (Objects.equals(post.getUser().getId(), userId)) {
                post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
            }
            else {
                throw new NotMyPostException("현재 로그인한 이용자가 작성한 게시글이 아닙니다. " +
                        "자신이 작성한 게시글만 수정 가능합니다.");
            }
        }
        else {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
    }

    @Transactional
    public void delete(Long userId, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        //글이 존재하면서, 해당 글이 현재 로그인한 이용자가 쓴 글일 경우에만 삭제 가능
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (Objects.equals(post.getUser().getId(), userId)) {
                List<Comment> parentList = commentRepository.findAllParentByPostId(postId);
                List<Long> parentIds = parentList.stream().map(Comment::getId).collect(Collectors.toList());

                commentRepository.deleteAllByParentIdsIn(parentIds); //대댓글 삭제
                commentRepository.deleteAllParentByIdsIn(parentIds); //댓글 삭제
                //Likes, Scrap 테이블에서 해당 글 삭제
                likesRepository.deleteAllByPostId(postId);
                scrapRepository.deleteAllByPostId(postId);

                postRepository.delete(post);
            }
            else {
                throw new NotMyPostException("현재 로그인한 이용자가 작성한 게시글이 아닙니다. " +
                        "자신이 작성한 게시글만 삭제 가능합니다.");
            }
        }
        else {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
    }

    public Page<PostShortResponse> getMyCmtPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByUserCmt(userId, pageable);
    }

    public Page<PostShortResponse> getMyLikesPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByUserLikes(userId, pageable);
    }

    public Page<PostShortResponse> getMyScrapPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByUserScrap(userId, pageable);
    }

    @Transactional
    public void saveLikesPost(Long userId, Long postId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<Likes> likesOptional = likesRepository.findById(
                LikesId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );

        if(userOptional.isPresent() && postOptional.isPresent()
                && likesOptional.isEmpty()) {
            User user = userOptional.get();
            Post post = postOptional.get();
            Likes likes = Likes.builder()
                    .user(user)
                    .post(post)
                    .build();

            likesRepository.save(likes);
            post.plusLikes();
        }
        else if(postOptional.isEmpty()) {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
        else if(likesOptional.isPresent()) {
            throw new DuplicateRequestException("이미 해당 게시글을 좋아요 하였습니다.");
        }
    }

    @Transactional
    public void saveScrapPost(Long userId, Long postId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<Scrap> scrapOptional = scrapRepository.findById(
                ScrapId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );

        if(userOptional.isPresent() && postOptional.isPresent()
                && scrapOptional.isEmpty()) {
            User user = userOptional.get();
            Post post = postOptional.get();
            Scrap scrap = Scrap.builder()
                    .user(user)
                    .post(post)
                    .build();

            scrapRepository.save(scrap);
            post.plusScraps();
        }
        else if(postOptional.isEmpty()) {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
        else if(scrapOptional.isPresent()) {
            throw new DuplicateRequestException("이미 해당 게시글을 스크랩 하였습니다.");
        }
    }

    @Transactional
    public void deleteLikesPost(Long userId, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<Likes> likesOptional = likesRepository.findById(
                LikesId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );

        if (postOptional.isPresent() && likesOptional.isPresent()) {
            Post post = postOptional.get();
            Likes likes = likesOptional.get();

            likesRepository.delete(likes);
            post.minusLikes();
        }
        else if (postOptional.isEmpty()) {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
        else {
            throw new NoPrevRequestException("좋아요 하지 않은 게시글이므로 좋아요 취소가 불가합니다.");
        }
    }

    @Transactional
    public void deleteScrapPost(Long userId, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<Scrap> scrapOptional = scrapRepository.findById(
                ScrapId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );

        if (postOptional.isPresent() && scrapOptional.isPresent()) {
            Post post = postOptional.get();
            Scrap scrap = scrapOptional.get();

            scrapRepository.delete(scrap);
            post.minusScraps();
        }
        else if (postOptional.isEmpty()) {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
        else {
            throw new NoPrevRequestException("스크랩 하지 않은 게시글이므로 스크랩 취소가 불가합니다.");
        }
    }
}
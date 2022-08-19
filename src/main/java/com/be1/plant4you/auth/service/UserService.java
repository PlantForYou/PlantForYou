package com.be1.plant4you.auth.service;

import com.be1.plant4you.auth.domain.RefreshToken;
import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.auth.dto.response.UserNicknameResponse;
import com.be1.plant4you.auth.repository.RefreshTokenRepository;
import com.be1.plant4you.auth.repository.UserRepository;
import com.be1.plant4you.board.service.CommentService;
import com.be1.plant4you.board.service.PostService;
import com.be1.plant4you.common.utils.SecurityUtil;
import com.be1.plant4you.common.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PostService postService;
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserUtil userUtil;

    public UserNicknameResponse getUserNickname() {
        String userNickname = userRepository.findNameById(SecurityUtil.getCurrentUserId());
        return UserNicknameResponse.builder()
                .userNickname(userNickname)
                .build();
    }

    @Transactional
    public void withdraw() { //탈퇴 시 해당 유저와 관련된 정보 일단 모두 삭제하는 것으로 구현
        postService.deleteMyPost();
        postService.deleteMyLikes();
        postService.deleteMyScrap();
        commentService.deleteMyComment();

        User user = userUtil.getCurrentUser();
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByKey(user.getId());
        refreshTokenOptional.ifPresent(refreshTokenRepository::delete);
        userRepository.delete(user);
    }

    @Transactional
    public void changeUserNickname(String userNickname) {
        User user = userUtil.getCurrentUser();
        user.changeNickname(userNickname);
    }
}

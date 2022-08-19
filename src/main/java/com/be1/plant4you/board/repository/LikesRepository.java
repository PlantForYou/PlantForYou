package com.be1.plant4you.board.repository;

import com.be1.plant4you.board.domain.LikesId;
import com.be1.plant4you.board.domain.Likes;
import com.be1.plant4you.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, LikesId> {

    @Modifying
    @Query("delete from Likes l where l.post.id = :postId")
    void deleteAllByPostId(@Param("postId") Long postId);

    @Modifying
    @Query("delete from Likes l where l.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

    @Query("select distinct p from Likes l join l.post p on l.user.id = :userId")
    List<Post> findAllPostByUserId(@Param("userId") Long userId);
}
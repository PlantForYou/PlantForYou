package com.be1.plant4you.board.repository;

import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.domain.ScrapId;
import com.be1.plant4you.board.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {

    @Modifying
    @Query("delete from Scrap s where s.post.id = :postId")
    void deleteAllByPostId(@Param("postId") Long postId);

    @Modifying
    @Query("delete from Scrap s where s.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

    @Query("select distinct p from Scrap s join s.post p on s.user.id = :userId")
    List<Post> findAllPostByUserId(@Param("userId") Long userId);
}
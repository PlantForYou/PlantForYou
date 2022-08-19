package com.be1.plant4you.board.repository;

import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.repository.custom.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @Query("select p from Post p where p.user.id = :userId")
    List<Post> findByUserId(@Param("userId") Long userId);
}

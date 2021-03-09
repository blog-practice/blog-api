package com.samy.blog.myblog.repositories;

import com.samy.blog.myblog.domains.Blog;
import com.samy.blog.myblog.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findTop10ByOrderByLikesDesc();


    @Query("select  b from Blog  b left join b.comments c group by b.id order by b.likes desc, count(c) desc  ")
    List<Blog> findTop10ByOrderByLikesDescCommentsDesc();


}

package com.samy.blog.myblog.repositories;

import com.samy.blog.myblog.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User findByEmail(String email);
}

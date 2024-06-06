package com.chenrique.rest.webservices.restful_web_services.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Post findByUserId(Integer id);

	List<Post> findAllByUserId(Integer id);
}

package com.chenrique.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserRepository service;
	private PostRepository postService;

	public UserResource(UserRepository service, PostRepository postService) {
		this.service = service;
		this.postService = postService;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable("id") Integer id) {
		Optional<User> user = service.findById(id);

		if (user.isEmpty())
			throw new NotFoundUserException("id:" + id);

		EntityModel<User> entityModel = EntityModel.of(user.get());

		WebMvcLinkBuilder link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;

	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		service.deleteById(id);
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllPostOfUser(@PathVariable int id) {
		Optional<User> user = service.findById(id);
		if (user.isEmpty())
			throw new NotFoundUserException("id:" + id);

		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = service.findById(id);
		if (user.isEmpty())
			throw new NotFoundUserException("id:" + id);

		post.setUser(user.get());

		Post savePost = postService.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savePost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}

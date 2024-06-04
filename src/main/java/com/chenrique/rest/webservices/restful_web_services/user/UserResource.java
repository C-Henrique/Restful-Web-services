package com.chenrique.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

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

	private UserDAOService service;

	public UserResource(UserDAOService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable("id") Integer id) {
		User user = service.findOne(id);

		if (user == null)
			throw new NotFoundUserException("id:" + id);

		EntityModel<User> entityModel = EntityModel.of(user);

		WebMvcLinkBuilder link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;

	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		service.deleteById(id);
	}
}

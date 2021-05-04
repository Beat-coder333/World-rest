package it.object.rest.world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import it.object.rest.world.dto.User;

@RestController
public class LoginController {

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		ResponseEntity<User> resp = null;
		if (user.getUsername().isEmpty() && user.getPassword().isEmpty()) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return resp;

	}

}

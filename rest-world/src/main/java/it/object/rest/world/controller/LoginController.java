package it.object.rest.world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		ResponseEntity<String> resp = null;
		if (username.equals("user") && password.equals("user")) {
			resp = new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			resp = new ResponseEntity<>(HttpStatus.FORBIDDEN);

		}

		return resp;

	}

}

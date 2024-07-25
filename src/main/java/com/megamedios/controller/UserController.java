package com.megamedios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megamedios.entities.User;
import com.megamedios.exception.UserException;
import com.megamedios.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value="Controlador de usuarios")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value="Genera una lista de todos los usuarios", response = List.class)
	@GetMapping("/users/all")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
	}
	
	@ApiOperation(value="Devuelve un usuario por correo", response = List.class)
	@GetMapping("user/{correo}")
	public ResponseEntity<?> getUserByCorreo(@PathVariable("correo") String correo){
		try {
			User user = userService.getUserByCorreo(correo);
			return ResponseEntity.ok(user);
		}catch(UserException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando el usuario");
		}
	}
	
	@ApiOperation(value="Elimina a un usuario a partir de su correo", response = List.class)
	@DeleteMapping("/user/eliminate/{correo}")
	public ResponseEntity<?> deleteUser(@PathVariable("correo") String correo){
		try {
			userService.deleteUser(correo) ;
			return ResponseEntity.ok("Usuario eliminado");
		}catch(UserException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error eliminando el usuario");
		}
	}

}

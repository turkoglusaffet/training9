package com.spring.training9.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;

import com.spring.training9.entity.TblUser;
import com.spring.training9.jpa.UserRepository;

@RestController
@RequestMapping(value = "/userrest", headers = "Accept=*/*")
public class UserRestService {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/", 
			method = RequestMethod.GET, 
			produces = {"application/json", "application/xml"})
	public ResponseEntity<List<TblUser>> getUsers() {
		List<TblUser> users = userRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", 
			method = RequestMethod.GET, 
			produces = {"application/json", "application/xml"})
	public ResponseEntity<TblUser> getUser(@PathVariable("id") int id) {
		TblUser user = userRepository.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-{id}", 
			method = RequestMethod.GET, 
			produces = {"application/json", "application/xml"})
	public TblUser getUser2(@PathVariable("id") int id, RequestContext context) {
		System.out.println(context);
		TblUser user = userRepository.findById(id);
		return user;
	}
	
	@RequestMapping(value = "/", 
			method = RequestMethod.POST, 
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public ResponseEntity<TblUser> saveUser(@RequestBody @Valid TblUser tblUser, BindingResult result) {
		System.out.println(tblUser);
		if(result.hasErrors()) {
			for (ObjectError e : result.getAllErrors()) {
				System.out.println(e);
			}
		}
		return new ResponseEntity<>(userRepository.insert(tblUser), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.PUT, 
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public ResponseEntity<TblUser> editUser(@PathVariable("id") int id, @RequestBody @Valid TblUser tblUser, BindingResult result) {
		if(id != tblUser.getId()) {
			throw new RuntimeException();
		}
		if(result.hasErrors()) {
			for (ObjectError e : result.getAllErrors()) {
				System.out.println(e);
			}
		}
		return new ResponseEntity<>(userRepository.update(tblUser), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.DELETE, 
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
		userRepository.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

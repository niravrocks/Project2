package com.niit.backend.Controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.backend.dao.UserDao;
import com.niit.backend.model.Error;
import com.niit.backend.model.User;
@RestController
public class UserController  {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable(value="id") int id){
		User user=userDao.getUserById(id);
		//Person Id [1] doesn't exist -  1
		if(user==null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public  ResponseEntity<List<User>> getAllUsers(){
		System.out.println(userDao.getAllUsers());
		List<User> users=userDao.getAllUsers();
		if(users.isEmpty())
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user){
		logger.debug("Entering USERCONTROLLER : LOGIN");
		User validUser=userDao.authenticate(user);
		if(validUser==null){
			logger.debug("validUser is null");
			Error error=new Error(1,"Username and password doesnt exists...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //401
		}
		else{
			validUser.setOnline(true);
			userDao.updateUser(validUser); // to update online status in db
			logger.debug("validUser is not null");
			return new ResponseEntity<User>(validUser,HttpStatus.OK);//200
		}
	}

	//'?'  - Any Type [User,Error] 
	//ENDPOINT : http://localhost:8080/proj2backend/register 
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
		//client will send only username, password, email, role 
		try{
		logger.debug("USERCONTROLLER=>REGISTER " + user);
		user.setStatus(true);
		user.setOnline(false);
		User savedUser=userDao.registerUser(user);
		logger.debug("User Id generated is " + savedUser.getId());
		if(savedUser.getId()==0){
			Error error=new Error(2,"Couldnt insert user details ");
			return new ResponseEntity<Error>(error , HttpStatus.CONFLICT);
		}
		else
			return new ResponseEntity<User>(savedUser,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			Error error=new Error(2,"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
package findroof.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findroof.model.UserTest;
import findroof.repository.UserRepository;

@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String test() {
		//Insert
		logger.info("Inserting -> {}", repository.save(new UserTest("John", "A1234657")));
		
		logger.info("All users -> {}", repository.findAll());
		
		return repository.findAll().toString();
	}

}

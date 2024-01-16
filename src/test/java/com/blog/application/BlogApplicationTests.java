package com.blog.application;

import com.blog.application.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void test(){
		String className = userRepo.getClass().getName();
		String packageName = userRepo.getClass().getPackage().getName();
		System.out.println(className);
		System.out.println(packageName);
	}

}

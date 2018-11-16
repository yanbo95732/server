package com.teamsec.server.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.teamsec.server.demo.entity.Food;
import com.teamsec.server.demo.entity.Message;
import com.teamsec.server.demo.entity.Student;
import com.teamsec.server.demo.server.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	TestService testService;

	@Test
	public void contextLoads() {
		// Food food = testService.findFoodById(1L);
		Food food = testService.otherFoodById(1L);
		System.out.println(food);
	}

	@Test
	public void masterDataSource() {
		List<Student> list = testService.listStudent();
		for (Student student : list) {
			System.out.println(student);
		}
	}

	@Test
	public void slaveDataSource() {
		List<Message> list = testService.listMessage();
		for (Message message : list) {
			System.out.println(message);
		}
	}

}

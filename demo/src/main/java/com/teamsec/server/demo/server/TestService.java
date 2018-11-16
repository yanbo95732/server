/**
 * 
 */
package com.teamsec.server.demo.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamsec.server.demo.entity.Food;
import com.teamsec.server.demo.entity.Message;
import com.teamsec.server.demo.entity.Student;
import com.teamsec.server.demo.repository.message.MessageRepository;
import com.teamsec.server.demo.repository.student.FoodRepository;
import com.teamsec.server.demo.repository.student.StudentRepository;

/**
 * @author admin
 *
 */
@Service
public class TestService {
	@Autowired
	FoodRepository foodRepository;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	StudentRepository studentRepository;

	public Food findFoodById(Long id) {
		return foodRepository.findById(id);
	}

	public Food otherFoodById(Long id) {
		return foodRepository.findById(id);
	}

	public Message findMessageById(Long id) {
		return messageRepository.findById(id).get();
	}

	public Student findStudentById(Integer id) {
		return studentRepository.findById(id).get();
	}

	// ------------------

	public List<Message> listMessage() {
		return messageRepository.findAll();
	}

	public List<Food> listFood() {
		return foodRepository.findAll();
	}

	public List<Student> listStudent() {
		return studentRepository.findAll();
	}

}

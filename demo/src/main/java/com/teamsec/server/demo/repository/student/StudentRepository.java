/**
 * 
 */
package com.teamsec.server.demo.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamsec.server.demo.entity.Student;

/**
 * @author admin
 *
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}

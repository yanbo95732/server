/**
 * 
 */
package com.teamsec.server.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author admin
 *
 */
@Entity
public class Student {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;

	public Student() {
		super();
	}

	public Student(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}

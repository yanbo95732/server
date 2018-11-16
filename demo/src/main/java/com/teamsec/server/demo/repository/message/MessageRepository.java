/**
 * 
 */
package com.teamsec.server.demo.repository.message;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamsec.server.demo.entity.Message;

/**
 * @author admin
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}

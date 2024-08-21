package com.nagina_international.OMS_V1.repository.message;

import com.nagina_international.OMS_V1.entity.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

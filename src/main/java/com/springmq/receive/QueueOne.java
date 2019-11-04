package com.springmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues= {"hello"})
public class QueueOne {
	@RabbitHandler
	public void handle(String message) {
		System.err.println("消费者1 接收了一条消息 ："+message);
	}
}

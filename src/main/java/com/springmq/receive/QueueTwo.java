package com.springmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues= {"hi"})
public class QueueTwo {
	@RabbitHandler
	public void handle(String message) {
		System.err.println("消费者2 接收了一条消息 ："+message);
	}
}

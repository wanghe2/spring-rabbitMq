package com.springmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送者
 * @author wanghe
 *
 */
@RestController
public class MQSender {
	private final static String QUEUE_NAME_HELLO="hello";
	private final static String QUEUE_NAME_HI="hi";
	private final static String EXCHANGE_FANOUT="exchange_fanout";
	private final static String EXCHANGE_DIRECT="exchange_direct";
	private final static String ROUTE_KEY_ONE="route-key-2";
	private final static String EXCHANGE_TOPIC="exchange_topic";
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@RequestMapping("/sendmessage")
	public String send() {
		String message="long time no see,are you fine?";
		String message1="the weather is good, let's walk on";
		/**
		 * 只根据队列名称去发送
		 */
		amqpTemplate.convertSendAndReceive(QUEUE_NAME_HELLO,message);
		amqpTemplate.convertSendAndReceive(QUEUE_NAME_HI,message1);
		System.err.println("*************发送成功*************");
		return "消息发送成功";
	} 
	
	@RequestMapping("/fanout")
	public String fanout() {
		String message2="he was friend of mine,he die on the road";
		amqpTemplate.convertAndSend(EXCHANGE_FANOUT, "", message2);
		return "发布订阅成功";
	}
	
	@RequestMapping("/direct")
	public String direct() {
		String message3="凌晨四点醒来，发现海棠花未眠";
		amqpTemplate.convertAndSend(EXCHANGE_DIRECT, ROUTE_KEY_ONE, message3);
		return "路由模式执行成功";
	}
	
	@RequestMapping("/topic")
	public String topic() {
		String message4="回忆是一条没有尽头的路，一切以往的春天都不复存在";
		amqpTemplate.convertAndSend(EXCHANGE_TOPIC,"hello.good",message4);
		return "主题模式执行成功";
	}
	
}

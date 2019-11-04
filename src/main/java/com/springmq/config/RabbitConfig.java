package com.springmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	@Bean
	public Queue helloQeue() {
		return new Queue("hello");
	}
	
	@Bean
	public Queue hiQeue() {
		return new Queue("hi");
	}
	
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("exchange_fanout");
	}
	
	@Bean
	Binding bindExchangeMq1(Queue helloQeue,FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(helloQeue).to(fanoutExchange);
	}
	
	@Bean
	Binding bindExchangeMq2(Queue hiQeue,FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(hiQeue).to(fanoutExchange);
	}
	
	@Bean
	DirectExchange mydirectExchange() {
		return new DirectExchange("exchange_direct");
	}
	
	@Bean
	Binding bindingDirectExchange1() {
		return BindingBuilder.bind(helloQeue()).to(mydirectExchange()).with("route-key-1");
	}
	
	@Bean
	Binding bindingDirectExchange2() {
		return BindingBuilder.bind(hiQeue()).to(mydirectExchange()).with("route-key-2");
	}
	
	@Bean
	TopicExchange myTopicExchange() {
		return new TopicExchange("exchange_topic");
	}
	
	@Bean
	Binding bindingTopicExchange1() {
		return BindingBuilder.bind(helloQeue()).to(myTopicExchange()).with("hello.*");
	}
	
	@Bean
	Binding bindingTopicExchange2() {
		return BindingBuilder.bind(hiQeue()).to(myTopicExchange()).with("hi.*");
	}
}

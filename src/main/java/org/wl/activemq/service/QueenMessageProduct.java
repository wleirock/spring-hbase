package org.wl.activemq.service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;


@Service
public class QueenMessageProduct {
	private static Logger logger = LoggerFactory.getLogger(QueenMessageProduct.class);

	// queue模板
	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	// topic模板
	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	/**
	 * 向指定的队列发送消息
	 * 
	 * @param destination
	 * @param msg
	 */
	public void sendQueueMessage(Destination destination, final String msg) {
		logger.info("向指定队列：" + destination.toString() + " 发送了消息：" + msg);
		jmsQueueTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

	/**
	 * 向指定的队列发送消息--topic
	 * 
	 * @param destination
	 * @param msg
	 * @description 调用该类的对象为ActiveMQTopic
	 */
	public void sendTopicMessage(Destination destination, final String msg) {
		logger.info("向指定队列：" + destination.toString() + " 发送了消息：" + msg);
		jmsTopicTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

	/**
	 * 向默认队列(jmsQueueTemplate中的defaultDestination) 发送消息
	 * 
	 * @param msg
	 */
	public void sendMessage(final String msg) {
		String destination = jmsQueueTemplate.getDefaultDestination().toString();
		logger.info("向默认队列：" + destination + " 发送了消息：" + msg);
		jmsQueueTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}

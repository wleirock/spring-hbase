package org.wl.activemq.service;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.util.ByteSequence;

import javax.jms.Message;
import javax.jms.MessageListener;

public class ReceiveDataListener implements MessageListener {

	public void onMessage(Message message) {
		ActiveMQBytesMessage msg = (ActiveMQBytesMessage) message;
		ByteSequence bs = msg.getContent();
		System.out.println(new String(bs.data));
	}

}

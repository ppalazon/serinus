package org.serinus.cache;

import java.util.LinkedList;
import java.util.List;

public class MessageList {
	
	private List<String> messages;
	
	public MessageList()
	{
		this.messages = new LinkedList<String>();
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	

}

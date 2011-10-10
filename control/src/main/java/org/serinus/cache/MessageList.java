/*
 * Copyright 2011. Pablo Palazon (pablo.palazon@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.serinus.cache;

import java.util.LinkedList;
import java.util.List;

import org.serinus.data.Task;

public class MessageList {

	private List<Task> messages;

	public MessageList() {
		this.messages = new LinkedList<Task>();
	}

	public List<Task> getMessages() {
		return messages;
	}

	public void setMessages(List<Task> messages) {
		this.messages = messages;
	}

}

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

package org.serinus.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jivesoftware.smack.packet.Message;
import org.serinus.data.Task;


public class SerinusParser {
	
	public Task parser(Message text)
	{
		Task task = new Task();
		
		task.setOriginal(text.getBody());
		task.setAuthor(text.getFrom());
		task.setLinks(parserLinks(text.getBody()));
		task.setUsers(parserUsers(text.getBody()));
		task.setTopics(parserTopics(text.getBody()));
		
		return task;
	}
	
	public List<String> parserLinks(String text)
	{
		List<String> links = new ArrayList<String>();
		
		Pattern urlPattern = Pattern.compile("((https?|ftp|gopher|telnet|file|notes|ms-help):((//)|(\\\\))+[\\w\\d:#@%/;$\\(\\)~_?\\+-=\\.&]*)");
		
		Matcher matcher = urlPattern.matcher(text);
		while(matcher.find())
		{
			links.add(matcher.group());
		}
		
		return links;
	}
	
	public List<String> parserUsers(String text)
	{
		List<String> users = new ArrayList<String>();
		
		Pattern userPattern = Pattern.compile("(@\\S*)");
		
		Matcher matcher = userPattern.matcher(text);
		while(matcher.find())
		{
			users.add(matcher.group());
		}
		
		return users;
	}
	
	public List<String> parserTopics(String text)
	{
		List<String> topics = new ArrayList<String>();
		
		Pattern topicPattern = Pattern.compile("(#\\S*)");
		
		Matcher matcher = topicPattern.matcher(text);
		while(matcher.find())
		{
			topics.add(matcher.group());
		}
		
		return topics;
	}

}

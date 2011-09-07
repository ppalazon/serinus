package org.serinus.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
	
	private String original;	
	private String author;	
	private List<String> topics;	
	private List<String> links;	
	private List<String> users;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (original == null) {
			if (other.original != null)
				return false;
		} else if (!original.equals(other.original))
			return false;
		if (topics == null) {
			if (other.topics != null)
				return false;
		} else if (!topics.equals(other.topics))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@XmlElement
	public String getAuthor() {
		return author;
	}

	@XmlElement
	public List<String> getLinks() {
		return links;
	}

	@XmlElement
	public String getOriginal() {
		return original;
	}

	@XmlElement
	public List<String> getTopics() {
		return topics;
	}

	@XmlElement
	public List<String> getUsers() {
		return users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result
				+ ((original == null) ? 0 : original.hashCode());
		result = prime * result + ((topics == null) ? 0 : topics.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [original=").append(original).append(", author=")
				.append(author).append(", topics=").append(topics)
				.append(", links=").append(links).append(", users=")
				.append(users).append("]");
		return builder.toString();
	}

}

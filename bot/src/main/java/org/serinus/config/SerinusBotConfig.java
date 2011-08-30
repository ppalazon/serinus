package org.serinus.config;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ApplicationScoped
public class SerinusBotConfig {

	private String host;
	private Integer port;
	private String userBot;
	private String passwordBot;

	@XmlElement
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@XmlElement
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@XmlElement
	public String getUserBot() {
		return userBot;
	}

	public void setUserBot(String userBot) {
		this.userBot = userBot;
	}

	@XmlElement
	public String getPasswordBot() {
		return passwordBot;
	}

	public void setPasswordBot(String passwordBot) {
		this.passwordBot = passwordBot;
	}

}

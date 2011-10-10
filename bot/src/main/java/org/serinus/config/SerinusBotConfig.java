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

package org.serinus.config;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ApplicationScoped
public class SerinusBotConfig
{

	private String host;
	private Integer port;
	private String userBot;
	private String passwordBot;

	@XmlElement
	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	@XmlElement
	public Integer getPort()
	{
		return port;
	}

	public void setPort(Integer port)
	{
		this.port = port;
	}

	@XmlElement
	public String getUserBot()
	{
		return userBot;
	}

	public void setUserBot(String userBot)
	{
		this.userBot = userBot;
	}

	@XmlElement
	public String getPasswordBot()
	{
		return passwordBot;
	}

	public void setPasswordBot(String passwordBot)
	{
		this.passwordBot = passwordBot;
	}

}

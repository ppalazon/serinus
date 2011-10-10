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

import java.io.File;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.slf4j.cal10n.LocLogger;

@ApplicationScoped
public class SerinusBotConfiguration {

	private SerinusBotConfig serinusBotConfig;
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BOOTSTRAP);

	@PostConstruct
	public void construct() {
		serinusBotConfig = new SerinusBotConfig();
	}

	public void loadConfiguration() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(SerinusBotConfig.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		serinusBotConfig = (SerinusBotConfig) unmarshaller.unmarshal(new File(
				"serinus.xml"));

		log.info("Load SyncTime " + serinusBotConfig);
	}

	public SerinusBotConfig getSerinusBotConfig() {
		return serinusBotConfig;
	}

	public void setSerinusBotConfig(SerinusBotConfig serinusBotConfig) {
		this.serinusBotConfig = serinusBotConfig;
	}

}

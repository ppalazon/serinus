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

package org.serinus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.XMPPException;
import org.serinus.config.SerinusBotConfig;
import org.serinus.connection.Connection;
import org.serinus.connection.ThreadConnection;
import org.serinus.connection.keep.KeepContact;
import org.slf4j.cal10n.LocLogger;

@ApplicationScoped
public class SerinusBot {

	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BOOTSTRAP);

	@Inject
	private SerinusBotConfig serinusBotConfig;

	@Inject
	private SerinusBotParameters serinusBotParameters;
	
	@Inject @Default Connection connection;
	@Inject KeepContact keepContact;

	public void initializeSerinusBot(@Observes ContainerInitialized init) {
		if (serinusBotParameters.getErrors().size() != 0) {
			for (String err : serinusBotParameters.getErrors()) {
				log.error(err);
			}
		} else {
			
			try {
				connection.connect();
				
				Thread kc = new Thread(keepContact, "Serinus Keep in Contact");
				kc.start();
				
				while(true);
			} catch (XMPPException e) {
				log.error(e.getMessage());
			}
			
		}
	}

}

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

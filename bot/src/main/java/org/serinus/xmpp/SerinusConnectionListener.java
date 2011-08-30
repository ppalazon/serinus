package org.serinus.xmpp;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.ConnectionListener;
import org.slf4j.cal10n.LocLogger;

public class SerinusConnectionListener implements ConnectionListener {
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);

	@Override
	public void connectionClosed() {
		log.info("Connection closed");
	}

	@Override
	public void connectionClosedOnError(Exception arg0) {
		log.info("Connection closed on error");
	}

	@Override
	public void reconnectingIn(int arg0) {
		log.info("Reconnection");
	}

	@Override
	public void reconnectionFailed(Exception arg0) {
		log.info("Reconnection failed");
	}

	@Override
	public void reconnectionSuccessful() {
		log.info("Reconnection successful");
	}

}

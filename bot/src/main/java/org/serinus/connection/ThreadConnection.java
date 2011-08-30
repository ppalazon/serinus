package org.serinus.connection;

import javax.inject.Inject;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Roster.SubscriptionMode;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.serinus.config.SerinusBotConfig;
import org.serinus.xmpp.SerinusChatListener;
import org.serinus.xmpp.SerinusConnection;
import org.serinus.xmpp.SerinusConnectionListener;
import org.serinus.xmpp.SerinusFileTransferListener;
import org.serinus.xmpp.SerinusRosterListener;
import org.slf4j.cal10n.LocLogger;

@ThreadSerinus
public class ThreadConnection extends Connection implements Runnable {
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);
	
	@Override
	public void run() {
		try {			
			
			connect();
			
			while(true);
			 
		} catch (XMPPException e) {
			log.error(e.getMessage());
		} 
	}

}

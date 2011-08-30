package org.serinus.connection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.Roster.SubscriptionMode;
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

@Default
public class Connection {
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);
	
	@Inject SerinusBotConfig serinusBotConfig;
	
	@Inject SerinusConnectionListener serinusConnectionListener;
	@Inject SerinusChatListener serinusChatListener;
	@Inject SerinusRosterListener serinusRosterListener;
	@Inject SerinusFileTransferListener serinusFileTransferListener;
	@Inject SerinusConnection serinusConnection;
	
	public void connect() throws XMPPException
	{
		ConnectionConfiguration cc = new ConnectionConfiguration(
				serinusBotConfig.getHost(), serinusBotConfig.getPort());
		cc.setCompressionEnabled(true);
		cc.setReconnectionAllowed(true);
		cc.setRosterLoadedAtLogin(true);
		cc.setSASLAuthenticationEnabled(true);

		// Create a connection to the igniterealtime.org XMPP server.
		XMPPConnection con = new XMPPConnection(cc);
		
		serinusConnection.setXmppConnection(con);
		
		// Connect to the server
		con.connect();

		// Most servers require you to login before performing other
		// tasks.
		con.login(serinusBotConfig.getUserBot(),
				serinusBotConfig.getPasswordBot(), "SerinusBot");

		Presence presence = new Presence(Presence.Type.available);
		presence.setStatus("What's in your mind?");
		// Send the packet (assume we have a Connection instance called
		// "con").
		con.sendPacket(presence);
		
		con.addConnectionListener(serinusConnectionListener);
		con.getChatManager().addChatListener(serinusChatListener);
		
		con.getRoster().setSubscriptionMode(SubscriptionMode.accept_all);
		con.getRoster().addRosterListener(serinusRosterListener);
		
		FileTransferManager ftm = new FileTransferManager(con);
		ftm.addFileTransferListener(serinusFileTransferListener);
		
	}

}

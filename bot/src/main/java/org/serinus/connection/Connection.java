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
public class Connection
{

	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BEAN);

	@Inject
	SerinusBotConfig serinusBotConfig;

	@Inject
	SerinusConnectionListener serinusConnectionListener;
	@Inject
	SerinusChatListener serinusChatListener;
	@Inject
	SerinusRosterListener serinusRosterListener;
	@Inject
	SerinusFileTransferListener serinusFileTransferListener;
	@Inject
	SerinusConnection serinusConnection;

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

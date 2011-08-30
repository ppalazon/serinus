package org.serinus.xmpp;

import javax.enterprise.context.ApplicationScoped;

import org.jivesoftware.smack.XMPPConnection;

@ApplicationScoped
public class SerinusConnection {
	
	private XMPPConnection xmppConnection;

	public XMPPConnection getXmppConnection() {
		return xmppConnection;
	}

	public void setXmppConnection(XMPPConnection xmppConnection) {
		this.xmppConnection = xmppConnection;
	}

}

package org.serinus.connection.keep;

import javax.inject.Inject;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.RosterPacket.ItemType;
import org.serinus.xmpp.SerinusConnection;
import org.slf4j.cal10n.LocLogger;

public class KeepContact implements Runnable {
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);
	
	@Inject
	SerinusConnection serinusConnection;

	@Override
	public void run() {
		
		while(true)
		{		
			for(RosterEntry entry : serinusConnection.getXmppConnection().getRoster().getEntries())
			{
				log.info(entry.getUser());
				
				Presence presence = serinusConnection.getXmppConnection().getRoster().getPresence(entry.getUser());
				if(presence.isAvailable())
				{
					Message mesg = new Message();
					mesg.setBody("Ey men, how are you?");
					mesg.setTo(entry.getUser());
					serinusConnection.getXmppConnection().sendPacket(mesg);
				}
				
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
			
		}
		
	}

}

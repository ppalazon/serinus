package org.serinus.xmpp;

import javax.inject.Inject;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPException;
import org.slf4j.cal10n.LocLogger;

public class SerinusChatListener implements ChatManagerListener {
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);
	
	@Inject 
	SerinusMessageListener serinusMessageListener;
	
	@Inject SerinusConnection serinusConnection;

	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		
		if(!createdLocally)
			chat.addMessageListener(serinusMessageListener);
		
		checkUserInRoster(chat);		
	}
	
	public void checkUserInRoster(Chat chat)
	{
		Roster roster = serinusConnection.getXmppConnection().getRoster();
		if(!roster.contains(chat.getParticipant()))
		{
			try {
				roster.createEntry(chat.getParticipant(), chat.getParticipant(), new String[]{});
			} catch (XMPPException e) {
				log.error(e.getMessage());
			}
		}
	}

}

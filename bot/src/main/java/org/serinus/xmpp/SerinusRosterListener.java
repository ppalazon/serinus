package org.serinus.xmpp;

import java.util.Collection;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;
import org.slf4j.cal10n.LocLogger;

public class SerinusRosterListener implements RosterListener {
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);

	@Override
	public void entriesAdded(Collection<String> addresses) {
		for(String user : addresses)
		{
			log.info(user);
		}
	}

	@Override
	public void entriesDeleted(Collection<String> addresses) {
		for(String user : addresses)
		{
			log.info(user);
		}
	}

	@Override
	public void entriesUpdated(Collection<String> addresses) {
		for(String user : addresses)
		{
			log.info(user);
		}
	}

	@Override
	public void presenceChanged(Presence presence) {
		log.info("Presence changed "+presence.getFrom() +" - "+presence);
	}

}

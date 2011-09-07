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

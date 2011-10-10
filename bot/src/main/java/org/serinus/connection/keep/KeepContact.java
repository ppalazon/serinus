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

public class KeepContact implements Runnable
{

	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BEAN);

	@Inject
	SerinusConnection serinusConnection;

	@Override
	public void run()
	{

		while (true)
		{
			for (RosterEntry entry : serinusConnection.getXmppConnection()
					.getRoster().getEntries())
			{
				log.info(entry.getUser());

				Presence presence = serinusConnection.getXmppConnection()
						.getRoster().getPresence(entry.getUser());
				if (presence.isAvailable())
				{
					Message mesg = new Message();
					mesg.setBody("Ey men, how are you?");
					mesg.setTo(entry.getUser());
					serinusConnection.getXmppConnection().sendPacket(mesg);
				}

			}

			try
			{
				Thread.sleep(600000);
			} catch (InterruptedException e)
			{
				log.error(e.getMessage());
			}

		}

	}

}

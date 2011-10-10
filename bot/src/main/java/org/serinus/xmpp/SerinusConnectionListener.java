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

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.ConnectionListener;
import org.slf4j.cal10n.LocLogger;

public class SerinusConnectionListener implements ConnectionListener
{

	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BEAN);

	@Override
	public void connectionClosed()
	{
		log.info("Connection closed");
	}

	@Override
	public void connectionClosedOnError(Exception arg0)
	{
		log.info("Connection closed on error");
	}

	@Override
	public void reconnectingIn(int arg0)
	{
		log.info("Reconnection");
	}

	@Override
	public void reconnectionFailed(Exception arg0)
	{
		log.info("Reconnection failed");
	}

	@Override
	public void reconnectionSuccessful()
	{
		log.info("Reconnection successful");
	}

}

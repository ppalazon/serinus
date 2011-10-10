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

import java.io.File;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransfer.Status;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;
import org.slf4j.cal10n.LocLogger;

public class SerinusFileTransferListener implements FileTransferListener {

	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BEAN);

	@Override
	public void fileTransferRequest(FileTransferRequest request) {
		// Check to see if the request should be accepted
		if (shouldAccept(request)) {
			// Accept it
			IncomingFileTransfer transfer = request.accept();
			try {
				transfer.recieveFile(new File("shakespeare_complete_works.txt"));

				while (!transfer.isDone()) {
					if (transfer.getStatus().equals(Status.error)) {
						System.out.println("ERROR!!! " + transfer.getError());
					} else {
						System.out.println(transfer.getStatus());
						System.out.println(transfer.getProgress());
					}
					// sleep(1000);
				}

			} catch (XMPPException e) {
				log.error(e.getMessage());
			}
		} else {
			// Reject it
			request.reject();
		}
	}

	private boolean shouldAccept(FileTransferRequest request) {
		return true;
	}

}

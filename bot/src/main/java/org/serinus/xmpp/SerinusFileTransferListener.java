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
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);

	@Override
	public void fileTransferRequest(FileTransferRequest request) {
		// Check to see if the request should be accepted
        if(shouldAccept(request)) {
              // Accept it
              IncomingFileTransfer transfer = request.accept();
              try {
				transfer.recieveFile(new File("shakespeare_complete_works.txt"));
				
				while(!transfer.isDone()) {
		            if(transfer.getStatus().equals(Status.error)) {
		                  System.out.println("ERROR!!! " + transfer.getError());
		            } else {
		                  System.out.println(transfer.getStatus());
		                  System.out.println(transfer.getProgress());
		            }
		            //sleep(1000);
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

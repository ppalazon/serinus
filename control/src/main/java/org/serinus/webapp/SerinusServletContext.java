package org.serinus.webapp;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;
import org.serinus.graph.GraphManager;

@WebListener
public class SerinusServletContext implements ServletContextListener
{

	@Inject
	GraphManager graphManager;

	@Inject
	@Category("serinus-web")
	Logger logger;

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		logger.info("Initialize Serinus Application");
		graphManager.initializeGraphDb();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		logger.info("Close Serinus Application");
		graphManager.shutdownGraphDb();
	}

}

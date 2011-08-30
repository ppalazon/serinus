package org.serinus.config;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.slf4j.cal10n.LocLogger;

@ApplicationScoped
public class SerinusBotConfiguration {

	private SerinusBotConfig serinusBotConfig;
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BOOTSTRAP);
	
	@PostConstruct
	public void construct()
	{
		serinusBotConfig = new SerinusBotConfig();
	}
	
	public void loadConfiguration() throws JAXBException
	{
		JAXBContext context = JAXBContext.newInstance(SerinusBotConfig.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		serinusBotConfig = (SerinusBotConfig) unmarshaller.unmarshal(new File("serinus.xml"));
		
		log.info("Load SyncTime "+serinusBotConfig);
	}

	public SerinusBotConfig getSerinusBotConfig() {
		return serinusBotConfig;
	}

	public void setSerinusBotConfig(SerinusBotConfig serinusBotConfig) {
		this.serinusBotConfig = serinusBotConfig;
	}

}

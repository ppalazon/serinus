package org.serinus.graph.dao;

import java.io.File;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.serinus.graph.domain.Topic;

@RunWith(Arquillian.class)
public class InsertGraphTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "serinus-graphtest.jar").addClasses(
				SerinusDao.class, SerinusQuery.class)
				.addAsManifestResource(new File("src/main/webapp/WEB-INF/beans.xml"));
	}
	
	@Inject
	SerinusDao serinusDao;
	
	@Test
	public void insertNewPerson()
	{
		String topic = "#serinus-topic";
		Topic checkCreateTopic = serinusDao.checkCreateTopic(topic);
		long generatedId = checkCreateTopic.getUnderlyingNode().getId();
		
		Topic topic2 = serinusDao.createTopic(topic);
		long generatedId2 = topic2.getUnderlyingNode().getId();
		
		Assert.assertEquals(generatedId, generatedId2);
	}

}

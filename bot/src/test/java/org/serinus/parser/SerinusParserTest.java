package org.serinus.parser;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jivesoftware.smack.packet.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.serinus.data.Task;

@RunWith(Arquillian.class)
public class SerinusParserTest {

	/**
	 * Since Arquillian actually creates JAR files under the covers, the @Deployment
	 * is your way of controlling what is included in that Archive. Note, each
	 * class utilized in your test case - whether directly or indirectly - must
	 * be added to the deployment archive.
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addClasses(SerinusParser.class)
				.addAsManifestResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"));
	}
	
	@Inject
	SerinusParser serinusParser;
	
	@Test
	public void testParser()
	{
		Message message = new Message();
		message.setBody("Despliegue de las nuevas herramientas en #development, avisar a @ppalazon. Todo se puede ver en http://www.google.com/search?client=ubuntu&channel=fs&q=mk-archiver&ie=utf-8&oe=utf-8");
		message.setFrom("ppalazon@serinus.org");
		
		Task task = serinusParser.parser(message);
		Assert.assertNotNull(task);
		Assert.assertEquals(message.getBody(), task.getOriginal());
		Assert.assertEquals(1, task.getTopics().size());
		Assert.assertEquals(1, task.getUsers().size());
		Assert.assertEquals(1, task.getLinks().size());
	}

	@Test
	public void testParserLinks() {
		String text = "http://www.google.com";
		String text2 = "http://www.google.com ahora http://hola.com";
		
		Assert.assertEquals(1, serinusParser.parserLinks(text).size());
		Assert.assertEquals(2, serinusParser.parserLinks(text2).size());
	}
	
	@Test
	public void testParserUsers()
	{
		String text = "@ppalazon";
		String text2 = "Dile @ppalazon que venga cuando pueda";
		String text3 = "Dile @ppalazon que venga con @laura cuando pueda";
		
		Assert.assertEquals(1, serinusParser.parserUsers(text).size());
		Assert.assertEquals(1, serinusParser.parserUsers(text2).size());
		Assert.assertEquals(2, serinusParser.parserUsers(text3).size());
	}
	
	@Test
	public void testParserTopics()
	{
		String text = "#serinus";
		String text2 = "Despliegue del proyecto #serinus";
		String text3 = "Despliegue del proyecto #serinus, con en #development";
		
		Assert.assertEquals(1, serinusParser.parserTopics(text).size());
		Assert.assertEquals(1, serinusParser.parserTopics(text2).size());
		Assert.assertEquals(2, serinusParser.parserTopics(text3).size());
	}

}

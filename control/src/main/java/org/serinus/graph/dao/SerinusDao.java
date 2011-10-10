package org.serinus.graph.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.IndexHits;
import org.serinus.data.Task;
import org.serinus.graph.GraphManager;
import org.serinus.graph.SerinusRelationshipType;
import org.serinus.graph.domain.Doing;
import org.serinus.graph.domain.Person;
import org.serinus.graph.domain.Topic;
import org.serinus.graph.interceptor.GraphTransaction;
import org.serinus.parser.SerinusParser;

@ApplicationScoped
@GraphTransaction
public class SerinusDao
{
	@Inject
	private GraphManager graphManager;

	@Inject
	SerinusParser serinuParser;

	public void newSerinusTask(Task task)
	{
		Person person = checkCreateUser(Person.EMAIL, task.getAuthor());
		Doing doing = createDoing(task.getDate(), task.getUuid(),
				task.getOriginal());
		person.getUnderlyingNode().createRelationshipTo(
				doing.getUnderlyingNode(), SerinusRelationshipType.DOING);
		// Relationship with another person
		serinusRelationshipTopic(doing, task);
		// Relationship with another topics
		// serinusRelationshipPerson(doing, task);
	}

	public void serinusRelationshipTopic(Doing doing, Task task)
	{
		List<String> topics = serinuParser.parserTopics(task.getOriginal());
		for (String top : topics)
		{
			Topic topic = checkCreateTopic(top);
			doing.getUnderlyingNode().createRelationshipTo(
					topic.getUnderlyingNode(), SerinusRelationshipType.ABOUT);
		}
	}

	/**
	 * Must wait until have relationship between email and name and username
	 * 
	 * @param doing
	 * @param task
	 */
	@Deprecated
	public void serinusRelationshipPerson(Doing doing, Task task)
	{
		List<String> users = serinuParser.parserUsers(task.getOriginal());
		for (String user : users)
		{
			Person person = checkCreateUser(Person.NAME, user);
			doing.getUnderlyingNode().createRelationshipTo(
					person.getUnderlyingNode(), SerinusRelationshipType.NAMED);
		}
	}

	protected Doing createDoing(Date created, String uuid, String mesg)
	{
		Doing doing = new Doing(graphManager.getGraphDatabaseService()
				.createNode());
		doing.setCreated(created);
		doing.setUuid(uuid);
		doing.setDoing(mesg);
		return doing;
	}

	protected Person checkCreateUser(String attr, String value)
	{
		IndexHits<Node> query = graphManager.getIndexPerson()
				.query(attr, value);
		Node person = query.getSingle();
		if (person != null)
			return new Person(person);
		else
		{
			Person created = createUser(value);
			graphManager.getIndexPerson().add(created.getUnderlyingNode(),
					attr, value);
			return created;
		}
	}

	protected Person createUser(String email)
	{
		// TODO: Se puede buscar la información del usuario en otro sistema
		Person person = new Person(graphManager.getGraphDatabaseService()
				.createNode());
		person.setEmail(email);
		person.setUuid(UUID.randomUUID().toString());
		return person;
	}

	protected Topic checkCreateTopic(String topic)
	{
		IndexHits<Node> query = graphManager.getIndexTopic().query(Topic.NAME,
				topic);
		Node to = query.getSingle();
		if (to != null)
			return new Topic(to);
		else
		{
			Topic created = createTopic(topic);
			graphManager.getIndexTopic().add(created.getUnderlyingNode(),
					Person.NAME, topic);
			return created;
		}
	}

	protected Topic createTopic(String topic)
	{
		Topic to = new Topic(graphManager.getGraphDatabaseService()
				.createNode());
		to.setCreated(new Date());
		to.setName(topic);
		to.setUuid(UUID.randomUUID().toString());
		return to;
	}

}

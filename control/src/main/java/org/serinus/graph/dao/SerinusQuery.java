package org.serinus.graph.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;
import org.serinus.graph.GraphManager;
import org.serinus.graph.SerinusRelationshipType;
import org.serinus.graph.domain.Doing;
import org.serinus.graph.domain.Person;
import org.serinus.graph.interceptor.GraphTransaction;

@GraphTransaction
public class SerinusQuery {
	
	@Inject
	private GraphManager graphManager;
	
	@Inject
	private SerinusDao serinusDao;
	
	public List<Doing> getDoingByAuthor(String email)
	{
		Person person = serinusDao.checkCreateUser(Person.EMAIL, email);
		List<Doing> doings = new ArrayList<Doing>();
		for(Node node : getDoing(person).getAllNodes())
		{
			doings.add(new Doing(node));
		}
		return doings;
	}
	
	private Traverser getDoing(Person author)
	{
		return author.getUnderlyingNode().traverse(Order.BREADTH_FIRST,
				StopEvaluator.END_OF_GRAPH, 
				ReturnableEvaluator.ALL_BUT_START_NODE,
				SerinusRelationshipType.DOING, 
				Direction.OUTGOING);
	}

}

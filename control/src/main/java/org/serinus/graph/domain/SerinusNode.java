package org.serinus.graph.domain;

import java.util.Date;

import org.neo4j.graphdb.Node;

public class SerinusNode
{

	private Node underlyingNode;
	public static final String UUID = "uuid";
	public static final String CREATED = "created";

	public SerinusNode(Node underlyingNode)
	{
		super();
		this.underlyingNode = underlyingNode;
	}

	public Node getUnderlyingNode()
	{
		return underlyingNode;
	}

	public String getUuid()
	{
		return (String) underlyingNode.getProperty(UUID);
	}

	public void setUuid(String uuid)
	{
		underlyingNode.setProperty(UUID, uuid);
	}

	public Date getCreated()
	{
		return new Date((Long) underlyingNode.getProperty(CREATED));
	}

	public void setCreated(Date created)
	{
		underlyingNode.setProperty(CREATED, created.getTime());
	}

	@Override
	public int hashCode()
	{
		return underlyingNode.hashCode();
	}

}

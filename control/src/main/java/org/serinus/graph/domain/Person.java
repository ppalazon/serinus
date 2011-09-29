package org.serinus.graph.domain;

import org.neo4j.graphdb.Node;

public class Person extends SerinusNode
{

    public static final String NAME = "name";
    public static final String EMAIL = "email";

    public Person(Node underlyingNode)
    {
	super(underlyingNode);
    }

    public String getName()
    {
	return (String) getUnderlyingNode().getProperty(NAME);
    }

    public void setName(String name)
    {
	getUnderlyingNode().setProperty(NAME, name);
    }

    public String getEmail()
    {
	return (String) getUnderlyingNode().getProperty(EMAIL);
    }

    public void setEmail(String email)
    {
	getUnderlyingNode().setProperty(EMAIL, email);
    }

}

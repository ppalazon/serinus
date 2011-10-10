package org.serinus.graph.domain;

import org.neo4j.graphdb.Node;

public class Topic extends SerinusNode {

	public static final String NAME = "name";

	public Topic(Node underlyingNode) {
		super(underlyingNode);
	}

	public String getName() {
		return (String) getUnderlyingNode().getProperty(NAME);
	}

	public void setName(String name) {
		getUnderlyingNode().setProperty(NAME, name);
	}

}

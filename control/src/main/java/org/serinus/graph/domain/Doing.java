package org.serinus.graph.domain;

import org.neo4j.graphdb.Node;

public class Doing extends SerinusNode {

	public static final String DOING = "doing";

	public Doing(Node underlyingNode) {
		super(underlyingNode);
	}

	public String getDoing() {
		return (String) getUnderlyingNode().getProperty(DOING);
	}

	public void setDoing(String doing) {
		getUnderlyingNode().setProperty(DOING, doing);
	}

}

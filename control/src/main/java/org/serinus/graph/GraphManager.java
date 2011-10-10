package org.serinus.graph;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.serinus.graph.interceptor.GraphTransaction;

@ApplicationScoped
public class GraphManager {

	@Inject
	@Category("graphdb")
	private Logger logger;

	private GraphDatabaseService graphDatabaseService;
	private IndexManager indexManager;

	public void initializeGraphDb() {
		String repositoryJboss = System.getProperty("jboss.server.data.dir");
		logger.info("Graph database in " + repositoryJboss);
		if (repositoryJboss != null) {
			graphDatabaseService = new EmbeddedGraphDatabase(repositoryJboss
					+ "/serinus-graph");
			logger.info("Graph database in " + repositoryJboss);

			indexManager = graphDatabaseService.index();
		}
	}

	public void shutdownGraphDb() {
		graphDatabaseService.shutdown();
	}

	public GraphDatabaseService getGraphDatabaseService() {
		return graphDatabaseService;
	}

	public void setGraphDatabaseService(
			GraphDatabaseService graphDatabaseService) {
		this.graphDatabaseService = graphDatabaseService;
	}

	public Index<Node> getIndexPerson() {
		logger.debug("Getting person index");
		return indexManager.forNodes("person");
	}
	
	public Index<Node> getIndexPersonName() {
		logger.debug("Getting person index");
		return indexManager.forNodes("personname");
	}

	public Index<Node> getIndexTopic() {
		logger.debug("Getting topic index");
		return indexManager.forNodes("topic");
	}

	public Index<Node> getIndexSerinus() {
		logger.debug("Getting serinus full-text index");
		return indexManager.forNodes("serinus", MapUtil.stringMap(
				IndexManager.PROVIDER, "lucene", "type", "fulltext"));

	}

}

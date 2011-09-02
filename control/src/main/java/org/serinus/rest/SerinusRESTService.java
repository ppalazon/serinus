package org.serinus.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;
import org.serinus.api.SerinusPost;
import org.serinus.cache.TaskCache;
import org.serinus.data.Task;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members
 * table.
 */
@RequestScoped
@Path(value="/post")
public class SerinusRESTService implements SerinusPost {

	@Inject
	@Category("serinus")
	private Logger log;
	
	@Inject
	TaskCache taskCache;

	@Override
	@PUT
	@Produces("text/xml")
	public Response postTask(Task task) {

		log.info(String.valueOf(task));
		
		taskCache.addTask(task);

		return Response.ok().build();
	}

	@Override
	@GET
	@Path("/test/{test:\\S*}")
	@Produces("text/xml")
	public Response test(String test) {
		log.info(String.valueOf(test));

		return Response.ok().build();
	}
}

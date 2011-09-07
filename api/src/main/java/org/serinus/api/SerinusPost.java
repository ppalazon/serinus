package org.serinus.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.serinus.data.Task;

@Path(value="/post")
public interface SerinusPost {
	
	@PUT
	@Produces("text/xml")
	@Path("/post-task")
	@Consumes("text/xml")
	public Response postTask(@PathParam("task") Task task);
	
	@GET
	@Path("/test/{test:\\S*}")
	@Produces("text/xml")
	public Response test(@PathParam("test") String test);

}

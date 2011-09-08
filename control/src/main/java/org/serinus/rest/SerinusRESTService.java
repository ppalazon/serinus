/*
 * Copyright 2011. Pablo Palazon (pablo.palazon@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.serinus.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;
import org.serinus.api.SerinusPost;
import org.serinus.cache.TaskCache;
import org.serinus.data.Task;
import org.serinus.ui.TaskMonitor;

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
	
	@Inject
	TaskMonitor taskMonitor;

	@Override
	@PUT
	@Produces("application/xml")
	@Path("/post-task")
	@Consumes("application/xml")
	public Response postTask(Task task) {

		log.info(String.valueOf(task));
		
		taskCache.addTask(task);
		taskMonitor.send(task.getOriginal());

		return Response.ok().build();
	}

	@Override
	@GET
	@Path("/test/{test:\\S*}")
	@Produces("text/xml")
	public Response test(@PathParam("test") String test) {
		log.info(String.valueOf(test));

		return Response.ok().build();
	}
}

package org.serinus.http.proxy;


import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
import org.serinus.api.SerinusPost;

@ApplicationScoped
public class SerinusControlHttpProxy {
	
	private ClientExecutor clientExecutor;
	
	public SerinusControlHttpProxy()
	{
		HttpClient httpClient = new HttpClient();
		
		httpClient.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, new Integer(5000));
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, new Integer(30000));

		clientExecutor = new ApacheHttpClientExecutor(httpClient);
	}
	
	public SerinusPost getSerinusPost()
	{
		SerinusPost serinusPost = ProxyFactory.create(
				SerinusPost.class, "http://localhost:8080/serinus-control/post", clientExecutor);
		return serinusPost;
	}

}

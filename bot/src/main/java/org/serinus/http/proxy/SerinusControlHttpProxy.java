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

	public SerinusControlHttpProxy() {
		HttpClient httpClient = new HttpClient();

		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, new Integer(5000));
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				new Integer(30000));

		clientExecutor = new ApacheHttpClientExecutor(httpClient);

	}

	public SerinusPost getSerinusPost() {
		SerinusPost serinusPost = ProxyFactory.create(SerinusPost.class,
				"http://localhost:8080/serinus-control/rest", clientExecutor);
		return serinusPost;
	}

}

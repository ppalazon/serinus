package org.serinus.graph.interceptor;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

import org.neo4j.graphdb.Transaction;
import org.serinus.graph.GraphManager;

@GraphTransaction
@Interceptor
public class GraphTransactionInterceptor
{

	@Inject
	GraphManager graphManager;

	/**
	 * http://wiki.neo4j.org/content/Transactions
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object manageTransaction(InvocationContext ctx) throws Exception
	{
		Transaction graphTrans = graphManager.getGraphDatabaseService()
				.beginTx();
		Object result = null;
		try
		{
			result = ctx.proceed();
			graphTrans.success();
		} finally
		{
			graphTrans.finish();
		}
		return result;
	}

}

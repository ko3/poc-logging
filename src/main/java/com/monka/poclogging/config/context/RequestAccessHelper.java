package com.monka.poclogging.config.context;

import reactor.core.CoreSubscriber;
import reactor.util.context.Context;

public class RequestAccessHelper<T> extends AbstractContextLifter<T>{
	
	public RequestAccessHelper(CoreSubscriber<T> coreSubscriber) {
		super(coreSubscriber);
	}

	@Override
	protected void liftData(Context currentContext) {
		if(currentContext.hasKey(RequestContext.REQUEST_CONTEXT_KEY)) {
			RequestContext.updateRequestData(currentContext.get(RequestContext.REQUEST_CONTEXT_KEY));
		} else {
			RequestContext.clearData();
		}
		
	}

}

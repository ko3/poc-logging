package com.monka.poclogging.config.context;

import reactor.core.CoreSubscriber;

public class RequestAccessConfigurer extends ContextLifterConfigurer{

	@Override
	public String getContextKey() {
		// TODO Auto-generated method stub
		return RequestContext.REQUEST_CONTEXT_KEY;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public CoreSubscriber getSubscriber(CoreSubscriber coreSubscriber) {
		// TODO Auto-generated method stub
		return new RequestAccessHelper(coreSubscriber);
	}

}

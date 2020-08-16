package com.monka.poclogging.config.context;

import org.reactivestreams.Subscription;

import reactor.core.CoreSubscriber;
import reactor.util.context.Context;

public abstract class AbstractContextLifter<T> implements CoreSubscriber<T> {
	
	CoreSubscriber<T> coreSubscriber;
	
	public AbstractContextLifter(CoreSubscriber<T> coreSubscriber) {
		this.coreSubscriber = coreSubscriber;
	}
	
	@Override
	public void onSubscribe(Subscription s) {
		coreSubscriber.onSubscribe(s);
	}
	
	@Override
	public void onNext(T t) {
		liftData(coreSubscriber.currentContext());
		coreSubscriber.onNext(t);
	}
	
	@Override
	public void onError(Throwable t) {
		liftData(coreSubscriber.currentContext());
		coreSubscriber.onError(t);
	}
	
	@Override
	public Context currentContext() {
		return this.coreSubscriber.currentContext();
	}
	
	@Override
	public void onComplete() {
		coreSubscriber.onComplete();
	}
	
	protected abstract void liftData(Context currentContext);

}

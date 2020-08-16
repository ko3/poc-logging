package com.monka.poclogging.config.logging.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.monka.poclogging.config.context.RequestContext;

import ch.qos.logback.classic.spi.ILoggingEvent;

import static com.monka.poclogging.config.Constants.*;

public interface BaseAppender {
	
	static final Logger LOGGER = LoggerFactory.getLogger(BaseAppender.class);
	
	default void addToMDC(ILoggingEvent event, String key, String value) {
		MDC.put(key, value);
	}
	
	default void addToMDC(ILoggingEvent event, String key, int value) {
		MDC.put(key, Integer.toString(value));
	}
	
	default void addToMDC(ILoggingEvent event, String key, long value) {
		if(value != -1L) {
			MDC.put(key, Long.toString(value));
		}
	}
	
	default void processMDC(ILoggingEvent event) {
		addToMDC(event, TRANSACTIONID, RequestContext.getRequestData(String.class, TRANSACTIONID));
		addToMDC(event, E2EREQUESTID, RequestContext.getRequestData(String.class, E2EREQUESTID));
		addToMDC(event, STARTTIME, RequestContext.getRequestData(String.class, STARTTIME));
		addToMDC(event, CALLEDBY, RequestContext.getRequestData(String.class, CALLEDBY));
		addToMDC(event, REQUESTURI, RequestContext.getRequestData(String.class, REQUESTURI));
	}
	
	default boolean isLogEventEnabled(ILoggingEvent event) {
		boolean eventEnabled = true;
		
		return eventEnabled;
	}

}

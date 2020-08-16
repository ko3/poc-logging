package com.monka.poclogging.config.logging.appender;

import org.slf4j.MDC;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class BaseAsyncAppender extends AsyncAppender implements BaseAppender{
	
	@Override
	protected void append(ILoggingEvent event) {
		processMDC(event);
		if(isLogEventEnabled(event)) {
			super.append(event);
		}
		MDC.clear();
	}
}

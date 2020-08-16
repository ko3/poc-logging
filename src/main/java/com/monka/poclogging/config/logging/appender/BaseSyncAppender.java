package com.monka.poclogging.config.logging.appender;

import org.slf4j.MDC;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public class BaseSyncAppender extends ConsoleAppender<ILoggingEvent> implements BaseAppender{
	
	@Override
	protected void append(ILoggingEvent event) {
		processMDC(event);
		if(isLogEventEnabled(event)) {
			super.append(event);
		}
		MDC.clear();
	}
}

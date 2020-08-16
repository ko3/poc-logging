package com.monka.poclogging.config.logging.converter;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;

public class ExceptionNameConverter extends ThrowableProxyConverter{
	
	@Override
	protected String throwableProxyToString(IThrowableProxy tp) {
		if(tp == null) {
			return "Exception";
		}
		
		if(null != tp.getCause()) {
			return tp.getCause().getClassName();
		} else {
			return tp.getClassName();
		}
	}

}

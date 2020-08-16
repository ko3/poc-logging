package com.monka.poclogging.config.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import reactor.util.context.Context;

public class RequestContext {
	public static final String REQUEST_CONTEXT_KEY = "contextRequest";
	final static ThreadLocal<RequestAcess> requestAccess = new ThreadLocal<RequestAcess>();
	
	public static void upsertRequestData(String key, Object value) {
		if(requestAccess.get() != null)
			requestAccess.get().addAttribute(key, value);
	}
	
	public static <T> T getRequestData(Class<T> type, String key, T defaultValue) {
		T data = getRequestData(type, key);
		if(data == null) {
			data = defaultValue;
		}
		return data;
	}
	
	public static <T> T getRequestData(Class<T> type, String key) {
		if(requestAccess.get() != null) {
			return requestAccess.get().getAttribute(type, key);
		}
		return null;
	}
	
	public static <T> T getAndRemoveRequestData(Class<T> type, String key) {
		T value = getRequestData(type, key);
		deleteRequestData(key);
		return value;
	}
	
	public static void deleteRequestData(String key) {
		if(requestAccess.get() != null) {
			 requestAccess.get().attributes.remove(key);
		}
	}
	
	public static void updateRequestData(RequestAcess req) {
		requestAccess.set(req);
	}
	
	public static RequestAcess getCurrentData() {
		return requestAccess.get();
	}
	
	public static void clearData() {
		requestAccess.remove();
	}
	
	public static String getHttpHeader(String headerName) {
		if(requestAccess.get() != null && requestAccess.get().getHeaders() != null) {
			return requestAccess.get().getHeaders().getFirst(headerName);
		}
		return null;
	}
	
	public static void upsertRequestData(String key, Object value, Context ctx) {
		if(ctx != null) {
			RequestAcess reqA = ctx.get(REQUEST_CONTEXT_KEY);
			if(reqA != null) {
				reqA.attributes.put(key, value);
			}
			ctx.put(REQUEST_CONTEXT_KEY, reqA);
		}
	}
	
	
	public static class RequestAcess{
		private final MultiValueMap<String, HttpCookie> cookies;
		private final HttpHeaders headers;
		private Map<String, Object> attributes;
		
		public RequestAcess() {
			this.cookies = new LinkedMultiValueMap<>();
			this.headers = new HttpHeaders();
			attributes = new HashMap<>();
		}
		
		public RequestAcess(MultiValueMap<String, HttpCookie> cookies, HttpHeaders headers) {
			this.cookies = cookies;
			this.headers = headers;
			attributes = new HashMap<>();
		}
		
		public RequestAcess(RequestAcess source) {
			this.cookies = source.cookies;
			this.headers = source.headers;
			attributes = source.attributes;
		}
		
		public MultiValueMap<String, HttpCookie> getCookies(){
			return cookies;
		}
		
		public HttpHeaders getHeaders() {
			return headers;
		}
		
		@SuppressWarnings("unchecked")
		public <T> T getAttribute(Class<T> type, String key) {
			Object data = attributes.get(key);
			if(data != null) {
				return (T) data;
			}
			return null;
		}
		
		public void addAttribute(String key, Object value) {
			synchronized(attributes) {
				attributes.put(key, value);
			}
		}
	}
}

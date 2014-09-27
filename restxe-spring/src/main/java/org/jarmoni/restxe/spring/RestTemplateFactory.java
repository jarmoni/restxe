/*
 * Copyright (c) 2013. All rights reserved.
 * Original Author: ms
 * Creation Date: Feb 23, 2014
 */
package org.jarmoni.restxe.spring;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.jarmoni.restxe.common.Representation;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public final class RestTemplateFactory {

	private RestTemplateFactory() {
	}

	public static RestTemplate createTemplate(final Class<?> paramClass) {
		return createTemplate(paramClass, null);
	}

	public static RestTemplate createTemplate(final Class<?> paramClass, final ClientHttpRequestFactory requestFactory) {
		final RestTemplate restTemplate = requestFactory != null ? new RestTemplate(requestFactory) : new RestTemplate();
		restTemplate.setErrorHandler(new ResponseErrorHandler() {

			@Override
			public boolean hasError(final ClientHttpResponse response) throws IOException {
				return !response.getStatusCode().is2xxSuccessful();
			}

			@Override
			public void handleError(final ClientHttpResponse response) throws IOException {
				// Do nothing. The caller should evaluate the error code and
				// handle the error appropriately
			}
		});
		final HttpMessageConverter<?> messageConverter = new MappingJackson2HttpMessageConverter() {
			@Override
			protected JavaType getJavaType(final Type type, final Class<?> contextClass) {
				return TypeFactory.defaultInstance().constructParametricType(Representation.class, paramClass);
			}
		};
		final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(messageConverter);
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

}

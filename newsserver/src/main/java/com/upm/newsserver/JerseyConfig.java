package com.upm.newsserver;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.upm.newsserver.api.NewsApi;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(NewsApi.class);
	}
}

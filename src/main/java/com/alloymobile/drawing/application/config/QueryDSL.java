package com.alloymobile.drawing.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.ReactiveQuerydslWebConfiguration;

@Configuration(proxyBeanMethods=false)
public class QueryDSL extends ReactiveQuerydslWebConfiguration {
}

package org.activiti.explorer.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
  @PropertySource(value = "classpath:db.properties", ignoreResourceNotFound = true),
  @PropertySource(value = "classpath:engine.properties", ignoreResourceNotFound = true)
})
@ComponentScan(basePackages = { "org.activiti.explorer.conf" })
@ImportResource({"/WEB-INF/config/spring/activiti-ui-context.xml", "/WEB-INF/config/spring/activiti-login-context.xml"})
public class ApplicationConfiguration {
  
}

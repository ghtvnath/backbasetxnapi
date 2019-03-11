package com.nath.codeworks.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author ghtvnath
 * Initialize Dispatcher Servlet for Spring MVC Web application. Configure AppConfig and ApiSecurityConfig
 * classes as configuration classes. Map all requests with /rest endpoint through this servlet which 
 * will be handled by Spring MVC controller classes. 
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                AppConfig.class, ApiSecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/rest/*"};
    }
}

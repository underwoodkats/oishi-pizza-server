package com.underwoodkats.oishipizza.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * This class describes server configuration such as what methods are allowed and which party
 * server can response and etc.
 */
@Configuration
public class ServerConfig {
    @Bean
    public FilterRegistrationBean processCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://polar-spire-76257.herokuapp.com");
        config.addAllowedOrigin("http://localhost:3000");
        config.setAllowedHeaders(
                Collections.unmodifiableList(
                        Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
        config.setAllowedMethods(
                Collections.unmodifiableList(Arrays.asList("GET", "POST", "DELETE")));
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}

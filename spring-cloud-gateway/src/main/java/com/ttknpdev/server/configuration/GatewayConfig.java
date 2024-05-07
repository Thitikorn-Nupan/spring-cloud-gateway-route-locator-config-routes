package com.ttknpdev.server.configuration;

import com.ttknpdev.server.logging.LogBack;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// the Route Locator bean to retrieve the app route configuration. (work like yml config)
@Configuration
public class GatewayConfig {

    @Value("${uri[0]}")
    private String customerUrI;

    @Value("${uri[1]}")
    private String robotUrI;

    public GatewayConfig() {
        LogBack.setLog(GatewayConfig.class);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        LogBack.log.info("{}","initializing route locator config routes");
        // Now i can access 8888/customer/reads,read,create,update,delete
        return builder
                .routes()
                .route("customer optional", routeC -> routeC
                                .path("/customer/**")
                                .filters(filter -> filter.prefixPath("/api"))
                                .uri(customerUrI)
                )
                .route("robot optional" , routeR -> routeR
                        .path("/robot/**")
                        .uri(robotUrI)
                )
                .build();
    }
}

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

    @Value("${uri}")
    private String customerUrI;

    public GatewayConfig() {
        LogBack.setLog(GatewayConfig.class);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        LogBack.log.info("{}","initializing route locator config routes");
        // Now i can access 8888/customer/reads,read,create,update,delete
        /*
        return builder
                .routes()
                .route("customer optional", routeC -> routeC
                                .path("/customer/**")
                                .filters(filter -> filter.prefixPath("/api"))
                                .uri(customerUrI)
                )
                .build();
        */
        // Or if you want prefix as /api just add the full path on path() method and remove filter
        return builder
                .routes()
                .route("customer optional", routeC ->
                        routeC
                        .path("/api/customer/**")
                        .uri(customerUrI)
                )
                .build();
    }
}

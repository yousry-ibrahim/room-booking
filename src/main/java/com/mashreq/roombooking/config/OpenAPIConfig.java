package com.mashreq.roombooking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPi spring boot configuration class
 * @author : yousry
 * @version : 1.0
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Yousry's Rooms Booking API", version = "v1"))
public class OpenAPIConfig {
}

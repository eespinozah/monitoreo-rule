package com.mrisk.monitoreo.rule.infrastructure.config.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Parameter;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    @Bean
    public Docket api() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("X-Tena-Id").defaultValue("2").description("Client tena_id")
                .modelRef(new ModelRef("string")).parameterType("header").required(true).build());
        parameters.add(new ParameterBuilder().name("X-Acco-Id").defaultValue("21").description("Client acco_id")
                .modelRef(new ModelRef("string")).parameterType("header").required(true).build());
        
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.mrisk.monitoreo.rule.infrastructure")).paths(PathSelectors.any())
                .build().useDefaultResponseMessages(true).globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Rule Api Documentation").version("1")
                .description("Api Documentation For Rule Service").termsOfServiceUrl("urn:tos").build();
    }

}

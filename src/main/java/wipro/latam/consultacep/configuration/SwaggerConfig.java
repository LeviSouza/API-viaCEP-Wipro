package wipro.latam.consultacep.configuration;

import java.util.*;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.RequestHandlerProvider;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;


import javax.servlet.ServletContext;

import static java.util.stream.Collectors.toList;
import static springfox.documentation.RequestHandler.byPatternsCondition;
import static springfox.documentation.spring.web.paths.Paths.ROOT;


@Configuration
public class SwaggerConfig {

    @Bean
    public InitializingBean removeSpringfoxHandlerProvider(DocumentationPluginsBootstrapper bootstrapper) {
        return () -> bootstrapper.getHandlerProviders().removeIf(WebMvcRequestHandlerProvider.class::isInstance);
    }
    @Bean
    public RequestHandlerProvider customRequestHandlerProvider(Optional<ServletContext> servletContext, HandlerMethodResolver methodResolver, List<RequestMappingInfoHandlerMapping> handlerMappings) {
        String contextPath = servletContext.map(ServletContext::getContextPath).orElse(ROOT);
        return () -> handlerMappings.stream()
                .filter(mapping -> !mapping.getClass().getSimpleName().equals("IntegrationRequestMappingHandlerMapping"))
                .map(mapping -> mapping.getHandlerMethods().entrySet())
                .flatMap(Set::stream)
                .map(entry -> new WebMvcRequestHandler(contextPath, methodResolver, tweakInfo(entry.getKey()), entry.getValue()))
                .sorted(byPatternsCondition())
                .collect(toList());
    }
    RequestMappingInfo tweakInfo(RequestMappingInfo info) {
        if (info.getPathPatternsCondition() == null) return info;
        String[] patterns = info.getPathPatternsCondition().getPatternValues().toArray(String[]::new);
        return info.mutate().options(new RequestMappingInfo.BuilderConfiguration()).paths(patterns).build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("wipro.latam.consultacep"))
                .paths(PathSelectors.regex("/v1.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "ConsultarCep API REST",
                "API REST para consultar CEP",
                "1.0",
                "Terms od Service",
                new Contact("Levi Souza","https://www.linkedin.com/in/levi-apsouza/",
                        "levisouza30@outlook.com"),
                "Apache License Version 2.0",
                "http://www.apache.org/license.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
}

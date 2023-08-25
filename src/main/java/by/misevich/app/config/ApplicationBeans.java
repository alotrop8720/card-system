package by.misevich.app.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class ApplicationBeans {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    @Bean
    public Docket api() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("X-Auth-Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .build();
        List<Parameter> parameters = new ArrayList<>();

        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("by.misevich.app.core.controller"))
                .paths(PathSelectors.any())
                .build().pathMapping("")
                .globalOperationParameters(parameters);
    }
}

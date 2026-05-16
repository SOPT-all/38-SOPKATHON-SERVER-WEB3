package org.sopt.sopkathon.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://sopkathon-web3.p-e.kr"))
                .info(new Info()
                        .title("솝커톤 WEB 3팀 API")
                        .description("솝커톤 WEB 3팀 서버 API 문서")
                        .version("v1.0.0"));
    }
}
package com.example.demo2.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("demo2")
public class Demo2ApiProperty {

    @Getter
    @Setter
    private String originPermitida = "https://localhost:8000";

    @Getter
    public final Seguranca seguranca = new Seguranca();

    @Getter
    @Setter
    public static class Seguranca {

        private boolean enableHttps;

    }
}

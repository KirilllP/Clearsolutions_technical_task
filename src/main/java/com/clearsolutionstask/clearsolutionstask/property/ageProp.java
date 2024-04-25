package com.clearsolutionstask.clearsolutionstask.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("property")
@Setter
@Getter
public class ageProp {
    Integer minimalAge;
}

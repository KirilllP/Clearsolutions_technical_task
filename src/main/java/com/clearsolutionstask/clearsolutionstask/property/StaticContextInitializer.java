package com.clearsolutionstask.clearsolutionstask.property;

import com.clearsolutionstask.clearsolutionstask.validator.oldEnough.AdultValidator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StaticContextInitializer {

    private final ageProp ageProp;

    @PostConstruct
    void init() {
        ageProp.setMinimalAge("${property.minimalAge}");
    }

}
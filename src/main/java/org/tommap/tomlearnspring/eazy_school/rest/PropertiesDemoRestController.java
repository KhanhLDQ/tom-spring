package org.tommap.tomlearnspring.eazy_school.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.tomlearnspring.eazy_school.config.TomProperties;

@RestController
@RequestMapping(path = "/v1/api/rest-properties")
@RequiredArgsConstructor
@Slf4j
public class PropertiesDemoRestController {
    @Value("${tom.pageSize}")
    private int pageSize;

    @Value("${tom.contact.successMsg}")
    private String successMsg;

    private final Environment environment;
    private final TomProperties tomProperties;

    @GetMapping("/read")
    public ResponseEntity<String> read() {
//        String properties = String.format("pageSize: %d, successMsg: %s", pageSize, successMsg);
//        String properties = String.format("pageSize: %d, successMsg: %s, javaHome: %s, javaVersion: %s",
//                environment.getProperty("tom.pageSize", Integer.class),
//                environment.getProperty("tom.contact.successMsg", String.class),
//                environment.getProperty("java.home", String.class),
//                environment.getProperty("java.version", String.class)
//        );
        String properties = String.format("properties: %s", tomProperties);

        return ResponseEntity.ok(properties);
    }
}

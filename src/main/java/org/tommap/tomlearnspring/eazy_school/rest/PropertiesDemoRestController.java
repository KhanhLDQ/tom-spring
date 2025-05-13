package org.tommap.tomlearnspring.eazy_school.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/api/rest-properties")
@Slf4j
public class PropertiesDemoRestController {
    @Value("${tom.pageSize}")
    private int pageSize;

    @Value("${tom.contact.successMsg}")
    private String successMsg;

    @GetMapping("/read")
    public ResponseEntity<String> read() {
        String properties = String.format("pageSize: %d, successMsg: %s", pageSize, successMsg);
        return ResponseEntity.ok(properties);
    }
}

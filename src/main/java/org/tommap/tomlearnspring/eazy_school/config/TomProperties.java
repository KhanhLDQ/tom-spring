package org.tommap.tomlearnspring.eazy_school.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tom")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Validated
public class TomProperties {
    @Positive
    @Max(value = 50, message = "Should not be greater than 50")
    private int pageSize;

    private Map<String, String> contact;

    private List<String> branches;
}

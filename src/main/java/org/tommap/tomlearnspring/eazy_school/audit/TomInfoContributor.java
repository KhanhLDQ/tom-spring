package org.tommap.tomlearnspring.eazy_school.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TomInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("app_name", "Tom Learn Spring");
        infoMap.put("app_description", "Eazy School Web Application for Students and Admins");
        infoMap.put("app_author", "Tom");
        infoMap.put("app_version", "1.0.0");

        builder.withDetail("info", infoMap);
    }
}

package org.tommap.tomlearnspring.eazy_school.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class Holiday extends BaseEntity{
    private String day;
    private String reason;
    private Type type;

    public enum Type {
        FESTIVAL,  FEDERAL
    }
}

package com.project.onlineshop.dto.reference;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.project.onlineshop.model.reference.ScRStatus}
 */
@Value
public class ScRStatusDto implements Serializable {
    Long statusId;
    String name;
    String code;
    String type;
}
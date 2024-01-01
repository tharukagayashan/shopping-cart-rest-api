package com.projects.shoppingcart.dto.reference;

import com.projects.shoppingcart.model.reference.ScRStatus;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ScRStatus}
 */
@Value
public class ScRStatusDto implements Serializable {
    Long statusId;
    String name;
    String code;
    String type;
}
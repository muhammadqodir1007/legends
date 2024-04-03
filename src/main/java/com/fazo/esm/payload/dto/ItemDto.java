package com.fazo.esm.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private int adminId;
    private int categoryId;
    private int itemType;
    private long quantity;
    private String description;
}

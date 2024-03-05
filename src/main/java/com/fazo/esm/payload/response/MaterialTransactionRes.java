package com.fazo.esm.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaterialTransactionRes {
    private int id;
    private int materialTypeId;
    private int userId;
    private int categoryId;
    private long quantity;
    private LocalDateTime actionDate;
    private String actionType;
}

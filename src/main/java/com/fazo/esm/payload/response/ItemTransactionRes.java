package com.fazo.esm.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemTransactionRes {
    private int id;
    private int itemTypeId;
    private int userId;
    private int categoryId;
    private long quantity;
    private LocalDateTime actionDate;
    private String actionType;
}

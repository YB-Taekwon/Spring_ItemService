package com.ian.itemservice.type;

public enum ItemType {
    BOOK("도서"),
    FOOD("음식"),
    ETC("기타");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }
}

package com.stationary.bookmanagement.dto;


public enum BookTypeEnum {

    FICTION("FICTION",15),
    COMIC("COMIC", 0),
    JAVA("JAVA", 10),
    PHP("PHP", 10),
    HORROR("HORROR", 5),
    POLITICAL("POLITICAL", 20),
    OTHERS("OTHERS ", 30);

    private Integer discount;

    public Integer getDiscount() {
        return discount;
    }

    BookTypeEnum(String discountType, Integer discount) {
       this.discount = discount;
    }


}

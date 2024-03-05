package com.SpringCourse.WebServiceSpring.Currency;

public enum CurrencyType {
    PLN("Polish Zloty"),
    EUR("Euro"),
    GBP("British pound sterling"),
    USD("American Dollar");

    private final String description;

    CurrencyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
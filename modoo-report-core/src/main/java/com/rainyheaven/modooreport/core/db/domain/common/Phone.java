package com.rainyheaven.modooreport.core.db.domain.common;

import jakarta.persistence.Embeddable;

@Embeddable
public class Phone {

    private String firstNumber;
    private String secondNumber;
    private String thirdNumber;

    public Phone() {}

    public Phone(final String firstNumber, final String secondNumber, final String thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }
}

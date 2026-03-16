package br.com.bichofull.bichofull.domain.bet;

import lombok.Getter;

import java.math.BigDecimal;

public enum BetType {
    GROUP("Bicho", 0, 1, 25, new BigDecimal("18.00"), new BigDecimal("3.60")),
    DEZENA("Dezena", 1, 0, 99, new BigDecimal("60.00"), new BigDecimal("12.00")),
    THOUSANDS("Milhar",2, 0, 9999, new BigDecimal("4000.00"), new BigDecimal("800.00"));

    @Getter
    private final String name;

    private final int code;

    @Getter
    private final int minNumber;

    @Getter
    private final int maxNumber;

    @Getter
    private final BigDecimal winMainDraw;

    @Getter
    private final BigDecimal winSecondDraw;

    BetType(String name, int code, int minNumber, int maxNumber, BigDecimal winMainDraw, BigDecimal winSecondDraw){
        this.name = name;
        this.code = code;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.winMainDraw = winMainDraw;
        this.winSecondDraw = winSecondDraw;
    }

    public boolean isNumberValid(int number) {
        return number >= this.minNumber && number <= this.maxNumber;
    }
}

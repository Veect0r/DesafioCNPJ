package com.example.Desafiocnpj.validation;

import org.springframework.stereotype.Component;

@Component
public class CnpjValidation {

    private static final int[] CNPJ_FIRST_WEIGHTS = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int[] CNPJ_SECOND_WEIGHTS = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

    public String limparCnpj(String cnpj) {
        return cnpj.replaceAll("[^a-zA-Z0-9]", "");
    }

    public boolean isValidCnpj(String cnpj) {
        if (cnpj.chars().distinct().count() == 1) {
            return false;
        }

        int[] values = new int[14];
        for (int i = 0; i < 14; i++) {
            values[i] = cnpj.charAt(i) - 48;
        }

        int firstCheck = calculateCnpjCheckDigit(values, CNPJ_FIRST_WEIGHTS, 12);
        if (firstCheck != values[12]) {
            return false;
        }

        int secondCheck = calculateCnpjCheckDigit(values, CNPJ_SECOND_WEIGHTS, 13);
        return secondCheck == values[13];
    }

    private int calculateCnpjCheckDigit(int[] values, int[] weights, int length) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += values[i] * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
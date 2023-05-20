package com.questao2.Questao2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Questao2Application {

    public static void main(String[] args) {
        SpringApplication.run(Questao2Application.class, args);


        try {
            int resultado = calcularOperacao(8, 5, "soma");
            System.out.println("Resultado da soma: " + resultado);

            double resultadoDivisao = calcularOperacao(10, 5, "divisao");
            System.out.println("Resultado da divisão: " + resultadoDivisao);

            double resultadoMultiplicacao = calcularOperacao(10, 2, "multiplicacao");
            System.out.println("Resultado da multiplicação: " + resultadoMultiplicacao);

            double resultadoSubtracao = calcularOperacao(1, 2, "subtracao");
            System.out.println("Resultado da subtração: " + resultadoSubtracao);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static int calcularOperacao(int a, int b, String operacao) {
        switch (operacao) {
            case "soma" -> {
                return a + b;
            }
            case "subtracao" -> {
                return a - b;
            }
            case "multiplicacao" -> {
                return a * b;
            }
            case "divisao" -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Divisão por zero não é permitida.");
                }
                return a / b;
            }
            default -> throw new IllegalArgumentException("Operação inválida.");
        }

    }
}

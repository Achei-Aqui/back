package br.com.fcamara.acheiaquiapi.config.validacao;

import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
public  class ValidadorCNPJService {

    public static void validar(String cnpj) {
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("/", "");
        cnpj = cnpj.replace("-", "");

        validarNumerosIguais(cnpj);

        try {
            Long.parseLong(cnpj);
        } catch (NumberFormatException e) {
            throw new RuntimeException();
        }

        validarCalculoPrimeiroDigito(cnpj);
        validarCalculoSegundoDigito(cnpj);
    }

    public static void validarNumerosIguais(String cnpj) {
        // EXCEC��O CNPJ COM NUMEROS IGUAIS
        if(cnpj.equals("00000000000000") ||
                cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") ||
                cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") ||
                cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") ||
                cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") ||
                cnpj.equals("99999999999999") ||
                (cnpj.length() != 14)) {
            throw new RuntimeException();
        }
    }

    public static void validarCalculoPrimeiroDigito(String cnpj) {
        int digito13;
        int soma, i, resto, numero, peso;

        try {
            soma = 0;
            peso = 2;
            for(i = 11; i >= 0; i--) {
                numero = (int) (cnpj.charAt(i) - 48);
                soma = soma + (numero * peso);
                peso++;
                if(peso == 10) {
                    peso = 2;
                }
            }

            resto = soma % 11;
            if(resto > 1) {
                digito13 =  (11 - resto);
            } else {
                digito13 = 0;
            }

            if( (int) cnpj.charAt(12) - 48 != digito13) {
                throw new InputMismatchException();
            }

        } catch (InputMismatchException erro) {
            throw new RuntimeException(); // CPNJ INVALO EXCEPTION
        }
    }

    public static void validarCalculoSegundoDigito(String cnpj) {
        int digito14;
        int soma, i, resto, numero, peso;

        try {
            soma = 0;
            peso = 2;
            for(i = 12; i >= 0; i--) {
                numero = (int) (cnpj.charAt(i) - 48);
                soma = soma + (numero * peso);
                peso++;
                if(peso == 10) {
                    peso = 2;
                }
            }

            resto = soma % 11;
            if(resto > 1) {
                digito14 = (11 - resto);
            } else {
                digito14 = 0;
            }

            if( cnpj.charAt(13) - 48 != digito14) {
                throw new InputMismatchException();
            }

        } catch (InputMismatchException erro) {
            throw new RuntimeException(); // CNPJ INVALIDO EXCEPTIoN
        }
    }

}

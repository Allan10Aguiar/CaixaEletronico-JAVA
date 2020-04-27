package caixaeletronico;

import java.util.Scanner;

public class CaixaEletronico {

    //variaveis globais
    static String cpf = "", senha = "";
    static int op = 0, op1 = 0, IDENTIFICADOR = 0;
    static double depos = 0, saque = 0;
    static boolean verificador = true, verificador2 = true;
    static Scanner ler = new Scanner(System.in);

    //vetores de cadastro
    static String[] CPFv = {"12345678900", "00987654321"};
    static String[] SENHAv = {"01020304", "12345678"};
    static String[] AGENCIAv = {"000", "111"};
    static String[] CONTAv = {"CC", "CP"};
    static String[] BANCOv = {"BB", "CE"};
    static double[] SALDOv = {1000, 780};
    //-------------------

    public static void main(String[] args) {

        //RETORNA OS VERIFICADORES PARA TRUE
        verificador = true;
        verificador2 = true;

        //ACESSO INICIAL AO CAIXA
        while (verificador) {

            mostrarMenu("INICIAL");

            switch (op1) {
                case 1:

                    mostrarMenu("LOGIN");

                    if (efetuarLogin(cpf, senha)) {
                        System.out.println("Bem-vindo ao sistema!");
                        pularLinhas(2);
                        verificador = false;
                    } else {
                        //ACESSO NEGADO
                        System.out.println("Cpf ou senha incorreto!");
                    }

                    break;

                case 2:
                    verificador = false;
                    System.exit(0);
                    break;
            }

        }

        //ACESSO PREMITIDO AO MENU PRINCPAL
        while (verificador2) {

            mostrarMenu("PRINCIPAL");

            switch (op) {

                case 4:
                    //SAIR DO SWITCH E VOLTAR AO MENU INICIAL
                    System.out.println("Saindo...");
                    main(args);

                    verificador2 = false;

                    break;

                case 5:
                    //SAIR DE VEZ
                    System.out.println("Finalizando...");
                    System.exit(0);
                    break;

                case 1:

                    //MOSTRAR O SALDO
                    mostrarMenu("SALDO");
                    pularLinhas(2);
                    break;

                case 2:

                    //DEPÓSITO
                    System.out.println("Informe o valor a ser depositado:");
                    depos = ler.nextDouble();

                    SALDOv[IDENTIFICADOR] += depos;
                    mostrarMenu("COMPROVANTE");

                    pularLinhas(2);

                    break;

                case 3:

                    //SAQUE
                    System.out.println("Informe o valor à ser sacado:");
                    saque = ler.nextDouble();

                    if (saque > SALDOv[IDENTIFICADOR]) {
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("! Valor excede o saldo !");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        SALDOv[IDENTIFICADOR] -= saque;
                        mostrarMenu("SAQUE");
                    }

                    pularLinhas(2);

                    break;

                default:
                    //CASO O NÚMERO DIGITADO SEJA DIFERENTE DAS OPÇÕES
                    System.out.println("Opção inválida!");
                    pularLinhas(2);

            }

        }

    }

    public static void pularLinhas(int n) {

        int pula = 0;

        while (pula < n) {
            System.out.println("");
            pula++;
        }
    }

    public static boolean efetuarLogin(String cpf, String senha) {
        int a = 0, b = 0;

        for (String cpf1 : CPFv) {

            if (cpf.equals(cpf1)) {
                for (String senha1 : SENHAv) {

                    if (senha.equals(senha1) && a == b) {
                        IDENTIFICADOR = b;
                        return true;
                    }
                    b++;

                }
            }
            a++;
        }
        return false;

    }

    private static void mostrarMenu(String menu) {

        switch (menu) {

            case "PRINCIPAL":

                //MENU PRINCIPAL
                System.out.println("|---------------------------|");
                System.out.println("|        1 - SALDO          |");
                System.out.println("|        2 - DEPÓSITO       |");
                System.out.println("|        3 - SAQUE          |");
                System.out.println("|        4 - LOG-OUT        |");
                System.out.println("|        5 - SAIR           |");
                System.out.println("|---------------------------|");
                System.out.println("Escolha uma das opções acima:");
                op = ler.nextInt();

                break;

            case "INICIAL":

                //AUXILIARES PARA ESCREVER \\
                String aux1 = "||\\" + "\\ " + " //||";
                String aux2 = "|| \\" + "\\" + "// ||";

                //MENU DE INICIAL
                System.out.println("*****************************");
                System.out.println(" ______   ______   ___    ___");
                System.out.println("|      |    ||     " + aux1);
                System.out.println("|------|    ||     " + aux2);
                System.out.println("|      |    ||     ||      ||");
                System.out.println("");
                System.out.println("*****************************");
                System.out.println("|''''''''''OPÇÕES'''''''''''|");
                System.out.println("|         1 - LOGIN         |");
                System.out.println("|         2 - SAIR          |");
                System.out.println("|'''''''''''''''''''''''''''|");
                System.out.println("Escolha uma das opções acima:");
                op1 = ler.nextInt();

                break;

            case "LOGIN":

                //MENU DE LOGIN
                System.out.println("============LOGIN============");
                System.out.println("");
                System.out.print("      CPF: ");
                cpf = ler.next();
                System.out.println("");
                System.out.println("*****************************");
                System.out.println("");
                System.out.print("      SENHA: ");
                senha = ler.next();
                System.out.println("");
                System.out.println("============LOGIN============");
                pularLinhas(3);

                break;

            case "SALDO":

                //MOSTRAR O SALDO
                System.out.println("|##########EXTRATO##########|");
                System.out.println("|                           |");
                if (SALDOv[IDENTIFICADOR] >= 1000) {
                    System.out.printf("|SALDO: R$ %.2f          |\n", SALDOv[IDENTIFICADOR]);
                } else if (SALDOv[IDENTIFICADOR] >= 0 && SALDOv[IDENTIFICADOR] < 10) {
                    System.out.printf("|SALDO: R$ %.2f             |\n", SALDOv[IDENTIFICADOR]);
                } else if (SALDOv[IDENTIFICADOR] >= 10 && SALDOv[IDENTIFICADOR] < 100) {
                    System.out.printf("|SALDO: R$ %.2f            |\n", SALDOv[IDENTIFICADOR]);
                } else if (SALDOv[IDENTIFICADOR] >= 100 && SALDOv[IDENTIFICADOR] < 1000) {
                    System.out.printf("|SALDO: R$ %.2f           |\n", SALDOv[IDENTIFICADOR]);
                }
                System.out.println("|                           |");
                System.out.println("|###########################|");                

                break;

            case "COMPROVANTE":

                //MOSTRAR COMPROVANTE
                System.out.println("|########COMPROVANTE########|");
                System.out.println("|                           |");
                if (depos <= 999) {
                    System.out.printf("|DEPÓSITO: R$ %.2f        |\n", depos);
                } else {
                    System.out.printf("|DEPÓSITO: R$ %.2f       |\n", depos);
                }
                System.out.println("|                           |");
                System.out.println("|##########DEPÓSITO#########|");

                break;

            case "SAQUE":

                //MOSTRAR COMPROVANTE
                System.out.println("|########COMPROVANTE########|");
                System.out.println("|                           |");
                if (saque <= 999) {
                    System.out.printf("|SAQUE: R$ %.2f           |\n", saque);
                } else {
                    System.out.printf("|SAQUE: R$ %.2f          |\n", saque);
                }
                System.out.println("|                           |");
                System.out.println("|###########SAQUE###########|");

                break;
        }

    }

}

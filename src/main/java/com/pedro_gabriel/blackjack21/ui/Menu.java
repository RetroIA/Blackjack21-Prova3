package main.java.com.pedro_gabriel.blackjack21.ui;

import java.util.Scanner;
import main.java.com.pedro_gabriel.blackjack21.models.Jogo;
import main.java.com.pedro_gabriel.blackjack21.exceptions.ExcecaoJogadorInexistente;
import main.java.com.pedro_gabriel.blackjack21.exceptions.ExcecaoJogoNaoIniciado;

public class Menu {
    private Jogo jogo;
    private Scanner scanner;

    public Menu(Jogo jogo) {
        this.jogo = jogo;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPrincipal() throws ExcecaoJogadorInexistente, ExcecaoJogoNaoIniciado {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("|         Bem-vindo ao Blackjack       |");
            System.out.println("----------------------------------------");
            System.out.println("|1. Jogar contra um bot                |");
            System.out.println("|2. Jogar contra outro jogador         |");
            System.out.println("|3. Mostrar resultados                 |");
            System.out.println("|4. Sair                               |");
            System.out.println("----------------------------------------");

            int escolha = lerOpcao();

            switch (escolha) {
                case 1:
                    // Jogar contra um bot
                    System.out.println("Digite o nome do jogador:");
                    String nomeJogador = scanner.next();
                    jogo.iniciarJogo(nomeJogador, true);
                    break;
                case 2:
                    // Jogar contra outro jogador
                    System.out.println("Digite o nome do primeiro jogador:");
                    String nomeJogador1 = scanner.next();
                    System.out.println("Digite o nome do segundo jogador:");
                    String nomeJogador2 = scanner.next();
                    jogo.iniciarJogo(nomeJogador1, nomeJogador2, false);
                    break;
                case 3:
                    // Mostrar resultados
                    jogo.mostrarResultados();
                    break;
                case 4:
                    // Sair
                    System.out.println(
                            "Obrigado por jogar BlackJack21! Eis aqui o resultado de todas as partidas jogadas:");
                    jogo.mostrarResultados();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    private int lerOpcao() {
        System.out.print("\nEscolha uma opção: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

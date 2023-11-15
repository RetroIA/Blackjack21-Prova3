package main.java.com.pedro_gabriel.blackjack21;

import main.java.com.pedro_gabriel.blackjack21.models.Jogo;
import main.java.com.pedro_gabriel.blackjack21.ui.Menu;
import main.java.com.pedro_gabriel.blackjack21.exceptions.ExcecaoJogadorInexistente;
import main.java.com.pedro_gabriel.blackjack21.exceptions.ExcecaoJogoNaoIniciado;

/* Nome: Pedro Gabriel Ferreira Franco;
   Prova de disciplina de programação II;
   Github: RetroAI ;
*/

public class Main {
    public static void main(String[] args) {
        try {
            Jogo jogo = Jogo.getInstancia();
            Menu menu = new Menu(jogo);
            menu.exibirMenuPrincipal();
        } catch (ExcecaoJogadorInexistente e) {
            System.out.println(e.getMessage());
        } catch (ExcecaoJogoNaoIniciado e) {
            System.out.println(e.getMessage());
        }
    }
}

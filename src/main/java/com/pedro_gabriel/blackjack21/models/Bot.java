package main.java.com.pedro_gabriel.blackjack21.models;

public class Bot extends Jogador {

    public Bot(String nome) {
        super(nome);
    }

    // Método para decidir se o bot deve "bater" ou "ficar"
    public boolean deveBater(int cartaVisivelJogadorHumano) {
        int pontuacao = calcularPontuacao();

        // Se a pontuação for menor que 17, o bot deve sempre "bater"
        if (pontuacao < 17) {
            return true;
        }

        // Se a pontuação for menor ou igual 21 e a pontuação do jogador for maior que 7
        // o bot pede uma carta
        if (pontuacao <= 21 && cartaVisivelJogadorHumano >= 7) {
            return true;
        }

        // Em todos os outros casos, o bot deve "ficar"
        return false;
    }

}
package main.java.com.pedro_gabriel.blackjack21.models;

public class Carta {
    private String valor;
    private String naipe;

    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public String getValor() {
        return this.valor;
    }

    public String getNaipe() {
        return this.naipe;
    }

    public int getValorNumerico() {
        switch (this.valor) {
            case "A":
                return 1; // Escolhi para valer 1, sem fazer os casos de quando vale 11.
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(this.valor); // Para números de 2 a 10
        }
    }

    @Override
    public String toString() {
        String carta = "┌───────┐\n" +
                String.format("|%-7s|\n", this.valor) +
                "|   " + this.naipe + "   |\n" + // Exibe o naipe no centro da carta
                String.format("|%7s|\n", this.valor) +
                "└───────┘";
        return carta;
    }

    // Método de fabrica de cartas
    public static Carta criarCarta(String valor, String naipe) {
        return new Carta(valor, naipe);
    }

}
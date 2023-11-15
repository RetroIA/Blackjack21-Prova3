package main.java.com.pedro_gabriel.blackjack21.models;

import java.util.ArrayList;

public class Jogador {
    private ArrayList<Carta> cartas;
    private String nome;

    public Jogador(String nome) {
        this.nome = nome;
        this.cartas = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public void receberCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public int calcularPontuacao() {
        int pontuacao = 0;
        for (Carta carta : cartas) {
            pontuacao += carta.getValorNumerico();
        }
        return pontuacao;
    }

    public void mostrarCartas() {
        System.out.println(this.nome + "'s cartas:");
        for (Carta carta : this.cartas) {
            System.out.println(carta);
        }
    }

    public void limparCartas() {
        this.cartas.clear();
    }

}

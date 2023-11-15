package main.java.com.pedro_gabriel.blackjack21.models;

import java.util.ArrayList;
import java.util.Collections;
import main.java.com.pedro_gabriel.blackjack21.exceptions.ExcecaoBaralhoVazio;

public class Baralho {
    private ArrayList<Carta> cartas;

    public Baralho() {
        this.cartas = new ArrayList<>();
        String[] naipes = { "O", "E", "P", "C" };
        String[] valores = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

        for (String naipe : naipes) {
            for (String valor : valores) {
                this.cartas.add(Carta.criarCarta(valor, naipe));
            }
        }
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }

    public void embaralhar() {
        Collections.shuffle(this.cartas);
    }

    public Carta retirarCarta() throws ExcecaoBaralhoVazio {
        if (this.cartas.isEmpty()) {
            throw new ExcecaoBaralhoVazio("O baralho está vazio!");
        }
        return this.cartas.remove(this.cartas.size() - 1);
    }

}

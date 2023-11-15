package test.java.com.pedro_gabriel.blackjack21;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.com.pedro_gabriel.blackjack21.exceptions.ExcecaoBaralhoVazio;
import main.java.com.pedro_gabriel.blackjack21.models.Baralho;
import main.java.com.pedro_gabriel.blackjack21.models.Carta;

public class BaralhoTest {

    @Test
    public void testRetirarCarta() {
        Baralho baralho = new Baralho();
        int tamanhoInicial = baralho.getCartas().size();
        try {
            Carta carta = baralho.retirarCarta();
            assertNotNull(carta);
            assertEquals(tamanhoInicial - 1, baralho.getCartas().size());
        } catch (ExcecaoBaralhoVazio e) {
            fail("Exceção ExcecaoBaralhoVazio lançada: " + e.getMessage());
        }
    }

    @Test
    public void testCarta() {
        Carta carta = new Carta("A", "O");
        assertEquals(1, carta.getValorNumerico());
        String esperado = "┌───────┐\n" +
                "|A      |\n" +
                "|   O   |\n" +
                "|      A|\n" +
                "└───────┘";
        assertEquals(esperado, carta.toString());
    }
}

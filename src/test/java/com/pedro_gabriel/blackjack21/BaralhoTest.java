



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
        String esperado = 
                "┌───────┐\n" +
                "|A      |\n" +
                "|   O   |\n" +
                "|      A|\n" +
                "└───────┘";
        assertEquals(esperado, carta.toString());
    }

     @Test
    public void testBaralhoInicializacao() {
        Baralho baralho = new Baralho();
        assertEquals(52, baralho.getCartas().size());
    }

    
    @Test
    public void testBaralhoEmbaralhado() {
        Baralho baralho1 = new Baralho();
        Baralho baralho2 = new Baralho();
        baralho1.embaralhar();
        assertNotEquals(baralho1.getCartas(), baralho2.getCartas());
    }

    
    @Test
    public void testRetirarCartaComBaralhoVazio() {
        Baralho baralho = new Baralho();
        for (int i = 0; i < 52; i++) {
            try {
                baralho.retirarCarta();
            } catch (ExcecaoBaralhoVazio e) {
                fail("Exceção ExcecaoBaralhoVazio lançada: " + e.getMessage());
            }
        }
        assertThrows(ExcecaoBaralhoVazio.class, () -> baralho.retirarCarta());
    }

    
    @Test
    public void testValorNumericoCarta() {
        Carta carta1 = new Carta("2", "O");
        assertEquals(2, carta1.getValorNumerico());
        Carta carta2 = new Carta("K", "O");
        assertEquals(10, carta2.getValorNumerico());
    }
}
package test.java.com.pedro_gabriel.blackjack21;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.java.com.pedro_gabriel.blackjack21.models.Jogador;
import main.java.com.pedro_gabriel.blackjack21.models.Bot;
import main.java.com.pedro_gabriel.blackjack21.models.Carta;

public class JogadorTest {

    @Test
    public void testJogador() {
        Jogador jogador = new Jogador("Teste");
        Carta carta = new Carta("A", "O");
        jogador.receberCarta(carta);
        assertTrue(jogador.getCartas().contains(carta));
        assertEquals(1, jogador.calcularPontuacao());
    }

    @Test
    public void testBot() {
        Bot bot = new Bot("Teste");
        Carta carta = new Carta("A", "O");
        bot.receberCarta(carta);
        assertTrue(bot.deveBater(10)); // Assume que a carta visível do jogador é 10
    }

    @Test
    public void testeReceberCarta() {
        Jogador jogador = new Jogador("Teste");
        Carta carta = new Carta("Espadas", "Ás");
        jogador.receberCarta(carta);
        assertEquals(1, jogador.getCartas().size());
        assertEquals(carta, jogador.getCartas().get(0));
    }

    @Test
    public void testeLimparCartas() {
        Jogador jogador = new Jogador("Teste");
        Carta carta = new Carta("Espadas", "Ás");
        jogador.receberCarta(carta);
        jogador.limparCartas();
        assertEquals(0, jogador.getCartas().size());
    }
    @Test
    public void testSetNomeJogador() {
        Jogador jogador = new Jogador("Teste");
        jogador.setNome("NovoNome");
        assertEquals("NovoNome", jogador.getNome());
    }

    
    @Test
    public void testBotFicar() {
        Bot bot = new Bot("Teste");
        Carta carta = new Carta("K", "O");
        bot.receberCarta(carta);
        bot.receberCarta(carta);
        assertFalse(bot.deveBater(6)); 
    }

    
    @Test
    public void testBotBater() {
        Bot bot = new Bot("Teste");
        Carta carta = new Carta("6", "O");
        bot.receberCarta(carta);
        assertTrue(bot.deveBater(10)); // Assume que a carta visível do jogador é 10
    }
}
package main.java.com.pedro_gabriel.blackjack21.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.java.com.pedro_gabriel.blackjack21.exceptions.ExcecaoBaralhoVazio;

public class Jogo {
    private static Jogo instancia;
    private ArrayList<Jogador> jogadores;
    private List<String> resultados = new ArrayList<>();

    private Baralho baralho;
    private boolean jogoIniciado;
    private Scanner scanner;

    private Jogo() {
        if (instancia != null) {
            throw new RuntimeException("Já existe uma instância do Jogo em execução!");
        }
        this.jogadores = new ArrayList<>();
        this.baralho = new Baralho();
        this.jogoIniciado = false;
        this.scanner = new Scanner(System.in);
        instancia = this;
    }

    // Para padrão Singleton
    public static Jogo getInstancia() {
        if (instancia == null) {
            instancia = new Jogo();
        }
        return instancia;
    }

    public void iniciarJogo(String nomeJogador1, boolean contraBot) {
        iniciarJogo(nomeJogador1, "Bot", contraBot);
    }

    public void iniciarJogo(String nomeJogador1, String nomeJogador2, boolean contraBot) {
        // Limpa as cartas dos jogadores no início de cada jogo

        // Se a lista de jogadores estiver vazia, adicione os jogadores à lista
        if (jogadores.isEmpty()) {
            this.jogadores.add(new Jogador(nomeJogador1));
            if (contraBot) {
                this.jogadores.add(new Bot(nomeJogador2));
            } else {
                this.jogadores.add(new Jogador(nomeJogador2));
            }
        }

        // Embaralha o baralho antes do início do jogo
        this.baralho.embaralhar();

        // Define jogoIniciado como true
        this.jogoIniciado = true;

        // Inicia o jogo
        jogar();
    }

    public void jogar() {
        while (true) {
            System.out.println("Digite 'j' para jogar uma rodada, 'r' para mostrar resultados, ou 's' para sair:");
            String comando = scanner.next().toLowerCase();
            switch (comando) {
                case "j":
                    jogarRodada();
                    break;
                case "r":
                    mostrarResultados();
                    break;
                case "s":
                    encerrarJogo();
                    return;
                default:
                    System.out.println("Comando inválido. Por favor, tente novamente.");
                    break;
            }
        }
    }

    public void jogarRodada() {
        if (!jogoIniciado) {
            System.out.println("O jogo ainda não começou. Por favor, inicie um novo jogo.");
            return;
        }

        // Dá duas cartas ao jogador
        for (Jogador jogador : this.jogadores) {
            try {
                jogador.receberCarta(this.baralho.retirarCarta());
                jogador.receberCarta(this.baralho.retirarCarta());
            } catch (ExcecaoBaralhoVazio e) {
                System.out.println("O baralho está vazio. Não é possível retirar mais cartas.");
            }
        }

        // Código para a jogada do jogador humano
        for (Jogador jogador : jogadores) {
            if (!(jogador instanceof Bot)) { // Verifica se o jogador é humano
                System.out.println(jogador.getNome() + ", é a sua vez.");

                // Exibe as cartas do jogador no início de sua vez
                exibirCartas(jogador);

                String comando;
                do {
                    comando = obterComandoDoUsuario();
                    processarComando(jogador, comando);
                } while (comando.equals("h"));
            }
        }

        // Após a jogada do jogador humano, é a vez do bot
        jogadaDoBot();

        // Determinar o vencedor da rodada
        Jogador vencedor = determinarVencedor();
        mostrarResultado(vencedor);

        for (Jogador jogador : jogadores) {
            jogador.limparCartas();
        }
    }

    public String obterComandoDoUsuario() {
        System.out.println("Digite 'h' para bater ou 's' para parar:");
        String comando = scanner.next().toLowerCase();
        while (!comando.equals("h") && !comando.equals("s")) {
            System.out.println("Comando inválido. Por favor, digite 'h' para bater ou 's' para parar:");
            comando = scanner.next().toLowerCase();
        }
        return comando;
    }

    public void processarComando(Jogador jogador, String comando) {
        if (comando.equals("h")) { // "h" para "hit" ou "bater"
            try {
                Carta carta = baralho.retirarCarta();
                jogador.receberCarta(carta);
                exibirCartas(jogador); // Exibe as cartas do jogador após cada jogada
            } catch (ExcecaoBaralhoVazio e) {
                System.out.println("O baralho está vazio. Não é possível retirar mais cartas.");
            }
        }
    }

    public Jogador determinarVencedor() {
        Jogador vencedor = null;
        int maiorPontuacao = 0;
        for (Jogador jogador : jogadores) {
            int pontuacao = jogador.calcularPontuacao();
            if (pontuacao > 21) {
                continue;
            }
            if (pontuacao > maiorPontuacao) {
                maiorPontuacao = pontuacao;
                vencedor = jogador;
            }
        }
        return vencedor;
    }

    public void mostrarResultados() {
        if (resultados.isEmpty()) {
            System.out.println("Nenhuma partida foi jogada ainda.");
        } else {
            System.out.println("\nResultados das partidas:\n");
            int numeroDaPartida = 1;
            for (String resultado : resultados) {
                System.out.println("--------------------------------------------------");
                System.out.println(numeroDaPartida + "° partida ocorrida durante o jogo:\n");
                System.out.println(resultado);
                System.out.println("--------------------------------------------------\n");
                numeroDaPartida++;
            }
        }
    }

    // Organizar os resultados dessa forma ao mostrar
    public void mostrarResultado(Jogador vencedor) {
        String resultado;
        if (jogadores.get(0).calcularPontuacao() > 21 && jogadores.get(1).calcularPontuacao() > 21) {
            resultado = "Empate!\n" +
                    "Participantes: " + jogadores.get(0).getNome() + " vs " + jogadores.get(1).getNome() + ";\n" +
                    "Pontuação do " + jogadores.get(0).getNome() + ": " + jogadores.get(0).calcularPontuacao() + ";\n" +
                    "Pontuação do " + jogadores.get(1).getNome() + ": " + jogadores.get(1).calcularPontuacao() + ";";
        } else {
            resultado = "Vencedor: " + vencedor.getNome() + "\n" +
                    "Participantes: " + jogadores.get(0).getNome() + " vs " + jogadores.get(1).getNome() + ";\n" +
                    "Pontuação do " + jogadores.get(0).getNome() + ": " + jogadores.get(0).calcularPontuacao() + ";\n" +
                    "Pontuação do " + jogadores.get(1).getNome() + ": " + jogadores.get(1).calcularPontuacao() + ";";
        }
        System.out.println(resultado);

        resultados.add(resultado);
    }

    public void encerrarJogo() {
        this.jogoIniciado = false;
        this.jogadores.clear();
        this.baralho = new Baralho();
    }

    public void jogadaDoBot() {
        for (Jogador jogador : jogadores) {
            if (jogador instanceof Bot) { // Verifica se o jogador é um bot
                Bot bot = (Bot) jogador;
                try {
                    while (bot.deveBater(cartaVisivelJogadorHumano())) { // O bot decide se deve "bater"
                        bot.receberCarta(baralho.retirarCarta());
                        exibirCartas(bot); // Exibe as cartas do bot após cada jogada
                    }
                } catch (ExcecaoBaralhoVazio e) {
                    System.out.println("O baralho está vazio. Não é possível retirar mais cartas.");
                    break; // Sai do loop se o baralho estiver vazio
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break; // Sai do loop se houver um erro ao obter as cartas visíveis do jogador
                }
            }
        }
    }

    public void exibirCartas(Jogador jogador) {
        System.out.println("\n----------------------------------------");
        System.out.println(jogador.getNome() + " tem as seguintes cartas:");
        for (Carta carta : jogador.getCartas()) {
            System.out.println(carta.toString());
        }
        System.out.println("----------------------------------------\n");
    }

    // Para logica e inteligência do bot
    public int cartaVisivelJogadorHumano() throws Exception {
        if (jogadores.size() > 0) {

            Jogador jogadorHumano = jogadores.get(0);

            // Verifica se o Jogador tem pelo menos uma carta
            if (jogadorHumano.getCartas().size() > 0) {
                // Retorna o valor numérico da primeira carta do jogador
                return jogadorHumano.getCartas().get(0).getValorNumerico();
            } else {
                throw new Exception("O jogador humano não tem cartas.");
            }
        } else {
            throw new Exception("Não há jogadores no jogo.");
        }
    }
}

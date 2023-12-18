package controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import model.alojamento.*;
import model.hotel.*;
import model.pessoa.*;
import view.*;

// Classe que vai controlar todo o sistema
public class SistemaController {

    private Hotel hotel;
    private Pessoa logado;

    public SistemaController(Hotel hotel) {
        this.hotel = hotel;
        logado = null;

        verificarPrimeiroAcesso();
    }

    // Inicia o sistema
    public void iniciarSistema() {

        boolean sair = false;
        do {
            try {
                String[] usuario = Menu.menuLogin();

                if (usuario == null || usuario.length == 0) {
                    // Se usuário fechou a tela de login, sair do loop
                    break;
                }

                logado = descobrirLogado(hotel, usuario);

                if (logado != null) {
                    if ((logado instanceof Funcionario) && logado.getLogin().equals("admin")) {
                        funcoesAdmin();
                    } else if (logado instanceof Funcionario) {
                        funcoesFuncionario();
                    } else if (logado instanceof Hospede) {
                        funcoesHospede();
                    }
                } else {
                    Mensagens.erroLogin();
                }
                sair = Mensagens.verificarSair();
            } catch (Exception e) {
                // Tratar a exceção aqui
                e.printStackTrace();
                System.out.println("Ocorreu um erro: " + e.getMessage());
                break;
            }
        } while (!sair);

        salvarEstadoHotel(hotel);
        Mensagens.mensagemFinal();
    }

    private Pessoa descobrirLogado(Hotel hotel, String[] usuario) {
        for (Funcionario funcionario : hotel.getFuncionarios()) {
            if (usuario[0].equals(funcionario.getLogin()) && usuario[1].equals(funcionario.getSenha())) {
                return funcionario;
            }
        }
        for (Hospedagem hospedagem : hotel.getHospedagens()) {
            if (usuario[0].equals(hospedagem.getHospede().getLogin())
                    && usuario[1].equals(hospedagem.getHospede().getSenha())) {
                return hospedagem.getHospede();
            }
        }
        return null;
    }

    private void funcoesAdmin() {
        OpcoesAdmin escolha;
        do {
            escolha = Menu.menuAdmin();

            if (escolha == null) {
                break;
            }
            switch (escolha) {
                case CADASTRAR_FUNC:
                    hotel.addFuncionario(Modificar.CadastrarFuncionario());
                    break;
                case REMOVER_FUNC:
                    hotel.removeFuncionario(Modificar.removerFuncionario(hotel));
                    break;
                case LISTAR_FUNC:
                    Relatorio.relatorioFuncionarios(hotel);
                    break;
                default:
                    break;
            }
        } while (escolha != OpcoesAdmin.SAIR);

    }

    private void funcoesFuncionario() {
        OpcoesFuncionario escolha;
        do {
            escolha = Menu.menuFuncionario();

            if (escolha == null) {
                break;
            }
            switch (escolha) {
                case CADASTRAR_HOSPEDAGEM:
                    hotel.addHospedagem(Modificar.cadastrarHospedagem(hotel.getAcomodacoes()));
                    break;
                case REMOVER_HOSPEDAGEM:
                    Hospedagem dadosDaHospedagem = Modificar.removerHospedagem(hotel);
                    if (dadosDaHospedagem != null) {
                        Relatorio.relatorioSaidaHospede(dadosDaHospedagem);
                        hotel.removeReserva(dadosDaHospedagem);
                    }
                    break;
                case LISTAR_ACOMODACOES:
                    Relatorio.relatorioAcomodacoes(hotel.getHospedagens(), hotel.getAcomodacoes());
                    break;
                case LISTAR_CLIENTES:
                    Relatorio.relatorioHospedes(hotel.getHospedagens());
                    break;
                default:
                    break;
            }

        } while (escolha != OpcoesFuncionario.SAIR);
    }

    private void funcoesHospede() {
        OpcoesHospede escolha;
        do {
            escolha = Menu.menuHospede();

            if (escolha == null) {
                break;
            }
            switch (escolha) {
                case LISTAR_CONSUMO:
                    Relatorio.relatorioConsumo(hotel.getHospedagens(), (Hospede) logado);
                    break;
                case RELATORIO_ESTADIA:
                    Relatorio.relatorioEstadia(hotel.getHospedagens(), (Hospede) logado);
                    break;
                default:
                    break;
            }

        } while (escolha != OpcoesHospede.SAIR);
    }

    // Verifica se é o primeiro acesso ao sistema
    private void verificarPrimeiroAcesso() {

        String caminhoData = "data/hotel.json";

        if (Files.exists(Paths.get(caminhoData))) {
            // Criar o objeto Gson
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class,
                            (JsonSerializer<LocalDate>) (src, typeOfSrc,
                                    context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                    .registerTypeAdapter(LocalDate.class,
                            (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate
                                    .parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                    .create();

            // Ler o arquivo JSON
            try (Reader reader = new FileReader("data/hotel.json")) {

                // Definir o tipo do objeto a ser lido
                Type hotelType = new TypeToken<Hotel>() {
                }.getType();

                // Converter JSON para Java Object
                Hotel hotel = gson.fromJson(reader, hotelType);

                // Inicializar o sistema com os dados do hotel
                this.hotel = hotel;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.hotel.addFuncionario(new Funcionario(01, "admin"));
        }
    }

    private void salvarEstadoHotel(Hotel hotel) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (src, typeOfSrc,
                                context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsString(),
                                DateTimeFormatter.ISO_LOCAL_DATE))
                .create();

        try (FileWriter writer = new FileWriter("data/hotel.json")) {
            gson.toJson(hotel, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

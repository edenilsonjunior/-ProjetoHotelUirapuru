package controller;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.*;

import model.alojamento.*;
import model.hotel.*;
import model.pessoa.*;
import view.*;

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

        boolean saiu = false;
        
        while (saiu == false) {

            String[] usuario = Menu.menuLogin();

            // Se usuário fechou a tela de login, sair do loop
            if (usuario == null || usuario.length == 0) {
                break;
            }

            // Descobrir quem está logado e qual menu mostrar
            logado = descobrirLogado(hotel, usuario);
            descobrirMenu();

            // Verificar se usuário quer sair do sistema
            saiu = Mensagens.verificarSair();
        }

        salvarEstadoHotel(hotel);
        Mensagens.mensagemFinal();
    }

    private Pessoa descobrirLogado(Hotel hotel, String[] usuario) {
        for (Funcionario funcionario : hotel.getFuncionarios()) 
        {
            if (usuario[0].equals(funcionario.getLogin()) && usuario[1].equals(funcionario.getSenha())) {
                return funcionario;
            }
        }
        for (Hospedagem hospedagem : hotel.getHospedagens()) 
        {
            Hospede atual = hospedagem.getHospede();
            if (usuario[0].equals(atual.getLogin()) && usuario[1].equals(atual.getSenha())) {
                return atual;
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
                    hotel.addHospedagem(Modificar.cadastrarHospedagem(hotel));
                    break;
                case CADASTRAR_ACOMODACAO:
                    hotel.addAcomodacao(Modificar.cadastrarAcomodacao());
                    break;
                case REMOVER_HOSPEDAGEM:
                    Hospedagem removido = Modificar.removerHospedagem(hotel);
                    if (removido != null) {
                        Relatorio.relatorioSaidaHospede(removido);
                        hotel.removeReserva(removido);
                    }
                    break;
                case LISTAR_ACOMODACOES:
                    Relatorio.relatorioAcomodacoes(hotel);
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

    private void verificarPrimeiroAcesso() {

        String caminhoData = "data/hotel.json";

        if (!Files.exists(Paths.get(caminhoData))) {
            adicionarDadosIniciais();
        }
        else{
            // Gson: biblioteca para converter objetos Java para JSON e vice-versa
            Gson gson = createGson();
    
            // Tenta ler o arquivo
            try (Reader dadosJSON = new FileReader(caminhoData)) {
    
                // Instancia um novo hotel com os dados do arquivo
                Hotel hotel = gson.fromJson(dadosJSON, Hotel.class);
                if (hotel != null) {
                    this.hotel = hotel;
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void salvarEstadoHotel(Hotel hotel) {

        String caminhoData = "data/hotel.json";
        Gson gson = createGson();

        try (FileWriter localArmazenamento = new FileWriter(caminhoData)) {
            gson.toJson(hotel, localArmazenamento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (src, typeOfSrc,
                                context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsString(),
                                DateTimeFormatter.ISO_LOCAL_DATE))
                .create();
    }

    private void descobrirMenu() {
        if (logado != null) {
            if (logado instanceof Funcionario) {
                if (logado.getLogin().equals("admin")) {
                    funcoesAdmin();
                } else {
                    funcoesFuncionario();
                }
            } else if (logado instanceof Hospede) {
                funcoesHospede();
            }
        } else {
            Mensagens.erroLogin();
        }
    }


    private void adicionarDadosIniciais() {

        // Adiciona um funcionário administrador
        hotel.addFuncionario(new Funcionario(01, "admin"));

        int numero = 0;

        // Gera 4 acomodações simples
        for (int i = 0; i < 4; i++) {
            hotel.addAcomodacao(new Acomodacao(i + 1, TipoAcomodacao.SIMPLES, 100, 1, 0, 0, numero));
            numero++;
        }

        // Gera 4 acomodações para casal
        for (int i = 0; i < 4; i++) {
            hotel.addAcomodacao(new Acomodacao(i + 5, TipoAcomodacao.CASAL, 150, 2, 3, 0, numero));
            numero++;
        }

        // Gera 2 acomodações de luxo
        for (int i = 0; i < 2; i++) {
            hotel.addAcomodacao(new Acomodacao(i + 9, TipoAcomodacao.LUXO, 300, 4, 4, 0, numero));
            numero++;
        }
    }
}

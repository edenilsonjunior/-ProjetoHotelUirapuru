package controller;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.*;

import controller.opcoes.*;
import model.alojamento.*;
import model.hotel.*;
import model.pessoa.*;
import view.*;

/**
 * Classe responsável por controlar o sistema do hotel.
 */
public class SistemaController {

    private Hotel hotel;
    private Pessoa logado;


    /**
     * Construtor da classe SistemaController.
     * 
     * Este construtor recebe um objeto Hotel e o atribui ao atributo hotel da classe.
     * Também verifica se é o primeiro acesso ao sistema, chamando o método verificarPrimeiroAcesso().
     * 
     * @param hotel o hotel a ser utilizado pelo sistema
     */
    public SistemaController(Hotel hotel) {
        this.hotel = hotel;
        logado = null;

        verificarPrimeiroAcesso();
    }


    /**
     * Inicia o sistema do hotel.
     * 
     * Este método é responsável por iniciar o sistema do hotel, exibindo o menu de login
     * e permitindo que os usuários interajam com as funcionalidades do sistema.
     * 
     */
    public void iniciarSistema() {

        boolean saiu = false;
        
        while (saiu == false) {

            String[] usuario = Menu.menuLogin();

            // Se usuário fechou a tela de login, sair do loop
            if (usuario == null || usuario.length == 0) {
                break;
            }

            descobrirMenu(usuario);
            salvarEstadoHotel(hotel);

            saiu = Mensagens.verificarSair();
        }
        
        salvarEstadoHotel(hotel);
        Mensagens.mensagemFinal();
    }
    

    /**
     * Verifica se é o primeiro acesso ao sistema.
     * Se o arquivo "data/hotel.json" não existir ou se o objeto hotel for nulo,
     * adiciona os dados iniciais chamando o método adicionarDadosIniciais().
     * Caso contrário, lê o arquivo JSON, converte os dados para um objeto Hotel
     * e atualiza o atributo hotel da classe.
     */
    private void verificarPrimeiroAcesso() {

        if (!Files.exists(Paths.get("data/hotel.json")) || hotel == null) {
            adicionarDadosIniciais();
        }
        else{
            // Gson: biblioteca para converter objetos Java para JSON e vice-versa
            Gson gson = createGson();
    
            // Tenta ler o arquivo
            try (Reader dadosJSON = new FileReader("data/hotel.json")) {
    
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
    

    /**
     * Método para descobrir o usuário logado no sistema.
     * 
     * @param hotel o hotel em que o usuário está logado
     * @param usuario um array contendo o login e senha do usuário
     * @return a pessoa logada no sistema, ou null se nenhum usuário for encontrado
     */
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


    /**
     * Método responsável por descobrir o menu de funcionalidades disponíveis para o usuário logado.
     * 
     * @param usuario um array de strings contendo as informações do usuário
     */
    private void descobrirMenu(String[] usuario) {

        logado = descobrirLogado(hotel, usuario);

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


    /**
     * Método responsável por executar as funcionalidades do administrador.
     */
    private void funcoesAdmin() {
        OpcoesAdmin escolha;
        do {
            escolha = Menu.menuGenerico(OpcoesAdmin.class, "Menu Administrador");

            if (escolha == null) {
                break;
            }
            switch (escolha) {
                case CADASTRAR_FUNC:
                    hotel.addFuncionario(Modificar.CadastrarFuncionario());
                    break;
                case LISTAR_FUNC:
                    Relatorio.relatorioFuncionarios(hotel);
                    break;
                case REMOVER_FUNC:
                hotel.removeFuncionario(Modificar.removerFuncionario(hotel));
                break;
                default:
                    break;
            }
        } while (escolha != OpcoesAdmin.SAIR);
    }

    
    /**
     * Método responsável por executar as opções disponíveis para os funcionários.
     */
    private void funcoesFuncionario() {
        OpcoesFuncionario escolha;
        do {
            escolha = Menu.menuGenerico(OpcoesFuncionario.class, "Menu Funcionário");

            if (escolha == null) {
                break;
            }
            switch (escolha) {
                case OPCOES_HOSPEDAGEM:
                    funcoesHospedagem();
                    break;
                case RELATORIOS:
                    funcoesRelatorio();
                    break;
                default:
                    break;
            }

        } while (escolha != OpcoesFuncionario.SAIR);
    }

    
    /**
     * Método responsável por executar as opções disponíveis para os hóspedes.
     */
    private void funcoesHospede() {
        OpcoesHospede escolha;
        do {
            escolha = Menu.menuGenerico(OpcoesHospede.class, "Menu Hóspede");

            if (escolha == null) {
                break;
            }
            switch (escolha) {
                case RELATORIO_CONSUMO:
                    Relatorio.relatorioConsumo(hotel.getHospedagens(), (Hospede) logado);
                    break;
                case RELATORIO_ESTADIA:
                    Relatorio.relatorioEstadia(hotel.getHospedagens(), (Hospede) logado);
                    break;
                case RELATORIO_PAGAMENTO_FATURADO:
                    Relatorio.relatorioTipoFaturado(hotel.getHospedagens(), (Hospede) logado);
                    break;
                default:
                    break;
            }

        } while (escolha != OpcoesHospede.SAIR);
    }


    /**
     * Método responsável por executar as opções disponíveis para as hospedagens.
     */
    private void funcoesHospedagem(){
        OpcoesHospedagem escolha;
        Hospedagem hospedagemModificada;

        do {
            escolha = Menu.menuGenerico(OpcoesHospedagem.class, "Menu Hospedagem");

            if (escolha == null) {
                break;
            }

            switch (escolha) {
                case CADASTRAR_HOSPEDAGEM:
                    hotel.addHospedagem(Modificar.cadastrarHospedagem(hotel));
                    break;
                case REMOVER_HOSPEDAGEM:
                    Hospedagem removido = Modificar.removerHospedagem(hotel);
                    if (removido != null) {
                        Relatorio.relatorioSaidaHospede(removido);
                        hotel.removeReserva(removido);
                    }
                    break;
                case REGISTRAR_CHEKIN:
                    hospedagemModificada = Menu.escolherCheckIn(hotel.getHospedagens());
                    if (hospedagemModificada != null) {
                        hotel.getHospedagens().get(hotel.getHospedagens().indexOf(hospedagemModificada)).setStatus(true);
                    }
                    break;

                case CADASTRAR_ACOMODACAO:
                    hotel.addAcomodacao(Modificar.cadastrarAcomodacao());
                    break;
                case ADICIONAR_ACOMPANHANTE:
                    hospedagemModificada = Menu.escolherModificado(hotel.getHospedagens());
                    if (hospedagemModificada != null) {
                        hospedagemModificada.getHospede().addAcompanhante(Modificar.cadastrarAcompanhante());
                    }
                    break;
                case ADICIONAR_CONSUMO:
                    hospedagemModificada = Menu.escolherModificado(hotel.getHospedagens());
                    if (hospedagemModificada != null) {
                        hospedagemModificada.getHospede().addConsumo(Modificar.cadastrarConsumo());
                    }
                    break;    
                default:
                    break;
            }
        } while (escolha != OpcoesHospedagem.VOLTAR);
    }


    /**
     * Método responsável por executar as opções disponíveis para os relatórios.
     */
    private void funcoesRelatorio(){
        OpcoesRelatorios escolha;

        LocalDate date[];

        do {
            escolha = Menu.menuGenerico(OpcoesRelatorios.class, "Menu Relatórios");

            if (escolha == null) {
                break;
            }
            switch (escolha) {
                case RELATORIO_HOSPEDES:
                    Relatorio.relatorioHospedes(hotel);
                    break;
                case RELATORIO_RESERVAS_HOJE:
                    Relatorio.relatorioReservasHoje(hotel);
                    break;
                case RELATORIO_ACOMODACOES:
                    Relatorio.relatorioAcomodacoes(hotel);
                    break;
                case RELATORIO_FATURAMENTO:
                    date = Modificar.escolherData();
                    Relatorio.relatorioFaturamento(date[0], date[1], hotel);
                    break;
                case RELATORIO_ATRASADOS:
                    date = Modificar.escolherData();
                    Relatorio.relatorioAtrasados(date[0], date[1], hotel);
                    break;
                default:
                    break;
            }

        } while (escolha != OpcoesRelatorios.VOLTAR);
    }
    

    /**
     * Método responsável por salvar o estado do hotel em um arquivo JSON.
     * @param hotel o hotel a ser salvo
     */
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


    /**
     * Método responsável por adicionar os dados iniciais do hotel.
     */
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


    public Hotel getHotel() {
        return hotel;
    }


    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public Pessoa getLogado() {
        return logado;
    }


    public void setLogado(Pessoa logado) {
        this.logado = logado;
    }



}

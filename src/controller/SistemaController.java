package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

            String[] credenciais = Menu.menuLogin();

            // Se usuário fechou a tela de login, sair do loop
            if (credenciais == null || credenciais.length == 0) {
                break;
            }

            logado = descobrirLogado(credenciais);
        
            descobrirMenu();

            salvarEstadoHotel();
            saiu = Mensagens.verificarSair();
        }
        
        salvarEstadoHotel();
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

        Gson gson = createGson();
        
        try (Reader dadosJSON = new FileReader("data/hotel.json")) {
            
            Hotel hotel = gson.fromJson(dadosJSON, Hotel.class);
            
            if (hotel != null) {
                this.hotel = hotel;
                return;
            }

            adicionarDadosIniciais();
        } 
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            adicionarDadosIniciais();
        }
    }



    /**
     * Método para descobrir o usuário logado no sistema.
     * 
     * @param credenciais um array contendo o login e senha do usuário
     * @return a pessoa logada no sistema, ou null se nenhum usuário for encontrado
     */
    private Pessoa descobrirLogado(String[] credenciais) {

        if (credenciais == null || credenciais.length == 0) {
            return null;
        }

        for (Funcionario x : hotel.getFuncionarios()) 
        {
            if (verificaLogin(credenciais, x)) {
                return x;
            }
        }

        for (Hospedagem hospedagem : hotel.getHospedagens()) 
        {
            Hospede y = hospedagem.getHospede();
            if (verificaLogin(credenciais, y)) {
                return y;
            }
        }

        return null;
    }
 
    /**
     * Método para verificar se as credenciais de login são válidas.
     * @param credenciais credenciais de login
     * @param pessoa pessoa a ser verificada
     * @return true se as credenciais forem válidas, false caso contrário
     */
    private boolean verificaLogin(String[] credenciais, Pessoa pessoa) {
        return credenciais[0].equals(pessoa.getLogin()) && credenciais[1].equals(pessoa.getSenha());
    }


    /**
     * Método responsável por descobrir o menu de funcionalidades disponíveis para o usuário logado.
     * 
     */
    private void descobrirMenu() {

        if (logado == null) {
            Mensagens.erroLogin();
            return;
        }

        if (logado instanceof Hospede){
            funcoesHospede();
        } 
        else if ((logado instanceof Funcionario) && (logado.getLogin().equals("admin"))) {
            funcoesAdmin();
        }
        else {
            funcoesFuncionario();
        }
        
    }
    
    /**
     * Método responsável por executar as opções disponíveis para os hóspedes.
     */
    private void funcoesHospede() {
        OpcoesHospede escolha;

        Hospede hospede = (Hospede) logado;
        List<Hospedagem> hospedagens = hotel.getHospedagens();

        do {
            escolha = Menu.menuGenerico(OpcoesHospede.class, "Menu Hóspede");

            if (escolha == null) {
                break;
            }

            if (escolha == OpcoesHospede.RELATORIO_CONSUMO) {
                Relatorio.relatorioConsumo(hospedagens, hospede);
            }
            else if(escolha == OpcoesHospede.RELATORIO_ESTADIA){
                Relatorio.relatorioEstadia(hospedagens, hospede);
            }
            else if(escolha == OpcoesHospede.RELATORIO_PAGAMENTO_FATURADO){
                Relatorio.relatorioTipoFaturado(hospedagens, hospede);
            }
            else if(escolha == OpcoesHospede.FAZER_PAGAMENTO){
                Hospedagem iraFazerPagamento = acharHospedagemPorHospede(hospede);
                Mensagens.mensagemPagamento(iraFazerPagamento.getPagamento().fazerPagamento(iraFazerPagamento));
            }
        } 
        while (escolha != OpcoesHospede.SAIR);
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

            if(escolha == OpcoesAdmin.CADASTRAR_FUNC){
                hotel.addFuncionario(Modificar.CadastrarFuncionario());
            }
            else if(escolha == OpcoesAdmin.LISTAR_FUNC){
                Relatorio.relatorioFuncionarios(hotel);
            }
            else if(escolha == OpcoesAdmin.REMOVER_FUNC){
                hotel.removeFuncionario(Modificar.removerFuncionario(hotel));
            }
        } 
        while (escolha != OpcoesAdmin.SAIR);
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

            if (escolha == OpcoesFuncionario.OPCOES_HOSPEDAGEM){
                funcoesHospedagem();
            }
            else if (escolha == OpcoesFuncionario.RELATORIOS){
                funcoesRelatorio();
            }
        } 
        while (escolha != OpcoesFuncionario.SAIR);
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
                case REGISTRAR_CHECK_IN:
                    hospedagemModificada = Menu.escolherCheckIn(hotel.getHospedagens());
                    if (hospedagemModificada != null) {
                        hotel.getHospedagens().get(hotel.getHospedagens().indexOf(hospedagemModificada)).transformarResevaEmHospedagem();;
                    }
                    break;
                case CADASTRAR_ACOMODACAO:
                    hotel.addAcomodacao(Modificar.cadastrarAcomodacao());
                    break;
                case ADICIONAR_ACOMPANHANTE:
                    hospedagemModificada = Menu.escolherModificado(hotel.getHospedagens());
                    if (hospedagemModificada != null) {
                        verificarDisponibilidade(hospedagemModificada);
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
        } 
        while (escolha != OpcoesHospedagem.VOLTAR);
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
     * Método responsável por achar uma hospedagem a partir de um hóspede.
     * @param hospede o hóspede a ser procurado
     * @return a hospedagem encontrada, ou null se não for encontrada
     */
    private Hospedagem acharHospedagemPorHospede(Hospede hospede) {
        for (Hospedagem hospedagem : hotel.getHospedagens()) {
            if (hospedagem.getHospede().equals(hospede)) {
                return hospedagem;
            }
        }
        return null;
    }
    
    /**
     * Método responsável por salvar o estado do hotel em um arquivo JSON.
     */
    private void salvarEstadoHotel() {
        
        String caminhoData = "data/hotel.json";
        Gson gson = createGson();
        
        try (FileWriter localArmazenamento = new FileWriter(caminhoData)) {
            gson.toJson(hotel, localArmazenamento);
        } 
        catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    
    /**
     * Método responsável por criar um objeto Gson.
     * @return o objeto Gson criado
     */
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
     * Método responsável por verificar se é possível adicionar um acompanhante à hospedagem.
     * @param hospedagem a hospedagem a ser verificada
     */
    private void verificarDisponibilidade(Hospedagem hospedagem) {

        // Variaveis que armazenam a quantidade máxima de adultos e crianças
        int maxAdultos = hospedagem.getAcomodacao().getMaxAdultos();
        int maxCriancas = hospedagem.getAcomodacao().getMaxCriancas();

        // Começa em 1 pois o hóspede já está incluso
        int adultos = 1;
        int criancas = 0;
        
        // Cria um acompanhante e verifica se é adulto ou criança
        Acompanhante acompanhante = Modificar.cadastrarAcompanhante();
        if (acompanhante.getIdade() >= 18) {
            adultos++;
        } else {
            criancas++;
        }

        // Verifica a quantidade de adultos e crianças na hospedagem
        for (Acompanhante x : hospedagem.getHospede().getAcompanhantes()) {
            if (x.getIdade() >= 18) {
                adultos++;
            } else {
                criancas++;
            }
        }
        
        // Verifica se é possível adicionar o acompanhante
        if (adultos <= maxAdultos && criancas <= maxCriancas) {
            hospedagem.getHospede().addAcompanhante(acompanhante);
        } 
        else {
            Mensagens.erroAcompanhante();
        }
    }


    /**
     * Método responsável por adicionar os dados iniciais do hotel.
     */
    private void adicionarDadosIniciais() {

        // Adiciona um funcionário administrador
        hotel.addFuncionario(new Funcionario(01, "admin"));

        int numero = 120;

        // Gera 4 acomodações simples
        for (int i = 0; i < 4; i++) {
            hotel.addAcomodacao(new Acomodacao(i + 1, TipoAcomodacao.SIMPLES, 100, 1, 0, 1, numero));
            numero++;
        }

        // Gera 4 acomodações para casal
        for (int i = 0; i < 4; i++) {
            hotel.addAcomodacao(new Acomodacao(i + 5, TipoAcomodacao.CASAL, 150, 2, 3, 2, numero));
            numero++;
        }

        // Gera 2 acomodações de luxo
        for (int i = 0; i < 2; i++) {
            hotel.addAcomodacao(new Acomodacao(i + 9, TipoAcomodacao.LUXO, 300, 4, 4, 3, numero));
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

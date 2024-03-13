package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import com.google.gson.*;
import model.alojamento.*;
import model.hotel.*;
import model.pessoa.*;

import view.utils.*;
import view.admin.AdminViewImpl;
import view.alojamento.AlojamentoViewImpl;
import view.funcionario.FuncionarioViewImpl;
import view.hospede.HospedeViewImpl;

/**
 * Classe responsável por controlar o sistema do hotel.
 */
public class SystemController {

    private Hotel hotel;
    private Pessoa logado;

    private IUtilsView utilsView;
    
    private AdminController adminController;
    private HospedeController hospedeController;
    private FuncionarioController funcionarioController;


    public SystemController() {
        
        this.hotel = Hotel.getInstance();
        logado = null;

        adminController = new AdminController(new AdminViewImpl());
        hospedeController = new HospedeController(new HospedeViewImpl());
        funcionarioController = new FuncionarioController(new FuncionarioViewImpl(), new AlojamentoViewImpl());

        this.utilsView = new UtilsViewImpl();

        verificarPrimeiroAcesso();
    }


    public void run() {

        do {
            String[] login = utilsView.login();

            if (login == null || login.length == 0) break;
            
            logado = descobrirLogado(login);
        
            descobrirMenu();
            salvarEstadoHotel();
        } 
        while (!utilsView.verificarSair());

        salvarEstadoHotel();
        UtilsViewImpl.mensagem("Obrigado por utilizar o sistema!", "Sistema Encerrado");
    }


    private Pessoa descobrirLogado(String[] login) {

        if (login == null || login.length == 0) return null;
        
        for (Funcionario x : hotel.getFuncionarios()) {
            if (verificaLogin(login, x)) {
                return x;
            }
        }

        for (Map.Entry<Hospede, Hospedagem> data : hotel.getHospedagens().entrySet()){
            if (verificaLogin(login, data.getKey())) {
                return data.getKey();
            }
        }
        return null;
    }
 

    private boolean verificaLogin(String[] login, Pessoa pessoa) {
        return login[0].equals(pessoa.getLogin()) && login[1].equals(pessoa.getSenha());
    }


    private void descobrirMenu() {

        if (logado == null) {
            UtilsViewImpl.mensagem("Usuário ou senha incorretos!", "Erro");
            return;
        }

        if (logado instanceof Hospede){
            Hospedagem atual = hotel.getHospedagens().get(logado);
            hospedeController.funcoesHospede(atual);
        } 
        else if ((logado instanceof Funcionario) && (logado.getLogin().equals("admin"))) {
            adminController.funcoesAdmin();
        }
        else {
            funcionarioController.funcoesFuncionario();
        }
        
    }
    

    private void verificarPrimeiroAcesso() {
        Gson gson = createGson();
        
        try (Reader dadosJSON = new FileReader("data/hotel.json")) {
            
            Hotel hotel = gson.fromJson(dadosJSON, Hotel.class);
            
            if (hotel != null) this.hotel = hotel;
            else adicionarDadosIniciais();
        } 
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            adicionarDadosIniciais();
        }
    }

    
    private void salvarEstadoHotel() {
        Gson gson = createGson();

        try (FileWriter localArmazenamento = new FileWriter("data/hotel.json")) {
            gson.toJson(hotel, localArmazenamento);
        } 
        catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }


    private Gson createGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDate.class,(JsonSerializer<LocalDate>) (src, typeOfSrc,context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE))).registerTypeAdapter(LocalDate.class,(JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsString(),DateTimeFormatter.ISO_LOCAL_DATE)).create();
    }


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
}

package controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.alojamento.*;
import model.hotel.*;
import model.pessoa.*;
import view.*;

// Classe que vai controlar todo o sistema
public class SistemaController {
    
    private Hotel hotel;
    private Pessoa logado;

    public SistemaController(Hotel hotel){
        this.hotel = hotel;
        logado = null;

        verificarPrimeiroAcesso();
    }

    // Inicia o sistema
    public void iniciarSistema() {

        boolean sair = false;
        do {
            try {
                String[] usuario = SistemaView.menuLogin();

                if (usuario == null || usuario.length == 0) {
                    // Se usuário fechou a tela de login, sair do loop
                    break;
                }

                logado = descobrirLogado(hotel, usuario);

                if((logado instanceof Funcionario) && logado.getLogin().equals("admin")){
                    funcoesAdmin();
                }
                else if(logado instanceof Funcionario) {
                    funcoesFuncionario();
                }
                else if(logado instanceof Hospede) {
                    funcoesHospede();
                }
                else {
                    SistemaView.erroLogin();
                }

                sair = SistemaView.verificarSair();
            } catch (Exception e) {
                // Tratar a exceção aqui
                e.printStackTrace();
                System.out.println("Ocorreu um erro: " + e.getMessage());
                break;
            }
        } while (!sair);
        
        salvarEstadoHotel(hotel);
        SistemaView.mensagemFinal();
    }







        
    private Pessoa descobrirLogado(Hotel hotel, String[] usuario) {
            for (Funcionario funcionario : hotel.getFuncionarios()) {
                if (usuario[0].equals(funcionario.getLogin()) && usuario[1].equals(funcionario.getSenha())) {
                    return funcionario;
                }
            }
            for (Hospedagem hospedagem : hotel.getHospedagens()) {
                if (usuario[0].equals(hospedagem.getHospede().getLogin()) && usuario[1].equals(hospedagem.getHospede().getSenha())) {
                    return hospedagem.getHospede();
                }
            }
            return null;
        }


    private void funcoesAdmin(){
        OpcoesAdmin escolha;
        do {
            escolha = SistemaView.menuAdmin();
            switch (escolha) {
                case CADASTRAR_FUNC:
                    hotel.addFuncionario(SistemaView.CadastrarFuncionario());
                    break;
                case LISTAR_FUNC:
                    SistemaView.listarFuncionarios(hotel);
                    break;
                case REMOVER_FUNC:
                    hotel.removeFuncionario(SistemaView.removerFuncionario(hotel));
                    break;
                default:
                    break;
            }
        } while (escolha != OpcoesAdmin.SAIR);

    }

    private void funcoesFuncionario(){
        OpcoesFuncionario escolha;
        do {
            escolha = SistemaView.menuFuncionario();

            switch (escolha) {
               case CADASTRAR_HOSPEDAGEM:
                   hotel.addHospedagem(SistemaView.cadastrarHospedagem(hotel.getAcomodacoes()));
                   break;
              case REMOVER_HOSPEDAGEM:
                    Hospedagem dadosDaHospedagem = SistemaView.removerHospedagem(hotel);
                    if (dadosDaHospedagem != null) {
                        SistemaView.relatorioSaidaHospede(dadosDaHospedagem);
                        hotel.removeReserva(dadosDaHospedagem);
                    }
               break;
                case LISTAR_ACOMODACOES:
                    SistemaView.relatorioAcomodacoes(hotel.getHospedagens(), hotel.getAcomodacoes());
                    break;
                case LISTAR_CLIENTES:
                    SistemaView.relatorioHospedes(hotel.getHospedagens());
                    break;
                default:
                    break;
            }

        } while (escolha != OpcoesFuncionario.SAIR);
    }

    private void funcoesHospede(){
//        OpcoesHospede escolha;
//        do {
//            escolha = SistemaView.menuHospede();
//
//            switch (escolha) {
//                case LISTAR_CONSUMO:
//                    SistemaView.relatorioConsumo(hotel.getHospedagens(), logado);
//                    break;
//                case RELATORIO_ESTADIA:
//                    SistemaView.relatorioEstadia(hotel.getHospedagens(), logado);
//                    break;
//                default:
//                    break;
//            }
//
//        } while (escolha != OpcoesHospede.SAIR);
    }

    // Verifica se é o primeiro acesso ao sistema
    private void verificarPrimeiroAcesso(){

        String caminhoData = "data/hotel.json";
        
        if (Files.exists(Paths.get(caminhoData))) 
        {
           // Criar o objeto Gson
            Gson gson = new Gson();
            
            // Ler o arquivo JSON
            try (Reader reader = new FileReader("data/hotel.json")) {
    
                // Definir o tipo do objeto a ser lido
                Type hotelType = new TypeToken<Hotel>(){}.getType();
    
                // Converter JSON para Java Object
                Hotel hotel = gson.fromJson(reader, hotelType);
    
                // Inicializar o sistema com os dados do hotel
                this.hotel = hotel;
    
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
        else{
            this.hotel.addFuncionario(new Funcionario(01, "admin"));
        }
    }

    private void salvarEstadoHotel(Hotel hotel) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("data/hotel.json")) {
            gson.toJson(hotel, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



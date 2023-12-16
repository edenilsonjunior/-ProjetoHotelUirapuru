package controller;

import javax.swing.JOptionPane;

import model.hotel.*;
import model.lodging.*;
import model.people.*;
import view.*;

// Classe que vai controlar todo o sistema
public class SistemaController {
    
    private Hotel hotel;
    private Pessoa logado;

    public SistemaController(Hotel hotel){
        this.hotel = hotel;
        logado = null;

        hotel.addFuncionario(new Funcionario(01, "admin"));
    }

    public void iniciarSistema(){

        String[] usuario = SistemaView.menuLogin();

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
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
        }
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
            // OpcoesAdmin escolha;
            // do {
            //     escolha = SistemaView.menuAdmin();
            //     switch (escolha) {
            //         case CADASTRAR:
            //             hotel.addFuncionario(SistemaView.CadastrarFuncionario());
            //             break;
            //         case LISTAR:
            //             SistemaView.listarFuncionarios(hotel);
            //             break;
            //         case REMOVER:
            //             hotel.removeFuncionario(SistemaView.removerFuncionario(hotel));
            //             break;
            //         default:
            //             break;
            //     }
            // } while (escolha != OpcoesAdmin.SAIR);
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
                        hotel.removeHospedagem(SistemaView.removerHospedagem(hotel));
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

        public enum OpcoesHospede {
            LISTAR_CONSUMO, RELATORIO_ESTADIA, SAIR;
        }
        private void funcoesHospede(){
            
        }

    }



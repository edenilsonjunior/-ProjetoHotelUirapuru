package view;

import javax.swing.*;

import model.hotel.*;
import model.lodging.*;
import model.people.*;

public class Sistema {
    public static void start() {
        Hotel hotel = new Hotel();

        // Teste de cadastro de acomodacao
        hotel.addAcomodacao(cadastrarAcomodacao());
        hotel.addAcomodacao(cadastrarAcomodacao());
        hotel.addAcomodacao(cadastrarAcomodacao());
        System.out.println(hotel.relatorioAcomodacoes());

        // Teste de cadastro de Hospede
        Hospede x = cadastrarHospede();
        x.addAcompanhante("Edenilson");
        x.addAcompanhante("Henrique");
        x.addAcompanhante("Roberts");
        x.addAcompanhante("Endrew");
        verHospede(x);

        // Teste de sistema de login
        boolean superUser = false;
        Pessoa logado = null;

        String[] usuario = menuLogin();

        // Se o usuario for admin
        if (usuario[0].equals("admin") && usuario[1].equals("admin")) {
            System.out.println("Bem vindo, administrador!");
            superUser = true;
        } else {
            for (Funcionario funcionario : hotel.getFuncionarios()) {
                if (usuario[0].equals(funcionario.getLogin()) && usuario[1].equals(funcionario.getSenha())) {
                    logado = funcionario;
                    System.out.println("Bem vindo, " + funcionario.getNome() + "!");
                    superUser = true;
                    break;
                }
            }

            if (!superUser) {
                for (Hospedagem hospedagem : hotel.getHospedagens()) {
                    if (usuario[0].equals(hospedagem.getHospede().getLogin()) && usuario[1].equals(hospedagem.getHospede().getSenha())) {
                        logado = hospedagem.getHospede();
                        System.out.println("Bem vindo, " + hospedagem.getHospede().getNome() + "!, você é hospede");
                        break;
                    }
                }
            }
        }
    }

    public static String[] menuLogin(){
        // Cria campos de texto para os dados
        JTextField campoLogin = new JTextField(10);
        JTextField campoSenha = new JTextField(10);

        // Cria um novo painel
        JPanel painel = new JPanel();

        // Define layout: boxlayout.y_axis é um layout que organiza os componentes em uma coluna
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); 

        // Adiciona rótulos e campos de texto ao painel
        painel.add(new JLabel("Login:"));
        painel.add(campoLogin);

        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);

        // Mostra o painel em um JOptionPane
        int result = JOptionPane.showConfirmDialog(null, painel, 
               "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            // retorna um array com os dados do login e senha
            String[] array = {campoLogin.getText(), campoSenha.getText()};
            return array;
        }
        return null;
    }


    public static Hospede cadastrarHospede(){

        JTextField campoNome = new JTextField(10);
        JTextField campoEndereco = new JTextField(10);
        JTextField campoCidade = new JTextField(10);
        JTextField campoEstado = new JTextField(10);
        JTextField campoTelefone = new JTextField(10);
        JTextField campoDataNascimento = new JTextField(10);
        JTextField campoPais = new JTextField(10);
        JTextField campoEmail = new JTextField(10);
        JTextField campoIdentificacao = new JTextField(10);
        JTextField campoNomePai = new JTextField(10);
        JTextField campoNomeMae = new JTextField(10);
        JTextField campoDadosCartao = new JTextField(10);

        JPanel painel = new JPanel();

        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); 


        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);

        painel.add(new JLabel("Endereco:"));
        painel.add(campoEndereco);

        painel.add(new JLabel("Cidade:"));
        painel.add(campoCidade);

        painel.add(new JLabel("Estado:"));
        painel.add(campoEstado);

        painel.add(new JLabel("Telefone:"));
        painel.add(campoTelefone);

        painel.add(new JLabel("Data de Nascimento:"));
        painel.add(campoDataNascimento);

        painel.add(new JLabel("Pais:"));
        painel.add(campoPais);

        painel.add(new JLabel("Email:"));
        painel.add(campoEmail);

        painel.add(new JLabel("Identificacao:"));
        painel.add(campoIdentificacao);

        painel.add(new JLabel("Nome do Pai:"));
        painel.add(campoNomePai);

        painel.add(new JLabel("Nome da Mae:"));
        painel.add(campoNomeMae);

        painel.add(new JLabel("Dados do Cartao:"));
        painel.add(campoDadosCartao);

        int result = JOptionPane.showConfirmDialog(null, painel, 
               "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
                
                Hospede hospede = new Hospede(
                                                campoPais.getText(), 
                                                campoEmail.getText(), 
                                                Integer.parseInt(campoIdentificacao.getText()), 
                                                campoNomePai.getText(), campoNomeMae.getText(), 
                                                Integer.parseInt(campoDadosCartao.getText()), 
                                                campoNome.getText(), campoEndereco.getText(), 
                                                campoCidade.getText(), campoEstado.getText(), 
                                                Integer.parseInt(campoTelefone.getText()), 
                                                campoDataNascimento.getText());
                return hospede;
            }
        return null;
    }

    public static void verHospede(Hospede hospede){
        JOptionPane.showMessageDialog(null, hospede.getDescricao());
    }


public static Acomodacao cadastrarAcomodacao() {

        JTextField campoCodigo = new JTextField(10);
        JComboBox<TipoAcomodacao> campoOpcao = new JComboBox<>(TipoAcomodacao.values());
        JTextField campoDiaria = new JTextField(10);
        JTextField campoMaxAdultos = new JTextField(10);
        JTextField campoMaxCriancas = new JTextField(10);
        JTextField campoAndar = new JTextField(10);
        JTextField campoNumeroQuarto = new JTextField(10);

        JPanel painel = new JPanel();

        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);

        painel.add(new JLabel("Tipo do quarto:"));
        painel.add(campoOpcao);

        painel.add(new JLabel("Diaria:"));
        painel.add(campoDiaria);

        painel.add(new JLabel("Maximo de adultos:"));
        painel.add(campoMaxAdultos);

        painel.add(new JLabel("Maximo de criancas:"));
        painel.add(campoMaxCriancas);

        painel.add(new JLabel("Andar:"));
        painel.add(campoAndar);

        painel.add(new JLabel("Numero do quarto:"));
        painel.add(campoNumeroQuarto);

        // Mostra o painel em um JOptionPane
        int result = JOptionPane.showConfirmDialog(null, painel, 
               "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Acomodacao acomodacao = new Acomodacao(
                Integer.parseInt(campoCodigo.getText()), 
                (TipoAcomodacao) campoOpcao.getSelectedItem(), 
                Double.parseDouble(campoDiaria.getText()), 
                Integer.parseInt(campoMaxAdultos.getText()), 
                Integer.parseInt(campoMaxCriancas.getText()), 
                Integer.parseInt(campoAndar.getText()), 
                Integer.parseInt(campoNumeroQuarto.getText()));
            return acomodacao;
        }
        return null;
    }

    public static Hospedagem cadastrarHospedagem() {
        // TODO: Implementar o método de cadastro de hospedagem
        return null;
    }

}


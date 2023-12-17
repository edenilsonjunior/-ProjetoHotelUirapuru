package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.swing.*;

import controller.OpcoesAdmin;
import controller.OpcoesFuncionario;
import controller.OpcoesHospede;

import model.alojamento.*;
import model.consumo.Consumo;
import model.consumo.TipoConsumo;
import model.hotel.*;
import model.pessoa.*;

// Essa classe apenas mostra as telas do sistema
public class SistemaView {

    public static String[] menuLogin() {
        // Cria campos de texto para os dados
        JTextField campoLogin = new JTextField(10);
        JTextField campoSenha = new JTextField(10);

        // Cria um novo painel
        JPanel painel = new JPanel();

        // Define layout: boxlayout.y_axis é um layout que organiza os componentes em
        // uma coluna
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
            return new String[]{ campoLogin.getText(), campoSenha.getText() };
        }
        return null;
    }

    public static OpcoesFuncionario menuFuncionario() {
        // Cria um array com as opções
        OpcoesFuncionario[] opcoes = { OpcoesFuncionario.CADASTRAR_HOSPEDAGEM, OpcoesFuncionario.LISTAR_ACOMODACOES,
                OpcoesFuncionario.REMOVER_HOSPEDAGEM, OpcoesFuncionario.LISTAR_CLIENTES, OpcoesFuncionario.SAIR };

        // Mostra um JOptionPane com as opções

        return (OpcoesFuncionario) JOptionPane.showInputDialog(null, "Escolha uma opção",
                "Menu Funcionario", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    public static OpcoesAdmin menuAdmin() {
        // Cria um array com as opções
        OpcoesAdmin[] opcoes = { OpcoesAdmin.CADASTRAR_FUNC, OpcoesAdmin.LISTAR_FUNC, OpcoesAdmin.REMOVER_FUNC,
                OpcoesAdmin.SAIR };

        // Mostra um JOptionPane com as opções

        return (OpcoesAdmin) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Admin",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    public static OpcoesHospede menuHospede() {
        // Cria um array com as opções
        OpcoesHospede[] opcoes = { OpcoesHospede.LISTAR_CONSUMO, OpcoesHospede.RELATORIO_ESTADIA, OpcoesHospede.SAIR };

        // Mostra um JOptionPane com as opções

        return (OpcoesHospede) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Hospede",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    public static void relatorioHospedes(List<Hospedagem> hospedagens) {
        StringBuilder hospedes = new StringBuilder();
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.isStatus()) {
                hospedes.append("Acomodacao: ").append(hospedagem.getAcomodacao().getNumeroQuarto()).append("\n");
                hospedes.append("Hospede:").append(hospedagem.getHospede().getNome()).append("\n");

                if (!hospedagem.getHospede().getAcompanhantes().isEmpty()) {
                    hospedes.append("Acompanhante(s):\n");
                    for (Acompanhante acompanhante : hospedagem.getHospede().getAcompanhantes()) {
                        hospedes.append(acompanhante.getNome()).append(", ").append(acompanhante.getIdade()).append("\n");
                    }
                }

                hospedes.append("Check-in: ").append(hospedagem.getChegada()).append("\n");
                hospedes.append("Check-out: ").append(hospedagem.getSaida()).append("\n");
                hospedes.append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, hospedes.toString());
    }

    public static void relatorioReservasHoje(List<Hospedagem> hospedagens) {
        StringBuilder reservas = new StringBuilder();
        for (Hospedagem hospedagem : hospedagens) {
            if (!hospedagem.isStatus()) {
                if (hospedagem.getChegada().equals(LocalDateTime.now().toLocalDate())) {
                    reservas.append("Hospede principal: ").append(hospedagem.getHospede().getNome()).append("\n");
                    reservas.append("Telefone: ").append(hospedagem.getHospede().getTelefone()).append("\n");
                    reservas.append("Tipo de acomodaçao: ").append(hospedagem.getAcomodacao().getOpcao()).append("\n");
                    reservas.append("Data prevista de saída: ").append(hospedagem.getSaida()).append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, reservas.toString());
    }

    public static void relatorioAcomodacoes(List<Hospedagem> hospedagens, List<Acomodacao> acomodacoes) {

        String str = "";
        int totalOcupadas = 0;
        int totalReservadas = 0;
        int disponiveis = 0;

        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.isStatus()) {
                totalOcupadas++;
            } else {
                totalReservadas++;
            }
        }
        disponiveis = acomodacoes.size() - (totalOcupadas + totalReservadas);

        // Uma caixa por acomodacao
        for (Acomodacao acomodacao : acomodacoes) {
            str = "Descricao da acomodacao: \n";
            str += acomodacao.relatorioAcomodacao() + "\n";
            JOptionPane.showMessageDialog(null, str);
        }

        // Estatisticas de acomodacoes
        str = "Estatisticas de acomodacoes: \n     ";
        str += "Total de acomodacoes: " + acomodacoes.size() + "\n";
        str += "Total de acomodacoes ocupadas: " + totalOcupadas + "\n";
        str += "Total de acomodacoes reservadas: " + totalReservadas + "\n";
        str += "Total de acomodacoes disponiveis: " + disponiveis + "\n";

        JOptionPane.showMessageDialog(null, str);
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
            return new Acomodacao(
                    Integer.parseInt(campoCodigo.getText()),
                    (TipoAcomodacao) campoOpcao.getSelectedItem(),
                    Double.parseDouble(campoDiaria.getText()),
                    Integer.parseInt(campoMaxAdultos.getText()),
                    Integer.parseInt(campoMaxCriancas.getText()),
                    Integer.parseInt(campoAndar.getText()),
                    Integer.parseInt(campoNumeroQuarto.getText()));
        }
        return null;
    }

    public static Hospede cadastrarHospede() {

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

            return new Hospede(
                    campoPais.getText(),
                    campoEmail.getText(),
                    Integer.parseInt(campoIdentificacao.getText()),
                    campoNomePai.getText(), campoNomeMae.getText(),
                    Integer.parseInt(campoDadosCartao.getText()),
                    campoNome.getText(), campoEndereco.getText(),
                    campoCidade.getText(), campoEstado.getText(),
                    Integer.parseInt(campoTelefone.getText()),
                    campoDataNascimento.getText());
        }
        return null;
    }

    public static Funcionario CadastrarFuncionario() {

        JTextField campoNome = new JTextField(10);
        JTextField campoEndereco = new JTextField(10);
        JTextField campoCidade = new JTextField(10);
        JTextField campoEstado = new JTextField(10);
        JTextField campoTelefone = new JTextField(10);
        JTextField campoDataNascimento = new JTextField(10);
        JTextField campoCodigo = new JTextField(10);

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
        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);

        int result = JOptionPane.showConfirmDialog(null, painel,
                "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

                return new Funcionario(
                        Integer.parseInt(campoCodigo.getText()),
                        campoNome.getText(), campoEndereco.getText(),
                        campoCidade.getText(), campoEstado.getText(),
                        Integer.parseInt(campoTelefone.getText()),
                        campoDataNascimento.getText());
        }
        return null;
    }

    public static Funcionario removerFuncionario(Hotel hotel) {

        JTextField campoCodigo = new JTextField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);

        int result = JOptionPane.showConfirmDialog(null, painel,
                "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            for (Funcionario funcionario : hotel.getFuncionarios()) {
                if (funcionario.getCodigo() == Integer.parseInt(campoCodigo.getText())) {
                    return funcionario;
                }
            }
        }
        return null;
    }

    public static void listarFuncionarios(Hotel hotel) {
        StringBuilder funcionarios = new StringBuilder();
        for (Funcionario funcionario : hotel.getFuncionarios()) {
            funcionarios.append("Nome: ").append(funcionario.getNome()).append("\n");
            funcionarios.append("Codigo: ").append(funcionario.getCodigo()).append("\n");
            funcionarios.append("Endereco: ").append(funcionario.getEndereco()).append("\n");
            funcionarios.append("Cidade: ").append(funcionario.getCidade()).append("\n");
            funcionarios.append("Estado: ").append(funcionario.getEstado()).append("\n");
            funcionarios.append("Telefone: ").append(funcionario.getTelefone()).append("\n");
            funcionarios.append("Data de Nascimento: ").append(funcionario.getDataNascimento()).append("\n");
            funcionarios.append("\n");
        }
        JOptionPane.showMessageDialog(null, funcionarios.toString());

    }

    public static void relatorioConsumo(List<Hospedagem> hospedagens, Hospede hospede) {
        StringBuilder consumo = new StringBuilder();
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.getHospede().equals(hospede)) {
                consumo.append("Acomodacao: ").append(hospedagem.getAcomodacao().getNumeroQuarto()).append("\n");
                consumo.append("Hospede:").append(hospedagem.getHospede().getNome()).append("\n");

                if (!hospedagem.getHospede().getAcompanhantes().isEmpty()) {
                    consumo.append("Acompanhante(s):\n");
                    for (Acompanhante acompanhante : hospedagem.getHospede().getAcompanhantes()) {
                        consumo.append(acompanhante.getNome()).append(", ").append(acompanhante.getIdade()).append("\n");
                    }
                }

                consumo.append("Check-in: ").append(hospedagem.getChegada()).append("\n");
                consumo.append("Check-out: ").append(hospedagem.getSaida()).append("\n");
                consumo.append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, consumo.toString());
    }

    public static void relatorioEstadia(List<Hospedagem> hospedagens, Hospede hospede) {
        StringBuilder estadia = new StringBuilder();
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.getHospede().equals(hospede)) {
                estadia.append("Acomodacao: ").append(hospedagem.getAcomodacao().getNumeroQuarto()).append("\n");
                estadia.append("Hospede:").append(hospedagem.getHospede().getNome()).append("\n");

                if (!hospedagem.getHospede().getAcompanhantes().isEmpty()) {
                    estadia.append("Acompanhante(s):\n");
                    for (Acompanhante acompanhante : hospedagem.getHospede().getAcompanhantes()) {
                        estadia.append(acompanhante.getNome()).append(", ").append(acompanhante.getIdade()).append("\n");
                    }
                }

                estadia.append("Check-in: ").append(hospedagem.getChegada()).append("\n");
                estadia.append("Check-out: ").append(hospedagem.getSaida()).append("\n");
                estadia.append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, estadia.toString());
    }

    public static void relatorioSaidaHospede() {
        // TODO: Implementar (letra C)
    }

    public static void relatorioFaturamento(LocalDate inicio, LocalDate fim, Hotel hotel) {

        if (inicio.isAfter(fim)) {
            JOptionPane.showMessageDialog(null, "Data de inicio maior que data de fim!");
        }
        else {
            double faturamentoDiarias = 0;
            double faturamentoFrigobar = 0;
            double faturamentoRestaurante = 0;
            double faturamentoLavanderia = 0;
            double faturamentoTelefonemas = 0;
            double descontos = 0;
            for (Hospedagem hospedagem : hotel.getHospedagens()) {
                if (hospedagem.isStatus()) {
                    if (hospedagem.getChegada().isAfter(inicio) && hospedagem.getSaida().isBefore(fim)) {
                        faturamentoDiarias += hospedagem.getAcomodacao().getDiaria();
                        descontos += hospedagem.getMulta();
                        for (Consumo consumo : hospedagem.getHospede().getListaConsumo()) {
                            if (consumo.getTipo() == TipoConsumo.FRIGOBAR) {
                                faturamentoFrigobar += consumo.getValor();
                            }
                            else if (consumo.getTipo() == TipoConsumo.RESTAURANTE) {
                                faturamentoRestaurante += consumo.getValor();
                            }
                            else if (consumo.getTipo() == TipoConsumo.LAVANDERIA) {
                                faturamentoLavanderia += consumo.getValor();
                            }
                            else if (consumo.getTipo() == TipoConsumo.TELEFONE) {
                                faturamentoTelefonemas += consumo.getValor();
                            }
                        }

                    }
                }
            }

            String str = "";
            str += "Faturamento entre " + inicio + " e " + fim + "\n";
            str += "Faturamento com diarias: " + faturamentoDiarias + "\n";
            str += "Faturamento com frigobar: " + faturamentoFrigobar + "\n";
            str += "Faturamento com restaurante: " + faturamentoRestaurante + "\n";
            str += "Faturamento com lavanderia: " + faturamentoLavanderia + "\n";
            str += "Faturamento com telefonemas: " + faturamentoTelefonemas + "\n";
            str += "Descontos: " + descontos + "\n";
            str += "Total: " + (faturamentoDiarias + faturamentoFrigobar + faturamentoRestaurante + faturamentoLavanderia + faturamentoTelefonemas - descontos) + "\n";
            JOptionPane.showMessageDialog(null, str);
        }
    }

//    public static void relatorioAtrasados(LocalDate inicio, LocalDate fim, Hotel hotel) {
//
//        if (inicio.isAfter(fim)) {
//            JOptionPane.showMessageDialog(null, "Data de inicio maior que data de fim!");
//        }
//        else {
//            String str = "";
//            for (Hospedagem hospedagem : hotel.getHospedagens()) {
//                if (!hospedagem.isStatus()) {
//                    if (hospedagem.getChegada().isAfter(inicio) && hospedagem.getChegada().isBefore(fim)) {
//
//                    }
//                }
//            }
//            JOptionPane.showMessageDialog(null, str);
//        }
//    }

    public String relatorioTipoFaturado() {
        // TODO: Implementar (letra G)
        return "";
    }

    public static Hospedagem cadastrarHospedagem(List<Acomodacao> acomodacoes) {
        // TODO: Implementar o método de cadastro de hospedagem
        return null;
    }

    public static Hospedagem removerHospedagem(Hotel hotel) {
        // TODO: Implementar o método de remoção de hospedagem
        return null;
    }

    /*
     * TODO: METODOS PARA IMPLEMENTAR (VIEW)
     * menuHospede()
     */

}
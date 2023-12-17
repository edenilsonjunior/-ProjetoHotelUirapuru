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
            String[] array = { campoLogin.getText(), campoSenha.getText() };
            return array;
        }
        return null;
    }

    public static OpcoesFuncionario menuFuncionario() {
        // Cria um array com as opções
        OpcoesFuncionario[] opcoes = { OpcoesFuncionario.CADASTRAR_HOSPEDAGEM, OpcoesFuncionario.LISTAR_ACOMODACOES,
                OpcoesFuncionario.REMOVER_HOSPEDAGEM, OpcoesFuncionario.LISTAR_CLIENTES, OpcoesFuncionario.SAIR };

        // Mostra um JOptionPane com as opções
        OpcoesFuncionario escolha = (OpcoesFuncionario) JOptionPane.showInputDialog(null, "Escolha uma opção",
                "Menu Funcionario", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        return escolha;
    }

    public static OpcoesAdmin menuAdmin() {
        // Cria um array com as opções
        OpcoesAdmin[] opcoes = { OpcoesAdmin.CADASTRAR_FUNC, OpcoesAdmin.LISTAR_FUNC, OpcoesAdmin.REMOVER_FUNC,
                OpcoesAdmin.SAIR };

        // Mostra um JOptionPane com as opções
        OpcoesAdmin escolha = (OpcoesAdmin) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Admin",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        return escolha;
    }

    public static OpcoesHospede menuHospede() {
        // Cria um array com as opções
        OpcoesHospede[] opcoes = { OpcoesHospede.LISTAR_CONSUMO, OpcoesHospede.RELATORIO_ESTADIA, OpcoesHospede.SAIR };

        // Mostra um JOptionPane com as opções
        OpcoesHospede escolha = (OpcoesHospede) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Hospede",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        return escolha;
    }

    public static void relatorioHospedes(List<Hospedagem> hospedagens) {
        String hospedes = "";
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.isStatus()) {
                hospedes += "Acomodacao: " + hospedagem.getAcomodacao().getNumeroQuarto() + "\n";
                hospedes += "Hospede:" + hospedagem.getHospede().getNome() + "\n";

                if (hospedagem.getHospede().getAcompanhantes().size() != 0) {
                    hospedes += "Acompanhante(s):\n";
                    for (Acompanhante acompanhante : hospedagem.getHospede().getAcompanhantes()) {
                        hospedes += acompanhante.getNome() + ", " + acompanhante.getIdade() + "\n";
                    }
                }

                hospedes += "Check-in: " + hospedagem.getChegada() + "\n";
                hospedes += "Check-out: " + hospedagem.getSaida() + "\n";
                hospedes += "\n";
            }
        }

        JOptionPane.showMessageDialog(null, hospedes);
    }

    public static void relatorioReservasHoje(List<Hospedagem> hospedagens) {
        String reservas = "";
        for (Hospedagem hospedagem : hospedagens) {
            if (!hospedagem.isStatus()) {
                if (hospedagem.getChegada().equals(LocalDateTime.now().toLocalDate())) {
                    reservas += "Hospede principal: " + hospedagem.getHospede().getNome() + "\n";
                    reservas += "Telefone: " + hospedagem.getHospede().getTelefone() + "\n";
                    reservas += "Tipo de acomodaçao: " + hospedagem.getAcomodacao().getOpcao() + "\n";
                    reservas += "Data prevista de saída: " + hospedagem.getSaida() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, reservas);
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

    public static void relatorioSaidaHospede() {
        // TODO: Implementar (letra C)
    }

    /**
     * Metodo que gera um relatorio de faturamento de Hospedagens e Consumo.
     * O relatorio é gerado de acordo com a data de inicio até uma data de fim.
     *
     * @param inicio data de inicio
     * @param fim data de fim
     * @param hotel dados da classe hotel
     */
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

    /**
     * Metodo que gera um relatorio de hospedes que estão com a fatura atrasada.
     * O relatorio é gerado de acordo com a data de inicio até uma data de fim.
     *
     * @param inicio data de inicio
     * @param fim data de fim
     * @param hotel dados da classe hotel
     */
    public static void relatorioAtrasados(LocalDate inicio, LocalDate fim, Hotel hotel) {

        if (inicio.isAfter(fim)) {
            JOptionPane.showMessageDialog(null, "Data de inicio maior que data de fim!");
        }
        else {
            String str = "";
            for (Hospedagem hospedagem : hotel.getHospedagens()) {
                if (!hospedagem.isStatus()) {
                    if (hospedagem.getSaida().isAfter(inicio) && hospedagem.getSaida().isBefore(fim)) {
                        str += "Hospede: " + hospedagem.getHospede().getNome() + "\n";
                        str += "Data de Saída: " + hospedagem.getSaida() + "\n";
                        str += "Data de Vencimento: " + hospedagem.getPagamento().getDataVencimento() + "\n";
                        str += "Valor Devido: " + hospedagem.getPagamento().calcularTotal(hospedagem) + "\n";
                        str += "\n";
                        JOptionPane.showMessageDialog(null, str);
                    }
                }
            }
        }
    }

    public static void listarFuncionarios(Hotel hotel) {
        // TODO: Implementar listarFuncionarios
    }

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
     * CadastrarFuncionario()
     * removerFuncionario()
     * listarFuncionarios()
     * 
     * 
     * menuHospede()
     * relatorioConsumo()
     * relatorioEstadia()
     */

}
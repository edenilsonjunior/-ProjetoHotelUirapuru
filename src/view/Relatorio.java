package view;

import model.alojamento.*;
import model.consumo.*;
import model.hotel.*;
import model.pagamento.TipoPagamento;
import model.pessoa.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Classe que representa as diversas telas de relatório do sistema.
 */
public class Relatorio {
    
    /**
     * Gera um relatório de funcionários com base em uma lista de funcionários.
     * O relatório inclui informações como nome, código, endereço, cidade, estado, telefone e data de nascimento.
     *
     * @param hotel O objeto {@link Hotel} contendo as informações dos funcionários.
     */
    public static void relatorioFuncionarios(Hotel hotel) {

        for (Funcionario funcionario : hotel.getFuncionarios()) {
            exibirMensagem(funcionario.getDescricao());
        }
    }
    

    /**
     * Gera um relatório de hóspedes com base em uma lista de hospedagens.
     * O relatório inclui informações como número da acomodação, nome do hóspede,
     * acompanhantes (se houver), data de check-in e data de check-out.
     *
     * @param hotel Lista de objetos {@link Hotel} contendo as informações de hospedagem.
     */
    public static void relatorioHospedes(Hotel hotel) {
        for (Hospedagem hospedagem : hotel.getHospedagens()) {
            exibirMensagem(hospedagem.getDescricaoHospede());
        }
    }


    /**
     * Gera um relatório de reservas para o dia atual com base em uma lista de hospedagens.
     * O relatório inclui informações como nome do hóspede principal, telefone, tipo de acomodação
     * e data prevista de saída para as reservas agendadas para o dia atual.
     *
     * @param hotel objeto {@link Hotel} que contém as informações de hospedagem.
     */
    public static void relatorioReservasHoje(Hotel hotel) {

        String reservas = "";

        for (Hospedagem hospedagem : hotel.getHospedagens()) {
            if (!hospedagem.isStatus()) {
                if (hospedagem.getChegada().equals(LocalDateTime.now().toLocalDate())) {

                    reservas = "Hospede principal: " + hospedagem.getHospede().getNome() + "\n";
                    reservas += "Telefone: " + hospedagem.getHospede().getTelefone() + "\n";
                    reservas += "Tipo de acomodaçao: " + hospedagem.getAcomodacao().getOpcao() + "\n";
                    reservas += "Data prevista de saída: " + hospedagem.getSaida() + "\n";

                    exibirMensagem(reservas);
                }
            }
        }
    }


    /**
     * Gera um relatório de acomodações, incluindo detalhes de cada acomodação e estatísticas gerais.
     *
     * @param hotel Lista de objetos {@link Hotel} contendo informações sobre as hospedagens e acomodacoes.
     */
    public static void relatorioAcomodacoes(Hotel hotel) {

        String str = "";
        int totalOcupadas = 0;
        int totalReservadas = 0;
        int disponiveis = 0;


        for (Hospedagem hospedagem : hotel.getHospedagens()) {
            if (hospedagem.isStatus()) {
                totalOcupadas++;
            } else {
                totalReservadas++;
            }
        }

        // Uma caixa por acomodacao
        for (Acomodacao acomodacao : hotel.getAcomodacoes()) {
            str = "Descricao da acomodacao: \n";
            str += acomodacao.relatorioAcomodacao() + "\n";
            exibirMensagem(str);
        }

        disponiveis = hotel.getAcomodacoes().size() - (totalOcupadas + totalReservadas);

        // Estatisticas de acomodacoes
        str = "Estatisticas de acomodacoes: \n";
        str += "Total de acomodacoes: " + hotel.getAcomodacoes().size() + "\n";
        str += "Total de ocupadas: " + totalOcupadas + "\n";
        str += "Total de reservadas: " + totalReservadas + "\n";
        str += "Total de disponiveis: " + disponiveis + "\n";

        exibirMensagem(str);
    }


    /**
     * Gera um relatório de consumo para um hóspede específico com base em uma lista de hospedagens.
     * O relatório inclui informações como acomodação, hóspede, acompanhantes, check-in e check-out.
     *
     * @param hospedagens Lista de objetos {@link Hospedagem} contendo as informações de hospedagem.
     * @param hospede     O objeto {@link Hospede} para o qual o relatório de consumo será gerado.
     */
    public static void relatorioConsumo(List<Hospedagem> hospedagens, Hospede hospede) {

        String consumo = "";
        Hospedagem hospedagem = null;


        for (Hospedagem h : hospedagens) {
            if (h.getHospede().equals(hospede)) {
                hospedagem = h;
            }
        }


        if (hospedagem != null) {
            consumo += "Acomodacao: " + hospedagem.getAcomodacao().getNumeroQuarto() + "\n";
            consumo += "Hospede: " + hospedagem.getHospede().getNome() + "\n";
            consumo += "Total Consumo: " + hospedagem.getHospede().totalConsumo() + "R$" + "\n";

            if (!hospedagem.getHospede().getListaConsumo().isEmpty()) {
                consumo += "Consumo: \n";
                for (Consumo c : hospedagem.getHospede().getListaConsumo()) {
                    consumo += c.getTipo() + " - " + c.getDescricao() + " - " + c.getValor() + "R$" + "\n";
                }
            }

            exibirMensagem(consumo);
        }
    }

    /**
     * Gera um relatório de estadia para um hóspede específico com base em uma lista de hospedagens.
     * O relatório inclui informações como acomodação, hóspede, acompanhantes, check-in e check-out.
     *
     * @param hospedagens Lista de objetos {@link Hospedagem} contendo as informações de hospedagem.
     * @param hospede     O objeto {@link Hospede} para o qual o relatório de estadia será gerado.
     */
    public static void relatorioEstadia(List<Hospedagem> hospedagens, Hospede hospede) {

        Hospedagem hospedagem = null;

        for (Hospedagem h : hospedagens) {
            if (h.getHospede().equals(hospede)) {
                hospedagem = h;
            }
        }

        if (hospedagem != null) {
            exibirMensagem(hospedagem.getDescricaoHospede());
        }
    }

    /**
     * Gera um relatório de saída para uma hospedagem específica, exibindo informações como nome do hóspede,
     * identificação, telefone, check-in, check-out, total de consumo, número da acomodação e valor total.
     *
     * @param hospedagem O objeto {@link Hospedagem} para o qual o relatório de saída será gerado.
     */
    public static void relatorioSaidaHospede(Hospedagem hospedagem) {

        String str = "";

        str += "Hospede: " + hospedagem.getHospede().getNome() + "\n";
        str += "Indentificacao: " + hospedagem.getHospede().getIdentificacao() + "\n";
        str += "Telefone: " + hospedagem.getHospede().getTelefone() + "\n";
        str += "Check-in: " + hospedagem.getChegada() + "\n";
        str += "Check-out: " + hospedagem.getSaida() + "\n";
        str += "Total Consumo: " + hospedagem.getHospede().totalConsumo() + "\n";
        str += "Acomodacao: " + hospedagem.getAcomodacao().getNumeroQuarto() + "\n";
        str += "Valor total: " + hospedagem.getPagamento().calcularTotal(hospedagem) + "\n";

        exibirMensagem(str);
    }


    /**
     * Gera um relatório de faturamento do hotel no período especificado.
     * O relatório inclui faturamento com diárias, frigobar, restaurante, lavanderia, telefonemas,
     * descontos (multas/juros) e o total de faturamento.
     *
     * @param inicio Data de início do período.
     * @param fim    Data de fim do período.
     * @param hotel  O objeto {@link Hotel} contendo as informações das hospedagens.
     */
    public static void relatorioFaturamento(LocalDate inicio, LocalDate fim, Hotel hotel) {

        if (inicio.isAfter(fim)) {
            exibirMensagem("Data de inicio maior que data de fim!");
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
                        descontos += hospedagem.getMulta() + hospedagem.getPagamento().calcularJuros();
                        for (Consumo consumo : hospedagem.getHospede().getListaConsumo()) {

                            switch (consumo.getTipo()) {
                                case FRIGOBAR:
                                    faturamentoFrigobar += consumo.getValor();
                                    break;
                                case RESTAURANTE:
                                    faturamentoRestaurante += consumo.getValor();
                                    break;
                                case LAVANDERIA:
                                    faturamentoLavanderia += consumo.getValor();
                                    break;
                                case TELEFONE:
                                    faturamentoTelefonemas += consumo.getValor();
                                    break;
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
            str += "Descontos (Multas/Juros): " + descontos + "\n";
            str += "Total: " + (faturamentoDiarias + faturamentoFrigobar + faturamentoRestaurante + faturamentoLavanderia + faturamentoTelefonemas + descontos) + "\n";

            exibirMensagem(str);
        }
    }


    /**
     * Gera um relatório de hóspedes com saída atrasada no período especificado.
     * O relatório inclui informações como nome do hóspede, data de saída, data de vencimento do pagamento
     * e o valor devido.
     *
     * @param inicio Data de início do período.
     * @param fim    Data de fim do período.
     * @param hotel  O objeto {@link Hotel} contendo as informações das hospedagens.
     */
    public static void relatorioAtrasados(LocalDate inicio, LocalDate fim, Hotel hotel) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (inicio.isAfter(fim)) {
            exibirMensagem("Data de inicio maior que data de fim!");
        }
        else {
            String str = "";
            for (Hospedagem hospedagem : hotel.getHospedagens()) {
                if (hospedagem.isStatus()) {
                    if (hospedagem.getSaida().isAfter(inicio) && hospedagem.getSaida().isBefore(fim)) {
                        str += "Hospede: " + hospedagem.getHospede().getNome() + "\n";
                        str += "Data de Saída: " + hospedagem.getSaida().format(formatter) + "\n";
                        str += "Data de Vencimento: " + hospedagem.getPagamento().getDataVencimento().format(formatter) + "\n";
                        str += "Valor Devido: " + hospedagem.getPagamento().calcularTotal(hospedagem) + "\n";
                        str += "\n";
                        exibirMensagem(str);
                    }
                }
            }
        }
    }

    /**
     * Gera um relatório de faturas para um determinado hóspede, exibindo informações sobre suas hospedagens futuras.
     *
     * @param hospedagens Lista de hospedagens a serem consideradas para o relatório.
     * @param hospede     Hóspede para o qual o relatório será gerado.
     */
    public static void relatorioTipoFaturado(List<Hospedagem> hospedagens, Hospede hospede) {

        String str = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Hospedagem hospedagem : hospedagens) 
        {
            if (hospedagem.getHospede().equals(hospede) && hospedagem.getPagamento().getOpcao() == TipoPagamento.FATURADO) 
            {
                str += "Acomodacao: " + hospedagem.getAcomodacao().getNumeroQuarto() + "\n";
                str += "Nome: " + hospedagem.getHospede().getNome() + "\n";
                str += "Identificacao: " + hospedagem.getHospede().getIdentificacao() + "\n";
                str += "Data de Saída: " + hospedagem.getSaida() + "\n";
                str += "Valor diária: R$" + hospedagem.getAcomodacao().getDiaria()  + "\n";
                
                if (hospedagem.getPagamento().isStatus()) {
                    str += "Total Geral: R$" + hospedagem.getPagamento().calcularTotal(hospedagem) + "\n";
                    str += "Valor das parcelas: " + hospedagem.getPagamento().calcularTotal(hospedagem) / 30 + "\n";
                    str += "Descontos (Multa/Juros): R$" + (hospedagem.getMulta() + hospedagem.getPagamento().calcularJuros()) + "\n";
                    str += "Data de Vencimento: " + hospedagem.getPagamento().getDataVencimento().format(formatter) + "\n";
                }

                exibirMensagem(str);
                break;
        
            }
        }
    }

    /**
     * Exibe uma mensagem.
     *
     * @param mensagem A mensagem a ser exibida.
     */
    private static void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}

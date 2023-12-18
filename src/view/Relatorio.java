package view;

import model.alojamento.*;
import model.consumo.*;
import model.hotel.*;
import model.pessoa.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;


public class Relatorio {
    
    /**
     * Gera um relatório de funcionários com base em uma lista de funcionários.
     * O relatório inclui informações como nome, código, endereço, cidade, estado, telefone e data de nascimento.
     *
     * @param hotel O objeto {@link Hotel} contendo as informações dos funcionários.
     */
    public static void relatorioFuncionarios(Hotel hotel) {

        for (Funcionario funcionario : hotel.getFuncionarios()) {
            JOptionPane.showMessageDialog(null, funcionario.getDescricao());
        }
    }
    

    /**
     * Gera um relatório de hóspedes com base em uma lista de hospedagens.
     * O relatório inclui informações como número da acomodação, nome do hóspede,
     * acompanhantes (se houver), data de check-in e data de check-out.
     *
     * @param hospedagens Lista de objetos {@link Hospedagem} contendo as informações de hospedagem.
     */
    public static void relatorioHospedes(Hotel hotel) {

        for (Hospedagem hospedagem : hotel.getHospedagens()) {
            
            String str = "";
            str += "Acomodacao: " + hospedagem.getAcomodacao().getNumeroQuarto() + "\n";
            str += "Hospede: " + hospedagem.getHospede().getNome() + "\n";

            if (!hospedagem.getHospede().getAcompanhantes().isEmpty()) {
                str += "Acompanhante(s):\n";
                for (Acompanhante acompanhante : hospedagem.getHospede().getAcompanhantes()) {
                    str += acompanhante.getNome() + ", " + acompanhante.getIdade() + "\n";
                }
            }

            str += "Check-in: " + hospedagem.getChegada() + "\n";
            str += "Check-out: " + hospedagem.getSaida() + "\n";
            str += "\n";

            JOptionPane.showMessageDialog(null, str);
        }
    }


    /**
     * Gera um relatório de reservas para o dia atual com base em uma lista de hospedagens.
     * O relatório inclui informações como nome do hóspede principal, telefone, tipo de acomodação
     * e data prevista de saída para as reservas agendadas para o dia atual.
     *
     * @param hospedagens Lista de objetos {@link Hospedagem} contendo as informações de hospedagem.
     */
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


    /**
     * Gera um relatório de acomodações, incluindo detalhes de cada acomodação e estatísticas gerais.
     *
     * @param hospedagens   Lista de objetos {@link Hospedagem} contendo informações sobre as hospedagens.
     * @param acomodacoes   Lista de objetos {@link Acomodacao} contendo informações sobre as acomodações.
     */
    public static void relatorioAcomodacoes(Hotel hotel) {

        String str;
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
        disponiveis = hotel.getAcomodacoes().size() - (totalOcupadas + totalReservadas);

        // Uma caixa por acomodacao
        for (Acomodacao acomodacao : hotel.getAcomodacoes()) {
            str = "Descricao da acomodacao: \n";
            str += acomodacao.relatorioAcomodacao() + "\n";
            JOptionPane.showMessageDialog(null, str);
        }

        // Estatisticas de acomodacoes
        str = "Estatisticas de acomodacoes: \n";
        str += "Total de acomodacoes: " + hotel.getAcomodacoes().size() + "\n";
        str += "Total de ocupadas: " + totalOcupadas + "\n";
        str += "Total de reservadas: " + totalReservadas + "\n";
        str += "Total de disponiveis: " + disponiveis + "\n";

        JOptionPane.showMessageDialog(null, str);
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

            JOptionPane.showMessageDialog(null, consumo.toString());
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

        StringBuilder estadia = new StringBuilder();

        Hospedagem hospedagem = null;

        for (Hospedagem h : hospedagens) {
            if (h.getHospede().equals(hospede)) {
                hospedagem = h;
            }
        }

        if (hospedagem != null) {
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

            JOptionPane.showMessageDialog(null, estadia.toString());
        }
    }

    /**
     * Gera um relatório de saída para uma hospedagem específica, exibindo informações como nome do hóspede,
     * identificação, telefone, check-in, check-out, total de consumo, número da acomodação e valor total.
     *
     * @param hospedagem O objeto {@link Hospedagem} para o qual o relatório de saída será gerado.
     */
    public static void relatorioSaidaHospede(Hospedagem hospedagem) {

        StringBuilder str = new StringBuilder();

        str.append("Hospede: ").append(hospedagem.getHospede().getNome()).append("\n");
        str.append("Indentificacao: ").append(hospedagem.getHospede().getIdentificacao()).append("\n");
        str.append("Telefone: ").append(hospedagem.getHospede().getTelefone()).append("\n");
        str.append("Check-in: ").append(hospedagem.getChegada()).append("\n");
        str.append("Check-out: ").append(hospedagem.getSaida()).append("\n");
        str.append("Total Consumo: ").append(hospedagem.getHospede().totalConsumo()).append("\n");        
        str.append("Acomodacao: ").append(hospedagem.getAcomodacao().getNumeroQuarto()).append("\n");
        str.append("Valor total: ").append(hospedagem.getPagamento().calcularTotal(hospedagem)).append("\n");

        JOptionPane.showMessageDialog(null, str.toString());
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

            JOptionPane.showMessageDialog(null, str);
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

    public String relatorioTipoFaturado() {
        // TODO: Implementar (letra G)
        return "";
    }
}

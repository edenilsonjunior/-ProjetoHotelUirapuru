package model.hotel;

import model.lodging.Acomodacao;
import model.lodging.Hospedagem;
import model.payment.Pagamento;
import model.people.Funcionario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private List<Funcionario> funcionarios;
    private List<Hospedagem> hospedagens;
    private List<Pagamento> pagamentos;
    private List<Acomodacao> acomodacoes;

    public Hotel() {
        this.funcionarios = new ArrayList<>();
        this.hospedagens = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
        this.acomodacoes = new ArrayList<>();
    }

    /**
     * Metodo que adiciona um funcionario da lista de funcionarios.
     *
     * @param funcionario dados da classe funcionario
     */
    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    /**
     * Metodo que remove um funcionario da lista de funcionarios.
     *
     * @param funcionario dados da classe funcionario
     */
    public void removeFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
    }

    /**
     * Metodo que remove uma reserva da lista de hospedagens.
     * Se a reserva for cancelada após 12 horas antes do check-in, é adicionado uma multa.
     *
     * @param hospedagem dados da classe hospedagem
     */
    public void removeReserva(Hospedagem hospedagem) {
        if (!hospedagem.isStatus()) {
            hospedagens.remove(hospedagem);
            LocalDateTime chegadatime = hospedagem.getChegada().atStartOfDay();
            if (LocalDateTime.now().plusHours(12).isAfter(chegadatime)) {
                hospedagem.setMulta(hospedagem.totalDiarias() * 0.1);
            }
        }
    }

    /**
     * Metodo que remove uma hospedagem a lista de hospedagens
     *
     * @param hospedagem dados da classe hospedagem
     */
    public void removeHospedagem(Hospedagem hospedagem) {
        if (hospedagem.isStatus()) {
            hospedagens.remove(hospedagem);
        }
    }

    /**
     * Metodo que adiciona uma reserva a lista de hospedagens
     *
     * @param hospedagem dados da classe hospedagem
     */
    public void addReserva(Hospedagem hospedagem) {
        if (!hospedagem.isStatus()) {
            hospedagens.add(hospedagem);
        }
    }

    /**
     * Metodo que adiciona uma hospedagem a lista de hospedagens
     *
     * @param hospedagem dados da classe hospedagem
     */
    public void addHospedagem(Hospedagem hospedagem) {
        if (hospedagem.isStatus()) {
            hospedagens.add(hospedagem);
        }
    }


    public String relatorioHospedes() {
        String hospedes = "";
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.isStatus()) {
                hospedes += "Acomodacao: " + hospedagem.getAcomodacao().getNumeroQuarto() + "\n";
                hospedes += "Hospede:" + hospedagem.getHospede().getNome() + "\n";

                hospedes += "Acompanhantes: \n";
                for (String acompanhante : hospedagem.getHospede().getAcompanhantes()) {
                    hospedes += acompanhante + "\n";
                }

                hospedes += "Check-in: " + hospedagem.getChegada() + "\n";
                hospedes += "Check-out: " + hospedagem.getSaida() + "\n";
                hospedes += "\n";
            }
        }
        return hospedes;
    }


    public String relatorioReservasHoje(){
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
        return reservas;
    }

    public String relatorioAcomodacoes() {
        
        String acomodacoes = "";
        int totalOcupadas = 0;
        int totalReservadas = 0;
        int disponiveis = 0;

        for (Hospedagem hospedagem : this.hospedagens) {
            if (hospedagem.isStatus()) {
                totalOcupadas++;
            }else{
                totalReservadas++;
            }
        }
        disponiveis = this.acomodacoes.size() - (totalOcupadas + totalReservadas);

        // Descricao de todas as acomodacoes
        acomodacoes += "-----------------------\n";
        for (Acomodacao acomodacao : this.acomodacoes) {
            acomodacoes += "Descricao da acomodacao: \n";
            acomodacoes += acomodacao.getDescricao() + "\n";
            acomodacoes += "-----------------------";
        }
        
        // Estatisticas de acomodacoes
        acomodacoes += "Total de acomodacoes: " + this.acomodacoes.size() + "\n";
        acomodacoes += "Total de acomodacoes ocupadas: " + totalOcupadas + "\n";
        acomodacoes += "Total de acomodacoes reservadas: " + totalReservadas + "\n";
        acomodacoes += "Total de acomodacoes disponiveis: " + disponiveis + "\n";

        return acomodacoes;
    }

    public String relatorioSaidaHospede(){
        // TODO: Implementar (letra C)
        return "";
    }

    public String relatorioFaturamento(LocalDate inicio, LocalDate fim){
        // TODO: Implementar (letra F)
        return "";
    }

    public String relatorioAtrasados(){
        // TODO: Implementar (letra H)
        return "";    
    }
}

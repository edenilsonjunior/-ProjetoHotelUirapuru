package model.hotel;

import model.lodging.Acomodacao;
import model.lodging.Hospedagem;
import model.payment.Pagamento;
import model.people.Funcionario;

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



}

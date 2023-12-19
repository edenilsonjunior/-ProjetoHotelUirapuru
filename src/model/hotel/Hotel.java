package model.hotel;

import model.alojamento.*;
import model.pessoa.*;

import java.time.*;
import java.util.*;

/**
 * Classe que representa um hotel.
 */
public class Hotel {

    private List<Funcionario> funcionarios;
    private List<Hospedagem> hospedagens;
    private List<Acomodacao> acomodacoes;


    /**
     * Construtor padrão da classe Hotel.
     * Inicializa as listas de funcionários, hospedagens e acomodações.
     */
    public Hotel() {
        this.funcionarios = new ArrayList<>();
        this.hospedagens = new ArrayList<>();
        this.acomodacoes = new ArrayList<>();
    }


    /**
     * Adiciona uma acomodação à lista de acomodações do hotel.
     *
     * @param acomodacao A acomodação a ser adicionada.
     */
    public void addAcomodacao(Acomodacao acomodacao) {
        this.acomodacoes.add(acomodacao);
    }


    /**
     * Remove uma acomodação da lista de acomodações do hotel.
     *
     * @param acomodacao A acomodação a ser removida.
     */
    public void removeAcomodacao(Acomodacao acomodacao) {
        this.acomodacoes.remove(acomodacao);
    }


    /**
     * Adiciona um funcionário à lista de funcionários do hotel.
     *
     * @param funcionario O funcionário a ser adicionado.
     */
    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }


    /**
     * Remove um funcionário da lista de funcionários do hotel.
     *
     * @param funcionario O funcionário a ser removido.
     */
    public void removeFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
    }


    /**
     * Adiciona uma reserva à lista de hospedagens do hotel, caso a hospedagem não esteja no status de ocupada.
     *
     * @param hospedagem A hospedagem a ser adicionada.
     */
    public void addReserva(Hospedagem hospedagem) {
        if (!hospedagem.isStatus()) {
            hospedagens.add(hospedagem);
        }
    }


    /**
     * Remove uma reserva da lista de hospedagens do hotel, se a hospedagem não estiver no status de ocupada.
     * Calcula multa se a data de chegada estiver a menos de 12 horas do momento atual.
     *
     * @param hospedagem A hospedagem a ser removida.
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
     * Adiciona uma hospedagem à lista de hospedagens do hotel se houver capacidade suficiente na acomodação.
     *
     * @param hospedagem A hospedagem a ser adicionada.
     * @return true se a hospedagem foi adicionada com sucesso, false caso contrário.
     */
    public boolean addHospedagem(Hospedagem hospedagem) {

        // Ja considerando que já existe um adulto (Hospede no qual foi feito o cadastro)
        int totalAdultos = 1; 
        int totalCriancas = 0;

        for (Acompanhante acompanhante : hospedagem.getHospede().getAcompanhantes()) {
            if (acompanhante.getIdade() >= 18) {
                totalAdultos++;
            }
            else if (acompanhante.getIdade() < 18 && acompanhante.getIdade() >= 0) {
                totalCriancas++;
            }
        }

        if (hospedagem.getAcomodacao().getMaxAdultos() <= totalAdultos &&
            hospedagem.getAcomodacao().getMaxCriancas() <= totalCriancas) {
            hospedagens.add(hospedagem);
            return true;
        }
        return false;
    }


    /**
     * Remove uma hospedagem da lista de hospedagens do hotel.
     *
     * @param hospedagem A hospedagem a ser removida.
     */
    public void removeHospedagem(Hospedagem hospedagem) {
        if (hospedagem.isStatus()) {
            hospedagens.remove(hospedagem);
        }
    }

    
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Hospedagem> getHospedagens() {
        return hospedagens;
    }

    public void setHospedagens(List<Hospedagem> hospedagens) {
        this.hospedagens = hospedagens;
    }

    public List<Acomodacao> getAcomodacoes() {
        return acomodacoes;
    }

    public void setAcomodacoes(List<Acomodacao> acomodacoes) {
        this.acomodacoes = acomodacoes;
    }

}

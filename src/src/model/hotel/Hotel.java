package model.hotel;

import model.alojamento.*;
import model.pagamento.*;
import model.pessoa.*;

import java.time.*;
import java.util.*;

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

    public void addAcomodacao(Acomodacao acomodacao) {
        this.acomodacoes.add(acomodacao);
    }

    public void removeAcomodacao(Acomodacao acomodacao) {
        this.acomodacoes.remove(acomodacao);
    }

    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public void removeFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
    }
    
    public void addReserva(Hospedagem hospedagem) {
        if (!hospedagem.isStatus()) {
            hospedagens.add(hospedagem);
        }
    }

    public void removeReserva(Hospedagem hospedagem) {
        if (!hospedagem.isStatus()) {
            hospedagens.remove(hospedagem);
            LocalDateTime chegadatime = hospedagem.getChegada().atStartOfDay();
            if (LocalDateTime.now().plusHours(12).isAfter(chegadatime)) {
                hospedagem.setMulta(hospedagem.totalDiarias() * 0.1);
            }
        }
    }

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

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public List<Acomodacao> getAcomodacoes() {
        return acomodacoes;
    }

    public void setAcomodacoes(List<Acomodacao> acomodacoes) {
        this.acomodacoes = acomodacoes;
    }
}

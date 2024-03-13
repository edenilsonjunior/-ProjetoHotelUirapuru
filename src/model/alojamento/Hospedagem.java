package model.alojamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.consumo.Consumo;
import model.pagamento.*;
import model.pessoa.*;

/**
 * Classe que representa uma hospedagem no hotel.
 */
public class Hospedagem {
    
    private Acomodacao acomodacao;
    private LocalDate chegada;
    private LocalDate saida;
    private Hospede hospede;
    private Pagamento pagamento;
    private TipoPagamento tipoPagamento;
    private int codigo;
    private double multa;
    private boolean status;

    /**
     * Construtor da classe Hospedagem para inicialização dos atributos.
     *
     * @param acomodacao     A acomodação associada à hospedagem.
     * @param hospede        O hóspede associado à hospedagem.
     * @param chegada        A data de chegada do hóspede.
     * @param saida          A data de saída do hóspede.
     * @param tipoPagamento  O tipo de pagamento escolhido.
     * @param codigo         O código da hospedagem.
     * @param status         O status da hospedagem (ativo ou inativo).
     */
    public Hospedagem(Acomodacao acomodacao, Hospede hospede, LocalDate chegada, LocalDate saida, TipoPagamento tipoPagamento, int codigo, boolean status) {
        this.acomodacao = acomodacao;
        this.hospede = hospede;
        this.chegada = chegada;
        this.saida = saida;
        this.codigo = codigo;
        this.multa = 0;
        this.status = status;
        this.acomodacao.setOcupado(true);
        this.tipoPagamento = tipoPagamento;
        
        if (status) {

            LocalDate dataVencimento = null;
            switch (tipoPagamento) {
                case DINHEIRO:
                    dataVencimento = getChegada().plusDays(1);
                    break;
                case CHEQUE:
                    dataVencimento = getChegada().plusDays(10);
                    break;
                case CREDITO:
                    dataVencimento = getChegada().plusDays(30);
                    break;
                case FATURADO:
                    dataVencimento = getSaida().plusDays(30);
                    break;
            }
            this.pagamento = new Pagamento(tipoPagamento, dataVencimento);
        }
    }


    /**
     * Calcula o total a ser pago pelas diárias da hospedagem.
     *
     * @return O valor total das diárias, considerando a acomodação ocupada durante o período.
     */
    public double totalDiarias() {
        if (isStatus()) {
            return acomodacao.getDiaria() * (saida.getDayOfYear() - chegada.getDayOfYear());
        } else {
            return 0;
        }
    }

    
    /**
     * Gera um relatório da hospedagem, incluindo informações sobre o hóspede, acomodação e custos.
     *
     * @return Uma string formatada com detalhes da hospedagem.
     */
    public String relatorioHospedagem() {
        return "Nome: " + getHospede().getNome() + "\n"
                + "Código: " + getCodigo() + "\n"
                + "Descrição: " + getAcomodacao().getOpcao() + "\n"
                + "Total de diárias: " + (saida.getDayOfYear() - chegada.getDayOfYear()) + "\n"
                + "Preço da diária: " + getAcomodacao().getDiaria() + "\n"
                + "Total: " + totalDiarias() + "\n"
                + "Multa: " + getMulta() + "\n"
                + "Total a pagar: " + (totalDiarias() + getMulta()) + "\n";
    }


    /**
     * Transforma uma reserva em uma hospedagem, atualizando o status e criando um novo pagamento.
     */
    public void transformarResevaEmHospedagem() {

        this.status = true;

        LocalDate dataVencimento = null;
        
        switch (tipoPagamento) {
            case DINHEIRO:
                dataVencimento = getChegada().plusDays(1);
                break;
            case CHEQUE:
                dataVencimento = getChegada().plusDays(10);
                break;
            case CREDITO:
                dataVencimento = getChegada().plusDays(30);
                break;
            case FATURADO:
                dataVencimento = getSaida().plusDays(30);
                break;
        }

        if (dataVencimento != null) {
            this.pagamento = new Pagamento(tipoPagamento, dataVencimento);
        }
    }



    /**
     * Metodo que retorna uma string com a descricao da hospedagem
     * @return String com a descricao da hospedagem
     */
    public String getDescricaoHospede(){
        String str = "";

        str += "Acomodacao: " + getAcomodacao().getNumeroQuarto() + "\n";
        str += "Hospede: " + getHospede().getNome() + "\n";

        if (!getHospede().getAcompanhantes().isEmpty()) {
            str += "Acompanhantes: \n";
            for (Acompanhante acompanhante : getHospede().getAcompanhantes()) {
                str += acompanhante.getNome() + ", " + acompanhante.getIdade() + " anos\n";
            }
        }

        str += "Check-in: " + getChegada() + "\n";
        str += "Check-out: " + getSaida() + "\n";
        
        return str;
    }


    public String getRelatorioConsumo() {
        StringBuilder sb = new StringBuilder();

        sb.append("Acomodaçao: " + getAcomodacao().getNumeroQuarto() + "\n");
        sb.append("Hospede: " + getHospede().getNome() + "\n");
        sb.append("Total Consumo: " + getHospede().totalConsumo()  + "R$" + "\n");

        if(!getHospede().getListaConsumo().isEmpty()){

            sb.append("Consumo: \n");

            for (Consumo c : getHospede().getListaConsumo()) {
                sb.append(c.getTipo() + " - " + c.getDescricao() + " - " + c.getValor() + "R$" + "\n");
            }
        }

        return sb.toString();
    }

    public String getRelatorioTipoFaturado() {

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        sb.append("Acomodaçao: " + getAcomodacao().getNumeroQuarto() + "\n");
        sb.append("Hospede: " + getHospede().getNome() + "\n");
        sb.append("Identificação: " + getCodigo() + "\n");

        sb.append("Check-in: " + getChegada().format(formatter) + "\n");
        sb.append("Check-out: " + getSaida().format(formatter) + "\n");

        if(getPagamento().isStatus()) {
            sb.append("Total Geral: R$" + getPagamento().calcularTotal(this) + "\n");
            sb.append("Valor das parcelas: " + getPagamento().calcularTotal(this) / 30 + "\n");
            sb.append("Descontos (Multa/Juros): R$" + (getMulta() + getPagamento().calcularJuros()) + "\n");
            sb.append("Data de Vencimento: " + getPagamento().getDataVencimento().format(formatter) + "\n");
        }

        return sb.toString();
    }



    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public LocalDate getChegada() {
        return chegada;
    }

    public void setChegada(LocalDate chegada) {
        this.chegada = chegada;
    }

    public LocalDate getSaida() {
        return saida;
    }

    public void setSaida(LocalDate saida) {
        this.saida = saida;
    }

    public Hospede getHospede() {
        return hospede;
    }
    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
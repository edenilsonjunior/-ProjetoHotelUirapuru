package model.alojamento;

import java.time.LocalDate;

import model.pagamento.Pagamento;
import model.pagamento.TipoPagamento;
import model.pessoa.Hospede;

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
        if (status) {
            this.pagamento = new Pagamento(tipoPagamento);
            switch (tipoPagamento) {
                case DINHEIRO:
                    this.pagamento.setDataVencimento(getChegada().plusDays(1));
                    break;
                case CHEQUE:
                    this.pagamento.setDataVencimento(getChegada().plusDays(10));
                    break;
                case CREDITO:
                    this.pagamento.setDataVencimento(getChegada().plusDays(30));
                    break;
                case FATURADO:
                    this.pagamento.setDataVencimento(getSaida().plusDays(30));
                    break;
            }
        }
        this.acomodacao.setOcupado(true);
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
        this.pagamento = new Pagamento(this.tipoPagamento);
        switch (tipoPagamento) {
            case DINHEIRO:
                this.pagamento.setDataVencimento(getChegada().plusDays(1));
                break;
            case CHEQUE:
                this.pagamento.setDataVencimento(getChegada().plusDays(10));
                break;
            case CREDITO:
                this.pagamento.setDataVencimento(getChegada().plusDays(30));
                break;
            case FATURADO:
                this.pagamento.setDataVencimento(getSaida().plusDays(30));
                break;
        }
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

}
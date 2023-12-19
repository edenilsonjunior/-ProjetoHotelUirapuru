package model.pagamento;

import model.alojamento.Hospedagem;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Classe que representa um pagamento de hospedagem.
 */
public class Pagamento {

    private TipoPagamento opcao;
    private int numeroFatura;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double juros;
    private boolean status;


    /**
     * Construtor da classe Pagamento.
     *
     * @param opcao O tipo de pagamento escolhido.
     */
    public Pagamento(TipoPagamento opcao) {
        this.opcao = opcao;
        this.numeroFatura = 0;
        this.dataPagamento = null;
        this.juros = 10;
        this.status = false;
    }


    /**
     * Realiza o pagamento da hospedagem e gera uma mensagem com os detalhes da transação.
     *
     * @param hospedagem A hospedagem associada ao pagamento.
     * @return Uma mensagem com os detalhes da transação.
     */
    public String fazerPagamento(Hospedagem hospedagem) {

        String mensagem = "";

        if (isStatus()) {

            mensagem = "Nome: " + hospedagem.getHospede().getNome() + "\n";
            mensagem += "Pagamento realizado em: " + this.dataPagamento + "\n";

            return mensagem;
        }
        else if (this.dataPagamento.isAfter(this.dataVencimento)) {
            this.dataPagamento = LocalDate.now();
            this.dataVencimento = null;
            this.status = true;
        }
        else {
            this.dataPagamento = LocalDate.now();
            this.dataVencimento = null;
            this.status = true;
        }

        mensagem += getNumeroFatura() + "\n";
        mensagem += "Nome: " + hospedagem.getHospede().getNome() + "\n";
        mensagem += "Pagamento realizado com sucesso" + "\n";
        mensagem += "Juros: R$" + calcularJuros() + "\n";
        mensagem += "Multa: R$" + hospedagem.getMulta() + "\n";
        mensagem += "Total: R$" + calcularTotal(hospedagem) + "\n";
    
        return mensagem;    
    }


    /**
     * Calcula o valor dos juros com base na diferença entre a data de pagamento e a data de vencimento.
     *
     * @return O valor dos juros calculado.
     */
    public double calcularJuros() {
        if (this.dataPagamento.isAfter(this.dataVencimento)) {
            Duration duration = Duration.between(this.dataVencimento, this.dataPagamento);
            return duration.toDays() * (this.juros/100);
        }
        return 0;
    }


    /**
     * Calcula o valor total a ser pago, considerando as diárias, consumo, juros e multa.
     *
     * @param hospedagem A hospedagem para a qual o cálculo será realizado.
     * @return O valor total a ser pago.
     */
    public double calcularTotal(Hospedagem hospedagem) {
        return hospedagem.totalDiarias() + hospedagem.getHospede().totalConsumo() + calcularJuros() + hospedagem.getMulta();
    }

    
    public TipoPagamento getOpcao() {
        return opcao;
    }

    public void setOpcao(TipoPagamento opcao) {
        this.opcao = opcao;
    }

    public int getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(int numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package model.pagamento;

import model.alojamento.Hospedagem;
import model.consumo.Consumo;

import java.time.Duration;
import java.time.LocalDate;

public class Pagamento {
    private TipoPagamento opcao;
    private int numeroFatura;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double juros;
    private boolean status;

    public Pagamento(TipoPagamento opcao) {
        this.opcao = opcao;
        this.numeroFatura = 0;
        this.dataPagamento = null;
        this.juros = 10;
        this.status = false;
    }

    /**
     * Metodo que realiza o processamento do pagamento, aplicando juros se feito após a data de vencimento
     * e cobrando uma multa se a reserva for cancelada antes do check-in.
     * Retorna uma mensagem indicando o status do pagamento detalhadamente.
     *
     * @return String contendo os detalhes do pagamento.
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
     * Metodo que calcula o valor dos juros a serem pagos.
     *
     * @return double contendo o valor dos juros.
     */
    public double calcularJuros() {
        if (this.dataPagamento.isAfter(this.dataVencimento)) {
            Duration duration = Duration.between(this.dataVencimento, this.dataPagamento);
            return duration.toDays() * (this.juros/100);
        }
        return 0;
    }

    /**
     * Metodo que calcula o valor total a ser pago.
     *
     * @param hospedagem dados da classe hospedagem
     * @return double contendo o valor total.
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

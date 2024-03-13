package dto;

import java.time.LocalDate;

import model.alojamento.Hospedagem;
import model.pagamento.TipoPagamento;

public class HospedagemDTO implements IDTO<Hospedagem>{

    private AcomodacaoDTO acomodacao;
    private HospedeDTO hospede;
    private LocalDate chegada;
    private LocalDate saida;
    private TipoPagamento tipoPagamento;
    private int codigo;
    private boolean status;

    public HospedagemDTO(AcomodacaoDTO acomodacao, HospedeDTO hospede, LocalDate chegada, LocalDate saida, String tipoPagamento, int codigo, boolean status){

        this.acomodacao = acomodacao;
        this.hospede = hospede;
        this.chegada = chegada;
        this.saida = saida;
        this.tipoPagamento = TipoPagamento.valueOf(tipoPagamento);
        this.codigo = codigo;
        this.status = status;
    }

    public HospedagemDTO(Hospedagem hospedagem){
        this.acomodacao = new AcomodacaoDTO(hospedagem.getAcomodacao());
        this.hospede = new HospedeDTO(hospedagem.getHospede());
        this.chegada = hospedagem.getChegada();
        this.saida = hospedagem.getSaida();
        this.tipoPagamento = hospedagem.getTipoPagamento();
        this.codigo = hospedagem.getCodigo();
        this.status = hospedagem.isStatus();
    }

    @Override
    public Hospedagem toModelDomain() {
        return new Hospedagem(acomodacao.toModelDomain(), hospede.toModelDomain(), chegada, saida, tipoPagamento, codigo, status);
    }

    public AcomodacaoDTO getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(AcomodacaoDTO acomodacao) {
        this.acomodacao = acomodacao;
    }

    public HospedeDTO getHospede() {
        return hospede;
    }

    public void setHospede(HospedeDTO hospede) {
        this.hospede = hospede;
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

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

package model.pagamento;

/**
 * Enumerador de tipos de pagamento.
 * <p>
 * Este enum representa os diferentes tipos de pagamento que podem ser utilizados.
 */
public enum TipoPagamento {
    /**
     * Opção para pagamento em dinheiro.
     */
    DINHEIRO, 

    /**
     * Opção para pagamento com cheque.
     */
    CHEQUE, 

    /**
     * Opção para pagamento com cartão de crédito.
     */
    CREDITO, 

    /**
     * Opção para pagamento faturado.
     */
    FATURADO;
}
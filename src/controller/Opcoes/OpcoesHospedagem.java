package controller.opcoes;

/**
 * Enumerador de opções para modificar a hospedagem.
 * <p>
 * Este enum representa as possíveis ações que podem ser realizadas que envolvem hospedagens.
 */
public enum OpcoesHospedagem {
    /**
     * Opção para cadastrar uma nova hospedagem.
     */
    CADASTRAR_HOSPEDAGEM, 

    /**
     * Opção para remover uma hospedagem.
     */
    REMOVER_HOSPEDAGEM, 

    /**
     * Opção para registrar o check-in.
     */
    REGISTRAR_CHEKIN,

    /**
     * Opção para cadastrar uma nova acomodação.
     */
    CADASTRAR_ACOMODACAO, 

    /**
     * Opção para adicionar um acompanhante.
     */
    ADICIONAR_ACOMPANHANTE,

    /**
     * Opção para adicionar um consumo.
     */
    ADICIONAR_CONSUMO,

    /**
     * Opção para voltar ao menu anterior.
     */
    VOLTAR; 
}
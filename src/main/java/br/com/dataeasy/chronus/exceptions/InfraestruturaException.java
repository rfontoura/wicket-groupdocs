package br.com.dataeasy.chronus.exceptions;

/**
 * <b>Description:</b>Exceção relacionada a problemas de infraestrutura da aplicação, como indisponibilidade de algum recurso (que não seja de
 * negócio), como banco de dados, sistema de arquivos, rede ou inconsistência que impossibilite continuidade normal da operação.<br>
 * <b>Project:</b> chronus-business <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 16/06/2015
 */
public class InfraestruturaException extends RuntimeException {

    private static final long serialVersionUID = 1982715081914278403L;

    /**
     *
     */
    public InfraestruturaException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InfraestruturaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public InfraestruturaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public InfraestruturaException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InfraestruturaException(Throwable cause) {
        super(cause);
    }
}

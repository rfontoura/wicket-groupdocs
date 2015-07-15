package br.com.dataeasy.chronus.persistencia.hibernate;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> carflow-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius.carvalho
 * @version Revision: $ Date: 26/05/2015
 */
public enum HbnDialects {

    ORACLE10G("org.hibernate.dialect.Oracle10gDialect"),
    POSTGRESQL("org.hibernate.dialect.PostgreSQLDialect"),
    SQLSERVER2008("org.hibernate.dialect.SQLServer2008Dialect"),
    SQLSERVER2012("org.hibernate.dialect.SQLServer2012Dialect");

    private final String dialect;

    HbnDialects(String dialect) {
        this.dialect = dialect;
    }

    public String getDialectClass() {
        return dialect;
    }

}

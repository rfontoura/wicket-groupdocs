package br.com.dataeasy.chronus.persistencia.hibernate;

import br.com.dataeasy.chronus.conectores.jdbc.DBMetadata;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius.carvalho
 * @version Revision: $ Date: 26/05/2015
 */
public final class DataBaseDialectResolver {

    private DataBaseDialectResolver() {}

    public static String getDialect() {
        String dialect = null;

        DBMetadata dbMeta = DBMetadata.getInstance();

        if (dbMeta.getDatabaseMetaData() == null) {
            return "";
        }

        if (dbMeta.isPostgreSQL()) {
            dialect = HbnDialects.POSTGRESQL.getDialectClass();
        } else if (dbMeta.isOracle()) {
            dialect = HbnDialects.ORACLE10G.getDialectClass();
        } else if (dbMeta.isSQLServer2008()) {
            dialect = HbnDialects.SQLSERVER2008.getDialectClass();
        } else if (dbMeta.isSQLServer2012()) {
            dialect = HbnDialects.SQLSERVER2012.getDialectClass();
        }

        if (dialect == null) {
            throw new RuntimeException("Banco de Dados não identificado.");
        }

        return dialect;
    }
}

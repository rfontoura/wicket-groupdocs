package br.com.dataeasy.chronus.conectores.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NoInitialContextException;
import javax.sql.DataSource;

/**
 * <b>Title:</b> DBMetadata.java <br>
 * <b>Description:</b> <br>
 * <b>Package:</b> br.com.dataeasy.chronus.conectores.jdbc <br>
 * <b>Project:</b> carflow-business <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius.carvalho
 * @version Revision: $ Date: 26/05/2015
 */
public final class DBMetadata {

    private static final DBMetadata INSTANCE = new DBMetadata();

    private DBMetadata() {}

    public static DBMetadata getInstance() {
        return INSTANCE;
    }

    private DatabaseMetaData dbMetadata;

    public void load() {
        DataSource     ds   = null;
        InitialContext ic   = null;
        Connection     conn = null;
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:jboss/datasources/ChronusDS");
            conn = ds.getConnection();
            dbMetadata = conn.getMetaData();
        } catch (NoInitialContextException | NameNotFoundException e) {
            // espera-se que aconteça em ambiente jetty, sem initial context.
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ic != null) {
                    ic.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public DatabaseMetaData getDatabaseMetaData() {
        if (dbMetadata == null) {
            this.load();
        }
        return dbMetadata;
    }

    public String getDBName() {
        try {
            return getDatabaseMetaData().getDatabaseProductName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getDBVersion() {
        try {
            return getDatabaseMetaData().getDatabaseProductVersion();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPostgreSQL() {
        return getDBName().contains("Postgre");
    }

    public boolean isOracle() {
        return getDBName().contains("Oracle");
    }

    public boolean isSQLServer2008() {
        // TODO: testar
        return getDBName().contains("SQLServer") && getDBVersion().contains("2008");
    }

    public boolean isSQLServer2012() {
        // TODO: testar
        return getDBName().contains("SQLServer2012") && getDBVersion().contains("2012");
    }
}

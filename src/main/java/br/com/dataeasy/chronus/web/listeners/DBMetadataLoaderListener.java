package br.com.dataeasy.chronus.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.dataeasy.chronus.conectores.jdbc.DBMetadata;

/**
 * <b>Title:</b> DBMetadataLoaderListener.java <br>
 * <b>Description:</b> <br>
 * <b>Package:</b> br.com.dataeasy.chronus.web.listeners <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius.carvalho
 * @version Revision: $ Date: 27/05/2015
 */
public class DBMetadataLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBMetadata.getInstance().load();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}

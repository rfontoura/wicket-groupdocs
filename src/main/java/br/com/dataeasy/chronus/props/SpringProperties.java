package br.com.dataeasy.chronus.props;

import java.io.InputStream;
import java.util.Properties;

import br.com.dataeasy.chronus.persistencia.hibernate.DataBaseDialectResolver;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> chronus-business <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius
 * @version Revision: $ Date: 24/06/2015
 */
@SuppressWarnings("serial")
public class SpringProperties extends Properties {

    public SpringProperties() {}

    public void init() {
        try {
            InputStream gdProperties = SpringProperties.class.getClassLoader().getResourceAsStream("group-docs.properties");
            this.load(gdProperties);
            // Add the dialect property
            this.setProperty("dataSource.dialect", DataBaseDialectResolver.getDialect());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

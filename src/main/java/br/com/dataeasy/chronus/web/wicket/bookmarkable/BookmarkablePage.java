package br.com.dataeasy.chronus.web.wicket.bookmarkable;

import org.apache.wicket.Page;

/**
 * <b>Description:</b> Mantém as configurações de caminho e classe de uma página bookmarkable do sistema. <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 14/06/2015
 */
public class BookmarkablePage {

    private String                path;
    private Class<? extends Page> classPage;

    public BookmarkablePage(String path, Class<? extends Page> classPage) {
        super();
        this.path = path;
        this.classPage = classPage;
    }

    public String getPath() {
        return path;
    }

    public Class<? extends Page> getPageClass() {
        return classPage;
    }

}

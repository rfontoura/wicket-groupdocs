package br.com.dataeasy.chronus.web.wicket.bookmarkable;

import java.util.List;

import br.com.dataeasy.chronus.web.wicket.application.AbstractWebApplication;

import com.google.common.collect.Lists;

/**
 * <b>Description:</b> Classe responsável por adicionar todas as páginas bookmarkable do sistema. <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 14/06/2015
 */
public class ChronusBookmarkables {

    public static void mount(final AbstractWebApplication app) {
        List<? extends IBookmarkablePages> bookmarks = Lists.newArrayList(//
                new TestBookmarkablePages() //
                );
        for (IBookmarkablePages bookmark : bookmarks) {
            for (BookmarkablePage page : bookmark.getPages()) {
                app.mountPage(page.getPath(), page.getPageClass());
            }
        }
    }

}

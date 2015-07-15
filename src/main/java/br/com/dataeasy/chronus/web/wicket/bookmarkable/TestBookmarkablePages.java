package br.com.dataeasy.chronus.web.wicket.bookmarkable;

import java.util.List;

import br.com.dataeasy.chronus.web.wicket.components.visualizador.VisualizadorPage;

/**
 * <b>Description:</b>Define grupo de páginas de testes.<br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 17/06/2015
 */
public class TestBookmarkablePages extends AbstractBookmarkablePages {

    @Override
    protected void carregarPaginas(List<BookmarkablePage> lista) {
        lista.add(new BookmarkablePage("visualizador/teste", VisualizadorPage.class));
    }

}

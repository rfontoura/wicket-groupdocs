package br.com.dataeasy.chronus.web.wicket.bookmarkable;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * <b>Description:</b> Interface para obtenção de uma lista de páginas bookmarkable de determinada área funcional do sistema. <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 14/06/2015
 */
public abstract class AbstractBookmarkablePages implements IBookmarkablePages {

    @Override
    public List<BookmarkablePage> getPages() {
        List<BookmarkablePage> lista = Lists.newArrayList();
        carregarPaginas(lista);
        return lista;
    }

    protected abstract void carregarPaginas(List<BookmarkablePage> lista);

}

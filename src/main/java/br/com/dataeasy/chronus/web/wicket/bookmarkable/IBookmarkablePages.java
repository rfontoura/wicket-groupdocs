package br.com.dataeasy.chronus.web.wicket.bookmarkable;

import java.util.List;

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
public interface IBookmarkablePages {

    List<BookmarkablePage> getPages();

}

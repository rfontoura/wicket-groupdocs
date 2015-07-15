package br.com.dataeasy.chronus.web.wicket.pages;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * <b>Description:</b> Página de login do sistema <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 12/06/2015
 */
@SuppressWarnings("serial")
public class LoginPage extends WebPage {

    public LoginPage(PageParameters parameters) {
        add(new LoginForm("loginForm"));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        HeadRenderer renderer = new HeadRenderer(response);
        // css
        renderer.cssHeaderItemForUrl("resources/css/bootstrap.min.css");
        renderer.cssHeaderItemForUrl("resources/css/fonts.googleapis.css");
        renderer.cssHeaderItemForUrl("resources/css/chronus.css");
        renderer.cssHeaderItemForUrl("resources/css/font-awesome.min.css");
        renderer.cssHeaderItemForUrl("resources/css/ionicons.min.css");
        // jquery
        renderer.javascriptJQuery();
        // js
        renderer.javascriptHeaderItemForUrl("resources/js/bootstrap.min.js");
        super.renderHead(response);
    }

}

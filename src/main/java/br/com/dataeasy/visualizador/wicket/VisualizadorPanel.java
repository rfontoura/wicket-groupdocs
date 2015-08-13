package br.com.dataeasy.visualizador.wicket;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.dataeasy.chronus.web.wicket.pages.HeadRenderer;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 10/07/2015
 */
@SuppressWarnings("serial")
public class VisualizadorPanel extends Panel {

    private String getCaminhoCss(String nomeArquivo) {
        return "resources/css/groupdocs/" + nomeArquivo;
    }

    private String getCaminhoJs(String nomeArquivo) {
        return "resources/js/groupdocs/" + nomeArquivo;
    }

    public VisualizadorPanel(String id) {
        super(id, new Model<>(null)); // TODO: verificar necessidade de passar valor
    }

    @Override
    public void renderHead(IHeaderResponse headerResponse) {
        super.renderHead(headerResponse);

        HeadRenderer renderer = new HeadRenderer(headerResponse);
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("jquery-ui-1.10.3.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("knockout-3.0.0.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("turn.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("modernizr.2.6.2.Transform2d.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("visualizador-groupdocs.js"));
        headerResponse.render(JavaScriptHeaderItem.forScript("configurarModernizrCssTransformers();", "parte1"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("installableViewer.min.js"));
        headerResponse.render(JavaScriptHeaderItem.forScript("configurarApplicationPath();", "parte2"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("GroupdocsViewer.all.min.js"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("bootstrap-groupdocs.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("GroupdocsViewer.all.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("jquery-ui-1.10.3.dialog.min.css"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("jquery.tinyscrollbar.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("GroupdocsAnnotation.all.min.js"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("Annotation.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("Annotation.Toolbox.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("fixes.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("visualizador.css"));
        headerResponse
                .render(StringHeaderItem
                        .forString("<!--[if IE]><style type=\"text/css\"> input[type=\"text\"].input_search { padding-right: 30px; width: 65px; } </style> <![endif]-->"));
        headerResponse.render(StringHeaderItem
                .forString("<!--[if IE 9]><style type=\"text/css\"> pan.input_search_clear { left: 140px; } </style> <![endif]-->"));
        headerResponse.render(JavaScriptHeaderItem.forScript("configurarContainer();", "parte3"));
    }
}

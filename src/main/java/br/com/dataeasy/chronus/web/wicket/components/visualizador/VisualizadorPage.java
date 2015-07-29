package br.com.dataeasy.chronus.web.wicket.components.visualizador;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.dataeasy.chronus.web.wicket.pages.HeadRenderer;
import br.com.dataeasy.visualizador.config.VisualizadorConfig;
import br.com.dataeasy.visualizador.wicket.VisualizadorPanel;

/**
 * <b>Description:</b>Tela com exemplo de utilização do Visualizador de Documentos<br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 15/06/2015
 */
public class VisualizadorPage extends WebPage {

    private static final long  serialVersionUID = 1L;

    @Override
    public void renderHead(final IHeaderResponse response) {
        HeadRenderer renderer = new HeadRenderer(response);
        renderer.javascriptJQuery();
    }

    @SpringBean
    private VisualizadorConfig config;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        VisualizadorPanel visualizadorPanel = new VisualizadorPanel("visualizadorPanel");
        visualizadorPanel.setRenderBodyOnly(true);
        add(visualizadorPanel);
    }
}

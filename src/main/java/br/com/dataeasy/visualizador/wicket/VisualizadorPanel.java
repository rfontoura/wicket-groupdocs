package br.com.dataeasy.visualizador.wicket;

import java.io.File;

import org.apache.commons.lang3.RandomUtils;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.dataeasy.chronus.web.wicket.pages.HeadRenderer;
import br.com.dataeasy.visualizador.config.VisualizadorConfig;
import br.com.dataeasy.visualizador.model.GroupDocsInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupdocs.annotation.handler.AnnotationHandler;

@SuppressWarnings("serial")
public class VisualizadorPanel extends Panel {

    @SpringBean
    private VisualizadorConfig visualizadorConfig;

    private String getCaminhoCss(String nomeArquivo) {
        return "resources/css/groupdocs/" + nomeArquivo;
    }

    private String getCaminhoJs(String nomeArquivo) {
        return "resources/js/groupdocs/" + nomeArquivo;
    }

    public VisualizadorPanel(String id) {
        super(id, new Model<>(null));
    }

    @Override
    public void renderHead(IHeaderResponse headerResponse) {
        super.renderHead(headerResponse);

        HeadRenderer renderer = new HeadRenderer(headerResponse);
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("jquery-ui-1.10.3.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("knockout-3.0.0.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("turn.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("modernizr.2.6.2.Transform2d.min.js"));
        headerResponse.render(JavaScriptHeaderItem.forScript(getScriptInicialObjetoVisualizador(), "parte0"));
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

    /**
     * Cria Javascript adicionando à página informações sobre imagem a ser renderizada e usuário.
     */
    private String getScriptInicialObjetoVisualizador() {
        String caminhoDir = WebApplication.get().getServletContext().getRealPath("/resources/docs");
        File[] files = new File(caminhoDir).listFiles();
        String caminhoArquivo = files[RandomUtils.nextInt(0, files.length)].getAbsolutePath();

        GroupDocsInfo info;

        try {
            String userName = AnnotationHandler.ANONYMOUS_USERNAME;
            String userId = visualizadorConfig.getUserId(userName);
            String fileId = visualizadorConfig.getFileId(caminhoArquivo);
            info = new GroupDocsInfo(fileId, userName, userId);
        } catch (Exception e) {
            info = new GroupDocsInfo();
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        return "window.infoVisualizador = " + gson.toJson(info) + ";";
    }
}

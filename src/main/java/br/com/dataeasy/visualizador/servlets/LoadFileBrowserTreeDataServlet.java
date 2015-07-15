package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.excecoes.VisualizadorInfraException;
import br.com.dataeasy.visualizador.media.MediaType;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author imy
 */
@WebServlet(name = "LoadFileBrowserTreeDataServlet", urlPatterns = { "/document-viewer/LoadFileBrowserTreeDataHandler" })
public class LoadFileBrowserTreeDataServlet extends AnnotationServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addCORSHeaders(request, response);
        try {
             writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.loadFileBrowserTreeDataHandler(request, response));
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao carregar navegador de arquivos.", e);
        }
    }
}

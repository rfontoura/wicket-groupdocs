package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.excecoes.VisualizadorInfraException;
import br.com.dataeasy.visualizador.media.MediaType;

/**
 * @author imy
 */
@WebServlet(name = "ViewDocumentServlet", urlPatterns = { "/document-viewer/ViewDocumentHandler" })
public class ViewDocumentServlet extends AnnotationServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addCORSHeaders(request, response);
        try {
             writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.viewDocumentHandler(request, response));
        } catch (Exception e) {
            throw new VisualizadorInfraException(e);
        }
    }
}

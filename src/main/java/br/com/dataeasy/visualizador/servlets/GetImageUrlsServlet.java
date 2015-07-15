package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.media.MediaType;

import com.groupdocs.annotation.common.Utils;
import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author imy
 */
@WebServlet(name = "GetImageUrlsServlet", urlPatterns = { "/document-viewer/GetImageUrlsHandler" })
public class GetImageUrlsServlet extends AnnotationServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addCORSHeaders(request, response);
        try {
            writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.getImageUrlsHandler(request, response));
        } catch (AnnotationException e) {
            Utils.err(AnnotationServlet.class, e);

        }
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}

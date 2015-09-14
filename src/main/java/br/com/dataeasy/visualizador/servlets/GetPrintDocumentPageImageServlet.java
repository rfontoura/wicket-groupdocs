package br.com.dataeasy.visualizador.servlets;

import static com.groupdocs.annotation.common.Utils.toJson;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.media.MediaType;

import com.groupdocs.annotation.domain.response.StatusResult;
import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author imy
 */
@WebServlet(name = "GetPrintDocumentPageImageServlet", urlPatterns = { "/document-annotation/GetPrintDocumentPageImageHandler/*" })
public class GetPrintDocumentPageImageServlet extends AnnotationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addCORSHeaders(request, response);
            int pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
            String path = request.getParameter("path");
            Boolean printAnnotations = Boolean.valueOf(request.getParameter("printAnnotations"));
            Object o = annotationHandler.getPrintDocumentPageImageHandler(path, pageIndex, printAnnotations, response);
            if (o instanceof InputStream) {
                writeOutput((InputStream) o, response);
            }
        } catch (AnnotationException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, MESSAGE_HANDLER_THROWS, e.getMessage());
            writeOutput(MediaType.APPLICATION_JSON, response, toJson(new StatusResult(false, e.getMessage())));
        }
    }
}

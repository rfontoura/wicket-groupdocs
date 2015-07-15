package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import br.com.dataeasy.visualizador.config.VisualizadorConfig;
import br.com.dataeasy.visualizador.media.MediaType;

import com.groupdocs.annotation.common.Utils;
import com.groupdocs.annotation.handler.AnnotationHandler;

/**
 * @author imy
 */
public abstract class AnnotationServlet extends HttpServlet {
    private static final long     serialVersionUID       = 1L;
    protected static final String MESSAGE_HANDLER_THROWS = "Handler throws exception: {0}";
    protected static final String DEFAULT_ENCODING       = "UTF-8";
    protected VisualizadorConfig  visualizadorConfig;
    protected AnnotationHandler   annotationHandler;

    public AnnotationServlet() {
        super();
    }

    /**
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        visualizadorConfig = ContextLoader.getCurrentWebApplicationContext().getBean(VisualizadorConfig.class);
        this.annotationHandler = visualizadorConfig.getAnnotationHandler();
    }

    protected void writeOutput(MediaType contentType, HttpServletResponse response, Object object) throws IOException {
        String json = (String) object;
        if (contentType != null && !contentType.toString().isEmpty()) {
            response.setContentType(contentType.toString());
        }
        response.getOutputStream().write(json.getBytes(DEFAULT_ENCODING));
    }

    protected void writeOutput(InputStream inputStream, HttpServletResponse response) throws IOException {
        if (inputStream == null) {
            Logger.getLogger(this.getClass()).error("inputStream is null");
        }
        IOUtils.copy(inputStream, response.getOutputStream());
        Utils.closeStreams(inputStream, response.getOutputStream());
    }

    protected void addCORSHeaders(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("origin");
        if (origin == null || "".equals(origin)) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type, Accept");
    }
}

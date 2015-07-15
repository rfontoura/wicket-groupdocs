package br.com.dataeasy.visualizador.servlets;

import java.io.File;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.config.ApplicationConfig;

import com.groupdocs.annotation.exception.AnnotationException;
import com.groupdocs.annotation.handler.AnnotationHandler;
import com.groupdocs.viewer.domain.path.EncodedPath;

@SuppressWarnings("serial")
@WebServlet(name = "GetFilePathServlet", urlPatterns = { "/GetFilePathHandler" })
public class GetFilePathServlet extends AnnotationServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeArquivo = request.getParameter("nomeArquivo");
        String caminhoArquivo = resolverCaminhoArquivo(nomeArquivo);

        // Por enquanto, usuário anônimo. XXX: posteriormente, configurar autorização de acesso Recupera ID do usuário.
        String nomeUsuario = AnnotationHandler.ANONYMOUS_USERNAME;
        EncodedPath path = new EncodedPath(caminhoArquivo, annotationHandler.getConfiguration());
        String idUsuario = getIdUsuario(nomeUsuario);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("idArquivo", path.getPath());
        builder.add("nomeUsuario", nomeUsuario);
        builder.add("idUsuario", idUsuario);
        JsonObject info = builder.build();

        response.setContentType("text/plain");
        response.getWriter().write(info.toString());
    }

    /**
     * Recupera ID (userGuid) do usuário codificado pelo GroupDocs.
     *
     * @param nomeUsuario o nome do usuário.
     */
    private String getIdUsuario(String nomeUsuario) {
        try {
            return annotationHandler.getUserGuid(nomeUsuario);
        } catch (AnnotationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Resolve caminho completo do arquivo através de verificação em armazém de binário etc.
     *
     * @param nomeArquivo o nome simples do arquivo
     * @return o caminho completo do arquivo
     */
    private String resolverCaminhoArquivo(String nomeArquivo) {
        ApplicationConfig applicationConfig = visualizadorConfig.getApplicationConfig();
        return new File(applicationConfig.getBasePath() + nomeArquivo).getAbsolutePath();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }
}

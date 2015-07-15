package br.com.dataeasy.visualizador.config;

import java.io.File;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import br.com.dataeasy.chronus.constantes.Constantes;
import br.com.dataeasy.visualizador.excecoes.VisualizadorInfraException;

import com.groupdocs.annotation.data.connector.IConnector;
import com.groupdocs.annotation.exception.AnnotationException;
import com.groupdocs.annotation.handler.AnnotationHandler;
import com.groupdocs.viewer.config.ServiceConfiguration;
import com.groupdocs.viewer.domain.path.EncodedPath;
import com.groupdocs.viewer.domain.path.GroupDocsPath;

/**
 * <b>Description:</b>Configurações centralizadas e inicializadas do Visualizador. Fornece acesso às configurações globais do componente.<br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 16/06/2015
 */
@Component
public class VisualizadorConfig {

    @Resource
    private ApplicationConfig   applicationConfig;
    protected AnnotationHandler annotationHandler;

    public AnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }

    public ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }

    @PostConstruct
    public void init() {
        try {
            this.annotationHandler = criarAnnotationHandler();
            applicationConfig.setWidgetId("container-visualizador");

        } catch (Exception e) {
            throw new VisualizadorInfraException("Problema ao inicializar infra do Visualizador.", e);
        }
    }

    /**
     * @return AnnotationHandler
     */
    private AnnotationHandler criarAnnotationHandler() {
        if (annotationHandler == null) {
            TimeZone.setDefault(Constantes.TIMEZONE_PADRAO);
            ServiceConfiguration serviceConfiguration = new ServiceConfiguration(applicationConfig);
            IConnector connector = null;
            try {
                // Necessário para formatação de Datas
                Locale.setDefault(Constantes.LOCALE_PADRAO);

                annotationHandler = new AnnotationHandler(serviceConfiguration, connector);
            } catch (Exception e) {
                String msg = "Problema ao criar AnnotationHandler. O ApplicationConfig do GroupDocs foi carregado corretamente?";
                throw new VisualizadorInfraException(msg, e);
            }
        }
        return annotationHandler;
    }

    /**
     * Retorna as tags <script> e <link> para inicialização do GroupDocs.
     *
     * @param request o HttpServletRequest
     * @return String com tags <script> e <link>
     */
    public String getHeader(HttpServletRequest request) {
        try {
            return annotationHandler.getHeader(applicationConfig.getApplicationPath(), request);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException(e);
        }
    }

    /**
     * Retorna o script que faz chamada Javascript à inicialização e utilização do GroupDocs.
     *
     * @param caminhoArquivo o caminho completo do arquivo a ser aberto.
     * @return o script a ser renderizado na página.
     */
    public String getAnnotationScripts(String caminhoArquivo) {
        String userName = AnnotationHandler.ANONYMOUS_USERNAME;
        File arquivo;
        // File arquivoDoSistema = new File(applicationConfig.getBasePath() + nomeArquivo);
        if (caminhoArquivo == null) {
            arquivo = new File(applicationConfig.getBasePath() + applicationConfig.getDefaultFileName());
        } else {
            arquivo = new File(caminhoArquivo);
        }
        GroupDocsPath path = new EncodedPath(arquivo.getAbsolutePath(), annotationHandler.getConfiguration());
        String initialPath = path.getPath();

        try {
            String userGuid = annotationHandler.getUserGuid(userName);
            return annotationHandler.getAnnotationScript(initialPath, userName, userGuid);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException(e);
        }
    }
}

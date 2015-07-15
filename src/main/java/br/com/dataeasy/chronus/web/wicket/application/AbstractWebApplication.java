package br.com.dataeasy.chronus.web.wicket.application;

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;

import br.com.dataeasy.chronus.constantes.Constantes;
import br.com.dataeasy.chronus.web.wicket.pages.AbstractWebPage;

/**
 * <b>Title:</b> AbstractWebApplication.java <br>
 * <b>Description:</b>
 * <ul>
 * <li>Configura a codificação padrão para leitura de templates para "UTF-8". O comportamento padrão do Wicket é buscar a codificação do sistema
 * operacional, o que causa problemas nos em sistemas cujo ambiente de desenvolvimento é muito diferente dos ambientes de publicação. Leia mais sobre
 * o padrão de codificação abaixo.
 * <li>Configura a estratégia de autorização para buscar roles a partir do servlet.
 * <li>Automaticamente redireciona o usuário a página padrão de login de acesso não autorizado caso ocorrer problema de autorização.
 * <li>Configura páginas padrão de erro de runtime e de sessão expirada no caso de configuração DEPLOYMENT.
 * </ul>
 * <br>
 * <b>Package:</b> br.com.dataeasy.chronus.web.wicket.application<br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 03/06/2015
 */
public abstract class AbstractWebApplication extends AuthenticatedWebApplication {

    private static final String CHAVE_TITULO_SISTEMA = "sistema.titulo";

    public AbstractWebApplication() {
        super();
        getComponentOnAfterRenderListeners().add(new TamanhoPaginaComponentOnAfterRenderListener());
        getRequestCycleListeners().add(new PageRequestHandlerTracker());
        getRequestCycleListeners().add(new RuntimeExceptionTracker());
    }

    /**
     * Atenção! Sempre que sobrescrever o método init, execute super.init().
     *
     * @see org.apache.wicket.Application#init()
     */
    @Override
    protected void init() {
        super.init();

        // Encoding padrão
        getMarkupSettings().setDefaultMarkupEncoding(Constantes.UTF_8);
    }

    /**
     * Obtém o título do sistema para apresentar no cabeçalho das páginas. Por default, pega o que estiver na chave "sistema.titulo". Nos aplicativos
     * sem internacionalização, pode ser mais simples sobrescrever o método.
     *
     * @param page a página que usará o título
     */
    public String getTituloSistema(AbstractWebPage page) {
        return page.getString(CHAVE_TITULO_SISTEMA);
    }

}

package br.com.dataeasy.chronus.web.wicket.application;

import static br.com.dataeasy.chronus.constantes.Constantes.UTF_8;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.head.PriorityFirstComparator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.joda.time.DateTimeZone;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import br.com.dataeasy.chronus.constantes.Constantes;
import br.com.dataeasy.chronus.web.wicket.bookmarkable.ChronusBookmarkables;
import br.com.dataeasy.chronus.web.wicket.pages.HomePage;
import br.com.dataeasy.chronus.web.wicket.pages.LoginPage;
import br.com.dataeasy.chronus.web.wicket.session.SecureWebSession;

/**
 * <b>Description:</b>Classe principal da aplicação web, que indica a página principal.<br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 08/06/2015
 */
public class ChronusApplication extends AbstractWebApplication implements ApplicationContextAware {

    private ApplicationContext context;

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        DateTimeZone.setDefault(DateTimeZone.forID("America/Sao_Paulo"));
        getRequestCycleSettings().setResponseRequestEncoding(UTF_8);
        getMarkupSettings().setDefaultAfterDisabledLink(null);
        getMarkupSettings().setDefaultBeforeDisabledLink(null);
        getMarkupSettings().setStripWicketTags(true);
        getResourceSettings().setHeaderItemComparator(new PriorityFirstComparator(true));
        getDebugSettings().setOutputComponentPath(true);
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, context, true));
        ChronusBookmarkables.mount(this);
    }

    /**
     * Sobrescrevendo criação de Session para definir Locale padrão.
     *
     * @param request o Request do Wicket
     * @param response o Response do Wicket
     * @return o Session do Wicket
     */
    @Override
    public Session newSession(Request request, Response response) {
        Session session = super.newSession(request, response);
        session.setLocale(Constantes.LOCALE_PADRAO);
        return session;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return SecureWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}

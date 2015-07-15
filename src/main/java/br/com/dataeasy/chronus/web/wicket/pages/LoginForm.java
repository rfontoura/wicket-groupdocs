package br.com.dataeasy.chronus.web.wicket.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
 * <b>Description:</b> Componente correspondente ao form de login da aplicação. <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 12/06/2015
 */
@SuppressWarnings("serial")
class LoginForm extends Form<Void> {

    private static final String SPRING_SECURITY_SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";

    private static Logger       LOGGER                        = Logger.getLogger(LoginPage.class);

    private String              identificacao;
    private String              senha;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public LoginForm(String id) {
        super(id);
        setModel(new CompoundPropertyModel(this));
        add(new RequiredTextField<String>("identificacao"));
        add(new PasswordTextField("senha"));
        add(new FeedbackPanel("feedback"));
    }

    @Override
    protected void onSubmit() {
        HttpServletRequest servletRequest = (HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest();
        String urlOriginal = getUrlOriginal(servletRequest.getSession());
        AuthenticatedWebSession session = AuthenticatedWebSession.get();
        if (session.signIn(identificacao, senha)) {
            if (urlOriginal != null) {
                LOGGER.info(String.format("Redirecionando para %s", urlOriginal));
                throw new RedirectToUrlException(urlOriginal);
            } else {
                LOGGER.info("Redirecionando para a página inicial");
                setResponsePage(getApplication().getHomePage());
            }
        } else {
            error("Erro no Login devido a credenciais inválidas.");
        }
    }

    /**
     * Retorna a URL que o usuário acessou antes de ser redirecionado para a página de login. Esta URL foi armazenada na sessão pelo spring-security.
     *
     * @return a URL original acessada pelo usuário ou null se nenhuma URL foi armazenada na sessão.
     */
    private String getUrlOriginal(HttpSession session) {
        SavedRequest savedRequest = (SavedRequest) session.getAttribute(SPRING_SECURITY_SAVED_REQUEST);
        if (savedRequest != null) {
            return savedRequest.getRedirectUrl();
        } else {
            return null;
        }
    }

}

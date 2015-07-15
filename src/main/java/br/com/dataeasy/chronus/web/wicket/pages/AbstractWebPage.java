package br.com.dataeasy.chronus.web.wicket.pages;

import java.io.Serializable;
import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.dataeasy.chronus.web.wicket.application.AbstractWebApplication;

import com.google.common.collect.Lists;

@SuppressWarnings("serial")
public abstract class AbstractWebPage extends WebPage implements IHeaderContributor {

    private List<AbstractAjaxBehavior> callOnceOnDomReady       = Lists.newArrayList();

    protected static final String      CHAVE_TITULO             = "page.titulo";

    /**
     * Wicket-ID do label em &lt;title&gt;.
     */
    protected static final String      WID_LABEL_TITLE          = "labelTitle";

    /**
     * Wicket-ID do label do t�tulo da página.
     */
    protected static final String      WID_LABEL_TITULO_PAGINA  = "labelTitulo";

    /**
     * Wicket-ID do label do título do sistema.
     */
    protected static final String      WID_LABEL_TITULO_SISTEMA = "labelNomeSistema";

    /**
     * Wicket-ID do menu.
     */
    protected static final String      WID_MENU                 = "menu";

    /**
     * Wicket-ID do link do logo para a pagina inicial.
     */
    protected static final String      WID_LOGO                 = "logo";

    /**
     * Construtor padrão.
     */
    public AbstractWebPage() {
        super();
    }

    /**
     * Constructor com model para página.
     *
     * @param model See Component
     * @see Component#Component(String, IModel)
     */
    public AbstractWebPage(IModel<?> model) {
        super(model);
    }

    /**
     * Constructor which receives wrapped query string parameters for a request. Having this constructor public means that your page is 'bookmarkable'
     * and hence can be called/ created from anywhere. For bookmarkable pages (as opposed to when you construct page instances yourself, this
     * constructor will be used in preference to a no-arg constructor, if both exist. Note that nothing is done with the page parameters argument.
     * This constructor is provided so that tools such as IDEs will include it their list of suggested constructors for derived classes.
     *
     * Please call this constructor (or the one with the pagemap) if you want to remember the pageparameters {@link #getPageParameters()}. So that
     * they are reused for stateless links.
     *
     * @param parameters Wrapped query string parameters.
     */
    public AbstractWebPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        addComponentesComuns();
    }

    private AbstractWebApplication getWebApplication() {
        return (AbstractWebApplication) Application.get();
    }

    public String getTitulo() {
        try {
            return getString(CHAVE_TITULO);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * O título da página que será incluído entre as tags &lt;title&gt; da página. Por default, usa o mesmo valor retornado por {@link #getTitulo()}.
     */
    public String getTituloTitle() {
        return getTitulo();
    }

    /**
     * Retorna o nome do sistema para mostrar no cabeçalho (acima do menu). Delega a execução para
     * {@link AbstractWebApplication#getTituloSistema(AbstractWebPage)}.
     * <p>
     * Não deve ser sobrescrito diretamente. Sobrescreva {@link AbstractWebApplication#getTituloSistema(AbstractWebPage)} ao invés disso.
     * </p>
     *
     * @return nome do sistema
     */
    public final String getTituloSistema() {
        return getWebApplication().getTituloSistema(this);
    }

    /**
     * Retorna o login do usuário autenticado.
     *
     * @return O login do usuário autenticado.
     */
    public String getLoginUsuario() {
        Request request = RequestCycle.get().getRequest();
        HttpServletRequest servletRequest = (HttpServletRequest) request.getContainerRequest();
        return servletRequest.getRemoteUser();
    }

    /**
     * Obtém do arquivo de propriedades a mensagem que possui a chave informada.
     *
     * @param chave A chave da mensagem a ser obtida
     * @param objeto O objeto que possui os valores a serem substituídos na mensagem.
     * @param pagina A página onde será exibida a mensagem.
     * @param parametros Os parâmetros a serem substituídos nas páginas.
     * @return A mensagem que possui a chave informada
     */
    protected String obterMensagem(String chave, Serializable objeto, WebPage pagina, Object[] parametros) {
        Model<Serializable> model = null;
        if (objeto != null) {
            model = new Model<Serializable>(objeto);
        }
        return new StringResourceModel(chave, pagina, model, parametros).getString();
    }

    /**
     * Adiciona os componentes comuns a tela.
     */
    /**
     *
     * void
     */
    protected void addComponentesBase() {

        //
    }

    /**
     * Adiciona os componentes básicos de todas as telas, assim como os componentes específicos da tela. Para adicionar novos elementos a uma página,
     * basta implementar o método {@link #addComponentesPagina()}. Caso seja necessário um conjunto totalmente diferente de componentes, pode-se fazer
     * sobrescrita do {@link #addComponentesBase()}.
     */
    protected void addComponentesComuns() {
        addComponentesBase();
        addComponentesPagina();
    }

    protected abstract void addComponentesPagina();

    @Override
    public void renderHead(final IHeaderResponse response) {
        HeadRenderer renderer = new HeadRenderer(response);
        // css
        renderer.cssHeaderItemForUrl("resources/css/bootstrap.min.css");
        renderer.cssHeaderItemForUrl("resources/css/fonts.googleapis.css");
        renderer.cssHeaderItemForUrl("resources/css/chronus.css");
        renderer.cssHeaderItemForUrl("resources/css/font-awesome.min.css");
        renderer.cssHeaderItemForUrl("resources/css/ionicons.min.css");
        // jquery
        renderer.javascriptJQuery();
        // js
        renderer.javascriptHeaderItemForUrl("resources/js/bootstrap.min.js");
        super.renderHead(response);
        if (CollectionUtils.isNotEmpty(callOnceOnDomReady)) {
            for (final AbstractAjaxBehavior behavior : callOnceOnDomReady) {
                final String script = ";Wicket.Ajax.get({ u: '" + behavior.getCallbackUrl() + "' });";
                renderer.javascriptOnDomReadyHeaderItemForScript(script);
            }
            callOnceOnDomReady.clear();
        }
    }
}

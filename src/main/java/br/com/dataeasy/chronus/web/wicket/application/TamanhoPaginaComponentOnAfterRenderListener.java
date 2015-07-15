package br.com.dataeasy.chronus.web.wicket.application;

import java.text.DecimalFormat;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.application.IComponentOnAfterRenderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>Description:</b> Realiza o monitoramento do tamanho das páginas em tempo de execução. <br>
 * <b>Project:</b> chronus-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 08/06/2015
 */
public class TamanhoPaginaComponentOnAfterRenderListener implements IComponentOnAfterRenderListener {

    private static final Logger LOG = LoggerFactory.getLogger(TamanhoPaginaComponentOnAfterRenderListener.class);

    private static final double BYTES_POR_KBYTE = 1024.0;
    private static final int TAMANHO_LIMITE_PAGINA_PASSIVEL_EXCECAO = 102400;
    private static final int TAMANHO_LIMITE_PAGINA_PASSIVEL_ALERTA = 51200;

    @Override
    public void onAfterRender(Component component) {
        if (component instanceof Page) {
            verificarTamanhoPagina((Page) component);
        }
    }

    private void verificarTamanhoPagina(Page page) {
        Application application = Application.get();
        if (application.usesDevelopmentConfig()) {
            long emBytes = page.getSizeInBytes();
            if (emBytes >= 0) {
                verificarTamanhoExcecao(emBytes);
                logTamanhoPaginaDebug(emBytes);
            } else {
                LOG.debug("Tamanho da página: impossível obter - talvez algum elemento não possa ser serializado");
            }
        }
    }

    private void verificarTamanhoExcecao(long emBytes) {
        if (emBytes > TAMANHO_LIMITE_PAGINA_PASSIVEL_EXCECAO) {
            throw new RuntimeException(getMensagemExcecaoTamanhoPagina(TAMANHO_LIMITE_PAGINA_PASSIVEL_EXCECAO,
                    emBytes));
        } else if (emBytes > TAMANHO_LIMITE_PAGINA_PASSIVEL_ALERTA) {
            logTamanhoPaginaWarn(emBytes);
        }
    }

    /**
     * Registra no log o tamanho da página em bytes caso o logger estiver com o nível de debug ligado.
     *
     * @param tamanhoEmBytes tamanho da página em bytes
     */
    protected void logTamanhoPaginaDebug(long tamanhoEmBytes) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("Tamanho da página: " + getStringTamanhoPagina(tamanhoEmBytes));
        }
    }

    /**
     * Registra no log o tamanho da página em bytes caso o logger estiver com o nível de warn ligado.
     *
     * @param tamanhoEmBytes tamanho da página em bytes
     */
    protected void logTamanhoPaginaWarn(long emBytes) {
        if (LOG.isWarnEnabled()) {
            LOG.warn(getMensagemExcecaoTamanhoPagina(TAMANHO_LIMITE_PAGINA_PASSIVEL_ALERTA, emBytes));
        }
    }

    /**
     * Retorna a mensagem exibida quando uma página tem tamanho superior ao definido em {@link AbstractWebApplication#getTamanhoPaginaWarning()} ou em
     * {@link AbstractWebApplication#getTamanhoPaginaExcecao()}.
     *
     * @param tamanhoPaginaEmBytes - tamanho da página em bytes
     */
    protected String getMensagemExcecaoTamanhoPagina(long tamanhoMaximoEmBytes, long tamanhoPaginaEmBytes) {
        return "O tamanho da sua página é superior a " + tamanhoMaximoEmBytes + " bytes. Tamanho da página: "
                + getStringTamanhoPagina(tamanhoPaginaEmBytes);
    }

    private String getStringTamanhoPagina(long tamanhoEmBytes) {
        double emKb = tamanhoEmBytes / BYTES_POR_KBYTE;
        DecimalFormat format = new DecimalFormat("#,##0.0");
        return tamanhoEmBytes + " bytes (" + format.format(emKb) + "KB)";
    }

}
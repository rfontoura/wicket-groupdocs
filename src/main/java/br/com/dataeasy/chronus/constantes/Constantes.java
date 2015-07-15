package br.com.dataeasy.chronus.constantes;

import java.util.Locale;
import java.util.TimeZone;

public final class Constantes {

    public static final String   PONTO                        = ".";
    public static final String   BARRA                        = "/";
    public static final String   HIFEN                        = "-";

    public static final String   UTF_8                        = "UTF-8";

    public static final String   INSTITUICAO_CREDORA          = "INSTITUICAO_CREDORA";
    public static final String   CONGLOMERADO                 = "CONGLOMERADO";
    public static final String   DETRAN                       = "DETRAN";
    public static final String   GESTORA                      = "GESTORA";
    public static final String   GENERICA                     = "GENERICA";

    public static final String   PAPEL_ADMINISTRADOR          = "ADMINISTRADOR";
    public static final String   PAPEL_USUARIO                = "USUARIO";
    public static final String   PAPEL_ANALISTA               = "ANALISTA";
    public static final String   PAPEL_SUPERVISOR             = "SUPERVISOR";
    public static final String   PAPEL_RESPONSAVEL_TECNICO    = "RESPONSAVEL_TECNICO";

    public static final String   PESSOA_FISICA                = "PF";
    public static final String   PESSOA_JURIDICA              = "PJ";

    public static final TimeZone TIMEZONE_PADRAO              = TimeZone.getTimeZone("America/Sao_Paulo");
    public static final Locale   LOCALE_PADRAO                = new Locale("pt", "BR");
    public static final int      NUMERO_CASAS_DECIMAIS_PADRAO = 2;
    public static final Long     NUMERO_REGISTROS_POR_PAGINA  = 20L;
    public static final int      TAMANHO_MAXIMO_CHASSI        = 21;

    // Fragmentos do Wicket
    public static final String   FRAGMENT_DROPDOWN            = "fragmentDropDown";
    public static final String   FRAGMENT_CHECKBOX            = "fragmentCheckBox";
    public static final String   FRAGMENT_TEXT                = "fragmentText";
    public static final String   FRAGMENT_NUMBER              = "fragmentNumber";
    public static final String   FRAGMENT_TEXTAREA            = "fragmentTextArea";

}

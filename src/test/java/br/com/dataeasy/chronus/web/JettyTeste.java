package br.com.dataeasy.chronus.web;


import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Classe standalone para início do Jetty Server em ambiente local/desenvolvimento. Use o parâmetro -Dcom.sun.management.jmxremote para iniciar o JMX
 * (e, por exemplo, conectar com o jconsole).
 */
public class JettyTeste {

    private static final String CAMINHO_CONTEXTO = "/chronus";

    /**
     * Função principal. Inicia o jetty server.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("wicket.configuration", "development");

        Server server = new Server();

        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(8443);
        http_config.setOutputBufferSize(32768);

        ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
        http.setPort(8080);
        http.setIdleTimeout(1000 * 60 * 60);

        server.addConnector(http);

        Resource keystore = Resource.newClassPathResource("/keystore");
        if (keystore != null && keystore.exists()) {
            // Se um keystore para um certificado SSL está disponível, inicia um connector SSL na porta 8443
            // Por padrão, a aplicação inicia com um certificado que está em /src/test/resources/keystore que expira no segundo
            // semestre de 2021. Não utilizar este certificado em nenhum lugar importante, dado que os passwords estão disponíveis no código-fonte.

            SslContextFactory sslContextFactory = new SslContextFactory();
            sslContextFactory.setKeyStoreResource(keystore);
            sslContextFactory.setKeyStorePassword("wicket");
            sslContextFactory.setKeyManagerPassword("wicket");

            HttpConfiguration https_config = new HttpConfiguration(http_config);
            https_config.addCustomizer(new SecureRequestCustomizer());

            ServerConnector https = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(
                    https_config));
            https.setPort(8443);
            https.setIdleTimeout(500000);

            server.addConnector(https);
            System.out.println("Acesso SSL à aplicação habilitado na porta 8443");
            System.out.println("Você pode acessar a aplicação utilizando SSL no caminho https://localhost:8443" + CAMINHO_CONTEXTO);
            System.out.println();
        }

        WebAppContext webApp = new WebAppContext();
        webApp.setServer(server);
        webApp.setContextPath(CAMINHO_CONTEXTO);
        webApp.setWar("src/main/webapp");
        webApp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/build/classes/");
        // Set the path to the override descriptor
        webApp.setOverrideDescriptor("/jetty-web.xml");

        server.setHandler(webApp);

        // uncomment next line if you want to test with JSESSIONID encoded in the urls
        // ((AbstractSessionManager)
        // bb.getSessionHandler().getSessionManager()).setUsingCookies(false);

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
        server.addEventListener(mBeanContainer);
        server.addBean(mBeanContainer);

        // Enable parsing of jndi-related parts of web.xml and jetty-env.xml
        Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        // classlist.addAfter(FragmentConfiguration.class.getName(), EnvConfiguration.class.getName(), PlusConfiguration.class.getName());
        classlist.addBefore(JettyWebXmlConfiguration.class.getName(), AnnotationConfiguration.class.getName());

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }
}

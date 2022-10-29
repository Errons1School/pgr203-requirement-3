package no.kristiania.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WebShop {

    private final Server server;
    private final static Logger logger = LoggerFactory.getLogger(WebShop.class);

    public WebShop(int port) {
        this.server = new Server(port);
        server.setHandler(createWebApp());
    }

    private static WebAppContext createWebApp() {
        var webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        var resources = Resource.newClassPathResource("/webapp");

//        resource that is read from .../target/classes/...

        try {
//            resources that is read from .../src/main/resources...
            var sourceDir = new File(resources.getFile().getAbsoluteFile().toString()
                    .replace('\\', '/')
                    .replace("target/classes", "src/main/resources")
            );
//            Use this for making jetty not locking down the files we use to make webpages
            if (sourceDir.isDirectory()) {

                webAppContext.setBaseResource(Resource.newResource(sourceDir));
                webAppContext.setInitParameter(DefaultServlet.CONTEXT_INIT + "useFileMappedBuffer", "false");
            }

        } catch (IOException e) {
//            If something should go wrong use .../target/classes/*resources*
            webAppContext.setBaseResource(resources);
            logger.warn("Resources is read from target-folder");
        }
        var servletHolder = webAppContext.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "no.kristiania.server");


        return webAppContext;
    }


    public void start() throws Exception {
        server.start();
        logger.warn("Server is starting on {}", getURL());
    }

    public URL getURL() throws MalformedURLException {
        return server.getURI().toURL();
    }

    public static void main(String[] args) throws Exception {
        var serv = new WebShop(8080);
        serv.start();
    }

}

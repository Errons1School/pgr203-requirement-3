package no.kristiania.webshop;

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
import java.util.Optional;

public class WebShop {

    private final Server server;
    private final static Logger logger = LoggerFactory.getLogger(WebShop.class);

    public WebShop(int port) throws IOException {
        this.server = new Server(port);
        server.setHandler(createWebApp());
    }

    private static WebAppContext createWebApp() throws IOException {
        var webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        var resources = Resource.newClassPathResource("/webapp");

        useFolderIfExist(webAppContext, resources);
//        resource that is read from .../target/classes/...

        webAppContext.setInitParameter(DefaultServlet.CONTEXT_INIT + "useFileMappedBuffer", "false");
        var servletHolder = webAppContext.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "no.kristiania.webshop");


        return webAppContext;
    }

    private static void  useFolderIfExist(WebAppContext webAppContext, Resource resources) throws IOException {
        if (resources.getFile() == null) {
            webAppContext.setBaseResource(resources);
            return;
        }
        var sourceDirectory = new File(resources.getFile().getAbsoluteFile().toString()
                .replace('\\', '/')
                .replace("target/classes", "src/main/resources"));
        if (sourceDirectory.isDirectory()) {
            webAppContext.setBaseResource(Resource.newResource(sourceDirectory));
            webAppContext.setInitParameter(DefaultServlet.CONTEXT_INIT + "useFileMappedBuffer", "false");
        } else {
            webAppContext.setBaseResource(resources);
            
        }

    }


    public void start() throws Exception {
        server.start();
        logger.warn("Server is starting on {}", getURL());
    }

    public URL getURL() throws MalformedURLException {
        return server.getURI().toURL();
    }

    public static void main(String[] args) throws Exception {
        int port = Optional.ofNullable(System.getenv("HTTP_PLATFORM_PORT"))
                .map(Integer::parseInt)
                .orElse(8080);
        new WebShop(port).start();
    }

}

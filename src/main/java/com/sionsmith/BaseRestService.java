package com.sionsmith;

import com.sionsmith.aws.DynamoDBManaged;
import com.sionsmith.config.AppConfig;
import com.sionsmith.representation.Blog;
import com.sionsmith.resources.IndexResource;
import com.sionsmith.spring.SpringContextLoaderListener;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import net.vz.mongodb.jackson.JacksonDBCollection;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.ws.rs.Path;
import java.util.Map;

/**
 * Created by sionsmith on 12/09/2014.
 */
public class BaseRestService extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new BaseRestService().run(args);
    }

    @Override
    public String getName() {
        return "base-rest-application";
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
//        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        // Init Spring context before we init the app context, we have to create a parent context with all the
        // config objects others rely on to get initialized
        AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        parent.refresh();
        parent.getBeanFactory().registerSingleton("configuration", configuration);
        parent.registerShutdownHook();
        parent.start();

        //the real main app context has a link to the parent context
        ctx.setParent(parent);
        ctx.register(MyAppSpringConfiguration.class);
        ctx.refresh();
        ctx.registerShutdownHook();
        ctx.start();

        //now that Spring is started, let's get all the beans that matter into DropWizard


//        JacksonDBCollection<Blog, String> blogs = JacksonDBCollection.wrap(db.getCollection("blogs"), Blog.class, String.class);

//        environment.addResource(new BlogResource(blogs));
//        environment.jersey().register(new IndexResource(blogs));

        //resources
        Map<String, Object> resources = ctx.getBeansWithAnnotation(Path.class);
        for(Map.Entry<String,Object> entry : resources.entrySet()) {
            environment.jersey().register(entry.getValue());
        }

        //last, but not least, let's link Spring to the embedded Jetty in Dropwizard
        environment.servlets().addServletListeners(new SpringContextLoaderListener(ctx));
    }
}

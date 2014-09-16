package com.sionsmith.resources;

/**
 * Created by sionsmith on 12/09/2014.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.sionsmith.model.Contact;
import com.sionsmith.repositories.ContactRepository;
import com.sionsmith.representation.Blog;
import com.sionsmith.views.IndexView;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {

    @Autowired
    private ContactRepository contactRepository;

    private JacksonDBCollection<Blog, String> collection;

    public IndexResource(JacksonDBCollection<Blog, String> blogs) {
        this.collection = blogs;
    }

    public IndexResource() {
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<Blog> index() {
//        DBCursor<Blog> dbCursor = collection.find();
//        List<Blog> blogs = new ArrayList<>();
//        while (dbCursor.hasNext()) {
//            Blog blog = dbCursor.next();
//            blogs.add(blog);
//        }
        Contact dave = new Contact("Sion1", "Smith1");
        contactRepository.save(dave);
//
        Iterable<Contact> allContacts = contactRepository.findAll();
        System.out.println(allContacts);
//        return new IndexView(blogs);
        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }

}
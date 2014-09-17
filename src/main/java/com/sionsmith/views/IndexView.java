package com.sionsmith.views;

import com.google.common.base.Charsets;
import com.sionsmith.model.Blog;
import io.dropwizard.views.View;

import java.util.List;

/**
 * Created by sionsmith on 12/09/2014.
 */
public class IndexView extends View {

    private List<Blog> blogs;

    public IndexView(List<Blog> blogs) {
        super("/views/index.ftl", Charsets.UTF_8);
        this.blogs = blogs;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }
}

package com.sionsmith.repositories;

import com.sionsmith.model.Blog;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by sionsmith on 29/05/2014.
 */
@EnableScan
public interface BlogRepository extends CrudRepository<Blog, String> {

    List<Blog> findByTitle(String lastName);

}

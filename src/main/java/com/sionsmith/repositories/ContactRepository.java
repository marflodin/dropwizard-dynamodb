package com.sionsmith.repositories;

import com.sionsmith.model.Contact;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by sionsmith on 29/05/2014.
 */
@EnableScan
public interface ContactRepository extends CrudRepository<Contact, String> {
    List<Contact> findByLastName(String lastName);
    Contact findByImageId(String imageId);
}

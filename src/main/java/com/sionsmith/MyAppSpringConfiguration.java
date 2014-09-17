package com.sionsmith;

import com.sionsmith.config.DynamoDBConfig;
import com.sionsmith.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

/**
 Main Spring Configuration
 */
@Configuration
//@ImportResource({ "classpath:spring/applicationContext.xml"})
@ContextConfiguration(classes = {DynamoDBConfig.class})
@ComponentScan(basePackages = {"com.sionsmith"})
public class MyAppSpringConfiguration {

}

package com.example.demo.configuration;

import com.example.demo.bookmodel.Books;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "bookEntityManagerFactory",
        transactionManagerRef = "bookTransactionManager",
        basePackages = {
        "com.example.demo.bookrepository"
})
public class BookConfiguration {

    @Bean(name = "bookdatasource")
    @ConfigurationProperties(prefix = "spring.book.datasource")
    public DataSource bookDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="bookEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                               @Qualifier("bookdatasource")DataSource dataSource)
    {
        Map<String,String> properties=new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL55Dialect");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("com.example.demo.bookmodel")
                .persistenceUnit("Books")
                .build();


    }

    @Bean(name="bookTransactionManager")
    public PlatformTransactionManager bookTransactionManager(@Qualifier("bookEntityManagerFactory")EntityManagerFactory bookEntityManagerFactory)
    {
        return new JpaTransactionManager(bookEntityManagerFactory);
    }
}

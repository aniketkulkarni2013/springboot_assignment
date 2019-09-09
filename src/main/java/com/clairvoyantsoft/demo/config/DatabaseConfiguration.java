package com.clairvoyantsoft.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.clairvoyantsoft.demo.repository")
public class DatabaseConfiguration implements TransactionManagementConfigurer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory);
        return txManager;
    }
}

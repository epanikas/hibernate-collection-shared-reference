package org.github.epanikas.blog.hibernate.sharedreferenceproblem.datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "org.github.epanikas.blog.hibernate.sharedreferenceproblem.repository")
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
//        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
//        simpleDriverDataSource.setUrl("jdbc:h2:./mydb;Mode=Oracle");
//        simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
//        simpleDriverDataSource.setUsername("sa");
//        simpleDriverDataSource.setPassword("");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(org.h2.Driver.class.getName());
        basicDataSource.setUrl("jdbc:h2:./mydb;Mode=Oracle");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");

        return basicDataSource;
    }

//    public DataSource dataSource() {
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setDriverClassName(oracle.jdbc.OracleDriver.class.getName());
//        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521/XEPDB1");
////        basicDataSource.setUsername("sys as sysdba");
////        basicDataSource.setPassword("oracle");
//        basicDataSource.setUsername("my_schema");
//        basicDataSource.setPassword("password");
//
//        return basicDataSource;
//    }

    @Bean
    @DependsOn("dataSourceInitializer")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("my-persistence-unit");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPersistenceXmlLocation("classpath:/META-INF/my-persistence.xml");
        return localContainerEntityManagerFactoryBean;
    }

//    private Properties getJpaProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.hbm2ddl.auto", "create");
//        return properties;
//    }

//    @Bean
//    public LocalSessionFactoryBean entityManagerFactory() {
//        LocalSessionFactoryBean  sessionFactoryBean =
//                new LocalSessionFactoryBean();
//
//        sessionFactoryBean.setDataSource(getDataSource());
////        sessionFactoryBean.setPackagesToScan("com.ldp.vigilantBean.domain");
////        sessionFactoryBean.setHibernateProperties(hibernateProperties());
////        sessionFactoryBean.afterPropertiesSet();
//
//        return sessionFactoryBean/*.getObject()*/;
//    }


    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//        hibernateTransactionManager.setDataSource(getDataSource());
//        hibernateTransactionManager.setSessionFactory(entityManagerFactory().getObject());
//        return hibernateTransactionManager;

//        return new DataSourceTransactionManager(getDataSource());

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(/*entityManagerFactory().getObject()*/entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    @DependsOn("dataSource")
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("./create-schema.sql"));
        resourceDatabasePopulator.setIgnoreFailedDrops(true);

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

}
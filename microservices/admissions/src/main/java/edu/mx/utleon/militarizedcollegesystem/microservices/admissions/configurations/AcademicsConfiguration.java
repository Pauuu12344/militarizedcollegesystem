package edu.mx.utleon.militarizedcollegesystem.microservices.admissions.configurations;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "academicsEntityManagerFactory",
        transactionManagerRef = "academicsTransactionManager",
        basePackages = "edu.mx.utleon.militarizedcollegesystem.microservices.admissions.academics"
)
public class AcademicsConfiguration {

    @Bean(name = "academicsDataSource")
    @ConfigurationProperties(prefix = "spring.academics.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "academicsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("academicsDataSource") DataSource dataSource
    ) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("edu.mx.utleon.militarizedcollegesystem.common.entities.academics")
                .persistenceUnit("academics")
                .build();
    }

    @Bean(name = "academicsTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("academicsEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
package com.tahauddin.syed.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.sql.DdlType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.SchemaToolingSettings.HBM2DDL_AUTO;

@Configuration
@EnableJpaRepositories(basePackages = "com.tahauddin.syed.domain.udemy.*",
        entityManagerFactoryRef = "mySqlUdemyEntityManager",
        transactionManagerRef = "mySqlUdemyTransactionManager")
@Slf4j
public class MySqlUdemyDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("mysql.udemy.datasource")
    @Primary
    public DataSourceProperties mySqlUdemyDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource mySqlUemyDataSource (@Qualifier("mySqlUdemyDataSourceProperties") DataSourceProperties mySqlUdemyDataSourceProperties) {
        return mySqlUdemyDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mySqlUdemyEntityManager (@Qualifier("mySqlUemyDataSource") DataSource mySqlUemyDataSource,
                                                                      EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
       return entityManagerFactoryBuilder
                .dataSource(mySqlUemyDataSource)
                .packages("com.tahauddin.syed.domain.udemy.*")
                .persistenceUnit("udemy")
                .properties(jpaProperties())
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager mySqlUdemyTransactionManager (
            @Qualifier("mySqlUdemyEntityManager") LocalContainerEntityManagerFactoryBean mySqlEntityManager) {
        return new JpaTransactionManager(mySqlEntityManager.getObject());
    }

    private Map<String, Object> jpaProperties () {
        Map<String, Object> properties = new HashMap<>();
        properties.put(SHOW_SQL, true);
        properties.put(FORMAT_SQL, true);
        properties.put(DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(HIGHLIGHT_SQL, true);
        properties.put(POOL_SIZE, "10");

        properties.put(HBM2DDL_AUTO, "validate");
        return properties;
    }

}

package com.tahauddin.syed.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(basePackages = "com.tahauddin.syed.domain.tahauddin.*" ,
        entityManagerFactoryRef = "mySqlTahauddinEntityManager",
        transactionManagerRef = "mySqlTahauddinTransactionManager")
@Slf4j
public class MySqlTahauddinDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("mysql.tahauddin.datasource")
    public DataSourceProperties mySqlTahauddinDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mySqlTahauddinDataSource (@Qualifier("mySqlTahauddinDataSourceProperties") DataSourceProperties mySqlTahauddinDataSourceProperties) {
        return mySqlTahauddinDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mySqlTahauddinEntityManager (@Qualifier("mySqlTahauddinDataSource") DataSource mySqlTahauddinDataSource,
                                                                      EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return entityManagerFactoryBuilder
                .dataSource(mySqlTahauddinDataSource)
                .packages("com.tahauddin.syed.domain.tahauddin.*")
                .persistenceUnit("tahauddin")
                .properties(jpaProperties())
                .build();
    }

    @Bean
    public PlatformTransactionManager mySqlTahauddinTransactionManager (
            @Qualifier("mySqlTahauddinEntityManager") LocalContainerEntityManagerFactoryBean mySqlTahauddinEntityManager) {
        return new JpaTransactionManager(mySqlTahauddinEntityManager.getObject());
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

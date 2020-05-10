package br.com.itau.multiple.datasource.integration.camel.config;


import br.com.itau.multiple.datasource.integration.camel.repo.CustomerRepo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(entityManagerFactoryRef = "customerEntityManager",
        transactionManagerRef = "customerTransactionManager",
        basePackageClasses = {CustomerRepo.class})
public class PersistenceCustomerAutoConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.data.cassandra")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean customerEntityManager() {

        final var properties = new HashMap<String, Object>();
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        //
        em.setDataSource(this.customerDataSource());
        em.setPackagesToScan("br.com.itau.multiple.datasource.integration.camel.domain.cassandra");
        em.setJpaVendorAdapter(vendorAdapter);
        //
        // properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.connection.driver_class", "cdata.cassandra.CassandraDriver");
        em.setJpaPropertyMap(properties);
        //
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager customerTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.customerEntityManager().getObject());
        return transactionManager;
    }

}

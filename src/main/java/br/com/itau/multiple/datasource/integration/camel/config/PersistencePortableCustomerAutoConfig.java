package br.com.itau.multiple.datasource.integration.camel.config;


import br.com.itau.multiple.datasource.integration.camel.repo.PortableCustomerRepo;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@AllArgsConstructor
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(entityManagerFactoryRef = "customerPortableEntityManager",
        transactionManagerRef = "customerPortableTransactionManager",
        basePackageClasses = {PortableCustomerRepo.class})
public class PersistencePortableCustomerAutoConfig {

    private final Environment env;

    @Bean
    public DataSource customerPortableDataSource() {

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("spring.data.mysql.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("spring.data.mysql.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("spring.data.mysql.password")));
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("spring.data.mysql.driver")));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean customerPortableEntityManager() {

        final var properties = new HashMap<String, Object>();
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        //
        em.setDataSource(this.customerPortableDataSource());
        em.setPackagesToScan("br.com.itau.multiple.datasource.integration.camel.domain.mysql");
        em.setJpaVendorAdapter(vendorAdapter);
        //
        //properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);
        //
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager customerPortableTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.customerPortableEntityManager().getObject());
        return transactionManager;
    }
}

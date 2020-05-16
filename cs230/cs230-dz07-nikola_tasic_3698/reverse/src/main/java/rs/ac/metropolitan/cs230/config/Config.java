package rs.ac.metropolitan.cs230.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"rs.ac.metropolitan.cs230.repository"},
                entityManagerFactoryRef = "customEntityManager",
                transactionManagerRef = "customTransactionManager")
public class Config {

        @Primary
        @Bean
        public LocalContainerEntityManagerFactoryBean customEntityManager() {
                LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
                em.setDataSource(customDatasource());
                em.setJpaProperties(additionalJpaProperties());
                em.setPackagesToScan("rs.ac.metropolitan.cs230.entity");
                em.setPersistenceUnitName("customEntityManager");
                HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
                em.setJpaVendorAdapter(vendorAdapter);
                return em;
        }

        @Primary
        @Bean
        @ConfigurationProperties("custom.datasource")
        public DataSource customDatasource() {
                return DataSourceBuilder.create().build();
        }

        private Properties additionalJpaProperties() {
                Properties properties = new Properties();
                properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                return properties;
        }

        @Primary
        @Bean
        public PlatformTransactionManager customTransactionManager() {
                JpaTransactionManager transactionManager = new JpaTransactionManager();
                transactionManager.setEntityManagerFactory(customEntityManager().getObject());
                return transactionManager;
        }

}

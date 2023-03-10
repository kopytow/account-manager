package org.example.contact.manager.config;

import org.example.common.JdbcConfig;
import org.example.common.PropertiesConfiguration;
import org.example.contact.manager.ContactCSVReader;
import org.example.contact.manager.ContactDao;
import org.example.contact.manager.ContactService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@ComponentScan("org.example.contact.manager")
@Import({JdbcConfig.class, PropertiesConfiguration.class})
public class ContactConfiguration {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ContactConfiguration(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Bean
    public ContactDao contactDao() {
        return new ContactDao(namedParameterJdbcTemplate);
    }

    @Bean
    public ContactCSVReader contactParser() {
        return new ContactCSVReader();
    }

    @Bean
    public ContactService contactService() {
        return new ContactService(
                contactDao(),
                contactParser()
        );
    }
}

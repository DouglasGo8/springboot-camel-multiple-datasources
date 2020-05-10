package br.com.itau.multiple.datasource.integration.camel.services;

import br.com.itau.multiple.datasource.integration.camel.domain.cassandra.Customer;
import br.com.itau.multiple.datasource.integration.camel.repo.CustomerRepo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerQueryByIdBean {

    private final CustomerRepo ops;

    public Customer customerById(String uuid) {
        return ops.findById(uuid).orElse(null);
    }
}

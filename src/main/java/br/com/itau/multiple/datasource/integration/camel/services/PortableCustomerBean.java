package br.com.itau.multiple.datasource.integration.camel.services;

import br.com.itau.multiple.datasource.integration.camel.domain.cassandra.Customer;
import br.com.itau.multiple.datasource.integration.camel.domain.mysql.PortableCustomer;
import br.com.itau.multiple.datasource.integration.camel.repo.PortableCustomerRepo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PortableCustomerBean {

    private final PortableCustomerRepo repo;

    public void savePortableCustomerFromCustomer(final Customer customer) {

        final var portableCustomer = new PortableCustomer(customer.getSsn(), customer.getName(), customer.getAge());

        this.repo.save(portableCustomer);

    }
}

package br.com.itau.multiple.datasource.integration.camel.repo;

import br.com.itau.multiple.datasource.integration.camel.domain.cassandra.Customer;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CassandraRepository<Customer, String> {
}

package br.com.itau.multiple.datasource.integration.camel.repo;

import br.com.itau.multiple.datasource.integration.camel.domain.mysql.PortableCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortableCustomerRepo extends CrudRepository<PortableCustomer, String> {
}

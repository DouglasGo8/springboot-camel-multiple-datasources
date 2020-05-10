package br.com.itau.multiple.datasource.integration.camel.domain.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_PORTABLE_CUSTOMER")
public class PortableCustomer {

    @Id
    @Column
    private String ssn;

    @Column
    private String name;

    @Column
    private int age;
}

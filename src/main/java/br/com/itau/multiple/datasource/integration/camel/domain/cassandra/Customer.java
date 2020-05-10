package br.com.itau.multiple.datasource.integration.camel.domain.cassandra;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("tbl_customer")
public class Customer {

    @PrimaryKey
    @Column("ssn")
    private String ssn;

    @Column("name")
    private String name;

    @Column("age")
    private int age;
}

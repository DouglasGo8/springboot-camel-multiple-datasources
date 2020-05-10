package br.com.itau.multiple.datasource.integration.camel.services;

import java.util.Random;

public class CustomerRandomIdBean {

    public String uuidFromCustomer() {
        //
        var bool = new Random().nextBoolean();
        //
        return (bool) ? "9bee9f08-a084-4fed-81d8-dd2ca44460bb" :
                "26ed51d2-62e8-4083-b99c-55e600bb9234";

    }
}

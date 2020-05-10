package br.com.itau.multiple.datasource.integration.camel.services;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRouterComponent extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        from("timer://fooTimer?fixedRate=true&period=5000")
                .bean(CustomerRandomIdBean::new)
                .bean(CustomerQueryByIdBean.class)
                .log("${body}")
                .bean(PortableCustomerBean.class)
                .end();

    }
}

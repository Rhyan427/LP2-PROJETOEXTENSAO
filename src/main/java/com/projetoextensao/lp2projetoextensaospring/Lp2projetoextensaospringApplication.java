package com.projetoextensao.lp2projetoextensaospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"entity"})
@EnableJpaRepositories(basePackages = {"repository"})
@ComponentScan(basePackages = {"com.projetoextensao.lp2projetoextensaospring", "service", "view", "dataTransfer"})
public class Lp2projetoextensaospringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lp2projetoextensaospringApplication.class, args);
    }

}

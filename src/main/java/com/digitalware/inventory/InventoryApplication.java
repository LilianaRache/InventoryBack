package com.digitalware.inventory;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner{

        @Autowired
        private JdbcTemplate template;
        
	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

        @Override
        public void run(String... args) throws Exception {
            template.execute("DROP TABLE PRODUCTO IF EXISTS");
            template.execute("CREATE TABLE PRODUCTO(id INTEGER(11) PRIMARY KEY auto_increment, nombre VARCHAR(255), precio DOUBLE)");
            
            for (int i = 0; i < 3; i++) {
                template.update("insert into producto(nombre, precio)values('producto" + i + "', 20020)");
            }
            
        }

   

}

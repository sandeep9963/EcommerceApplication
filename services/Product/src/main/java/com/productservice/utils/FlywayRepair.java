package com.productservice.utils;

import org.flywaydb.core.Flyway;

public class FlywayRepair {
        public static void main(String[] args) {
            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:postgresql://localhost:5432/product_db", "sandeep", "test")
                    .load();
            flyway.repair();
        }
}

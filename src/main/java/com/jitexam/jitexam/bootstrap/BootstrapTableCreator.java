package com.jitexam.jitexam.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class BootstrapTableCreator {

    private static final String sqlCreate =
            "CREATE TABLE IF NOT EXISTS " +
                    "usertable ( id BIGSERIAL PRIMARY KEY, " +
                    "first_name text, " +
                    "last_name text, " +
                    "username text, " +
                    "password text);";

    private final String jdbcUrl;

    private final String username;

    private final String password;

    public BootstrapTableCreator(
            @Value("${spring.datasource.url}") String jdbcUrl,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public void create() {
        Connection conn = null;
        Statement stmt = null;

        try {

            conn = DriverManager.getConnection(jdbcUrl, username, password);
            stmt = conn.createStatement();

            stmt.executeUpdate(sqlCreate);

            log.info("userTable created");

        } catch (
                SQLException e) {
            log.error("Table not created", e);

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                log.info("connection to database closed");
            } catch (Exception e) {
                log.error("error during closing database connection", e);
            }
        }
    }
}

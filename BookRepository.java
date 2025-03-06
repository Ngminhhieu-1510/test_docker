package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Phương thức test kết nối DB
    public String testConnection() {
        try {
            // Truy vấn đơn giản để kiểm tra kết nối
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database connection is successful!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}

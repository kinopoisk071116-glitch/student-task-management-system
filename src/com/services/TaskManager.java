package com.services;

import com.dao.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskManager {

    // Добавление задачи
    public void addTask(String title) {

        String sql = "INSERT INTO tasks(title, status, created_at) VALUES(?, ?, ?)";

        String time = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, "NEW");
            pstmt.setString(3, time);

            pstmt.executeUpdate();
            System.out.println("Task added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Показ задач
    public void showTasks() {

        String sql = "SELECT * FROM tasks";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\nID | TITLE               | STATUS | CREATED_AT");
            System.out.println("--------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String status = rs.getString("status");
                String createdAt = rs.getString("created_at");

                System.out.printf("%-3d | %-18s | %-6s | %s%n",
                        id, title, status, createdAt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление задачи
    public void deleteTask(int id) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Task deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Обновление статуса
    public void updateTask(int id, String status) {

        String sql = "UPDATE tasks SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            System.out.println("Task updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.crud;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:school.db";

    private Connection connection;

    public static void main(String[] args) {
        // Exemplo de uso
        DatabaseManager manager = new DatabaseManager();
        manager.connect();

        // Adicionando cursos
        manager.addCourse("Matemática", "Cálculo para ensino médio");
        manager.addCourse("Ciência", "Ciência da Natureza");

        // Criando alguns objetos Student para teste
        List<Student> students = List.of(
            new Student("Jonas Nogueira", 25, 1),
            new Student("Joao", 23, 2),
            new Student("Maria", 21, 1)
        );

        // Adicionando alunos
        manager.addStudent("Bruno Sousa", 23, 1);
        manager.addStudent("Isaac", 22, 2);

        // Buscar alunos por substring
        manager.searchStudentsByName("Bruno");

        // Atualizar aluno
        manager.updateStudent(1, "Bruno Sousa", 21, 1);

        // Remover aluno
        manager.removeStudent(2);

        manager.disconnect();
    }

    // Conectar ao banco de dados
    public void connect() {
        try {
            // Carrega o driver JDBC
            Class.forName("org.sqlite.JDBC");
            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Conectado ao banco de dados SQLite.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Desconectar do banco de dados
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Desconectado do banco de dados SQLite.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um curso
    public void addCourse(String name, String description) {
        String sql = "INSERT INTO Course(name, description) VALUES(?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
            System.out.println("Curso adicionado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um aluno
    public void addStudent(String name, int age, int courseId) {
        String sql = "INSERT INTO Student(name, age, course_id) VALUES(?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, courseId);
            pstmt.executeUpdate();
            System.out.println("Aluno adicionado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para remover um aluno
    public void removeStudent(int id) {
        String sql = "DELETE FROM Student WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Aluno removido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um aluno
    public void updateStudent(int id, String name, int age, int courseId) {
        String sql = "UPDATE Student SET name = ?, age = ?, course_id = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, courseId);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Aluno atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // Método para adicionar múltiplos alunos
    public void addStudents(List<Student> students) {
        String sql = "INSERT INTO Student(name, age, course_id) VALUES(?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Student student : students) {
                pstmt.setString(1, student.getName());
                pstmt.setInt(2, student.getAge());
                pstmt.setInt(3, student.getCourseId());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Alunos adicionados com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para buscar alunos por substring
    public void searchStudentsByName(String substring) {
        String sql = "SELECT * FROM Student WHERE name LIKE ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + substring + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("name") + 
                                   ", Idade: " + rs.getInt("age") + ", Curso ID: " + rs.getInt("course_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
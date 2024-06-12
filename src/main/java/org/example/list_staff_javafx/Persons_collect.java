package org.example.list_staff_javafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persons_collect {
    private String sql;

    public Persons_collect() {
        DataBaseHelper.CreateNewDataBase();
        DataBaseHelper.CreatePersonTable();
    }

    public boolean addPerson(Persons person) {
        sql = "INSERT INTO person(name, age, idNumber) VALUES(?,?,?)";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.setString(3, person.getIdNumber());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removePerson(String idNumber) {
        sql = "DELETE FROM person WHERE idNumber = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idNumber);
            pstmt.executeUpdate();
            System.out.println("Person removed");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updatePerson(Persons person) {
        sql = "UPDATE person SET name = ?, age = ? WHERE idNumber = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.setString(3, person.getIdNumber());
            pstmt.executeUpdate();
            System.out.println("Person was updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String PrintAllPersons() {
        sql = "SELECT * FROM person";
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String idNumber = rs.getString("idNumber");
                sb.append("Id = ").append(id)
                        .append(", Name = ").append(name)
                        .append(", Age = ").append(age)
                        .append(", IdNumber = ").append(idNumber)
                        .append("\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    public Persons SearchPersonsByIdNumber(String idNumber) {
        sql = "SELECT name, age, idNumber FROM person WHERE idNumber = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    return new Persons(name, age, idNumber);
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String FindPersonsByName(String name) {
        sql = "SELECT id, name, age, idNumber FROM person WHERE name = ?";
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String personName = rs.getString("name");
                    int age = rs.getInt("age");
                    String idNumber = rs.getString("idNumber");
                    sb.append("Id = ").append(id)
                            .append(", Name = ").append(personName)
                            .append(", Age = ").append(age)
                            .append(", IdNumber = ").append(idNumber)
                            .append("\n");
                }
                if (sb.length() == 0) {
                    sb.append("Person not found");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}

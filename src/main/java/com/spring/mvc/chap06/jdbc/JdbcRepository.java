package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.jdbc.entity.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
class JdbcRepository{

    String url = "jdbc:mariadb://localhost:3306/spring";

    String userName = "root";
    String password = "mariadb";

    public JdbcRepository(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    public void save(Person person){
        try {
            Connection conn =
                    DriverManager.getConnection(url, userName, password);

            conn.setAutoCommit(false);

            String sql = "INSERT INTO person" +
                    "(id, person_name, person_age)" +
                    "VALUES (?, ?, ?)";

            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, person.getId());
            psmt.setString(2, person.getPersonName());
            psmt.setInt(3, person.getPersonAge());

            int result = psmt.executeUpdate();

            if(result == 1) conn.commit();
            else conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Person person){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);

            conn.setAutoCommit(false);

            String sql = "UPDATE person " +
                    "SET person_name = ?, person_Age = ? " +
                    "WHERE id = ?";

            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, person.getPersonName());
            psmt.setInt(2, person.getPersonAge());
            psmt.setString(3, person.getId());

            int result = psmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);

            conn.setAutoCommit(false);

            String sql = "DELETE FROM person " +
                    "WHERE id = ?";

            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, id);

            int result = psmt.executeUpdate();

            if (result==1) conn.commit();
            else conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> findAll(){
        List<Person> personList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, userName, password);

            conn.setAutoCommit(false);

            String sql = "SELECT * FROM person";

            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {

                String id = rs.getString("id");
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");

                Person p = new Person(id, personName, personAge);

                personList.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }


    public Person findOne(String _id){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);

            conn.setAutoCommit(false);

            String sql = "SELECT * FROM person " +
                    "WHERE id = ?";

            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, _id);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()){

                String id = rs.getString("id");
                String userName = rs.getString("person_name");
                int age = rs.getInt("person_age");

                return new Person(id, userName, age);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.jdbc.entity.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepository {

    // db 연결 설정정보
    private String url = "jdbc:mariadb://localhost:3306/spring"; //데이터베이스 url
    private String username = "root";
    private String password = "mariadb";

    // jdbc 드라이버 로딩
    public JdbcRepository(){
        try {
            Class.forName("org.mariadb.jdbc.Drivar");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스 커넥션 얻기
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    //INSERT 기능
    public void save(Person person){

        Connection con = null;

        //1. db연결하고 연결정보를 얻어와야 한다.
        try {
            con = DriverManager.getConnection(url, username, password);
            // 2. 트랜젝션을 시작
            con.setAutoCommit(false); // 자동커밋 비활성화
            // 3. sql을 생성
            String sql = "INSERT INTO person (id, person_name, person_age) " +
                    "VALUES (?,?,?)";

            // 4. sql을 실행시켜주는 객체 얻어야 함
            PreparedStatement pstmt = con.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, person.getId());
            pstmt.setString(2, person.getPersonName());
            pstmt.setInt(3, person.getPersonAge());

            // 6. sql 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            //7. 트랜잭션 처리
            if(result == 1) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            try {
                if(con!=null) con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    // UPDATE 기능
    public void update(Person person) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, username, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "UPDATE person " +
                    "SET person_name = ?, person_age = ? " +
                    "WHERE id = ?";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());
            pstmt.setString(3, person.getId());

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // DELETE 기능
    public void delete(String id) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, username, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "DELETE FROM person " +
                    "WHERE id = ?";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, id);

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 전체 회원 조회
    //SELECT
    public List<Person> findAll(){
        List<Person> people = new ArrayList<>();

        try {
            //1. 데이터베이스 연결해서 연결정보 획득
            Connection con = DriverManager.getConnection(url, username, password);

            //2. sql 생성
            String sql = "SELECT * FROM person";

            //3. sql실행을 위한 객체 획득
            PreparedStatement pstmt = con.prepareStatement(sql);
            //4. ? 파라미터 채우기
            //5. sql실행 명령
            ResultSet resultSet = pstmt.executeQuery();

            //6. 결과집합 조작하기
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String personName = resultSet.getString("person_name");
                int personAge = resultSet.getInt("person_age");

                Person p = new Person(id, personName, personAge);
                people.add(p);
            }
        }catch (Exception e){

        }

        return people;
    }

    // 단일 조회 (아이디로 조회)

    public Person findOne(String id){
        try {
            //1. 데이터베이스 연결해서 연결정보 획득
            Connection con = DriverManager.getConnection(url, username, password);

            //2. sql 생성
            String sql = "SELECT * FROM person WHERE id = ?";

            //3. sql실행을 위한 객체 획득
            PreparedStatement pstmt = con.prepareStatement(sql);
            //4. ? 파라미터 채우기
            pstmt.setString(1, id);
            //5. sql실행 명령
            ResultSet resultSet = pstmt.executeQuery();

            //6. 결과집합 조작하기
            while (resultSet.next()){
                String pid = resultSet.getString("id");
                String personName = resultSet.getString("person_name");
                int personAge = resultSet.getInt("person_age");

                return new Person(pid, personName, personAge);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
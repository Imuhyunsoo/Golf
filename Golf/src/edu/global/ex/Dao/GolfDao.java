package edu.global.ex.Dao;

import edu.global.ex.Dto.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GolfDao {

    private DataSource dataSource = null;

    public GolfDao() {

        try {
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TeacherDto> TeacherList() {

        List<TeacherDto> dtoList = new ArrayList<>();

        System.out.println("list() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT TEACHER_CODE, TEACHER_NAME, CLASS_NAME, " +
                           "TO_CHAR(TBL_TEACHER_202201.CLASS_PRICE,'L999,999') AS CLASS_PRICE, " +
                           "CONCAT(CONCAT(CONCAT(SUBSTR(TEACHER_REGIST_DATE, 1, 4), '년'), " +
                           "CONCAT(SUBSTR(TEACHER_REGIST_DATE, 5, 2), '월')), " +
                           "CONCAT(SUBSTR(TEACHER_REGIST_DATE, 7, 2), '일')) AS TEACHER_REGIST_DATE " +
                           "FROM TBL_TEACHER_202201 ORDER BY TEACHER_CODE";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                String teacher_code = resultSet.getString("teacher_code");
                String teacher_name = resultSet.getString("teacher_name");
                String class_name = resultSet.getString("class_name");
                String class_price = (resultSet.getString("class_price"));
                String teacher_regist_date = resultSet.getString("teacher_regist_date");

                TeacherDto dto = new TeacherDto(teacher_code, teacher_name, class_name, class_price, teacher_regist_date);
                dtoList.add(dto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public List<ProfileDto> MemberList() {

        List<ProfileDto> dtoList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT CONCAT(CONCAT(SUBSTR(REGIST_MONTH, 1, 4), '년'), " +
                           "CONCAT(SUBSTR(REGIST_MONTH, 5, 2), '월')) AS REGIST_MONTH, C_NO, C_NAME, CLASS_NAME, CLASS_AREA, " +
                           "TO_CHAR(TUITION,'L999,999') AS TUITION, GRADE " +
                           "FROM (SELECT REGIST_MONTH, TBL_CLASS_202201.C_NO, C_NAME, CLASS_AREA, TUITION, GRADE,TEACHER_CODE " +
                           "FROM TBL_MEMBER_202201, TBL_CLASS_202201 " +
                           "WHERE TBL_MEMBER_202201.C_NO = TBL_CLASS_202201.C_NO) JTABLE, TBL_TEACHER_202201 " +
                           "WHERE JTABLE.TEACHER_CODE = TBL_TEACHER_202201.TEACHER_CODE";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String regist_month = resultSet.getString("regist_month");
                String c_no = resultSet.getString("c_no");
                String c_name = resultSet.getString("c_name");
                String class_name = resultSet.getString("class_name");
                String class_area = resultSet.getString("class_area");
                String tuition = resultSet.getString("tuition");
                String grade = resultSet.getString("grade");

                ProfileDto dto = new ProfileDto(regist_month, c_no, c_name, class_name, class_area, tuition, grade);
                dtoList.add(dto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public List<SalesDto> SalesList() {

        List<SalesDto> dtoList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT TBL_CLASS_202201.TEACHER_CODE, CLASS_NAME, TEACHER_NAME, TO_CHAR(SUM(TUITION),'L999,999') AS TUITION " +
                           "FROM TBL_CLASS_202201, TBL_TEACHER_202201 " +
                           "WHERE TBL_CLASS_202201.TEACHER_CODE = TBL_TEACHER_202201.TEACHER_CODE " +
                           "GROUP BY TUITION, TBL_CLASS_202201.TEACHER_CODE, CLASS_NAME, TEACHER_NAME ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String teacher_code = resultSet.getString("teacher_code");
                String class_name = resultSet.getString("class_name");
                String teacher_name = resultSet.getString("teacher_name");
                String tuition = resultSet.getString("tuition");

                SalesDto dto = new SalesDto(teacher_code, class_name, teacher_name, tuition);
                dtoList.add(dto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public List<ApplyDto> apply(String c_name, String class_area, String class_name) {

        List<ApplyDto> dtoList = new ArrayList<>();

        System.out.println("apply() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT DISTINCT C_NAME, CLASS_AREA, CLASS_NAME " +
                    "FROM (SELECT REGIST_MONTH, TBL_CLASS_202201.C_NO, C_NAME, CLASS_AREA, TUITION, GRADE,TEACHER_CODE " +
                    "FROM TBL_MEMBER_202201, TBL_CLASS_202201 " +
                    "WHERE TBL_MEMBER_202201.C_NO = TBL_CLASS_202201.C_NO) JTABLE, TBL_TEACHER_202201 " +
                    "WHERE JTABLE.TEACHER_CODE = TBL_TEACHER_202201.TEACHER_CODE";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("c_name");
                String area = resultSet.getString("class_area");

                ApplyDto applyDto = new ApplyDto(name,area);
                dtoList.add(applyDto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public void insert(String regist_month, String c_no, String class_area, String tuition) {

        System.out.println("insert() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query1 = "INSERT INTO TBL_CLASS_202201 (REGIST_MONTH, C_NO, CLASS_AREA, TUITION) " +
                    " VALUES (?,?,?,?)";
            String query2 = "UPDATE TBL_CLASS_202201 SET TEACHER_CODE = " +
                    " (CASE TUITION " +
                    " WHEN 100000 THEN '100' " +
                    " WHEN 200000 THEN '200' " +
                    " WHEN 150000 THEN '300' " +
                    " WHEN 400000 THEN '400' " +
                    " END) WHERE C_NO = ? ";

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1,regist_month);
            preparedStatement.setString(2,c_no);
            preparedStatement.setString(3,class_area);
            preparedStatement.setInt(4, Integer.parseInt(tuition));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, c_no);

            int update = preparedStatement.executeUpdate();
            System.out.println("업데이트 갯수 : " + update);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<ClassNameDto> className() {

        List<ClassNameDto> classNames = new ArrayList<>();

        System.out.println("className() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT DISTINCT CLASS_NAME\n" +
                    "FROM (SELECT REGIST_MONTH, TBL_CLASS_202201.C_NO, C_NAME, CLASS_AREA, TUITION, GRADE,TEACHER_CODE\n" +
                    "      FROM TBL_MEMBER_202201, TBL_CLASS_202201\n" +
                    "      WHERE TBL_MEMBER_202201.C_NO = TBL_CLASS_202201.C_NO) JTABLE, TBL_TEACHER_202201\n" +
                    "WHERE JTABLE.TEACHER_CODE = TBL_TEACHER_202201.TEACHER_CODE";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String className = resultSet.getString("class_name");

                ClassNameDto classNameDto = new ClassNameDto(className);
                classNames.add(classNameDto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return classNames;
    }
}

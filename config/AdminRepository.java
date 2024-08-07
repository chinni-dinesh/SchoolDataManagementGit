package com.schoolDataManagement.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.schoolDataManagement.commonDTO.AdminRequest;
import com.schoolDataManagement.commonDTO.TeacherRequest;
import com.schoolDataManagement.response.Response;
import com.schoolDataManagement.util.Constants;
import com.schoolDataManagement.util.QueryConstants;
import com.schoolDataManagement.web.Admin;
import com.schoolDataManagement.web.Teacher;

@Repository
public class AdminRepository {

	private final DataSource dataSource;

	@Autowired
	public AdminRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// method to get a connection
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public Admin adminLogin(AdminRequest request) {

		Admin admin = new Admin();
		try {
			Connection connection = getConnection();
			String query = QueryConstants.VALIDATE_ADMIN;
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, request.getUserName());
			ps.setString(2, request.getPassword());
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				admin = new Admin();
				admin.setId(res.getInt(1));
				admin.setUserName(res.getString(2));
				admin.setPassword(res.getString(3));
			}
			connection.close();

			return admin;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Teacher saveTeacher(TeacherRequest request) {

		Teacher teacher = new Teacher();
		String query = QueryConstants.ADD_TEACHER;
		int res = 0;
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, request.getName());
			ps.setString(2, request.getEmail());
			ps.setString(3, request.getContactNumber());
			ps.setString(4, request.getSubject());
			ps.setString(5, request.getClassTeacher());
			ps.setString(6, request.getSalary());
			ps.setString(7, request.getAddress());
			ps.setString(8, request.getPassword());
			res = ps.executeUpdate();

			connection.close();

			if (res != 0) {
				teacher.setName(request.getName());
				teacher.setEmail(request.getEmail());
				teacher.setContactNumber(request.getContactNumber());
				teacher.setClassTeacher(request.getClassTeacher());
				teacher.setAddress(request.getAddress());
			}
			return teacher;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public List<Teacher> fetchAllTeachers() {
		List<Teacher> list = new ArrayList<>();
		Teacher teacher;
		String query = QueryConstants.FETCH_ALL_TEACHERS;

		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				teacher = new Teacher();

				teacher.setName(rs.getString("NAME"));
				teacher.setEmail(rs.getString("EMAIL"));
				teacher.setContactNumber(rs.getString("CONTACT"));
				teacher.setClassTeacher(rs.getString("CLASS_TEACHER"));
				teacher.setSubject(rs.getString("SUBJECT"));
				teacher.setSalary(rs.getString("SAL"));
				teacher.setAddress(rs.getString("ADDRESS"));
				teacher.setPassword(rs.getString("PASSWORD"));

				list.add(teacher);
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}
}

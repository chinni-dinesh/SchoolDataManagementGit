package com.schoolDataManagement.util;

public class QueryConstants {

	public static final String VALIDATE_ADMIN = "SELECT * FROM ADMIN WHERE ADMIN_NAME=? AND PASSWORD=?";
	public static final String ADD_TEACHER = "INSERT INTO TEACHER (NAME, EMAIL, CONTACT, SUBJECT, CLASS_TEACHER, SAL,"
			+ " ADDRESS, PASSWORD) VALUES (?,?,?,?,?,?,?,?)";
	public static final String FETCH_ALL_TEACHERS = "SELECT * FROM TEACHER";
}

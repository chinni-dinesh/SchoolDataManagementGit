package com.schoolDataManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.schoolDataManagement.commonDTO.AdminRequest;
import com.schoolDataManagement.commonDTO.TeacherRequest;
import com.schoolDataManagement.response.Response;
import com.schoolDataManagement.service.AdminService;
import com.schoolDataManagement.web.Admin;
import com.schoolDataManagement.web.Teacher;

@RestController
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping(value = "/validateAdminLogin")
	public ResponseEntity<Response<Admin>> validateAdminLogin(@RequestBody AdminRequest request) {
		Response<Admin> response = new Response<>();
		try {

			response = service.adminLogin(request);
		} catch (Exception e) {
			e.getMessage();
		}
		return Optional.ofNullable(response).map(resp -> new ResponseEntity<>(resp, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping(value = "/saveTeacher")
	public ResponseEntity<Response<Teacher>> saveTeacher(@RequestBody TeacherRequest request) {
		Response<Teacher> response = new Response<>();
		try {
			
			response = service.saveTeacher(request);
		} catch (Exception e) {
			e.getMessage();
		}
		return Optional.ofNullable(response).map(resp -> new ResponseEntity<>(resp, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping(value = "/getAll")
	public ResponseEntity<Response<List<Teacher>>> getAllTeachers() {
		Response<List<Teacher>> response = new Response<>();
		try {
			
			response = service.getAllTeachers();
		} catch (Exception e) {
			e.getMessage();
		}
		return Optional.ofNullable(response).map(resp -> new ResponseEntity<>(resp, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

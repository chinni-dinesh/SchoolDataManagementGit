package com.schoolDataManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolDataManagement.commonDTO.AdminRequest;
import com.schoolDataManagement.commonDTO.TeacherRequest;
import com.schoolDataManagement.config.AdminRepository;
import com.schoolDataManagement.response.Response;
import com.schoolDataManagement.util.AES;
import com.schoolDataManagement.util.Constants;
import com.schoolDataManagement.util.EncyptDecryptKey;
import com.schoolDataManagement.web.Admin;
import com.schoolDataManagement.web.Teacher;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;

	public Response<Admin> adminLogin(AdminRequest request) {
		Response<Admin> response = new Response<>();
		try {

			request.setPassword(AES.encrypt(request.getPassword(), EncyptDecryptKey.SECRET_KEY));

			Admin admin = repository.adminLogin(request);
			if (admin.getUserName() != null) {
				response.setStatus(Constants.SUCCESS);
				response.setMessage(Constants.LOGIN_SUCCESSFULL);
			} else {
				response.setStatus(Constants.FAILURE);
				response.setMessage(Constants.LOGIN_FAILURE);
			}
			response.setData(admin);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response<Teacher> saveTeacher(TeacherRequest request) {
		Response<Teacher> response = new Response<>();
		try {
			request.setEmail(AES.encrypt(request.getEmail(), EncyptDecryptKey.SECRET_KEY));
			request.setContactNumber(AES.encrypt(request.getContactNumber(), EncyptDecryptKey.SECRET_KEY));
			request.setAddress(AES.encrypt(request.getAddress(), EncyptDecryptKey.SECRET_KEY));
			request.setPassword(AES.encrypt(request.getPassword(), EncyptDecryptKey.SECRET_KEY));

			Teacher teacher = repository.saveTeacher(request);
			
			if (teacher.getName() != null) {
				teacher.setEmail(AES.decrypt(teacher.getEmail(), EncyptDecryptKey.SECRET_KEY));
				teacher.setContactNumber(AES.decrypt(teacher.getEmail(), EncyptDecryptKey.SECRET_KEY));
				teacher.setAddress(AES.decrypt(teacher.getAddress(), EncyptDecryptKey.SECRET_KEY));
			}
			response.setStatus(Constants.SUCCESS);
			response.setMessage(Constants.SAVED);
			response.setData(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Response<List<Teacher>> getAllTeachers(){
		Response<List<Teacher>> response = new Response<>();
		response.setData(repository.fetchAllTeachers());
		return response;
	}
}

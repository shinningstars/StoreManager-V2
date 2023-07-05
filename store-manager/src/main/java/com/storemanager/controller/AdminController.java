package com.storemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storemanager.payload.AdminCreationPayload;
import com.storemanager.payload.AdminUpdatePayload;
import com.storemanager.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Description(value = "Create an admin")
	@PostMapping("/create")
	public ResponseEntity<Object> createAdmin(@RequestBody AdminCreationPayload adminPayload) {

		return adminService.createAdmin(adminPayload);
	}

	@Description(value = "Update an admin")
	@PostMapping("/update")
	public ResponseEntity<Object> updateAdmin(@RequestBody AdminUpdatePayload adminUpdatePayload) {
		return adminService.updateAdmin(adminUpdatePayload);
	}

	@Description(value = "Delete an admin")
	@PostMapping("/delete/{adminId}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable Long adminId) {
		return adminService.deleteAdmin(adminId);
	}

	@Description(value = "This API returns an admin")
	@GetMapping("/select/{adminId}")
	public ResponseEntity<Object> getAdmin(@PathVariable Long adminId) {
		return adminService.getAdmin(adminId);
	}

	@Description(value = "This API returns all admins")
	@GetMapping("/get-all-admins")
	public ResponseEntity<Object> getAdmins() {
		return adminService.getAdmins();
	}
	
	@Description(value = "This API validates the credentials to login.")
	@GetMapping("/validate")
	public ResponseEntity<Object> validateCredentials(@RequestHeader("email") String email,
			@RequestHeader("password") String password) {
		return adminService.validateCredentials(email, password);
	}

}

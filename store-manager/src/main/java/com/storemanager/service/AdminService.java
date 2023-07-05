package com.storemanager.service;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanager.entity.Admin;
import com.storemanager.payload.AdminCreationPayload;
import com.storemanager.payload.AdminUpdatePayload;
import com.storemanager.payload.ErrorDetails;
import com.storemanager.payload.ResponseDetails;
import com.storemanager.repository.AdminRepository;
import com.storemanager.constants.Constants;
import com.storemanager.constants.RegexPattern;

@Service
public class AdminService {
 
	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<Object> createAdmin(AdminCreationPayload adminPayload) {

		System.out.println("Matched = " + "iasdasd@asdsd.sdf".matches(RegexPattern.EMAIL_REGEX));

		if (adminPayload.getFirstName() == null || adminPayload.getFirstName().isEmpty()
				|| adminPayload.getFirstName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-003", Constants.FIRST_NAME_CANNOT_BE_EMPTY));

		}

		if (adminPayload.getLastName() == null || adminPayload.getLastName().isEmpty()
				|| adminPayload.getLastName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-004", "Last name cannot be empty or null"));

		}
		if (Long.toString(adminPayload.getPincode()).length() != 6) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-005", "Only six digits allowed in pincode field"));
		}

		long contact = adminPayload.getContact();

		// Check if the contact number is not 10 digits long
		if (String.valueOf(contact).length() != 10) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-007", "Only ten digits allowed in contact field"));
		}

		List<Admin> adminContactResponse = adminRepository.findByContact(adminPayload.getContact());
		if (!adminContactResponse.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-008", "Contact already exist."));
		}

		List<Admin> adminEmailResponse = adminRepository.findByEmail(adminPayload.getEmail());
		if (!adminEmailResponse.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-008", "Email already exist."));
		}
		
		Admin admin = getAdmin(adminPayload);
		return ResponseEntity.ok(adminRepository.save(admin));
	}

	private Admin getAdmin(AdminCreationPayload adminPayload) {
		Admin admin = new Admin();
		admin.setFirstName(adminPayload.getFirstName());
		admin.setLastName(adminPayload.getLastName());
		admin.setEmail(adminPayload.getEmail());
		admin.setContact(adminPayload.getContact());
		admin.setAddress(adminPayload.getAddress());
		admin.setGender(adminPayload.getGender());
		admin.setCity(adminPayload.getCity());
		admin.setState(adminPayload.getState());
		admin.setPincode(adminPayload.getPincode());
		return admin;
	}

	public ResponseEntity<Object> updateAdmin(AdminUpdatePayload adminUpdatePayload) {
		Admin admin = new Admin();
		admin.setAdminId(adminUpdatePayload.getAdminId());
		admin.setFirstName(adminUpdatePayload.getFirstName());
		admin.setLastName(adminUpdatePayload.getLastName());
		admin.setEmail(adminUpdatePayload.getEmail());
		admin.setContact(adminUpdatePayload.getContact());
		admin.setAddress(adminUpdatePayload.getAddress());
		admin.setGender(adminUpdatePayload.getGender());
		admin.setCity(adminUpdatePayload.getCity());
		admin.setState(adminUpdatePayload.getState());
		admin.setPincode(adminUpdatePayload.getPincode());
		admin.setCreatedAt(adminUpdatePayload.getCreatedAt());
		admin.setUpdatedAt(LocalDateTime.now());
		admin.setDeleted(adminUpdatePayload.isDeleted());

		admin.setUpdatedAt(LocalDateTime.now());
		return ResponseEntity.ok(adminRepository.save(admin));
	}

	public ResponseEntity<Object> deleteAdmin(Long adminId) {
		Optional<Admin> response = adminRepository.findById(adminId);

		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-001", "Admin with given AdminId = '" + adminId + "' not found on the System"));

		}
		Admin admin = response.get();
		admin.setDeleted(true);
		adminRepository.save(admin);
		return ResponseEntity.ok(new ResponseDetails("Admin with Id = '" + adminId + "' Deleted Sucessfully."));
	}

	public ResponseEntity<Object> getAdmin(Long adminId) {
		Optional<Admin> response = adminRepository.findById(adminId);
		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-002", "Admin with given AdminId = '" + adminId + "' not found on the System"));
		}
		return ResponseEntity.ok(adminRepository.save(response.get()));
	}

	public ResponseEntity<Object> getAdmins() {
		List<Admin> admins = adminRepository.findAllByIsDeleted(false);
		return ResponseEntity.ok(admins);
	}
	public ResponseEntity<Object> validateCredentials(String email, String password) {

		Optional<Admin> adminResponse = adminRepository.getByEmailAndPassword(email, password);
		if (!adminResponse.isPresent()) {
			// login failed "Email or password in incorrect."
		}

		// login sucess "Login Sucess."
		return null;
	}

}

package com.hospital.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.dto.AppointmentRequest;
import com.hospital.management.dto.AppointmentResponseDto;
import com.hospital.management.exception.HospitalManagementException;
import com.hospital.management.service.HospitalManagementAppointmentService;
import com.hospital.management.service.HospitalManagementSpecialistDetailsService;
import com.hospital.management.service.HospitalManagementVacancyService;
import com.hospital.management.vo.SpecialistDetailsProperties.SpecialistDetails;

@RestController
@RequestMapping("/hospitalmanagement")
public class HospitalManagementController {

	@Autowired
	private HospitalManagementSpecialistDetailsService hospitalManagementSpecialistDetailsService;

	@Autowired
	private HospitalManagementAppointmentService hospitalManagementAppointmentService;

	@Autowired
	private HospitalManagementVacancyService hospitalManagementVacancyService;

	@GetMapping("/")
	public String healCheck() {
		return "I am alive !";
	}

	@GetMapping("/getspecialistDetails")
	public ResponseEntity<List<SpecialistDetails>> getSpecaialistDetails(
			@RequestParam(value = "hospitalname") final String hospitalName,
			@RequestParam(value = "specialisttype") final String specialistType) throws HospitalManagementException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(hospitalManagementSpecialistDetailsService.getSpecialistDetails(hospitalName, specialistType));
	}

	@PostMapping("/book/appointment")
	public ResponseEntity<AppointmentResponseDto> bookAppointment(
			@RequestBody final AppointmentRequest appointmentRequest) throws HospitalManagementException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(hospitalManagementAppointmentService.bookAppointment(appointmentRequest));
	}

	@GetMapping("/getadmission")
	public ResponseEntity<String> getVacantBeds(@RequestParam(value = "hospitalname") final String hospitalName)
			throws HospitalManagementException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(hospitalManagementVacancyService.getVacantBedCount(hospitalName));
	}
}

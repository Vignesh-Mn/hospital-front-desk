package com.hospital.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.management.dto.AppointmentRequest;
import com.hospital.management.dto.AppointmentResponseDto;
import com.hospital.management.exception.HospitalManagementException;
import com.hospital.management.vo.HospitalSpecialListDetails;

@Service
public class HospitalManagementAppointmentService {

	@Autowired
	private HospitalManagementValidationService hospitalManagementValidataionService;

	public AppointmentResponseDto bookAppointment(final AppointmentRequest appointmentRequest)
			throws HospitalManagementException {
		final HospitalSpecialListDetails hospitalSpecialListDetails = hospitalManagementValidataionService
				.validateAppointmentRequest(appointmentRequest);
		return new AppointmentResponseDto().setPatientName(appointmentRequest.getPatientName())
				.setAppointmentDay(appointmentRequest.getAppointmentDay())
				.setAppointmentTime(hospitalSpecialListDetails.getAvailableTime())
				.setSpecialistName(hospitalSpecialListDetails.getSpecialistName());
	}
}

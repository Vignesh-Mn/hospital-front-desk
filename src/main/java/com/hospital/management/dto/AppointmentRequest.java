package com.hospital.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequest {

	private String hospitalName;

	private String specialistName;

	private String appointmentDay;

	private String patientName;

}

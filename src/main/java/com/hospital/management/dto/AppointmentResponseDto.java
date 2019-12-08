package com.hospital.management.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class AppointmentResponseDto {

	private String specialistName;

	private String patientName;

	private String appointmentDay;

	private String appointmentTime;
}

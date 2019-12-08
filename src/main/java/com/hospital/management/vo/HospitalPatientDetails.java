package com.hospital.management.vo;

import java.util.List;

import com.hospital.management.enums.PatientStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class HospitalPatientDetails {

	private String hospitalName;

	private List<PatientStatus> patientStatus;

	private int totalBedCount;
}

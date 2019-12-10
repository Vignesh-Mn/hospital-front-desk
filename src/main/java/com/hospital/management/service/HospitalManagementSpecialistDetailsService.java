package com.hospital.management.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.management.exception.HospitalManagementException;
import com.hospital.management.vo.HospitalSpecialListDetails;
import com.hospital.management.vo.SpecialistDetailsProperties.SpecialistDetails;

@Service
public class HospitalManagementSpecialistDetailsService {

	@Autowired
	private HospitalManagementValidationService hospitalManagementValidationService;

	public List<SpecialistDetails> getSpecialistDetails(final String hospitalName, final String specialistType)
			throws HospitalManagementException {
		final HospitalSpecialListDetails hospitalSpecialListDetails = hospitalManagementValidationService
				.validateGetSpecialistRequestDetails(hospitalName, specialistType);
		return Arrays.asList(new SpecialistDetails().setType(hospitalSpecialListDetails.getType())
				.setName(hospitalSpecialListDetails.getSpecialistName())
				.setAvailableTime(hospitalSpecialListDetails.getAvailableTime())
				.setAvailableDay(hospitalSpecialListDetails.getAvaialableDay())
				.setHospitalId(hospitalSpecialListDetails.getHospitalId())
				.setAvailabilityStatus(hospitalSpecialListDetails.getAvailabilityStatus()));
	}
}

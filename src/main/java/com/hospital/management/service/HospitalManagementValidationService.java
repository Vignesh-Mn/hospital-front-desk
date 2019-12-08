package com.hospital.management.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hospital.management.dto.AppointmentRequest;
import com.hospital.management.exception.HospitalManagementException;
import com.hospital.management.util.HospitalManagementUtil;
import com.hospital.management.vo.HospitalSpecialListDetails;

@Service
public class HospitalManagementValidationService {

	@Autowired
	private HospitalManagementUtil hospitalManagementUtil;

	public HospitalSpecialListDetails validateAppointmentRequest(final AppointmentRequest appointmentRequest)
			throws HospitalManagementException {

		final List<HospitalSpecialListDetails> hospitalSpecialListDetailsList = hospitalManagementUtil
				.getSpecialistDetailsByHospitalMap().get(appointmentRequest.getHospitalName());
		if (CollectionUtils.isEmpty(hospitalSpecialListDetailsList)) {
			throw new HospitalManagementException("Invalid Hospital Name " + appointmentRequest.getHospitalName(),
					HttpStatus.BAD_REQUEST.value());
		}

		return validateAndGetSpecialistDetails(hospitalSpecialListDetailsList, appointmentRequest);
	}

	private HospitalSpecialListDetails validateAndGetSpecialistDetails(
			final List<HospitalSpecialListDetails> hospitalSpecialListDetails,
			final AppointmentRequest appointmentRequest) throws HospitalManagementException {
		final HospitalSpecialListDetails hospitalSpecialListDetailsObj;
		final Optional<HospitalSpecialListDetails> hospitalSpecialListDetail = hospitalSpecialListDetails.stream()
				.filter(specialist -> specialist.getSpecialistName().equals(appointmentRequest.getSpecialistName()))
				.findFirst();

		if (!hospitalSpecialListDetail.isPresent()) {
			throw new HospitalManagementException("Invalid Specialist Name " + appointmentRequest.getSpecialistName(),
					HttpStatus.BAD_REQUEST.value());
		}

		hospitalSpecialListDetailsObj = hospitalSpecialListDetail.get();

		if (!Arrays.asList(hospitalSpecialListDetailsObj.getAvaialableDay().split(","))
				.contains(appointmentRequest.getAppointmentDay())) {
			throw new HospitalManagementException(
					"Requested Specalist slot " + appointmentRequest.getAppointmentDay() + " not available ",
					HttpStatus.BAD_REQUEST.value());
		}

		return hospitalSpecialListDetailsObj;
	}
}

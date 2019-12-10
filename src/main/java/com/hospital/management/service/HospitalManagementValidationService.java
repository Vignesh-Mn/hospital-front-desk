package com.hospital.management.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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

	public HospitalSpecialListDetails validateGetSpecialistRequestDetails(final String hospitalName,
			final String specalistType) throws HospitalManagementException {

		if (StringUtils.isAnyEmpty(hospitalName, specalistType)) {
			throw new HospitalManagementException("Hospital Name / Speciallist Type cannot be empty",
					HttpStatus.BAD_REQUEST.value());
		}
		final List<HospitalSpecialListDetails> hospitalSpecialListDetailsList = hospitalManagementUtil
				.getSpecialistDetailsByHospitalMap().get(hospitalName);
		if (CollectionUtils.isEmpty(hospitalSpecialListDetailsList)) {
			throw new HospitalManagementException("Invalid Hospital Name " + hospitalName,
					HttpStatus.BAD_REQUEST.value());
		}
		final Optional<HospitalSpecialListDetails> hospitalSpecialListDetail = hospitalSpecialListDetailsList.stream()
				.filter(specialist -> specialist.getType().equals(specalistType)).findFirst();
		if (!hospitalSpecialListDetail.isPresent()) {
			throw new HospitalManagementException(
					"No specialist of type " + specalistType + " is available in hospital " + hospitalName,
					HttpStatus.BAD_REQUEST.value());
		}

		return hospitalSpecialListDetail.get();
	}

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

package com.hospital.management.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;
import com.hospital.management.enums.PatientStatus;
import com.hospital.management.exception.HospitalManagementException;
import com.hospital.management.vo.HospitalPatientDetails;

@Service
public class HospitalManagementVacancyService {

	private final Map<String, HospitalPatientDetails> patientStatusMap = ImmutableMap
			.<String, HospitalPatientDetails>builder()
			.put("Miot",
					new HospitalPatientDetails().setHospitalName("Miot").setTotalBedCount(10)
							.setPatientStatus(getRandomPatientStatus(10)))
			.put("Apollo",
					new HospitalPatientDetails().setHospitalName("Apollo").setTotalBedCount(23)
							.setPatientStatus(getRandomPatientStatus(10)))
			.put("Ramachandra", new HospitalPatientDetails().setHospitalName("Ramachandra").setTotalBedCount(18)
					.setPatientStatus(getRandomPatientStatus(10)))
			.build();

	private List<PatientStatus> getRandomPatientStatus(int totalBedCount) {
		final Builder<PatientStatus> patientStatusListBuilder = ImmutableList.<PatientStatus>builder();
		IntStream.range(0, totalBedCount)
				.forEach(index -> patientStatusListBuilder.add(PatientStatus.randomPatientStatus()));
		return patientStatusListBuilder.build();
	}

	public String getVacantBedCount(final String hospitalName) throws HospitalManagementException {
		final HospitalPatientDetails hospitalPatientDetails = patientStatusMap.get(hospitalName);

		if (Objects.isNull(hospitalPatientDetails)) {
			throw new HospitalManagementException("Invalid Hospital Name " + hospitalName,
					HttpStatus.BAD_REQUEST.value());
		}

		long vacantBeds = hospitalPatientDetails.getPatientStatus().stream()
				.filter(patientStatus -> patientStatus == PatientStatus.DISCHARGE).count();

		if (vacantBeds == 0) {
			throw new HospitalManagementException("No vacant beds available in " + hospitalName,
					HttpStatus.NOT_ACCEPTABLE.value());
		}

		return "Number of beds available is = " + vacantBeds;
	}
}

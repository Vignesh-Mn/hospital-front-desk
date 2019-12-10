package com.hospital.management.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.hospital.management.vo.HospitalDetailsProperties;
import com.hospital.management.vo.HospitalDetailsProperties.HospitalDetails;
import com.hospital.management.vo.HospitalSpecialListDetails;
import com.hospital.management.vo.SpecialistDetailsProperties;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class HospitalManagementUtil {

	@Autowired
	private HospitalDetailsProperties hospitalDetailsProperties;

	@Autowired
	private SpecialistDetailsProperties specialistDetailsProperties;

	public Map<String, List<HospitalSpecialListDetails>> getSpecialistDetailsByHospitalMap() {
		final Builder<String, List<HospitalSpecialListDetails>> hospitalSpecialistDetailsBuilder = ImmutableMap
				.<String, List<HospitalSpecialListDetails>>builder();
		hospitalDetailsProperties.getHospitalList().forEach(hospital -> hospitalSpecialistDetailsBuilder
				.put(hospital.getName(), comparenadGetSpecialistDetails(hospital)));
		return hospitalSpecialistDetailsBuilder.build();
	}

	private List<HospitalSpecialListDetails> comparenadGetSpecialistDetails(HospitalDetails hospital) {
		return specialistDetailsProperties.getSpecialist().stream()
				.filter(specialist -> specialist.getHospitalId() == hospital.getId())
				.map(specialistDetails -> new HospitalSpecialListDetails(specialistDetails.getName(),
						specialistDetails.getType(), specialistDetails.getAvailableDay(),
						specialistDetails.getAvailableTime(), specialistDetails.getHospitalId(),
						specialistDetails.getAvailabilityStatus()))
				.collect(Collectors.toList());
	}
}

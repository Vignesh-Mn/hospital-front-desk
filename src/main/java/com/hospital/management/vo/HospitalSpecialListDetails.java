package com.hospital.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@AllArgsConstructor
public class HospitalSpecialListDetails {

	private String specialistName;

	private String type;

	private String avaialableDay;

	private String availableTime;

	private int hospitalId;

	private String availabilityStatus;
}

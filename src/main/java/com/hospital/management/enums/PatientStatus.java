package com.hospital.management.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PatientStatus {

	DISCHARGE, OUT_PATIENT;

	private static final List<PatientStatus> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static PatientStatus randomPatientStatus() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}

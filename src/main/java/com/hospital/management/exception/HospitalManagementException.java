package com.hospital.management.exception;

import lombok.Getter;

@Getter
public class HospitalManagementException extends Exception {

	private static final long serialVersionUID = 6636228378889844531L;

	private final int errorCode;

	public HospitalManagementException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}

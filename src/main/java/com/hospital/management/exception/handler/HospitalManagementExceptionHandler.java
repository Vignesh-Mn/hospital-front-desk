package com.hospital.management.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hospital.management.dto.HospitalManagementExceptionDto;
import com.hospital.management.exception.HospitalManagementException;

@ControllerAdvice
public class HospitalManagementExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HospitalManagementException.class)
	public ResponseEntity<HospitalManagementExceptionDto> handleBusiessException(final HospitalManagementException ex) {
		return ResponseEntity.status(HttpStatus.valueOf(ex.getErrorCode()))
				.body(new HospitalManagementExceptionDto(ex.getMessage()));
	}
}

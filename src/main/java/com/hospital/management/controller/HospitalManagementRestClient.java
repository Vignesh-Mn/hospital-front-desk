package com.hospital.management.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.hospital.management.exception.HospitalManagementException;
import com.hospital.management.vo.SpecialistDetailsProperties.SpecialistDetails;

@Controller
public class HospitalManagementRestClient {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${specailist.details.url}")
	private String specialistDetailsUri;

	@SuppressWarnings("unchecked")
	@GetMapping("/restclient")
	public ResponseEntity<List<SpecialistDetails>> getSpecialistDetails(@RequestParam("port") final Integer port,
			@RequestParam("hospitalname") final String hospitalName,
			@RequestParam("specialisttype") final String specialistType, final HttpServletRequest httpRequest)
			throws HospitalManagementException {
		final ResponseEntity<List<SpecialistDetails>> response;
		final String url = "http://localhost:" + port + "/hospitalmanagement" + specialistDetailsUri + "?hospitalname="
				+ hospitalName + "&specialisttype=" + specialistType;
		final String acceptTye = StringUtils.isEmpty(httpRequest.getHeader("Accept")) ? MediaType.APPLICATION_JSON_VALUE
				: httpRequest.getHeader("Accept");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.valueOf(acceptTye)));
		HttpEntity<?> request = new HttpEntity<>(headers);

		try {
			response = this.restTemplate.exchange(url, HttpMethod.GET, request,
					(Class<List<SpecialistDetails>>) new ArrayList<SpecialistDetails>().getClass());
		} catch (Exception e) {
			throw new HospitalManagementException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}
}

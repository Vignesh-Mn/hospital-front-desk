package com.hospital.management.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@PropertySource("classpath:specialist.properties")
@ConfigurationProperties("hospital")
@Getter
@Setter
public class SpecialistDetailsProperties {

	private List<SpecialistDetails> specialist = new ArrayList<>();

	@Getter
	@Setter
	public static class SpecialistDetails {
		private String name;

		private String type;

		private String availableDay;

		private String availableTime;

		private int hospitalId;
	}
}

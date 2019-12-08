package com.hospital.management.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties("hospitals")
@Getter
@Setter
public class HospitalDetailsProperties {

	private List<HospitalDetails> hospitalList = new ArrayList<>();

	@Getter
	@Setter
	public static class HospitalDetails {
		private String name;

		private int id;
	}
}

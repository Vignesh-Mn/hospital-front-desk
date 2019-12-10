package com.hospital.management.dto;

import java.util.List;

import com.hospital.management.vo.SpecialistDetailsProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class SpecaialistDetailsDto {

	private List<SpecialistDetailsProperties> specialistDetailsProperties;
}

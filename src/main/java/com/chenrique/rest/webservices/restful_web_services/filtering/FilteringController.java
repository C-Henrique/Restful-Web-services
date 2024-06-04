package com.chenrique.rest.webservices.restful_web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue filter() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		MappingJacksonValue jacksonValue = new MappingJacksonValue(someBean);

		SimpleBeanPropertyFilter filterSome = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filter = new SimpleFilterProvider().addFilter("SomerFilter", filterSome);
		jacksonValue.setFilters(filter);

		return jacksonValue;
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> filterList() {
		return Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("v4", "v5", "v6"));
	}
}
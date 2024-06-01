package com.chenrique.rest.webservices.restful_web_services.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenrique.rest.webservices.restful_web_services.version.person.Name;
import com.chenrique.rest.webservices.restful_web_services.version.person.PersonV1;
import com.chenrique.rest.webservices.restful_web_services.version.person.PersonV2;

@RestController
public class VersionPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Teste da Silva");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSeconVersionOfPerson() {
		return new PersonV2(new Name("Teste", "da Silva"));
	}
}

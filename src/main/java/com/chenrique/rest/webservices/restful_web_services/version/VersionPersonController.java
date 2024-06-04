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

	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParam() {
		return new PersonV1("Teste da Silva");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSeconVersionOfPersonRequestParam() {
		return new PersonV2(new Name("Teste", "da Silva"));
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Teste da Silva");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSeconVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Teste", "da Silva"));
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonRequestAccept() {
		return new PersonV1("Teste da Silva");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSeconVersionOfPersonRequestAccept() {
		return new PersonV2(new Name("Teste", "da Silva"));
	}
}

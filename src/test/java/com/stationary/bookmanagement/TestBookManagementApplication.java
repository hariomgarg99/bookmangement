package com.stationary.bookmanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stationary.bookmanagement.dto.BookDto;
import com.stationary.bookmanagement.entity.Book;
import com.stationary.bookmanagement.util.BookTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BookManagementApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestBookManagementApplication {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;


	@Test
	public void testAddBook() throws JsonProcessingException {
		BookDto book = BookTest.getBook();
		//HttpEntity<String> entity = getStringHttpEntity(book);
		ResponseEntity<String> result = restTemplate.postForEntity("/books/",book,String.class);
		System.out.println(result);
		//assertEquals(book,result.getBody());
	}




	private HttpEntity<String> getStringHttpEntity(Object object) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String jsonMeterData = mapper.writeValueAsString(object);
		return (HttpEntity<String>) new HttpEntity(jsonMeterData, headers);
	}

}

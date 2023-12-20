package com.paycontrol.v1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paycontrol.model.Person;
import com.paycontrol.repository.PersonRepository;
import com.paycontrol.service.PersonService;

import java.text.MessageFormat;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class PersonControllerRestAssuredTest {

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private PersonService personService;

  @LocalServerPort
  private int port;

  private final String urlPathResource = MessageFormat.format(
      "{0}{1}", "/v1/", "persons");

//  <dependency>
//    	<groupId>io.rest-assured</groupId>
//    	<artifactId>rest-assured</artifactId>
//    	<scope>test</scope>
//    </dependency>
//
//  @BeforeAll
//  public void setUp() {
//    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//    RestAssured.port = port;
//    RestAssured.basePath = "/restaurantes";
//
//    jsonRestauranteCorreto = ResourceUtils.getContentFromResource(
//        "/json/correto/restaurante-new-york-barbecue.json");
//
//    jsonRestauranteSemFrete = ResourceUtils.getContentFromResource(
//        "/json/incorreto/restaurante-new-york-barbecue-sem-frete.json");
//
//    jsonRestauranteSemCozinha = ResourceUtils.getContentFromResource(
//        "/json/incorreto/restaurante-new-york-barbecue-sem-cozinha.json");
//
//    jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource(
//        "/json/incorreto/restaurante-new-york-barbecue-com-cozinha-inexistente.json");
//
//    databaseCleaner.clearTables();
//    prepararDados();
//  }
//
//  @BeforeEach
//  void before() {
//    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//  }
//
//  @Test
//  @Rollback
//  @Transactional
//  @SneakyThrows
//  void testValidateSchemaSaveAndGetAllPersons() {
//    final Person person = getPersonExample();
//
//    this.mockMvc.perform(post(urlPathResource)
//        .content(asJsonString(person))
//        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//        .andExpect(status().isCreated())
//        .andExpect(jsonPath("name").value("Diego"))
//        .andExpect(jsonPath("email").value("diegogb2013@gmail.com"))
//        .andReturn()
//        .getResponse()
//        .getContentAsString();
//
//    final String json = this.mockMvc.perform(get(urlPathResource)
//        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$[0].name").value("Diego"))
//        .andExpect(jsonPath("$[0].email").value("diegogb2013@gmail.com"))
//        .andReturn()
//        .getResponse()
//        .getContentAsString();
//    //super.validateSchema(json, "/schema.v1/controller/schema-response-template.json");
//  }
//
//  @Test
//  @Rollback
//  @Transactional
//  @SneakyThrows
//  void testSaveEmailWhenSubSubjectAndDescriptionIsNull() {
//    final Person person = getPersonExample();
//
//    final String json = this.mockMvc.perform(post(urlPathResource)
//        .content(asJsonString(person))
//        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//        .andExpect(status().isCreated())
//        .andExpect(jsonPath("name").value("Diego"))
//        .andExpect(jsonPath("email").value("diegogb2013@gmail.com"))
//        .andReturn()
//        .getResponse()
//        .getContentAsString();
//
//  }
//
//  @Test
//  @Rollback
//  @SneakyThrows
//  void validateSchemaGetTemplateEmail() {
//    final Person person = getPersonExample();
//
//    this.mockMvc.perform(post(urlPathResource)
//        .content(asJsonString(person))
//        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//        .andExpect(status().isCreated())
//        .andExpect(jsonPath("name").value("Diego"))
//        .andExpect(jsonPath("email").value("diegogb2013@gmail.com"))
//        .andReturn()
//        .getResponse()
//        .getContentAsString();
//
//    final String json = this.mockMvc.perform(get(urlPathResource + "/12")
//        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.name").value("Diego"))
//        .andExpect(jsonPath("$.id").value(12))
//        .andReturn()
//        .getResponse()
//        .getContentAsString();
//    //super.validateSchema(json, "/schema.v1/controller/schema-response-template.json");
//  }
//
//  private static String asJsonString(final Object obj) {
//    try {
//      return new ObjectMapper().writeValueAsString(obj);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  private Person getPersonExample() {
//    return Person.builder().
//        name("Diego").email("diegogb2013@gmail.com").phone("559999999999").status(true).
//        build();
//  }
}

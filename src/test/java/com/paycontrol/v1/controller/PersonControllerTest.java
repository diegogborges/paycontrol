package com.paycontrol.v1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paycontrol.model.Person;
import com.paycontrol.repository.PersonRepository;
import com.paycontrol.service.PersonService;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PersonControllerTest {

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private PersonService personService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  private final String urlPathResource = MessageFormat.format(
      "{0}{1}", "/v1/", "persons");

  @BeforeEach
  void before() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  @Rollback
  @Transactional
  @SneakyThrows
  void testValidateSchemaSaveAndGetAllPersons() {
    final Person person = getPersonExample();

    this.mockMvc.perform(post(urlPathResource)
        .content(asJsonString(person))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("name").value("Diego"))
        .andExpect(jsonPath("email").value("diegogb2013@gmail.com"))
        .andReturn()
        .getResponse()
        .getContentAsString();

    final String json = this.mockMvc.perform(get(urlPathResource)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Diego"))
        .andExpect(jsonPath("$[0].email").value("diegogb2013@gmail.com"))
        .andReturn()
        .getResponse()
        .getContentAsString();
    //validateSchema(json, "/schema.v1/controller/schema-response-person.json");
  }

  @Test
  @Rollback
  @Transactional
  @SneakyThrows
  void testSaveEmailWhenSubSubjectAndDescriptionIsNull() {
    final Person person = getPersonExample();

    final String json = this.mockMvc.perform(post(urlPathResource)
        .content(asJsonString(person))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("name").value("Diego"))
        .andExpect(jsonPath("email").value("diegogb2013@gmail.com"))
        .andReturn()
        .getResponse()
        .getContentAsString();
    //validateSchema(json, "/schema.v1/controller/schema-response-person.json");
  }

  @Test
  @Rollback
  @SneakyThrows
  void validateSchemaGetTemplateEmail() {
    final Person person = getPersonExample();

    this.mockMvc.perform(post(urlPathResource)
        .content(asJsonString(person))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("name").value("Diego"))
        .andExpect(jsonPath("email").value("diegogb2013@gmail.com"))
        .andReturn()
        .getResponse()
        .getContentAsString();

    final String json = this.mockMvc.perform(get(urlPathResource + "/12")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Diego"))
        .andExpect(jsonPath("$.id").value(12))
        .andReturn()
        .getResponse()
        .getContentAsString();
    validateSchema(json, "/schema.v1/controller/schema-response-person.json");
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Person getPersonExample() {
    return Person.builder().
        name("Diego").email("diegogb2013@gmail.com").phone("559999999999").status(true).
        build();
  }

  @SneakyThrows
  private void validateSchema(final String json, final String resourceFileName) {
    @Cleanup final InputStream in = this.getClass().getResourceAsStream(resourceFileName);
    final String schema = new String(in.readAllBytes(), StandardCharsets.UTF_8);

    System.out.println("json " + json);
    System.out.println("json " + new JSONObject(new JSONTokener(schema)));

    SchemaLoader.builder()
        .schemaJson(new JSONObject(new JSONTokener(schema)))
        .draftV7Support()
        .build()
        .load()
        .build()
        .validate(new JSONTokener(json).nextValue());
  }
}

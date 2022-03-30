package com.sakinr.patika.airportreservationsystem.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sakinr.patika.airportreservationsystem.exception.handler.GenericExceptionHandler;
import com.sakinr.patika.airportreservationsystem.model.entity.AirportCompany;
import com.sakinr.patika.airportreservationsystem.model.entity.Flight;
import com.sakinr.patika.airportreservationsystem.service.iml.AirportCompanyServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class AirportCompanyControllerTest {

    private MockMvc mvc;

    @Mock
    private AirportCompanyServiceImpl airportCompanyService;

    @InjectMocks
    private AirportCompanyController airportCompanyController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(airportCompanyController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void welcome() throws Exception {
        // init
        String expectedWelcomeMessage = "Welcome to Airportcompany Service!";
        // when
        MockHttpServletResponse response = mvc.perform(get("/api/airport-company")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().equals(expectedWelcomeMessage));
    }

    @Test
    void getAllAirportCompanies() throws Exception {
        // init test values / given
        List<AirportCompany> expectedAirportCompanies = getTestAirportCompanies();

        // stub - when
        Mockito.when(airportCompanyService.getAllAirportCompanies()).thenReturn(expectedAirportCompanies);

        MockHttpServletResponse response = mvc.perform(get("/api/airport-company/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<AirportCompany> actualAirportCompanies = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<AirportCompany>>() {
        });
        assertEquals(expectedAirportCompanies.size(), actualAirportCompanies.size());

    }

    @Test
    void getAirportCompany() throws Exception {
        // init test values
        List<AirportCompany> expectedAirportCompanies = getTestAirportCompanies();

        // stub - given
        Mockito.when(airportCompanyService.getAirportCompany(1)).thenReturn(expectedAirportCompanies.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/airport-company/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AirportCompany actualAirportCompany = new ObjectMapper().readValue(response.getContentAsString(), AirportCompany.class);
        Assert.assertEquals(expectedAirportCompanies.get(0), actualAirportCompany);
    }

    @Test
    void saveAirportCompany() throws Exception {
        // init test values
        List<AirportCompany> expectedAirportCompanies = getTestAirportCompanies();
        AirportCompany expectedAirportCompany = new AirportCompany();
        expectedAirportCompany.setName("SampleAirportCompany");
        expectedAirportCompanies.add(expectedAirportCompany);

        // stub - given
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedAirportJsonStr = ow.writeValueAsString(expectedAirportCompany);
        Mockito.doNothing().when(airportCompanyService).addAirportCompany(expectedAirportCompany);

        MockHttpServletResponse response = mvc.perform(post("/api/airport-company/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedAirportJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(airportCompanyService, Mockito.times(1)).addAirportCompany(any());
    }

    @Test
    public void deleteAirportCompany() throws Exception {
        // stub - given
        Mockito.when(airportCompanyService.deleteAirportCompany(any())).thenReturn(true);

        MockHttpServletResponse response = mvc.perform(delete("/api/airport-company/delete?id=5")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String actualResponseStr = response.getContentAsString();
        Assert.assertEquals("true", actualResponseStr);
    }

    private List<AirportCompany> getTestAirportCompanies() {
        // init test values
        List<AirportCompany> airportCompanies = new ArrayList<>();
        airportCompanies.add(new AirportCompany(1, "Airport X", null));
        airportCompanies.add(new AirportCompany(2, "Airport Y",
                Arrays.asList(
                        new Flight(1, "Flight-ABC", 50, 400, new Date(), new Date(), null, null, null),
                        new Flight(2, "Flight-XYZ", 100, 300, new Date(), new Date(), null, null, null)
                )));
        airportCompanies.add(new AirportCompany(3, "Airport Z", null));
        return airportCompanies;
    }
}
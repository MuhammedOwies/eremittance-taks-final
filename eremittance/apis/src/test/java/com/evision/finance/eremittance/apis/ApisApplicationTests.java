package com.evision.finance.eremittance.apis;

import com.evision.finance.eremittance.apis.service.RemittanceServiceImpl;
import com.evision.finance.eremittance.apis.view.RemittanceVO;
import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@WithMockUser
@RunWith(SpringRunner.class)
public class ApisApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private RemittanceServiceImpl service;

    private final RemittanceVO remMock = new RemittanceVO().setSenderName("name")
            .setSenderId("123")
            .setEmailAddress("mail@test.com")
            .setStatus(StatusRef.ACTV)
            .setRemittanceAmount("15000")
            .setCountryArName("egypt")
            .setCountryId(1)
            .setCompanyId(1)
            .setCurrencyCode((short) 100)
            .setCompanyCommission((short) 5)
            .setBenficiaryCommission((short) 5)
            .setTypeOfId("type");

    @Test
    @DisplayName("create a valid remittance")
    public void createRemittance() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Mockito.when(service.createRemittance(Mockito.any(RemittanceVO.class))).thenReturn(remMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Remittance/")
                .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(remMock))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    @DisplayName("create a invalid remittance")
    public void createInvalidRemittance() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Mockito.when(service.createRemittance(Mockito.any(RemittanceVO.class))).thenReturn(remMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Remittance/")
                .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(remMock.setTypeOfId(null)))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("create remittance with invalid URI")
    public void createRemittanceInvalidUri() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Mockito.when(service.createRemittance(Mockito.any(RemittanceVO.class))).thenReturn(remMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Remittance1/")
                .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(remMock))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}

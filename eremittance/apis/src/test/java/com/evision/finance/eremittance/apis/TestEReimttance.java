//package com.evision.finance.eremittance.apis;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.evision.finance.eremittance.apis.controllers.RemittanceController;
//import com.evision.finance.eremittance.apis.service.RemittanceServiceImpl;
//import com.evision.finance.eremittance.apis.view.RemittanceVO;
//import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@WebMvcTest(value = RemittanceController.class)
//@WithMockUser
//public class TestEReimttance extends ApisApplicationTests {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RemittanceServiceImpl service;
//
//    private final RemittanceVO remMock = new RemittanceVO().setSenderName("name")
//            .setSenderId("123")
//            .setEmailAddress("mail@test.com")
//            .setStatus(StatusRef.ACTV)
//            .setRemittanceAmount("15000")
//            .setCountryArName("egypt")
//            .setCountryId(1)
//            .setCompanyId(1)
//            .setCurrencyCode((short) 100)
//            .setCompanyCommission((short) 5)
//            .setBenficiaryCommission((short) 5);
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void createRemittance() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValueAsString(remMock);
//        Mockito.when(service.createRemittance(Mockito.any(RemittanceVO.class))).thenReturn(remMock);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/Remittance")
//                .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(remMock))
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//
//        assertEquals(result.getRequest().getRequestURL().toString(),
//                     response.getHeader(HttpHeaders.LOCATION));
//    }
//}

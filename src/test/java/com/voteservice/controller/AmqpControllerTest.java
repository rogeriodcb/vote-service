package com.voteservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.voteservice.dto.MessageQueueDTO;
import com.voteservice.service.AmqpService;
import com.voteservice.utils.AmqpUtils;

/**
* <p>**************************************************************************************
* <p>                          <b>AmqpControllerTest</b>
* <p>**************************************************************************************
* <p>
* This class performs AMQP test by using the AmqpControllerTest.
* 
* @author      Rogério de C. Brito
*/
@ExtendWith(MockitoExtension.class)
public class AmqpControllerTest {
	public static final String VOTESERVICE_API_URL_PATH = "/send";
	public static final String MESSAGE_TO_TEST="This is a message test.";
	private MockMvc mockMvc;
	
	@Mock
	private AmqpService amqpService;
	
	@InjectMocks
	private AmqpController amqpController;
	
	// before test class
	@BeforeTestClass
    public static void init() {
    	System.out.println("==================================================================");
    	System.out.println("=                 AmqpController tests                     =");
    	System.out.println("==================================================================");
    }
	
	// after test class
	@AfterTestClass
	public static void finish() {
		System.out.println("======================= End of test case =========================");
	}
	
	// For each test configures mocMvc
	@BeforeEach
	void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(amqpController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();     
    }
	
	/***************************************************************
   	* <b>Test case 1.0</b>
   	* <br> test message sending when POST request is called.
   	****************************************************************/
	@Test
    void testMessageSend() throws Exception {
		/***************** initial setup *****************/
        System.out.println("\nTest Case 1.0 - test messaging send when POST request is called...\n");
        
        MessageQueueDTO messageQueueDTO = new MessageQueueDTO();

        messageQueueDTO.setText(MESSAGE_TO_TEST);
        

        
        /************* Execution and verification**************/
		
		mockMvc.perform(post(VOTESERVICE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(AmqpUtils.asJsonString(messageQueueDTO)))
                .andExpect(status().isAccepted());

        System.out.println("\n<<done>>\n");
	}
	
}

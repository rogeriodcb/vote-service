package com.voteservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import com.voteservice.model.VoteMember;
import com.voteservice.service.VoteMemberService;
import com.voteservice.utils.VoteMemberUtils;

/**
* <p>**************************************************************************************
* <p>                          <b>VoteMemberControllerTest</b>
* <p>**************************************************************************************
* <p>
* This class performs the VoteMemberController class methods tests.
* 
* Note: these tests uses the "VoteMemberUtils" to produces faker data
* 
* @author      Rogério de C. Brito
*/
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VoteMemberControllerTest {
	public static final String VOTESERVICE_API_URL_PATH = "/voteservice-api/v1";
	private MockMvc mockMvc;
	
	@Mock
	private VoteMemberService voteMemberService;
	
	@InjectMocks
	private VoteMemberController voteMemberController;
	
	// before test class
	@BeforeTestClass
    public static void init() {
    	System.out.println("==================================================================");
    	System.out.println("=                 VoteMemberController tests                     =");
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
        mockMvc = MockMvcBuilders.standaloneSetup(voteMemberController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();     
    }
	
	/***************************************************************
   	* <b>Test case 1.0</b>
   	* <br> test the createVote when POST request is called.
   	****************************************************************/
	@Test
	@Order(1)
    void testWhenPOSTisCalledIfVoteIsCreated() throws Exception {
		/***************** initial setup *****************/
        System.out.println("\nTest Case 1.0 - test the vote member creation when POST request is called...\n");

		VoteMember voteMember = VoteMemberUtils.createFakeVoteMember();

        
        /************* Execution and verification**************/

		mockMvc.perform(post(VOTESERVICE_API_URL_PATH+"/votetopic/1/votemember")
				.contentType(MediaType.APPLICATION_JSON)
				.content(VoteMemberUtils.asJsonString(voteMember)))
				.andExpect(status().isCreated());

        System.out.println("\n<<done>>\n");
	}
	
	/***************************************************************
   	* <b>Test case 1.1</b>
   	* <br> test the findVoteById method
   	****************************************************************/
	@Test
	@Order(2)
    void testWhenGETisCalledIfFindVoteByIdReturnsOk() throws Exception {
		/***************** initial setup *****************/
        System.out.println("\nTest Case 1.1 - test the findByID method when GET request is called...\n");

		VoteMember voteMember = VoteMemberUtils.createFakeVoteMember();

        /************* Execution and verification**************/
		
		mockMvc.perform(get(VOTESERVICE_API_URL_PATH+"/votemember/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(VoteMemberUtils.asJsonString(voteMember)))
				.andExpect(status().isOk());

        System.out.println("\n<<done>>\n");
	}
}

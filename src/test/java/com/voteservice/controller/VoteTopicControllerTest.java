package com.voteservice.controller;

import static com.voteservice.utils.VoteTopicUtils.asJsonString;
import static com.voteservice.utils.VoteTopicUtils.createFakeVoteTopic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.Is;
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

import com.voteservice.dto.MessageResponseDTO;
import com.voteservice.model.VoteTopic;
import com.voteservice.service.VoteTopicService;

/**
* <p>**************************************************************************************
* <p>                          <b>VoteTopicControllerTest</b>
* <p>**************************************************************************************
* <p>
* This class performs the VoteTopicController class methods tests.
* 
* Note: these tests uses the "VoteTopicUtils" to produces faker data
* 
* @author      Rogério de C. Brito
*/
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VoteTopicControllerTest {
	
	public static final String VOTESERVICE_API_URL_PATH = "/voteservice-api/v1";
	private MockMvc mockMvc;
	
	@Mock
	private VoteTopicService voteTopicService;
	
	@InjectMocks
	private VoteTopicController voteTopicController;
	
	// before test class
	@BeforeTestClass
    public static void init() {
    	System.out.println("==================================================================");
    	System.out.println("=                 VoteTopicController tests                      =");
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
        mockMvc = MockMvcBuilders.standaloneSetup(voteTopicController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();     
    }
	
	/***************************************************************
   	* <b>Test case 1.0</b>
   	* <br> test the vote topic creation when POST request is called.
   	****************************************************************/
	@Test
	@Order(1)
    void testWhenPOSTisCalledIfVoteTopicIsCreated() throws Exception {
		/***************** initial setup *****************/
        System.out.println("\nTest Case 1.0 - test the vote topic creation when POST request is called...\n");

		VoteTopic voteTopic = createFakeVoteTopic();

        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Vote topic created with ID " + voteTopic.getVoteTopicId())
                .build();
        
        /************* Execution and verification**************/
        when(voteTopicService.save(voteTopic)).thenReturn(expectedMessageResponse);

        mockMvc.perform(post(VOTESERVICE_API_URL_PATH+"/votetopic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(voteTopic)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
        
        System.out.println("\n<<done>>\n");

    }
	
	
	/***************************************************************
   	* <b>Test case 1.1</b>
   	* <br> test the vote topic creation when name field is empty.
   	* <br> The vote topic cannot be created.
   	****************************************************************/
	@Test
	@Order(2)
    void testIfNameIsEmptyThenVoteTopicShouldNotBeCreated() throws Exception {
		/***************** initial setup *****************/
        System.out.println("\nTest Case 1.1 - test the vote topic creation when POST request is called and a empty name is passed...\n");

		VoteTopic voteTopic = createFakeVoteTopic();
		voteTopic.setName("");
		
        /************* Execution and verification**************/
		mockMvc.perform(post(VOTESERVICE_API_URL_PATH+"/votetopic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(voteTopic)))
                .andExpect(status().isBadRequest());

		System.out.println("\n<<done>>\n");

    }
	
	
	/***************************************************************
   	* <b>Test case 2.0</b>
   	* <br> test the response of "All" method when called. This
   	* method returns status ok.
   	****************************************************************/
	@Test
	@Order(3)
    void testWhenAllMethodisCalledThenOkStatusShouldbeReturned() throws Exception {
		/***************** initial setup *****************/
        System.out.println("\nTest Case 2.0 - test the response of All method when GET request is called...\n");

		VoteTopic voteTopic = createFakeVoteTopic();
		voteTopic.setName("");
		
        /************* Execution and verification**************/
		mockMvc.perform(get(VOTESERVICE_API_URL_PATH+"/votetopics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(voteTopic)))
                .andExpect(status().isOk());

		System.out.println("\n<<done>>\n");

    }
	
	/***************************************************************
   	* <b>Test case 2.1</b>
   	* <br> test the response of "findById" method when called. This
   	* method returns status ok.
   	****************************************************************/
	@Test
	@Order(4)
    void testWhenFindByIdMethodisCalledThenOkStatusShouldbeReturned() throws Exception {
		/***************** initial setup *****************/
        System.out.println("\nTest Case 2.1 - test the response of findById method when GET request is called...\n");

		VoteTopic voteTopic = createFakeVoteTopic();
		voteTopic.setName("");
		
        /************* Execution and verification**************/
		mockMvc.perform(get(VOTESERVICE_API_URL_PATH+"/votetopic/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(voteTopic)))
                .andExpect(status().isOk());

		System.out.println("\n<<done>>\n");

    }
}

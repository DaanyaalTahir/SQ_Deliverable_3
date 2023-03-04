package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

	/**
     * Test The add functions with two binary numbers of the different length, same leading bit
     */
	@Test
    public void add3() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","10110").param("operand2","1101"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10110))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1101))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(100011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

	/**
     * Test The add functions with two binary numbers of the different length, different leading bit
     */
	@Test
    public void add4() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","101010").param("operand2","011"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(101101))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    /**
     * Test The add functions by adding zero to a binary number
     */
	@Test
    public void add5() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","0").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }


    /**
     * Test The bitwiseAnd function with numbers of different length and different leading bits    
    */
	@Test
    public void and1() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","01011").param("operand2","1001"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    /**
     * Test The bitwiseAnd function with numbers of the same length and same leading bits
    */
	@Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","11011").param("operand2","11011"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(11011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    /**
     * Test The bitwiseAnd function with numbers of the same length but different leading bits     
     */
	@Test
    public void and3() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","01011").param("operand2","11011"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    /**
     * Test The bitwiseOr function with numbers of different length and different leading bits     
     */
	@Test
    public void or1() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","1011").param("operand2","01001"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    /**
     * Test The bitwiseOr function with numbers of same length and different leading bits    
     */
	@Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","1100").param("operand2","0101"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1100))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(101))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1101))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    /**
     * Test The bitwiseOr function with numbers of different length and same leading bits    
     */
	@Test
    public void or3() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","1011").param("operand2","11001"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    /**
     * Test The multiply function with numbers of different length, with both leading 1's    
     */
	@Test
    public void multiply1() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","11001").param("operand2","11"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(11001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1001011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    /**
     * Test The multiply function with numbers of same length, with both leading 1's    
     */
	@Test
    public void multiply2() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","100").param("operand2","101"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(100))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(101))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10100))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    /**
     * Test The multiply function with numbers of different length, with both leading 0's   
     */
	@Test
    public void multiply3() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","01001").param("operand2","001"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    /**
     * Test The multiply function with numbers of same length, with both leading 0's  
     */
	@Test
    public void multiply4() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","01100").param("operand2","01010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1100))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111000))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
}
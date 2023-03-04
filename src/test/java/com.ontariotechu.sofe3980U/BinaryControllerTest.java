package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	@Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }

	/**
     * Test The add functions with two binary numbers of the same length, same leading bit
     */
	@Test
    public void postParameter() throws Exception {
    this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1110"))
        .andExpect(model().attribute("operand1", "111"));
    }

	/**
     * Test The add functions with two binary numbers of the different length, same leading bit
     */
    @Test
    public void postParameter1() throws Exception {
    this.mvc.perform(post("/").param("operand1","10110").param("operator","+").param("operand2","1101"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "100011"))
        .andExpect(model().attribute("operand1", "10110"));
    }

	/**
     * Test The add functions with two binary numbers of the different length, different leading bit
     */
    @Test
    public void postParameter2() throws Exception {
    this.mvc.perform(post("/").param("operand1","101010").param("operator","+").param("operand2","011"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "101101"))
        .andExpect(model().attribute("operand1", "101010"));
    }

    /**
     * Test The add functions by adding zero to a binary number
     */
    @Test
    public void postParameter3() throws Exception {
    this.mvc.perform(post("/").param("operand1","0").param("operator","+").param("operand2","1010"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1010"))
        .andExpect(model().attribute("operand1", "0"));
    }

    /**
     * Test The bitwiseAnd function with numbers of different length and different leading bits  
     */
    @Test
    public void postParameter4() throws Exception {
    this.mvc.perform(post("/").param("operand1","01011").param("operator","&").param("operand2","1001"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1001"))
        .andExpect(model().attribute("operand1", "01011"));
    }

    /**
     * Test The bitwiseAnd function with numbers of the same length and same leading bits
     */
    @Test
    public void postParameter5() throws Exception {
    this.mvc.perform(post("/").param("operand1","11011").param("operator","&").param("operand2","11011"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "11011"))
        .andExpect(model().attribute("operand1", "11011"));
    }

    /**
     * Test The bitwiseAnd function with numbers of the same length but different leading bits     
     */
    @Test
    public void postParameter6() throws Exception {
    this.mvc.perform(post("/").param("operand1","01011").param("operator","&").param("operand2","11011"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1011"))
        .andExpect(model().attribute("operand1", "01011"));
    }

    /**
     * Test The bitwiseOr function with numbers of different length and different leading bits     
     */
    @Test
    public void postParameter7() throws Exception {
    this.mvc.perform(post("/").param("operand1","1011").param("operator","|").param("operand2","01001"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1011"))
        .andExpect(model().attribute("operand1", "1011"));
    }

    /**
     * Test The bitwiseOr function with numbers of same length and different leading bits    
     */
    @Test
    public void postParameter8() throws Exception {
    this.mvc.perform(post("/").param("operand1","1100").param("operator","|").param("operand2","0101"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1101"))
        .andExpect(model().attribute("operand1", "1100"));
    }

    /**
     * Test The bitwiseOr function with numbers of different length and same leading bits    
     */
    @Test
    public void postParameter9() throws Exception {
    this.mvc.perform(post("/").param("operand1","1011").param("operator","|").param("operand2","11001"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "11011"))
        .andExpect(model().attribute("operand1", "1011"));
    }

    /**
     * Test The multiply function with numbers of different length, with both leading 1's    
     */
    @Test
    public void postParameter10() throws Exception {
    this.mvc.perform(post("/").param("operand1","11001").param("operator","*").param("operand2","11"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1001011"))
        .andExpect(model().attribute("operand1", "11001"));
    }

    /**
     * Test The multiply function with numbers of same length, with both leading 1's    
     */
    @Test
    public void postParameter11() throws Exception {
    this.mvc.perform(post("/").param("operand1","100").param("operator","*").param("operand2","101"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "10100"))
        .andExpect(model().attribute("operand1", "100"));
    }

    /**
     * Test The multiply function with numbers of different length, with both leading 0's   
     */
    @Test
    public void postParameter12() throws Exception {
    this.mvc.perform(post("/").param("operand1","01001").param("operator","*").param("operand2","001"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1001"))
        .andExpect(model().attribute("operand1", "01001"));
    }

    /**
     * Test The multiply function with numbers of same length, with both leading 0's  
     */
    @Test
    public void postParameter13() throws Exception {
    this.mvc.perform(post("/").param("operand1","01100").param("operator","*").param("operand2","01010"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1111000"))
        .andExpect(model().attribute("operand1", "01100"));
    }


    
}
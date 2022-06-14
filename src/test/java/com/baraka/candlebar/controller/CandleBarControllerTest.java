package com.baraka.candlebar.controller;

import com.baraka.candlebar.entity.CandleBar;
import com.baraka.candlebar.service.CandleBarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandleBarController.class)
public class CandleBarControllerTest {

    @MockBean
    CandleBarService candleBarService;

    @Autowired
    private MockMvc mockMvc;

    CandleBar candleBar = createCandleBar();

    @Test
    public void testGetCandleBar() throws Exception {
        mockMvc.perform(
                        get("/candlebar/get/TSLA")
                                .contentType(APPLICATION_JSON))
                .andExpect(status().is(200));

        verify(candleBarService, times(1)).getStockDetails("TSLA");
    }

    private CandleBar createCandleBar() {
        return new CandleBar(
                "TSLA",
                10D,
                9D,
                11D,
                4D,
                1653009360006L
        );
    }


}

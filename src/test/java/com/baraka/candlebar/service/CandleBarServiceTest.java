package com.baraka.candlebar.service;

import com.baraka.candlebar.dto.Stock;
import com.baraka.candlebar.entity.CandleBar;
import com.baraka.candlebar.repository.CandleBarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandleBarServiceTest {


    @Mock
    private CandleBarRepository candleBarRepository;

    @InjectMocks
    private CandleBarService candleBarService;

    private CandleBar candleBar;

    private Stock stock;

    private List<CandleBar> candleBarList;

    private List<Stock> stockList;

    @BeforeEach
    public void setUp() {

        candleBar = createCandleBar();
        candleBarList = new ArrayList<>();
        candleBarList.add(candleBar);

        stock = createStock();
        stockList = new ArrayList<>();
        stockList.add(stock);

    }

    @AfterEach
    public void tearDown() {
        candleBar = null;
        stock = null;
    }


    @Test
    public void testGetStockDetails() {
        when(candleBarRepository.existsById(1L)).thenReturn(true);
        assertThat(candleBarService.getStockDetails("TSLA")).isEqualTo(true);
    }

    @Test
    public void testSaveCandleBarClient() {
        when(candleBarRepository.save(any())).thenReturn(stockList);
        candleBarService.saveCandleBarClient(stockList);
        verify(candleBarRepository, times(1)).save(any());
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

    private Stock createStock() {
        return new Stock(10D,
                "TSLA",
                1653009360006L,
                10
        );
    }
}

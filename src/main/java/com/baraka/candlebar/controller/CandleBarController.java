package com.baraka.candlebar.controller;


import com.baraka.candlebar.entity.CandleBar;
import com.baraka.candlebar.exception.StockNotFoundException;
import com.baraka.candlebar.service.CandleBarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candlebar")
public class CandleBarController {

    private CandleBarService candleBarService;

    public CandleBarController(CandleBarService candleBarService) {
        this.candleBarService = candleBarService;
    }


    @GetMapping("/get/{stock}")
    public ResponseEntity<List<CandleBar>> getStockDetails(@PathVariable String stock) throws StockNotFoundException{

            List<CandleBar> lst = candleBarService.getStockDetails(stock);
            return new ResponseEntity<List<CandleBar>>(lst, HttpStatus.OK);
    }

}

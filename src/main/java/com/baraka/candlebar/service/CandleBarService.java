package com.baraka.candlebar.service;

import com.baraka.candlebar.dto.Stock;
import com.baraka.candlebar.entity.CandleBar;
import com.baraka.candlebar.exception.StockNotFoundException;
import com.baraka.candlebar.exception.WebServiceErrorException;
import com.baraka.candlebar.repository.CandleBarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class CandleBarService {

    CandleBarRepository candleBarRepository;

    public CandleBarService(CandleBarRepository candleBarRepository) {
        this.candleBarRepository =candleBarRepository;
    }


    public List<CandleBar> getStockDetails(String symbol) {

        List<CandleBar> candleBarList = candleBarRepository.getCandleBarBySymbolEquals(symbol);

        if (candleBarList.isEmpty()) {
            throw new StockNotFoundException();
        }


        return candleBarList;

    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCandleBarClient(List<Stock> data)  {
        if(CollectionUtils.isEmpty(data)){
            throw new WebServiceErrorException();
        }
        for (Stock stock : data) {
            long unixTime = getTimeWithoutSeconds();

            CandleBar candleBar = candleBarRepository.getCandleBarBySymbolEqualsAndDateGreaterThan(stock.getS(), unixTime);
            if (Objects.isNull(candleBar)) {
                candleBar=new CandleBar();
                candleBar.setOpenPrice(stock.getP());
                candleBar.setClosePrice(stock.getP());
                candleBar.setDate(stock.getT());
                candleBar.setSymbol(stock.getS());
                candleBar.setHighestPrice(stock.getP());
                candleBar.setLowestPrice(stock.getP());
            } else {
                candleBar.setHighestPrice(stock.getP() > candleBar.getOpenPrice() ? stock.getP() : candleBar.getOpenPrice());
                candleBar.setClosePrice(stock.getP());
                candleBar.setLowestPrice(stock.getP() < candleBar.getOpenPrice() ? stock.getP() : candleBar.getOpenPrice());
            }
            candleBarRepository.save(candleBar);
        }
    }

    private long getTimeWithoutSeconds() {

        Calendar calendar = Calendar.getInstance();
        // calendar.set(Calendar.MINUTE,0);//delete it for one minute
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();

    }

}

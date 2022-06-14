package com.baraka.candlebar.repository;

import com.baraka.candlebar.entity.CandleBar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandleBarRepository extends CrudRepository<CandleBar, Long> {

    CandleBar getCandleBarBySymbolEqualsAndDateGreaterThan( String symbol, Long date);

    List<CandleBar> getCandleBarBySymbolEquals( String symbol);


}

package com.baraka.candlebar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table
public class CandleBar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String symbol;
    private double openPrice;
    private double closePrice;
    private double highestPrice;
    private double lowestPrice;
    private long date;

    public CandleBar(String symbol, double openPrice, double closePrice, double highestPrice, double lowestPrice, long date) {
        this.symbol = symbol;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.date = date;
    }

    public CandleBar() {

    }

    public int hashCode() {
        return Objects.hash(id, symbol, date, openPrice, closePrice, highestPrice, lowestPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CandleBar other = (CandleBar) obj;
        return Objects.equals(id, other.id);
    }


}

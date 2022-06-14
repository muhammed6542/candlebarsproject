package com.baraka.candlebar.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class CandleClient {

    private List<Stock> data = null;
    private String type;

    @Override
    public int hashCode() {
        return Objects.hash(data, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CandleClient other = (CandleClient) obj;
        return Objects.equals(data, other.data) && Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return "Example [data=" + data + ", type=" + type + "]";
    }


}

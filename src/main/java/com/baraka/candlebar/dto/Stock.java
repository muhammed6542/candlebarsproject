package com.baraka.candlebar.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    private Double p;
    private String s;
    private Long t;
    private Integer v;

    public Stock(Double p , String s , Long t , Integer v ){
        this.p=p;
        this.s=s;
        this.t=t;
        this.v=v;
    }

    public Stock (){
    }


}

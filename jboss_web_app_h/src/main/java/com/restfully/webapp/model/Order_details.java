package com.restfully.webapp.model;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
public class Order_details {

    public int id;
    public int orders_id;
    public int additional_service_id;
    public int number;
    public int price;
    
    public Order_details(int id, int orders_id, int additional_service_id, int number, int price) {
        this.id = id;
        this.orders_id = orders_id;
        this.additional_service_id = additional_service_id;
        this.number = number;
        this.price = price;
    }

    public String toJsonString() {
        String JsonString;
        JsonString = "";
        if (id != 0)
        {JsonString = JsonString + "\"id\":" + id;}
        if (orders_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"orders_id\":" + orders_id;}
        if (additional_service_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"additional_service_id\":" + additional_service_id;}
        {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"number\":" + number
                    + "\"price\":" + price;}
        return ("{"+JsonString+"}");
    }

}
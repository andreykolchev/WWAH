package com.restfully.webapp.model;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
public class Orders {

    public int id;
    public int order_number;
    public String order_date;
    public String description;
    public int account_id;
    public int car_id;
    public int get_service_location_id;
    public String get_date_time;
    public int put_service_location_id;
    public String put_date_time;
    
    public Orders(int id, int order_number, String order_date, String description, int account_id, int car_id, int get_service_location_id, String get_date_time, int put_service_location_id, String put_date_time) {
        this.id = id;
        this.order_number = order_number;
        this.order_date = order_date;
        this.description = description;
        this.account_id = account_id;
        this.car_id = car_id;
        this.get_service_location_id = get_service_location_id;
        this.get_date_time = get_date_time;
        this.put_service_location_id = put_service_location_id;
        this.put_date_time = put_date_time;
    }
 
    public String toJsonString() {
        String JsonString;
        JsonString = "";
        if (id != 0)
        {JsonString = JsonString + "\"id\":" + id;}
        if (order_number != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"order_number\":" + order_number;}
        if (!(order_date.equals("")))
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"order_date\":\"" + order_date + "\"";}
        {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"description\":\"" + description + "\"";}
        if (account_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"account_id\":" + account_id;}
        if (car_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"car_id\":" + car_id;}
        if (get_service_location_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"get_service_location_id\":" + get_service_location_id;}
        if (!(get_date_time.equals("")))
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"get_date_time\":\"" + get_date_time + "\"";}
        if (put_service_location_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"put_service_location_id\":" + put_service_location_id;}
        if (!(put_date_time.equals("")))
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"put_date_time\":\"" + put_date_time + "\"";}
        return ("{"+JsonString+"}");
    }
}

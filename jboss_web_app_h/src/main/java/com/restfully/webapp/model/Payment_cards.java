package com.restfully.webapp.model;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
public class Payment_cards {

    public int id;
    public int account_id;
    public String holder_name;
    public int number;
    public int end_month;
    public int end_year;
    
    public Payment_cards(int id, int account_id, String holder_name, int number, int end_month, int end_year) {
        this.id = id;
        this.account_id = account_id;
        this.holder_name = holder_name;
        this.number = number;
        this.end_month = end_month;
        this.end_year = end_year;
    }

    public String toJsonString() {
        String JsonString;
        JsonString = "";
        if (id != 0)
        {JsonString = JsonString + "\"id\":" + id;}
        if (account_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"account_id\":" + account_id;}
        if (!(holder_name.equals("")))
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"holder_name\":" + holder_name + "\"";}
        if (number != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"number\":" + number;}
        if (end_month != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"end_month\":" + end_month;}
        if (end_year != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"end_year\":" + end_year;}
        return ("{"+JsonString+"}");
    }

}
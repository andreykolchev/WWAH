package com.restfully.webapp.model;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
public class Driver {

    public int id;
    public int account_id;
    public String name;
    public String date_of_birth;
    
    public Driver(int id, int account_id, String name, String date_of_birth) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
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
        if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
        JsonString = JsonString + "\"name\":\"" + name + "\""
                + ",\"date_of_birth\":\"" + date_of_birth + "\"";
        return ("{"+JsonString+"}");
    }

}
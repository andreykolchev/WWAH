package com.restfully.webapp.model;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
public class City {

    public int id;
    public int language_id;
    public int country_id;
    public String name;

    public City(int id, int language_id, int country_id, String name) {
        this.id = id;
        this.language_id = language_id;
        this.country_id = country_id;
        this.name = name;
    }

    public String toJsonString() {
        String JsonString;
        JsonString = "";
        if (id != 0)
        {JsonString = JsonString + "\"id\":" + id;}
        if (language_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"language_id\":" + language_id;}
        if (country_id != 0)
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"country_id\":" + country_id;}
        if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
        JsonString = JsonString + "\"name\":\"" + name + "\"";
        return ("{"+JsonString+"}");
    }

}
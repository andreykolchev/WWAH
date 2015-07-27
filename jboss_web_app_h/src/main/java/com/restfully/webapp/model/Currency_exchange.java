package com.restfully.webapp.model;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
public class Currency_exchange {

    public int id;
    public int course;
    public int multiplicity;
    
    public Currency_exchange(int id, int course, int multiplicity) {
        this.id = id;
        this.course = course;
        this.multiplicity = multiplicity;
    }

    public String toJsonString() {
        String JsonString;
        JsonString = "";
        if (id != 0)
        {JsonString = JsonString + "\"id\":" + id;}
        if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
        JsonString = JsonString + "\"course\":\"" + course
                + ",\"multiplicity\":\"" + multiplicity;
        return ("{"+JsonString+"}");
    }

}
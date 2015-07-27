package com.restfully.webapp.model;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
public class Language {

    public int id;
    public String name;

    public Language(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toJsonString() {
        String JsonString;
        JsonString = "";
        if (id != 0) {JsonString = "\"id\":" + id + ",\"name\":\"" + name + "\"";}
        return ("{" + JsonString + "}");
    }

}

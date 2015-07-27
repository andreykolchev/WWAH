package com.restfully.webapp.model;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
public class InterfaceItem {

    public int id;
    public int language_id;
    public String name;
    public String value;

    public InterfaceItem(int id, int language_id, String name, String value) {
        this.id = id;
        this.language_id = language_id;
        this.name = name;
        this.value = value;
    }

    public String toJsonString() {
        return ("{\"id\":" + id
                + ",\"language_id\":\"" + language_id + "\""
                + ",\"name\":\"" + name + "\""
                + ",\"value\":\"" + value + "\""
                + "}");
    }
}

package com.restfully.webapp.model;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
public class Account {

    public int id;
    public String user_name;
    public String pass;
    public String e_mail;
    public int currency_id;
    public String date_of_birth;

    public Account(int id, String user_name, String pass, String e_mail, int currency_id, String date_of_birth) {
        this.id = id;
        this.user_name = user_name;
        this.pass = pass;
        this.e_mail = e_mail;
        this.currency_id = currency_id;
        this.date_of_birth = date_of_birth;
        
    }

    public String toJsonString() {
        String JsonString;
        JsonString = "";
        JsonString = JsonString + "\"id\":" + id;
        if (!(user_name.equals("")))
            {if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
            JsonString = JsonString + "\"user_name\":" + user_name + "\"";}
        if (!(JsonString.equals("")))
            {JsonString = JsonString + ",";}
        JsonString = JsonString + "\"pass\":\"" + pass + "\""
                + ",\"e_mail\":\"" + e_mail + "\""
                + ",\"currency_id\":" + currency_id
                + ",\"date_of_birth\":\"" + date_of_birth + "\"";
        return ("{"+JsonString+"}");
    }

}
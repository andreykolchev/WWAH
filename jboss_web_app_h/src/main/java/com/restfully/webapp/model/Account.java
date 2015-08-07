package com.restfully.webapp.model;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import lombok.Data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@Entity(name = "account")
public class Account implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)  
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "pass")
    private String pass;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Card> cards = new HashSet<Card>(0);
    
    public String toJsonString() {
        StringBuilder JsonString = new StringBuilder("");
        JsonString = JsonString.append("{");
        JsonString = JsonString.append("\"id\":" + id);
        JsonString = JsonString.append(",\"name\":\"" + name + "\"");
        JsonString = JsonString.append(",\"pass\":\"" + pass + "\"");
        JsonString = JsonString.append("}");
        return (JsonString.toString());
    }

}

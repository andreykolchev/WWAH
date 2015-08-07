package com.restfully.webapp.model;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "card")
public class Card implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)  
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "card_id")
    private Long card_id;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public String toJsonString() {
        StringBuilder JsonString = new StringBuilder("");
        JsonString = JsonString.append("{");
        JsonString = JsonString.append("\"id\":" + id);
        JsonString = JsonString.append(",\"card_id\":" + card_id);
        JsonString = JsonString.append(",\"name\":\"" + name + "\"");
        JsonString = JsonString.append("}");
        return (JsonString.toString());
    }

}

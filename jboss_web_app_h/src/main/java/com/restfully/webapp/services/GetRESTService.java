package com.restfully.webapp.services;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */

import com.restfully.webapp.DAO.CardDAO;
import com.restfully.webapp.model.Card;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/get")
public class GetRESTService {


    private final CardDAO cardDAO = new CardDAO();
   
    
    @GET
    @Path("/card")
    @Produces("application/javascript")
    public String getAccCards (@QueryParam("callback") String callback, 
                               @QueryParam("account_id") int account_id){
        List<Card> cardList = cardDAO.getByAccId(account_id);
        if (cardList==null) {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Card card : cardList) {
            jsonStringBuilder.append(card.toJsonString());
            if (++i < cardList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }

      
}

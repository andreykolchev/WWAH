package com.restfully.webapp.services;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */

import com.restfully.webapp.DAO.CountryDAO;
import com.restfully.webapp.model.Country;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/get")
public class GetRESTService {


    private final CountryDAO countryDAO = new CountryDAO();
   
    
    @GET
    @Path("/country")
    @Produces("application/javascript")
    public String getCounry (@QueryParam("callback") String callback, 
                             @QueryParam("country_id") int country_id, 
                             @QueryParam("language_id") int language_id
                            ) throws SQLException{
        List<Country> countryList = countryDAO.find(country_id, language_id);
        if (countryList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Country country : countryList) {
            jsonStringBuilder.append(country.toJsonString());
            if (++i < countryList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
      
}

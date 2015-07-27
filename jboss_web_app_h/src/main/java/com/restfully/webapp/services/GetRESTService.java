package com.restfully.webapp.services;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.DAO.AccountDAO;
import com.restfully.webapp.DAO.Additional_serviceDAO;
import com.restfully.webapp.DAO.CarDAO;
import com.restfully.webapp.DAO.CityDAO;
import com.restfully.webapp.DAO.CountryDAO;
import com.restfully.webapp.DAO.CurrencyDAO;
import com.restfully.webapp.DAO.Currency_exchangeDAO;
import com.restfully.webapp.DAO.DriverDAO;
import com.restfully.webapp.DAO.InterfaceItemDAO;
import com.restfully.webapp.DAO.LanguageDAO;
import com.restfully.webapp.DAO.Order_detailsDAO;
import com.restfully.webapp.DAO.OrdersDAO;
import com.restfully.webapp.DAO.Payment_cardsDAO;
import com.restfully.webapp.DAO.Service_locationDAO;
import com.restfully.webapp.model.Account;
import com.restfully.webapp.model.Additional_service;
import com.restfully.webapp.model.Car;
import com.restfully.webapp.model.City;
import com.restfully.webapp.model.Country;
import com.restfully.webapp.model.Currency;
import com.restfully.webapp.model.Currency_exchange;
import com.restfully.webapp.model.Driver;
import com.restfully.webapp.model.InterfaceItem;
import com.restfully.webapp.model.Language;
import com.restfully.webapp.model.Order_details;
import com.restfully.webapp.model.Orders;
import com.restfully.webapp.model.Payment_cards;
import com.restfully.webapp.model.Service_location;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/get")
public class GetRESTService {

    private final AccountDAO accountDAO = new AccountDAO();
    private final Additional_serviceDAO additional_serviceDAO = new Additional_serviceDAO();    
    private final CarDAO carDAO = new CarDAO();
    private final CityDAO cityDAO = new CityDAO();
    private final CountryDAO countryDAO = new CountryDAO();
    private final CurrencyDAO currencyDAO = new CurrencyDAO();
    private final Currency_exchangeDAO currency_exchangeDAO = new Currency_exchangeDAO();
    private final DriverDAO driverDAO = new DriverDAO();
    private final LanguageDAO languageDAO = new LanguageDAO();
    private final InterfaceItemDAO interfaceItemDAO = new InterfaceItemDAO();
    private final Order_detailsDAO order_detailsDAO = new Order_detailsDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();
    private final Payment_cardsDAO payment_cardsDAO = new Payment_cardsDAO();
    private final Service_locationDAO service_locationDAO = new Service_locationDAO();
    
    @GET
    @Path("/account")
    @Produces("application/javascript")
    public String getAccount (@QueryParam("callback") String callback, 
                              @QueryParam("user_name") String user_name
                             ) throws SQLException{
        List<Account> accountList = accountDAO.find(user_name);
        if (accountList.isEmpty()) 
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Account account : accountList) {
            jsonStringBuilder.append(account.toJsonString());
            if (++i < accountList.size()) {
                jsonStringBuilder.append(",");
           }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/additional_service")
    @Produces("application/javascript")
    public String getAdditional_service (@QueryParam("callback") String callback, 
                                         @QueryParam("additional_service_id") int additional_service_id, 
                                         @QueryParam("language_id") int language_id,
                                         @QueryParam("currency_id") int currency_id
                                        ) throws SQLException{
        List<Additional_service> additional_serviceList = additional_serviceDAO.find(additional_service_id,language_id, currency_id);
        if (additional_serviceList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Additional_service additional_service : additional_serviceList) {
            jsonStringBuilder.append(additional_service.toJsonString());
            if (++i < additional_serviceList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/car")
    @Produces("application/javascript")
    public String getCar (@QueryParam("callback") String callback, 
                          @QueryParam("car_id") int car_id, 
                          @QueryParam("language_id") int language_id,
                          @QueryParam("currency_id") int currency_id
                         )  throws SQLException{
        List<Car> carList = carDAO.find(car_id, language_id, currency_id);
        if (carList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Car car : carList) {
            jsonStringBuilder.append(car.toJsonString());
            if (++i < carList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/city")
    @Produces("application/javascript")
    public String getCity (@QueryParam("callback") String callback, 
                           @QueryParam("country_id") int country_id, 
                           @QueryParam("language_id") int language_id, 
                           @QueryParam("city_id") int city_id
                          ) throws SQLException{
        List<City> cityList = cityDAO.find(country_id,language_id,city_id);
        if (cityList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (City city : cityList) {
            jsonStringBuilder.append(city.toJsonString());
            if (++i < cityList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
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
    
    @GET
    @Path("/currency")
    @Produces("application/javascript")
    public String getCurrency (@QueryParam("callback") String callback, 
                               @QueryParam("currency_id") int currency_id, 
                               @QueryParam("language_id") int language_id
                              ) throws SQLException{
        List<Currency> currencyList = currencyDAO.find(currency_id, language_id);
        if (currencyList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Currency currency : currencyList) {
            jsonStringBuilder.append(currency.toJsonString());
            if (++i < currencyList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/currency_exchange")
    @Produces("application/javascript")
    public String getCurrency_exchange (@QueryParam("callback") String callback, 
                                        @QueryParam("currency_id") int currency_id
                                       ) throws SQLException{
        List<Currency_exchange> currency_exchangeList = currency_exchangeDAO.find(currency_id);
        if (currency_exchangeList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Currency_exchange currency_exchange : currency_exchangeList) {
            jsonStringBuilder.append(currency_exchange.toJsonString());
            if (++i < currency_exchangeList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/driver")
    @Produces("application/javascript")
    public String getDriver (@QueryParam("callback") String callback, 
                             @QueryParam("account_id") int account_id, 
                             @QueryParam("id") int id
                            ) throws SQLException{
        List<Driver> driverList = driverDAO.find(account_id, id);
        if (driverList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Driver driver : driverList) {
            jsonStringBuilder.append(driver.toJsonString());
            if (++i < driverList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/language")
    @Produces("application/javascript")
    public String getLanguage (@QueryParam("callback") String callback, 
                               @QueryParam("id") int id, 
                               @QueryParam("name") String name
                              ) throws SQLException{
        List<Language> languageList = languageDAO.find(id, name);
        if (languageList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Language language : languageList) {
            jsonStringBuilder.append(language.toJsonString());
            if (++i < languageList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/interface")
    @Produces("application/javascript")
    public String getInterfaceLanguage(@QueryParam("callback") String callback,
                                       @QueryParam("language_id") int language_id) throws SQLException {
        List<InterfaceItem> interfaceItemList = interfaceItemDAO.findByLanguageId(language_id);
        if (interfaceItemList.isEmpty()) {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (InterfaceItem interfaceItem  : interfaceItemList) {
            jsonStringBuilder.append(interfaceItem.toJsonString());
            if (++i < interfaceItemList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    
    @GET
    @Path("/order_details")
    @Produces("application/javascript")
    public String getOrder_details (@QueryParam("callback") String callback, 
                                    @QueryParam("id") int id, 
                                    @QueryParam("orders_id") int orders_id, 
                                    @QueryParam("additional_service_id") int additional_service_id
                                   ) throws SQLException{
        List<Order_details> order_detailsList = order_detailsDAO.find(id, orders_id, additional_service_id);
        if (order_detailsList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Order_details order_details : order_detailsList) {
            jsonStringBuilder.append(order_details.toJsonString());
            if (++i < order_detailsList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/orders")
    @Produces("application/javascript")
    public String getOrders (@QueryParam("callback") String callback, 
                             @QueryParam("id") int id, 
                             @QueryParam("order_number") int order_number, 
                             @QueryParam("order_date") String order_date, 
                             @QueryParam("account_id") int account_id, 
                             @QueryParam("car_id") int car_id, 
                             @QueryParam("get_service_location_id") int get_service_location_id, 
                             @QueryParam("get_date_time") String get_date_time, 
                             @QueryParam("put_service_location_id") int put_service_location_id, 
                             @QueryParam("put_date_time") String put_date_time
                            ) throws SQLException{
        List<Orders> ordersList = ordersDAO.find(id, order_number, order_date, account_id, car_id, get_service_location_id, get_date_time, put_service_location_id, put_date_time);
        if (ordersList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Orders orders : ordersList) {
            jsonStringBuilder.append(orders.toJsonString());
            if (++i < ordersList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/payment_cards")
    @Produces("application/javascript")
    public String getPayment_cards (@QueryParam("callback") String callback, 
                                    @QueryParam("id") int id, 
                                    @QueryParam("account_id") int account_id, 
                                    @QueryParam("holder_name") String holder_name, 
                                    @QueryParam("number") int number, 
                                    @QueryParam("end_month") int end_month, 
                                    @QueryParam("end_year") int end_year
                                   ) throws SQLException{
        List<Payment_cards> payment_cardsList = payment_cardsDAO.find(id, account_id, holder_name, number, end_month, end_year);
        if (payment_cardsList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Payment_cards payment_cards : payment_cardsList) {
            jsonStringBuilder.append(payment_cards.toJsonString());
            if (++i < payment_cardsList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }
    
    @GET
    @Path("/service_location")
    @Produces("application/javascript")
    public String getService_location (@QueryParam("callback") String callback, 
                                       @QueryParam("city_id") int city_id, 
                                       @QueryParam("language_id") int language_id, 
                                       @QueryParam("service_location_id") int service_location_id
                                      ) throws SQLException{
        List<Service_location> service_locationList = service_locationDAO.find(city_id,language_id,service_location_id);
        if (service_locationList.isEmpty())
        //{throw new WebApplicationException(Response.Status.NO_CONTENT);}
        {return (callback + "()");}
        StringBuilder jsonStringBuilder = new StringBuilder();
        int i = 0;
        for (Service_location service_location : service_locationList) {
            jsonStringBuilder.append(service_location.toJsonString());
            if (++i < service_locationList.size()) {
                jsonStringBuilder.append(",");
            }
        }
        return (callback + "([" + jsonStringBuilder.toString() + "])");
    }

}

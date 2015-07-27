package com.restfully.webapp.services;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.DAO.AccountDAO;
import com.restfully.webapp.DAO.DriverDAO;
import com.restfully.webapp.DAO.Order_detailsDAO;
import com.restfully.webapp.DAO.OrdersDAO;
import com.restfully.webapp.DAO.Payment_cardsDAO;
import com.restfully.webapp.model.Account;
import com.restfully.webapp.model.Driver;
import com.restfully.webapp.model.Order_details;
import com.restfully.webapp.model.Orders;
import com.restfully.webapp.model.Payment_cards;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;



@Path("/post")
public class PostRESTService {
 
    private final AccountDAO accountDAO = new AccountDAO();
    private final DriverDAO driverDAO = new DriverDAO();
    private final Order_detailsDAO order_detailsDAO = new Order_detailsDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();
    private final Payment_cardsDAO payment_cardsDAO = new Payment_cardsDAO();
    private final AtomicInteger idCounter = new AtomicInteger();
    
    @GET
    @Path("/account")
    @Produces("application/javascript")
    public String createAccount (@QueryParam("callback") String callback,
                                 @QueryParam("user_name") String user_name,
                                 @QueryParam("pass") String pass,
                                 @QueryParam("e_mail") String e_mail,
                                 @QueryParam("currency_id") int currency_id,
                                 @QueryParam("date_of_birth") String date_of_birth
                                ) throws SQLException {
        int id = 0;
        
        Account account = new Account(id, user_name, pass, e_mail, currency_id, date_of_birth);
           
        account = accountDAO.create(account);
        
        return (callback + "([" + account.toJsonString() + "])");
        
    }
    
    @GET
    @Path("/driver")
    @Produces("application/javascript")
    public String createDriver (@QueryParam("callback") String callback,
                                 @QueryParam("name") String name,
                                 @QueryParam("date_of_birth") String date_of_birth,
                                 @QueryParam("account_id") int account_id
                                ) throws SQLException {
        int id = 0;
        
        Driver driver = new Driver(id, account_id, name, date_of_birth);
           
        driver = driverDAO.create(driver);
        
        return (callback + "([" + driver.toJsonString() + "])");
        
    }
    
    @GET
    @Path("/order_details")
    @Produces("application/javascript")
    public String createOrderDetails (@QueryParam("callback") String callback,
                                 @QueryParam("orders_id") int orders_id,
                                 @QueryParam("additional_service_id") int additional_service_id,
                                 @QueryParam("number") int number,
                                 @QueryParam("price") int price
                                ) throws SQLException {
        int id = 0;
        
        Order_details order_details = new Order_details(id, orders_id, additional_service_id, number, price);
           
        order_details = order_detailsDAO.create(order_details);
        
        return (callback + "([" + order_details.toJsonString() + "])");
        
    }
       
    @GET
    @Path("/orders")
    @Produces("application/javascript")
    public String createOrder (@QueryParam("callback") String callback,
                               @QueryParam("description") String description,
                               @QueryParam("account_id") int account_id,
                               @QueryParam("car_id") int car_id,
                               @QueryParam("get_service_location_id") int get_service_location_id,
                               @QueryParam("get_date_time") String get_date_time,
                               @QueryParam("put_service_location_id") int put_service_location_id,
                               @QueryParam("put_date_time") String put_date_time
                              ) throws SQLException {
        int id = 0;
        String Date = "";
        
        Orders orders = new Orders(id, id, Date, description, account_id, car_id, get_service_location_id, get_date_time, put_service_location_id, put_date_time);
           
        orders = ordersDAO.create(orders);
        
        return (callback + "([" + orders.toJsonString() + "])");
        
    }
    
    @GET
    @Path("/payment_cards")
    @Produces("application/javascript")
    public String createDriver (@QueryParam("callback") String callback,
                                 @QueryParam("account_id") int account_id,
                                 @QueryParam("holder_name") String holder_name,
                                 @QueryParam("number") int number,
                                 @QueryParam("end_month") int end_month,
                                 @QueryParam("end_year") int end_year
                                ) throws SQLException {
        int id = 0;
        
        Payment_cards payment_cards = new Payment_cards(id, account_id, holder_name, number, end_month, end_year);
           
        payment_cards = payment_cardsDAO.create(payment_cards);
        
        return (callback + "([" + payment_cards.toJsonString() + "])");
        
    }
    
     
}

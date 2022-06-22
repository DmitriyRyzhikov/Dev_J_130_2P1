
package Dev_J_130;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Repository {
    
    public List<Products> getProductsList(){
        List<Products> productsList = new ArrayList<>();
        try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB", "Pronard", "Pronard20");  
            Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM products");
            while(rs.next()){
                Products products = new Products();
                products.setArticle(rs.getString(1));
                products.setProduct_name(rs.getString(2));
                products.setProduct_color(rs.getString(3));
                products.setProduct_price(rs.getInt(4));
                products.setProduct_in_stock(rs.getInt(5)); 
                productsList.add(products);
            }           
        }
        catch(SQLException se){
            System.out.println("Произошла какая-то ошибка"); }
    return productsList;
    }    
      
    public List<Orders> getOrdersList(){
        List<Orders> ordersList = new ArrayList<>();
        try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB", "Pronard", "Pronard20");  
            Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM orders");
            while(rs.next()){
                Orders orders = new Orders();
                orders.setOrder_id(rs.getInt(1));
                orders.setOrder_date(rs.getDate(2) != null? rs.getDate(2).toLocalDate(): null);
                orders.setСustomer_name(rs.getString(3));
                orders.setСustomer_phone(rs.getString(4));
                orders.setСustomer_email(rs.getString(5)); 
                orders.setShipment_address(rs.getString(6));
                orders.setOrder_status(rs.getString(7));
                orders.setShipment_date(rs.getDate(8) != null? rs.getDate(8).toLocalDate(): null); //NullPointerExeption, если сделать напрямую.
                ordersList.add(orders);
            }           
        }
        catch(SQLException se){
            System.out.println("Произошла какая-то ошибка"); }        
        return ordersList;
    }
    
    public List<Order_positions> getOrder_positionsList(){
        List<Order_positions> orders_positionsList = new ArrayList<>();
        try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB", "Pronard", "Pronard20");  
            Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM order_positions");
            while(rs.next()){
                Order_positions ordPos = new Order_positions();
                ordPos.setOrder_cod(rs.getInt(1));
                ordPos.setProduct_article(rs.getString(2));
                ordPos.setOrder_price(rs.getInt(3)); 
                ordPos.setAmount(rs.getInt(4));
                orders_positionsList.add(ordPos);
            }           
        }
        catch(SQLException se){
            System.out.println("Произошла какая-то ошибка"); }     
        
        return orders_positionsList;
    }
    
/*Загрузка и печать списка наименований товаров заказов с заданными id; наименование 
товара должно включать цвет, если он задан. В методе реализована проверка параметра.
Если номер заказа отсутсвует в базе данных, выводится соответствующее сообщение.    
*/
    public void getProductsAndPrint(int id){
        
        int order_cod;
        String product_name;
        String product_color;
        
        List<Integer> ordersID = new ArrayList<>();        
        getOrdersList().forEach(x -> ordersID.add(x.getOrder_id()));        
        if(ordersID.contains(id)) {
         
           System.out.println("Список наименований товаров для заказа с номером " + id + ":");
        
           try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB", "Pronard", "Pronard20");
                Statement stm = con.createStatement()){
               ResultSet rs = stm.executeQuery("SELECT order_cod, product_name, product_color FROM  products INNER JOIN order_positions ON order_cod=" 
                                                + id + "AND  article=product_article");
                while(rs.next()){
                      order_cod = rs.getInt(1);
                      product_name = rs.getString(2);
                      product_color = rs.getString(3);
                      System.out.println("номер заказа: " + order_cod + ", наименование товара: " + product_name + ", цвет товара: " 
                                         + (product_color != null? product_color : "в спецификации товара цвет не указан."));
                      }
                }
            catch(SQLException se){
                  System.out.println("Произошла какая-то ошибка");
                 }
        }
        else
            System.out.println("Указанный номер заказа - " + id + " в базе данных отсутствует.");
    }
/*    
 Метод, реализующий регистрацию заказа; атрибуты заказа задаются параметрами метода; при регистрации 
задаются следующие параметры: 
o ФИО заказчика;
o контактный телефон;
o адрес эл. почты;
o адрес доставки;
o артикулы товаров, включённых в заказ;
o количество товара для каждой позиции заказа.
*/
    public void newOrder(String сustomer_name, String сustomer_phone, String сustomer_email, String shipment_address, Map<String, Integer> goodsToByu){
          //int res = 0;
          String sql = "INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB", "Pronard", "Pronard20");
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, lastOrderNumber() + 1);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setString(3, сustomer_name);
            ps.setString(4, сustomer_phone);
            ps.setString(5, сustomer_email);
            ps.setString(6, shipment_address);
            ps.setString(7, "P");
            ps.setDate(8, null);
            ps.executeUpdate();  
            
        }catch(SQLException se){
            System.out.println("Произошла какая-то ошибка");
        }
    }
    
/*
    Метод получает массив номеров существующих заказов из таблицы orders, 
    сортирует их по возрастанию и возвращает максимальный номер. Этот номер 
    считается последним использованным номером для нумерации заказов. 
    При регистрации нового заказа, ему присваивается этот номер + 1.
    
    */    
    public int lastOrderNumber(){
           List<Orders> ordersList = getOrdersList();
           List<Integer> orderNumbers = new ArrayList<>();
           ordersList.forEach(x -> orderNumbers.add(x.getOrder_id()));
           Collections.sort(orderNumbers);
        return orderNumbers.get(orderNumbers.size()-1);
    }
    
}

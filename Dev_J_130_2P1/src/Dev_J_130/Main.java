package Dev_J_130;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        
        Repository repo = new Repository();
        
        //выводим на печать полное содержимое трех таблиц нашей БД: 
        repo.getProductsList().forEach(System.out::println); 
        System.out.println();
        repo.getOrdersList().forEach(System.out::println); 
        System.out.println();
        repo.getOrder_positionsList().forEach(System.out::println); 
        System.out.println();
        
    /*Загрузка и печать списка наименований товаров заказов с заданными id; наименование 
     товара должно включать цвет, если он задан.
    */    
        repo.getProductsAndPrint(3); 
        
        Map<String, Integer> order = new HashMap<>();
        order.put("3251615", 2);
        order.put("3251620", 4);
        order.put("3251619", 4);
        
        repo.newOrder("Dmitry", "559-60-21", "dmitry@dtp.spb.ru", "Saint-Petersburg", order);

        
    }
    
}

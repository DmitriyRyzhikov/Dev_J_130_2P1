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
        
        System.out.println();
    /*
        Набираем товар в корзину и делаем заказ.
    */    
        Map<String, Integer> shoppingСart = new HashMap<>();
        
        shoppingСart.put("3251615", 2);
        shoppingСart.put("3251617", 3);
        shoppingСart.put("3251619", 4);
        
    //регистрируем новый заказ.    
        repo.newOrder("Брежнев Леонид Ильич", "100-00-00", "ilyich@kpss-rulit.su", "Москва, Красная площадь, Кремль", shoppingСart);

  
    }
    
}

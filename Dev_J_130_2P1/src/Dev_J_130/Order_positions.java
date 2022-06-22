
package Dev_J_130;

public class Order_positions {
    
    private Integer order_cod;
    private String product_article;
    private Integer order_price;
    private Integer amount;

    public Order_positions() { }

    public Order_positions(Integer order_cod, String product_article, 
                           Integer order_price, Integer amount) {
        this.order_cod = order_cod;
        this.product_article = product_article;
        this.order_price = order_price;
        this.amount = amount;
    }

    public Integer getOrder_cod() {
        return order_cod;
    }

    public void setOrder_cod(Integer order_cod) {
        this.order_cod = order_cod;
    }

    public String getProduct_article() {
        return product_article;
    }

    public void setProduct_article(String product_article) {
        this.product_article = product_article;
    }

    public Integer getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Integer order_price) {
        this.order_price = order_price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Позиции заказа {код записи заказа = " + order_cod + ", артикул товара = " + 
                product_article + ", цена  = " + order_price + ", количество  = " + 
                amount + '}';
    }  
}

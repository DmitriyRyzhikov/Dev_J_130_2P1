
package Dev_J_130;

public class Products {
    
    private String article;
    private String product_name;
    private String product_color;
    private int product_price;
    private int product_in_stock;

    public Products() { }

    public Products(String article, String product_name, String product_color, int product_price, int product_in_stock) {
        this.article = article;
        this.product_name = product_name;
        this.product_color = product_color;
        this.product_price = product_price;
        this.product_in_stock = product_in_stock;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_in_stock() {
        return product_in_stock;
    }

    public void setProduct_in_stock(int product_in_stock) {
        this.product_in_stock = product_in_stock;
    }

    @Override
    public String toString() {
        return "Продукт {артикул = " + article + ", наименование = " + 
                product_name + ", цвет = " + product_color + ", цена = " + 
                product_price + ", остаток на складе = " + product_in_stock + '}';
    }
    
    
    
}

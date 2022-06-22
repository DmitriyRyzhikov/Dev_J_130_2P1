
package Dev_J_130;

import java.time.LocalDate;


public class Orders {
    
    private Integer order_id;
    private LocalDate order_date;
    private String сustomer_name;
    private String сustomer_phone;
    private String сustomer_email;
    private String shipment_address;
    private String order_status;
    private LocalDate shipment_date;

    public Orders() { }
    
    public Orders(Integer order_id, LocalDate order_date, String сustomer_name, 
                  String сustomer_phone, String сustomer_email, String shipment_address, 
                  String order_status, LocalDate shipment_date) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.сustomer_name = сustomer_name;
        this.сustomer_phone = сustomer_phone;
        this.сustomer_email = сustomer_email;
        this.shipment_address = shipment_address;
        this.order_status = order_status;
        this.shipment_date = shipment_date;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public String getСustomer_name() {
        return сustomer_name;
    }

    public void setСustomer_name(String сustomer_name) {
        this.сustomer_name = сustomer_name;
    }

    public String getСustomer_phone() {
        return сustomer_phone;
    }

    public void setСustomer_phone(String сustomer_phone) {
        this.сustomer_phone = сustomer_phone;
    }

    public String getСustomer_email() {
        return сustomer_email;
    }

    public void setСustomer_email(String сustomer_email) {
        this.сustomer_email = сustomer_email;
    }

    public String getShipment_address() {
        return shipment_address;
    }

    public void setShipment_address(String shipment_address) {
        this.shipment_address = shipment_address;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public LocalDate getShipment_date() {
        return shipment_date;
    }

    public void setShipment_date(LocalDate shipment_date) {
        this.shipment_date = shipment_date;
    }

    @Override
    public String toString() {
        return "Заказы {идентификатор = " + order_id + ", дата создания = " + 
                order_date + ", ФИО заказчика = " + сustomer_name + 
                ", контактный телефон = " + сustomer_phone + ", адрес эл. почты = " + 
                сustomer_email + ", адрес доставки = " + shipment_address + 
                ", статус заказа = " + order_status + ", дата отгрузки заказа = " +
                shipment_date + '}';
    }  
}

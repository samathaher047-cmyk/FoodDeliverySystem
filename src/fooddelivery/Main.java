package fooddelivery;

public class Main {

    public static void main(String[] args) {

        Meal meal = new Meal("Burger", 10.0, 2);
        Drink drink = new Drink("Cola", 3.0, true);

        Order order = new Order("Sama", new StandardDelivery());

        order.addItem(meal, 2);
        order.addItem(drink);

        System.out.println("Order Number: " + order.getOrderNumber());
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Subtotal: " + order.getSubtotal());
        System.out.println("Delivery Fee: " + order.getDeliveryOption().getFee());
        System.out.println("Total before payment: " + order.getTotal());

        double paidAmount = order.pay(new CardPayment());

        System.out.println("Amount paid by card: " + paidAmount);
        System.out.println("Order Status: " + order.getStatus());

        order.deliver();

        System.out.println("Final Status: " + order.getStatus());
    }
}
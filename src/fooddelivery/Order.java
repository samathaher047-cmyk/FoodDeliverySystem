package fooddelivery;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int nextOrderNumber = 1001;

    private int orderNumber;
    private String customerName;
    private OrderStatus status;
    private List<OrderLine> items;
    private DeliveryOption deliveryOption;

    public Order(String customerName, DeliveryOption deliveryOption) {
        this.orderNumber = nextOrderNumber++;
        this.customerName = customerName;
        this.deliveryOption = deliveryOption;
        this.status = OrderStatus.NEW;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        addItem(item, 1);
    }

    public void addItem(MenuItem item, int quantity) {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException(
                "Cannot add items unless order is NEW"
            );
        }

        if (item == null) {
            throw new IllegalArgumentException(
                "Item cannot be null"
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be positive"
            );
        }

        items.add(new OrderLine(item, quantity));
    }

    public double getSubtotal() {
        double subtotal = 0;

        for (OrderLine line : items) {
            subtotal += line.getLineTotal();
        }

        return subtotal;
    }

    public double getTotal() {
        return getSubtotal() + deliveryOption.getFee();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double pay(PaymentMethod paymentMethod) {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException(
                "Only NEW orders can be paid"
            );
        }

        if (paymentMethod == null) {
            throw new IllegalArgumentException(
                "Payment method cannot be null"
            );
        }

        double amount = paymentMethod.calculateTotal(getTotal());
        status = OrderStatus.PAID;

        return amount;
    }

    public void deliver() {
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException(
                "Only PAID orders can be delivered"
            );
        }

        status = OrderStatus.DELIVERED;
    }

    public void cancel() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException(
                "Only NEW orders can be cancelled"
            );
        }

        status = OrderStatus.CANCELLED;
    }

    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public int getItemCount() {
        return items.size();
    }
}
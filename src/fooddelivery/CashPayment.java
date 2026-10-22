package fooddelivery;

public class CashPayment implements PaymentMethod {

    public double calculateTotal(double amount) {
        return amount;
    }
}
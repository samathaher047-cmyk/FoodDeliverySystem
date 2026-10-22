package fooddelivery;

public class CardPayment implements PaymentMethod {

    public double calculateTotal(double amount) {
        return amount * 1.02;
    }
}
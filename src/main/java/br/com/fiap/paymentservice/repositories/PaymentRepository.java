package br.com.fiap.paymentservice.repositories;

import br.com.fiap.paymentservice.bean.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private List<Payment> payments;

    public PaymentRepository() {
        if (this.payments == null) {
            this.payments = new ArrayList<Payment>();
        }
    }

    public Payment addPayment(Payment payment) {
        this.payments.add(payment);
        return payment;
    }

    public boolean updatePayment(Payment payment) {
        int index = this.payments.indexOf(payment);

        if (index == -1) {
            return false;
        }

        this.payments.set(index, payment);
        return true;
    }

    public boolean deletePayment(int id) {
        Payment payment = new Payment(id);

        int index = this.payments.indexOf(payment);

        if (index == -1) {
            return false;
        }

        this.payments.remove(index);
        return true;
    }

    public List<Payment> getPayments() {
        return this.payments;
    }
}

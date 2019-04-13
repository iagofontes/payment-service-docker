package br.com.fiap.paymentservice.services;

import br.com.fiap.paymentservice.bean.Payment;
import br.com.fiap.paymentservice.repositories.PaymentRepository;

public class PaymentService {

    private PaymentRepository repo;

    public PaymentService() {
        this.repo = new PaymentRepository();
    }

    public Payment findById(int id) {
        int index = this.repo.getPayments().indexOf(new Payment(id));

        if (index == -1) {
            return null;
        }
        return this.repo.getPayments().get(index);
    }

    public Payment save(Payment payment) {
        return this.repo.addPayment(payment);
    }

    public boolean update(Payment payment) {
        return this.repo.updatePayment(payment);
    }

    public boolean delete(int id) {
        return this.repo.deletePayment(id);
    }
}

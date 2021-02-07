package pl.bzynwala.voucherstore.sales.payment;

import pl.bzynwala.payment.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}

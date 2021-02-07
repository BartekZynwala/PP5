package pl.bzynwala.voucherstore.sales.payment;

import pl.bzynwala.payment.payu.exceptions.PayUException;
import pl.bzynwala.voucherstore.sales.ordering.Reservation;

public interface PaymentGateway {
    PaymentDetails registerFor(Reservation reservation) throws PayUException;

    boolean isTrusted(PaymentUpdateStatusRequest updateStatusRequest);
}

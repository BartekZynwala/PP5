package pl.bzynwala.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bzynwala.payment.payu.PayU;
import pl.bzynwala.payment.payu.PayUApiCredentials;
import pl.bzynwala.payment.payu.http.NetHttpClientPayuHttp;
import pl.bzynwala.voucherstore.productcatalog.ProductCatalogFacade;
import pl.bzynwala.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.bzynwala.voucherstore.sales.offer.OfferMaker;
import pl.bzynwala.voucherstore.sales.ordering.ReservationRepository;
import pl.bzynwala.voucherstore.sales.payment.PayUPaymentGateway;
import pl.bzynwala.voucherstore.sales.payment.PaymentGateway;
import pl.bzynwala.voucherstore.sales.productd.ProductCatalogProductDetailsProvider;
import pl.bzynwala.voucherstore.sales.productd.ProductDetailsProvider;

import java.util.UUID;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        var alwaysSameCustomer = UUID.randomUUID().toString();

        return new SalesFacade(
                new InMemoryBasketStorage(),
                productCatalogFacade,
                () -> alwaysSameCustomer,
                (productId) -> true,
                offerMaker,
                paymentGateway,
                reservationRepository
        );
    }

    @Bean
    PaymentGateway payUPaymentGateway(PayU payU) {
        return new PayUPaymentGateway(payU);
    }

    @Bean
    PayU payU() {
        return new PayU(
            PayUApiCredentials.sandbox(),
            new NetHttpClientPayuHttp()
        );
    }

    @Bean
    OfferMaker offerMaker(ProductDetailsProvider productDetailsProvider) {
        return new OfferMaker(productDetailsProvider);
    }

    @Bean
    ProductDetailsProvider productDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        return new ProductCatalogProductDetailsProvider(productCatalogFacade);
    }
}

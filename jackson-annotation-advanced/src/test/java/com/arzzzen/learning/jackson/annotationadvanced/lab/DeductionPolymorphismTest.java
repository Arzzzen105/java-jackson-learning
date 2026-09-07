package com.arzzzen.learning.jackson.annotationadvanced.lab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

class DeductionPolymorphismTest {

    @Test
    @DisplayName("asd")
    void test() throws Exception {

        // ASSERT

        String json = """
                [
                    {
                        "card_number": "1234-5678-9012-3456",
                        "expiration_date": "02/2030",
                        "cvv" : "228"
                    },
                    {
                        "email" : "johndoe@example.com",
                        "account_id" : "1234567890"
                    },
                    {
                        "wallet_type": "Apple Pay",
                        "wallet_id": "1234",
                        "device_id" : "4321"
                    }
                ]
                """;

        // ACT

        List<DeductionPolymorphism.PaymentMethod> methodList = DeductionPolymorphism.deserializePaymentMethods(json);

        // ASSERT

        assertThat(methodList).hasSize(3);
        assertThat(methodList.get(0))
                .isInstanceOf(DeductionPolymorphism.CreditCard.class);
        assertThat(methodList.get(1))
                .isInstanceOf(DeductionPolymorphism.PayPal.class);
        assertThat(methodList.get(2))
                .isInstanceOf(DeductionPolymorphism.DigitalWallet.class);

    }
}

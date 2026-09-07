package com.arzzzen.learning.jackson.annotationadvanced.lab;

import com.arzzzen.learning.jackson.annotationadvanced.Context;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class DeductionPolymorphism {

    private static final ObjectMapper objectMapper = Context.getMapper();
    private static final TypeReference<List<PaymentMethod>> paymentMethodsTypeRef = new TypeReference<>() {};


    // Jackson will try to guess the type based on JSON field names
    // No external properties are required
    // This is a bit dangerous, as it can lead to unexpected behavior based on the concrete classes implementation
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = CreditCard.class, name = "credit_card"),
            @JsonSubTypes.Type(value = PayPal.class, name = "paypal"),
            @JsonSubTypes.Type(value = DigitalWallet.class, name = "digital_wallet")
    })
    public interface PaymentMethod { }

    public record CreditCard(
            @JsonProperty("card_number")
            String cardNumber,

            @JsonProperty("expiration_date")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
            YearMonth expirationDate,

            @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
            String cvv
    ) implements PaymentMethod { }

    public record PayPal(
            @JsonProperty("email")
            String email,

            @JsonProperty("account_id")
            String accountId
    ) implements PaymentMethod { }

    public record DigitalWallet(
            @JsonProperty("wallet_type")
            String walletType,

            @JsonProperty("wallet_id")
            String walletId,

            @JsonProperty("device_id")
            String deviceId
    ) implements PaymentMethod { }


    public static List<PaymentMethod> deserializePaymentMethods(String json) throws Exception {
        return objectMapper.readValue(json, paymentMethodsTypeRef);
    }
}

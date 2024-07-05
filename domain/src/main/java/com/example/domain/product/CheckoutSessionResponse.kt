package com.example.domain.product


import com.google.gson.annotations.SerializedName

data class CheckoutSessionResponse(
    @SerializedName("session")
    val session: Session?,
    @SerializedName("status")
    val status: String?
) {
    data class Session(
        @SerializedName("after_expiration")
        val afterExpiration: Any?,
        @SerializedName("allow_promotion_codes")
        val allowPromotionCodes: Any?,
        @SerializedName("amount_subtotal")
        val amountSubtotal: Int?,
        @SerializedName("amount_total")
        val amountTotal: Int?,
        @SerializedName("automatic_tax")
        val automaticTax: AutomaticTax?,
        @SerializedName("billing_address_collection")
        val billingAddressCollection: Any?,
        @SerializedName("cancel_url")
        val cancelUrl: String?,
        @SerializedName("client_reference_id")
        val clientReferenceId: String?,
        @SerializedName("client_secret")
        val clientSecret: Any?,
        @SerializedName("consent")
        val consent: Any?,
        @SerializedName("consent_collection")
        val consentCollection: Any?,
        @SerializedName("created")
        val created: Int?,
        @SerializedName("currency")
        val currency: String?,
        @SerializedName("currency_conversion")
        val currencyConversion: Any?,
        @SerializedName("custom_fields")
        val customFields: List<Any?>?,
        @SerializedName("custom_text")
        val customText: CustomText?,
        @SerializedName("customer")
        val customer: Any?,
        @SerializedName("customer_creation")
        val customerCreation: String?,
        @SerializedName("customer_details")
        val customerDetails: CustomerDetails?,
        @SerializedName("customer_email")
        val customerEmail: String?,
        @SerializedName("expires_at")
        val expiresAt: Int?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("invoice")
        val invoice: Any?,
        @SerializedName("invoice_creation")
        val invoiceCreation: InvoiceCreation?,
        @SerializedName("livemode")
        val livemode: Boolean?,
        @SerializedName("locale")
        val locale: Any?,
        @SerializedName("metadata")
        val metadata: Metadata?,
        @SerializedName("mode")
        val mode: String?,
        @SerializedName("object")
        val objectX: String?,
        @SerializedName("payment_intent")
        val paymentIntent: Any?,
        @SerializedName("payment_link")
        val paymentLink: Any?,
        @SerializedName("payment_method_collection")
        val paymentMethodCollection: String?,
        @SerializedName("payment_method_configuration_details")
        val paymentMethodConfigurationDetails: Any?,
        @SerializedName("payment_method_options")
        val paymentMethodOptions: PaymentMethodOptions?,
        @SerializedName("payment_method_types")
        val paymentMethodTypes: List<String?>?,
        @SerializedName("payment_status")
        val paymentStatus: String?,
        @SerializedName("phone_number_collection")
        val phoneNumberCollection: PhoneNumberCollection?,
        @SerializedName("recovered_from")
        val recoveredFrom: Any?,
        @SerializedName("saved_payment_method_options")
        val savedPaymentMethodOptions: Any?,
        @SerializedName("setup_intent")
        val setupIntent: Any?,
        @SerializedName("shipping_address_collection")
        val shippingAddressCollection: Any?,
        @SerializedName("shipping_cost")
        val shippingCost: Any?,
        @SerializedName("shipping_details")
        val shippingDetails: Any?,
        @SerializedName("shipping_options")
        val shippingOptions: List<Any?>?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("submit_type")
        val submitType: Any?,
        @SerializedName("subscription")
        val subscription: Any?,
        @SerializedName("success_url")
        val successUrl: String?,
        @SerializedName("total_details")
        val totalDetails: TotalDetails?,
        @SerializedName("ui_mode")
        val uiMode: String?,
        @SerializedName("url")
        val url: String?
    ) {
        data class AutomaticTax(
            @SerializedName("enabled")
            val enabled: Boolean?,
            @SerializedName("liability")
            val liability: Any?,
            @SerializedName("status")
            val status: Any?
        )

        data class CustomText(
            @SerializedName("after_submit")
            val afterSubmit: Any?,
            @SerializedName("shipping_address")
            val shippingAddress: Any?,
            @SerializedName("submit")
            val submit: Any?,
            @SerializedName("terms_of_service_acceptance")
            val termsOfServiceAcceptance: Any?
        )

        data class CustomerDetails(
            @SerializedName("address")
            val address: Any?,
            @SerializedName("email")
            val email: String?,
            @SerializedName("name")
            val name: Any?,
            @SerializedName("phone")
            val phone: Any?,
            @SerializedName("tax_exempt")
            val taxExempt: String?,
            @SerializedName("tax_ids")
            val taxIds: Any?
        )

        data class InvoiceCreation(
            @SerializedName("enabled")
            val enabled: Boolean?,
            @SerializedName("invoice_data")
            val invoiceData: InvoiceData?
        ) {
            data class InvoiceData(
                @SerializedName("account_tax_ids")
                val accountTaxIds: Any?,
                @SerializedName("custom_fields")
                val customFields: Any?,
                @SerializedName("description")
                val description: Any?,
                @SerializedName("footer")
                val footer: Any?,
                @SerializedName("issuer")
                val issuer: Any?,
                @SerializedName("metadata")
                val metadata: Metadata?,
                @SerializedName("rendering_options")
                val renderingOptions: Any?
            ) {
                class Metadata
            }
        }

        data class Metadata(
            @SerializedName("city")
            val city: String?,
            @SerializedName("details")
            val details: String?,
            @SerializedName("phone")
            val phone: String?,
            @SerializedName("postalcode")
            val postalcode: String?
        )

        data class PaymentMethodOptions(
            @SerializedName("card")
            val card: Card?
        ) {
            data class Card(
                @SerializedName("request_three_d_secure")
                val requestThreeDSecure: String?
            )
        }

        data class PhoneNumberCollection(
            @SerializedName("enabled")
            val enabled: Boolean?
        )

        data class TotalDetails(
            @SerializedName("amount_discount")
            val amountDiscount: Int?,
            @SerializedName("amount_shipping")
            val amountShipping: Int?,
            @SerializedName("amount_tax")
            val amountTax: Int?
        )
    }
}
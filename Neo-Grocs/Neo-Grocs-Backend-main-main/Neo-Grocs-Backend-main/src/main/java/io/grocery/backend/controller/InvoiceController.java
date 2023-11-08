package io.grocery.backend.controller;

import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Invoice;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import io.grocery.backend.entity.User;

import java.util.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @PostMapping("/new")
    public String generateInvoice(
        @AuthenticationPrincipal User user,
        @RequestParam(name = "a6") int amount
    ) 
            throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_T76JXdHBPbMH4O", "wU76p3bOWRYcZjBUQq5XLxdP");

        JSONObject invoiceRequest = new JSONObject();
        invoiceRequest.put("type", "invoice");
        invoiceRequest.put("description", "Invoice for the month of January 2020");
        invoiceRequest.put("partial_payment", true);
        JSONObject customer = new JSONObject();
        customer.put("name", user.getName());
        customer.put("contact", user.getContact());
        customer.put("email", user.getEmail());
        JSONObject billingAddress = new JSONObject();
        billingAddress.put("line1", "iamneo, SSR Towers");
        billingAddress.put("line2", "Avinashi road");
        billingAddress.put("zipcode", "641603");
        billingAddress.put("city", "Coimbatore");
        billingAddress.put("state", "Tamil Nadu");
        billingAddress.put("country", "in");
        customer.put("billing_address", billingAddress);
        JSONObject shippingAddress = new JSONObject();
        shippingAddress.put("line1", "5/201, kavitha Nagar");
        shippingAddress.put("line2", "Anupparpalayam");
        shippingAddress.put("zipcode", "641603");
        shippingAddress.put("city", "Tiruppur");
        shippingAddress.put("state", "Tamil Nadu");
        shippingAddress.put("country", "in");
        customer.put("shipping_address", shippingAddress);
        invoiceRequest.put("customer", customer);
        List<Object> lines = new ArrayList<>();
        JSONObject lineItems = new JSONObject();
        lineItems.put("name", "Payment for Neo-Grocs");
        lineItems.put("description", "An online Grocery Store");
        lineItems.put("amount", amount);
        lineItems.put("currency", "INR");
        lineItems.put("quantity", 1);
        lines.add(lineItems);
        invoiceRequest.put("line_items", lines);
        invoiceRequest.put("email_notify", 1);
        invoiceRequest.put("sms_notify", 1);
        invoiceRequest.put("currency", "INR");
        invoiceRequest.put("expire_by", 2980479824L);

        Invoice invoice = razorpay.invoices.create(invoiceRequest);
        return "Work Done";
    }

}

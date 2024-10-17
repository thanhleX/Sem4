package org.example.project4.WebPacket.Application.Controller.paypal;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.example.project4.WebPacket.Infastructure.service.paypal.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPayment(@RequestParam Double total, @RequestParam String currency, @RequestParam String method,
                                 @RequestParam String intent, @RequestParam String description,
                                 @RequestParam String cancelUrl, @RequestParam String successUrl) throws PayPalRESTException {
        return paymentService.createPayment(total, currency, method, intent, description, cancelUrl, successUrl);
    }

    @PostMapping("/execute")
    public Payment executePayment(@RequestParam String paymentId, @RequestParam String payerId) throws PayPalRESTException {
        return paymentService.executePayment(paymentId, payerId);
    }
}
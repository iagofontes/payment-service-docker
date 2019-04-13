package br.com.fiap.paymentservice.controllers;

import br.com.fiap.paymentservice.bean.Payment;
import br.com.fiap.paymentservice.services.PaymentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@Api(value = "Payment", description = "Payment Service REST API")
public class PaymentController {

    private PaymentService service;

    public PaymentController() {
        this.service = new PaymentService();
    }

    @ApiOperation(httpMethod = "GET", value = "Get Payment By Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns Payment and Success Status", response = Payment.class)
    })
    @GetMapping("/findById/{id}")
    public ResponseEntity<Payment> find(@ApiParam( value = "Payment Id", required = true) @PathVariable("id") int id){
        Payment payment = this.service.findById(id);
        if (payment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "POST", value = "Post Payment")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns generated Payment and Created Status", response = Payment.class)
    })
    @PostMapping("/save")
    public ResponseEntity<Object> savePayment(@RequestBody Payment payment){
        Payment savePayment = this.service.save(payment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/findById/{id}")
                .buildAndExpand(savePayment.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(httpMethod = "PATCH", value = "Update Payment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success Status")
    })
    @PatchMapping("/update")
    public ResponseEntity updatePayment(@RequestBody Payment payment){
        if (!this.service.update(payment))
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete Payment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retuns Success Status")
    })
    @DeleteMapping("/payment/{id}")
    public ResponseEntity deletePayment(@ApiParam( value = "Order Id", required = true) @PathVariable("id") int id){
        if (this.service.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
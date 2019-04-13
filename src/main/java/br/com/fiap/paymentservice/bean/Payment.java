package br.com.fiap.paymentservice.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @ApiModelProperty(notes = "The database generated Order ID")
    private int id;

    @ApiModelProperty(notes = "Credit card Number")
    private String numeroCartao;

    @ApiModelProperty(notes = "Expiration Date")
    private String validade;

    @ApiModelProperty(notes = "Credit Card Flag")
    private String bandeira;

    @ApiModelProperty(notes = "Amount")
    private BigDecimal valorCompra;

    public Payment(int id) { this.id = id; }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Payment))
            return false;
        if (obj == this)
            return true;

        Payment payment= (Payment) obj;
        return payment.getId() == this.id;
    }
}

package com.example.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table(name = "conta", uniqueConstraints = {
        @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
        @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})
        })
@SuperBuilder
@NoArgsConstructor

public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idConta;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false, precision= 22, scale = 2)
    private BigDecimal saldo;

    @Column(nullable = false)
    private boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id", foreignKey = @ForeignKey(name = "fk_conta_cliente"))
    private Cliente cliente;

    public abstract String getTipo();

    public void sacar(BigDecimal valor){
        validarValorMAiorQueZero(valor);
        if(this.saldo.compareTo(valor)<0){
            throw new IllegalArgumentException("Saldo insuficiente para o saque.");
        }
        this.saldo = this.saldo.subtract(valor);
    }
    public void depositar(BigDecimal valor) {
        validarValorMAiorQueZero(valor);
            this.saldo = this.saldo.add(valor);
    }
    public void transferir(BigDecimal valor, Conta contaDestino){
        if(this.idConta.equals(contaDestino.getIdConta())){
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta.");
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    protected static void validarValorMAiorQueZero(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da operação deve ser maior que zero.");
        }
    }


}

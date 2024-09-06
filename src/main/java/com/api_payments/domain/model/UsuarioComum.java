package com.api_payments.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuario_comum")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioComum implements UserDetails, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String nomeCompleto;
    @CPF
    private String cpf;
    private String email;
    private String senha;
    @Column(name = "saldo_da_conta", precision = 38, scale = 2)
    private BigDecimal saldoConta;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private Login login;
    @JsonManagedReference
    @OneToMany(mappedBy = "senderID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public UsuarioComum(Builder builder) {
        this.id = builder.id;
        this.nomeCompleto = builder.nomeCompleto;
        this.cpf = builder.cpf;
        this.email = builder.email;
        this.senha = builder.senha;
        this.saldoConta = builder.saldoConta;
        this.login = builder.login;
        this.transactions = builder.transactions;
    }

    //PADRÃO DE PROJETO: BUILDER
    public static class Builder {
        private Long id;
        private String cpf;
        private String nomeCompleto;
        private String email;
        private String senha;
        private BigDecimal saldoConta;
        private Login login;
        private List<Transaction> transactions;

        public Builder withNOMECOMPLETO(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
            return this;
        }

        public Builder withCPF(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder withEMAIL(String email) {
            this.email = email;
            return this;
        }

        public Builder withSENHA(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder withSALDODACONTA(BigDecimal saldoConta) {
            this.saldoConta = saldoConta;
            return this;
        }

        public Builder withLOGIN(Login login) {
            this.login = login;
            return this;
        }

        public Builder withTRANSACTIONS(List<Transaction> transactionList) {
            this.transactions = transactionList;
            return this;
        }

        public UsuarioComum builder() {
            return new UsuarioComum(this);
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nomeCompleto;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //PADRAO DE PROJETO: PROTOTYPE
    @Override
    protected Object clone() throws CloneNotSupportedException {
        UsuarioComum usuarioComum = new UsuarioComum();
        UsuarioComum usuarioComumClone = (UsuarioComum) usuarioComum.clone();
        return usuarioComumClone;
    }
}

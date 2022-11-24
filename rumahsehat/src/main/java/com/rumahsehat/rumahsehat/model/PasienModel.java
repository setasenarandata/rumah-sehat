package com.rumahsehat.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pasien")
public class PasienModel extends UserModel implements Serializable {
    @NotNull
    @Column(name="saldo", nullable = false)
    private Integer saldo;

    @NotNull
    @Size(max = 3)
    @Column(name="umur", nullable = false)
    private Integer Umur;
}

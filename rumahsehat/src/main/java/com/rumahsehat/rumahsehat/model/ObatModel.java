package com.rumahsehat.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "obat")
public class ObatModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id_obat", nullable = false)
    private String id_obat;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_obat", nullable = false)
    private String nama_obat;

    @Column(name = "stok", nullable = true, columnDefinition = "integer default 100")
    private Integer stok;

    @NotNull
    @Size(max = 50)
    @Column(name = "harga", nullable = false)
    private Integer harga;
}

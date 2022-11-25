package com.rumahsehat.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentModel implements Serializable{

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kode",nullable = false)
    private String kode;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(name = "waktu_awal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "uuid_dokter", referencedColumnName = "id", nullable = false)
     @OnDelete(action = OnDeleteAction.CASCADE)
     private DokterModel dokter;

     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "uuid_pasien", referencedColumnName = "id", nullable = false)
     @OnDelete(action = OnDeleteAction.CASCADE)
     private PasienModel pasien;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "kode_tagihan", referencedColumnName = "kode")
     private TagihanModel tagihan;
     
    @OneToOne(mappedBy = "appointment")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ResepModel resep;
}

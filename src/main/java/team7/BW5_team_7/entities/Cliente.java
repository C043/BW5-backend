package team7.BW5_team_7.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team7.BW5_team_7.enums.TipoCliente;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @Column(name = "partita_iva")
    private String partitaIva;

    private String email;

    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;

    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;

    @Column(name = "fatturato_annuale")
    private double fatturatoAnnuale;

    private String pec;

    private Long telefono;

    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private Long telefonoContatto;

    @Column(name = "logo_aziendale")
    private String logoAziendale;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    private String provincia;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Indirizzo> listaIndirizzi;

    public Cliente(String ragioneSociale, String partitaIve, String email, double fatturatoAnnuale, String pec, Long telefono, String emailContatto, String nomeContatto,
                   String cognomeContatto, Long telefonoContatto, TipoCliente tipo) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIve;
        this.email = email;
        this.dataInserimento = LocalDate.now();
        this.dataUltimoContatto = LocalDate.now();
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = "https://ui-avatars.com/api/?name=" + this.ragioneSociale;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", partitaIve='" + partitaIva + '\'' +
                ", email='" + email + '\'' +
                ", dataInserimento=" + dataInserimento +
                ", dataUltimoContatto=" + dataUltimoContatto +
                ", fatturatoAnnuale=" + fatturatoAnnuale +
                ", pec='" + pec + '\'' +
                ", telefono=" + telefono +
                ", emailContatto='" + emailContatto + '\'' +
                ", nomeContatto='" + nomeContatto + '\'' +
                ", cognomeContatto='" + cognomeContatto + '\'' +
                ", telefonoContatto=" + telefonoContatto +
                ", logoAziendale='" + logoAziendale + '\'' +
                ", tipo=" + tipo +
                ", listaIndirizzi=" + listaIndirizzi +
                '}';
    }
}

package ar.com.reba.testproject.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contacto", schema = "public", catalog = "reba")
public class Contacto {
    private int contactoId;
    private String tipo;
    private String descripcion;
    private Persona personaByPersonaId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @SequenceGenerator(name="contacto_id_seq",sequenceName="public", allocationSize=1)
    @Column(name = "contacto_id")
    public int getContactoId() {
        return contactoId;
    }

    public void setContactoId(int contactoId) {
        this.contactoId = contactoId;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return contactoId == contacto.contactoId && Objects.equals(tipo, contacto.tipo) && Objects.equals(descripcion, contacto.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactoId, tipo, descripcion);
    }

    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    public Persona getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(Persona personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }
}

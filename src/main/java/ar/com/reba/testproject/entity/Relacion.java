package ar.com.reba.testproject.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Relacion {
    private int relacionId;
    private Persona personaByHijoId;
    private Persona personaByPadreId;

    @Id
    @Column(name = "relacion_id")
    public int getRelacionId() {
        return relacionId;
    }

    public void setRelacionId(int relacionId) {
        this.relacionId = relacionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relacion relacion = (Relacion) o;
        return relacionId == relacion.relacionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relacionId);
    }

    @ManyToOne
    @JoinColumn(name = "hijo_id", referencedColumnName = "persona_id")
    public Persona getPersonaByHijoId() {
        return personaByHijoId;
    }

    public void setPersonaByHijoId(Persona personaByHijoId) {
        this.personaByHijoId = personaByHijoId;
    }

    @ManyToOne
    @JoinColumn(name = "padre_id", referencedColumnName = "persona_id")
    public Persona getPersonaByPadreId() {
        return personaByPadreId;
    }

    public void setPersonaByPadreId(Persona personaByPadreId) {
        this.personaByPadreId = personaByPadreId;
    }
}

package ar.com.reba.testproject.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "persona", schema = "public", catalog = "reba")
public class Persona {
    private int personaId;
    private String nombre;
    private String apellido;
    private String documentoTipo;
    private String documentoNumero;
    private String pais;
    private Integer edad;
    private Collection<Relacion> hijos;
    private Collection<Relacion> padres;
    private Collection<Contacto> contactosByPersonaId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @SequenceGenerator(name="persona_id_seq",sequenceName="public", allocationSize=1)
    @Column(name = "persona_id")
    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellido")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Basic
    @Column(name = "documento_tipo")
    public String getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    @Basic
    @Column(name = "documento_numero")
    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    @Basic
    @Column(name = "pais")
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Basic
    @Column(name = "edad")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return personaId == persona.personaId && Objects.equals(nombre, persona.nombre) && Objects.equals(apellido, persona.apellido) && Objects.equals(documentoTipo, persona.documentoTipo) && Objects.equals(documentoNumero, persona.documentoNumero) && Objects.equals(pais, persona.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, nombre, apellido, documentoTipo, documentoNumero, pais);
    }

//    @OneToMany(mappedBy = "personaByHijoId")
    @OneToMany(mappedBy = "personaByPadreId", cascade = CascadeType.ALL)
    public Collection<Relacion> getHijos() {
        return hijos;
    }

    public void setHijos(Collection<Relacion> relacionsByPersonaId) {
        this.hijos = relacionsByPersonaId;
    }

//    @OneToMany(mappedBy = "personaByPadreId")
    @OneToMany(mappedBy = "personaByHijoId", cascade = CascadeType.ALL)
    public Collection<Relacion> getPadres() {
        return padres;
    }

    public void setPadres(Collection<Relacion> relacionsByPersonaId_0) {
        this.padres = relacionsByPersonaId_0;
    }

    @OneToMany(mappedBy = "personaByPersonaId", cascade = CascadeType.ALL)
    public Collection<Contacto> getContactosByPersonaId() {
        return contactosByPersonaId;
    }

    public void setContactosByPersonaId(Collection<Contacto> contactosByPersonaId) {
        this.contactosByPersonaId = contactosByPersonaId;
    }
}

package com.company;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "piezas", schema = "gestionproyectos", catalog = "")
public class PiezasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Codigo", nullable = false, length = 6)
    private String codigo;
    @Basic
    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;
    @Basic
    @Column(name = "Precio", nullable = false, precision = 0)
    private double precio;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = -1)
    private String descripcion;
    @OneToMany(mappedBy = "piezasByCodPieza")
    private Collection<GestionEntity> gestionsByCodigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<GestionEntity> getGestionsByCodigo() {
        return gestionsByCodigo;
    }

    public void setGestionsByCodigo(Collection<GestionEntity> gestionsByCodigo) {
        this.gestionsByCodigo = gestionsByCodigo;
    }
}

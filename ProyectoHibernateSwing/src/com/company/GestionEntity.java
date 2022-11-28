package com.company;

import javax.persistence.*;

@Entity
@Table(name = "gestion", schema = "gestionproyectos", catalog = "")
@IdClass(GestionEntityPK.class)
public class GestionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CodProveedor", nullable = false, length = 6)
    private String codProveedor;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CodPieza", nullable = false, length = 6)
    private String codPieza;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CodProyecto", nullable = false, length = 6)
    private String codProyecto;
    @Basic
    @Column(name = "Cantidad", nullable = true, precision = 0)
    private Double cantidad;

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getCodPieza() {
        return codPieza;
    }

    public void setCodPieza(String codPieza) {
        this.codPieza = codPieza;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}

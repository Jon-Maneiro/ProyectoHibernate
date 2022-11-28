package com.company;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "proveedores", schema = "gestionproyectos", catalog = "")
public class ProveedoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Codigo", nullable = false, length = 6)
    private String codigo;
    @Basic
    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;
    @Basic
    @Column(name = "Apellidos", nullable = false, length = 30)
    private String apellidos;
    @Basic
    @Column(name = "Direccion", nullable = false, length = 40)
    private String direccion;
    @OneToMany(mappedBy = "proveedoresByCodProveedor")
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Collection<GestionEntity> getGestionsByCodigo() {
        return gestionsByCodigo;
    }

    public void setGestionsByCodigo(Collection<GestionEntity> gestionsByCodigo) {
        this.gestionsByCodigo = gestionsByCodigo;
    }
}

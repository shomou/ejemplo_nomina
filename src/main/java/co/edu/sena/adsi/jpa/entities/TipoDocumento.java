/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jperez
 */


@Entity
@Table(name="tipos_documento")
public class TipoDocumento implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
     private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 3,max = 45)
    @Column(name = "descripcion")
    private String descripcion;

     
     //Relación OneToMany con Departamentos
    @OneToMany(mappedBy="tipoDocumento")
    private List <Usuario>  usuariosList;
    
    
    /** 
    * Constructor de la Clase.
    */
   
    public TipoDocumento() {
    }
    
    public TipoDocumento(Integer id) {
        this.id = id;
    }

    public TipoDocumento(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuario> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" + "id=" + id + '}';
    }
    
    
    
}

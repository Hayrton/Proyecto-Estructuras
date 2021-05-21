/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Proyecto2.modelo;

/**
 *
 * @author Hayrton
 */
public class Usuarios {
    private String Id;
    private String Nombre;
    private String Password;

    public Usuarios() {
    }

    
    
    public Usuarios(String Id, String Nombre, String Password) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Password = Password;
    }
    
    public String imprimeUsuario(){
        StringBuilder builder = new StringBuilder();
        builder.append("Id: ").append(Id).append("\\n");
        builder.append("Nombre: ").append(Nombre).append("\\n");
        builder.append("Password: ").append(Password).append("\\n");
        return builder.toString();
    }

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.Id != null ? this.Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuarios other = (Usuarios) obj;
        if ((this.Id == null) ? (other.Id != null) : !this.Id.equals(other.Id)) {
            return false;
        }
        return true;
    }

    
    
    

}

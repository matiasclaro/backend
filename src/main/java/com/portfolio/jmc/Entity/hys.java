
package com.portfolio.jmc.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class hys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreS;
    private int porcentajeS;
    private String ImagenS;

    public hys() {
    }

    public hys(String nombreS, int porcentajeS, String imagenS) {
        this.nombreS = nombreS;
        this.porcentajeS = porcentajeS;
        this.ImagenS = imagenS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    public int getPorcentajeS() {
        return porcentajeS;
    }

    public void setPorcentajeS(int porcentajeS) {
        this.porcentajeS = porcentajeS;
    }

    public String getImagenS() {
        return ImagenS;
    }

    public void setImagenS(String ImagenS) {
        this.ImagenS = ImagenS;
    }






}





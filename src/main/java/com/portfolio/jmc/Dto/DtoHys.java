
package com.portfolio.jmc.Dto;

import javax.validation.constraints.NotBlank;


public class DtoHys {
    @NotBlank
    private String nombreS;
    @NotBlank
    private int porcentajeS;
    private String imagenS;

    public DtoHys() {
    }

    public DtoHys(String nombreS, int porcentajeS, String imagenS) {
        this.nombreS = nombreS;
        this.porcentajeS = porcentajeS;
        this.imagenS = imagenS;
    }

    public String getImagenS() {
        return imagenS;
    }

    public void setImagenS(String imagenS) {
        this.imagenS = imagenS;
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
    
    
    
    
}

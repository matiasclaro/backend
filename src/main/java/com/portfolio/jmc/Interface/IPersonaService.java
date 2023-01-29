
package com.portfolio.jmc.Interface;

import com.portfolio.jmc.Entity.Persona;
import java.util.List;


public interface IPersonaService {
 
    public List<Persona> getPesonas();
 
 
 public void savePersona(Persona persona);
     
 
 
 public void deletePersona(Long id);

 
 
 public Persona findPersona(Long id);
 
     
     
 
}

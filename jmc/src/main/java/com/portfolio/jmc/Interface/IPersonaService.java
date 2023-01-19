
package com.portfolio.jmc.Interface;

import com.portfolio.jmc.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //traer una lista de Persona
    public List<Persona> getPersona();
    
    //Guardar un objeto Persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto
    
    public void deletePersona(Long id);
    
    // buscar una persona
    public Persona findPersona (Long id);
    
    
    
}

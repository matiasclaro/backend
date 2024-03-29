
package com.portfolio.jmc.Controller;

import com.portfolio.jmc.Dto.DtoEducacion;
import com.portfolio.jmc.Entity.Educacion;
import com.portfolio.jmc.Security.Controller.Mensaje;
import com.portfolio.jmc.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = {"https://froentendjmc-d6dd8.web.app" , "http://localhost:4200"})
public class CEducacion {
    @Autowired
    SEducacion sEducacion;
   
    
     @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!sEducacion.existById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    
    }
   
    @GetMapping("/lista")
        public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        
        if(!sEducacion.existById(id)){
            
            return new ResponseEntity(new Mensaje("No existe el Id"), HttpStatus.NOT_FOUND);
            }
        
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Edudacion eliminada"),HttpStatus.OK);
        
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEducacion){
        
        if(StringUtils.isBlank(dtoEducacion.getNombreEdu()))
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
         if (sEducacion.existByNombreEdu(dtoEducacion.getNombreEdu()))
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
         
         if(StringUtils.isBlank(dtoEducacion.getDescripcionEdu()))
            return new ResponseEntity(new Mensaje ("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        
    
          Educacion educacion =new Educacion (dtoEducacion.getNombreEdu(),dtoEducacion.getDescripcionEdu(), dtoEducacion.getImgEdu());
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje ("Educacion agregada"), HttpStatus.OK);
    
    
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoEducacion){
        
        if(!sEducacion.existById(id))
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.NOT_FOUND);
       
        if(sEducacion.existByNombreEdu(dtoEducacion.getNombreEdu()) && sEducacion.getByNombreEdu(dtoEducacion.getNombreEdu()).get().getId()!=id)
            return new ResponseEntity(new Mensaje("Esa educacion existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoEducacion.getNombreEdu()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoEducacion.getDescripcionEdu()))
            return new ResponseEntity(new Mensaje ("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEdu(dtoEducacion.getNombreEdu());
        educacion.setDescripcionEdu(dtoEducacion.getDescripcionEdu());
        educacion.setImgEdu(dtoEducacion.getImgEdu());
        
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"),HttpStatus.OK);
    }
    
    
    
    
    
}

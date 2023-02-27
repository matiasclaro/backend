package com.portfolio.jmc.Controller;

import com.portfolio.jmc.Dto.DtoProyecto;
import com.portfolio.jmc.Entity.Proyectos;
import com.portfolio.jmc.Security.Controller.Mensaje;
import com.portfolio.jmc.Service.SProyectos;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontendjmc.web.app", "http://localhost:4200"})
public class CProyectos {

    @Autowired
    SProyectos sProyectos;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        sProyectos.delete(id);

        return new ResponseEntity(new Mensaje("Proyecto Eliminada"), HttpStatus.OK);

    }

    @PostMapping("/create")

    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoproyecto) {
        if (StringUtils.isBlank(dtoproyecto.getNombreP())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.OK);
        }
        if (sProyectos.existsByNombreP(dtoproyecto.getNombreP())) {
            return new ResponseEntity(new Mensaje("Ese Proyecto existe"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = new Proyectos(dtoproyecto.getNombreP(), dtoproyecto.getDescripcionP(), dtoproyecto.getImagenP(), dtoproyecto.getLinkP());
        sProyectos.save(proyectos);

        return new ResponseEntity(new Mensaje("" + proyectos.getId()), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")

    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyecto dtoproyecto) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (sProyectos.existsByNombreP(dtoproyecto.getNombreP()) && sProyectos.getByNombreP(dtoproyecto.getNombreP()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese Proyecto existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoproyecto.getNombreP())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //Hacer para todos los campos

        Proyectos proyectos = sProyectos.getOne(id).get();
        proyectos.setNombreP(dtoproyecto.getNombreP());
        proyectos.setDescripcionP(dtoproyecto.getDescripcionP());
        proyectos.setImagenP(dtoproyecto.getImagenP());
        proyectos.setLinkP(dtoproyecto.getLinkP());
        

        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto actualizada"), HttpStatus.OK);
    }
}

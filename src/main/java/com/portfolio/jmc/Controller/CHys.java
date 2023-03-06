package com.portfolio.jmc.Controller;

import com.portfolio.jmc.Dto.DtoHys;

import com.portfolio.jmc.Entity.hys;
import com.portfolio.jmc.Security.Controller.Mensaje;
import com.portfolio.jmc.Service.Shys;
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
@RequestMapping("/skill")
      
@CrossOrigin(origins = {"https://froentendjmc-d6dd8.web.app" , "http://localhost:4200"})

public class CHys {

    @Autowired
    Shys shys;

    @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
        }

        hys hYs = shys.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list() {
        List<hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    @PostMapping("/create")

    public ResponseEntity<?> create(@RequestBody DtoHys dtohys) {
        if (StringUtils.isBlank(dtohys.getNombreS())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.OK);
        }
        if (shys.existsByNombreS(dtohys.getNombreS())) {
            return new ResponseEntity(new Mensaje("Esa Skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        hys hYs = new hys(dtohys.getNombreS(), dtohys.getPorcentajeS(), dtohys.getImagenS());
        shys.save(hYs);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHys dtohys) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (shys.existsByNombreS(dtohys.getNombreS()) && shys.getByNombreS(dtohys.getNombreS()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtohys.getNombreS())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        

        hys hYs = shys.getOne(id).get();
        hYs.setNombreS(dtohys.getNombreS());
        hYs.setPorcentajeS(dtohys.getPorcentajeS());
        hYs.setImagenS(dtohys.getImagenS());

        shys.save(hYs);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        shys.delete(id);

        return new ResponseEntity(new Mensaje("Skill Eliminado"), HttpStatus.OK);

    }
}

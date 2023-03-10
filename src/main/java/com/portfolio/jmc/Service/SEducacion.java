package com.portfolio.jmc.Service;

import com.portfolio.jmc.Entity.Educacion;
import com.portfolio.jmc.Repository.REducacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SEducacion {

    @Autowired
    REducacion rEducacion;

    public List<Educacion> list() {
        return rEducacion.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return rEducacion.findById(id);
    }

    public Optional<Educacion> getByNombreEdu(String nombreEdu) {
        return rEducacion.findByNombreEdu(nombreEdu);
    }

    public void save(Educacion educacion) {
        rEducacion.save(educacion);
    }

    public void delete(int id) {
        rEducacion.deleteById(id);
    }

    public boolean existById(int id) {
        return rEducacion.existsById(id);
    }

    public boolean existByNombreEdu(String nombreEdu) {
        return rEducacion.existsByNombreEdu(nombreEdu);
    }

}

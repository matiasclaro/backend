/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.jmc.Repository;

import com.portfolio.jmc.Entity.hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matias
 */
public interface Rhys extends JpaRepository<hys, Integer> {
    Optional<hys> findByNombreS(String nombreS);
    public boolean existsByNombreS(String nombreS);
    
    
}

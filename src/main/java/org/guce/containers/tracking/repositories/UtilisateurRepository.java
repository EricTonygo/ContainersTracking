/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.repositories;

import java.util.List;
import org.guce.containers.tracking.models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author penda
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Utilisateur findByEmail(String email);
    List<Utilisateur> findByEmailContainingOrNomContainingOrPrenomContaining(String email, String nom, String prenom, Sort sort);
    Page<Utilisateur> findByEmailContainingOrNomContainingOrPrenomContaining(String email, String nom, String prenom, Pageable pageable);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author penda
 */
public interface IUtilisateurService {
    public List<Utilisateur> findAll();
    public Utilisateur save(Utilisateur c);
    public Utilisateur update(Utilisateur c);
    public void delete(Utilisateur c);
    public Utilisateur getUser(Long id);
    Utilisateur findByEmail(String email);
    List<Utilisateur> findByEmailContainingOrNomContainingOrPrenomContaining(String email, String nom, String prenom);
    Page<Utilisateur> findByEmailContainingOrNomContainingOrPrenomContaining(String email, String nom, String prenom, Pageable pageable);
}

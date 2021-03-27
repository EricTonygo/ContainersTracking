/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.guce.containers.tracking.repositories.UtilisateurRepository;
import org.guce.containers.tracking.services.IUtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author penda
 */
@Service
@Component
public class UtilisateurService implements IUtilisateurService{
    
    @Autowired
    UtilisateurRepository userRepository;

    @Override
    public List<Utilisateur> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Utilisateur save(Utilisateur c) {
        return userRepository.save(c);
    }

    @Transactional
    @Override
    public Utilisateur update(Utilisateur c) {
        return userRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(Utilisateur c) {
        userRepository.delete(c);
    }

    @Override
    public Utilisateur getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Utilisateur> findByEmailContainingOrNomContainingOrPrenomContaining(String email, String nom, String prenom) {
        return userRepository.findByEmailContainingOrNomContainingOrPrenomContaining(email, nom, prenom, Sort.by(Sort.Direction.ASC, "nom"));
    }

    @Override
    public Page<Utilisateur> findByEmailContainingOrNomContainingOrPrenomContaining(String email, String nom, String prenom, Pageable pageable) {
        return userRepository.findByEmailContainingOrNomContainingOrPrenomContaining(email, nom, prenom, pageable);
    }
    
}

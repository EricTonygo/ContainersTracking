/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.HistoriqueRechercheConteneur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author penda
 */
public interface IHistoriqueRechercheConteneurService {
    public List<HistoriqueRechercheConteneur> findAll();
    public Page<HistoriqueRechercheConteneur> findAll(Pageable pageable);
    public HistoriqueRechercheConteneur save(HistoriqueRechercheConteneur c);
    public HistoriqueRechercheConteneur update(HistoriqueRechercheConteneur c);
    public void delete(HistoriqueRechercheConteneur c);
    public HistoriqueRechercheConteneur getUserContainerSearch(Long id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.Charger;

/**
 *
 * @author penda
 */
public interface IChargerService {
    public List<Charger> findAll();
    public Charger save(Charger c);
    public Charger update(Charger c);
    public void delete(Charger c);
    public Charger getCharger(Long id);
}

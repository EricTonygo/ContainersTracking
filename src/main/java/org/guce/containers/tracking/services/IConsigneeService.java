/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.Consignee;

/**
 *
 * @author penda
 */
public interface IConsigneeService {
    public List<Consignee> findAll();
    public Consignee save(Consignee c);
    public Consignee update(Consignee c);
    public void delete(Consignee c);
    public Consignee getConsignee(Long id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.Consignee;
import org.guce.containers.tracking.repositories.ConsigneeRepository;
import org.guce.containers.tracking.services.IConsigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author penda
 */
@Service
@Component
public class ConsigneeService implements IConsigneeService{

    @Autowired
    ConsigneeRepository consigneeRepository;
    
    @Override
    public List<Consignee> findAll() {
        return consigneeRepository.findAll();
    }

    @Transactional
    @Override
    public Consignee save(Consignee c) {
        return consigneeRepository.save(c);
    }

    @Transactional
    @Override
    public Consignee update(Consignee c) {
        return consigneeRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(Consignee c) {
        consigneeRepository.delete(c);
    }

    @Override
    public Consignee getConsignee(Long id) {
        return consigneeRepository.getOne(id);
    }
    
}

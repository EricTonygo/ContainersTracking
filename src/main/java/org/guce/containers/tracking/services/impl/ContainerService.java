/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.Container;
import org.guce.containers.tracking.repositories.ContainerRepository;
import org.guce.containers.tracking.services.IContainerService;
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
public class ContainerService implements IContainerService{

    @Autowired
    ContainerRepository containerRepository;
    
    @Override
    public List<Container> findAll() {
        return containerRepository.findAll();
    }

    @Transactional
    @Override
    public Container save(Container c) {
        return containerRepository.save(c);
    }

    @Transactional
    @Override
    public Container update(Container c) {
        return containerRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(Container c) {
        containerRepository.delete(c);
    }

    @Override
    public Container getContainer(Long id) {
        return containerRepository.getOne(id);
    }
    
}

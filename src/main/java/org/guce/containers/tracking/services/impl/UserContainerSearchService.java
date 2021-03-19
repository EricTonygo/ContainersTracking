/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.UserContainerSearch;
import org.guce.containers.tracking.repositories.UserContainerSearchRepository;
import org.guce.containers.tracking.services.IUserContainerSearchService;
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
public class UserContainerSearchService implements IUserContainerSearchService {
    
    @Autowired
    UserContainerSearchRepository userContainerSearchRepository;

    @Override
    public List<UserContainerSearch> findAll() {
        return userContainerSearchRepository.findAll();
    }

    @Transactional
    @Override
    public UserContainerSearch save(UserContainerSearch c) {
        return userContainerSearchRepository.save(c);
    }

    @Transactional
    @Override
    public UserContainerSearch update(UserContainerSearch c) {
        return userContainerSearchRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(UserContainerSearch c) {
        userContainerSearchRepository.delete(c);
    }

    @Override
    public UserContainerSearch getUserContainerSearch(Long id) {
        return userContainerSearchRepository.getOne(id);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.User;
import org.guce.containers.tracking.repositories.UserRepository;
import org.guce.containers.tracking.services.IUserService;
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
public class UserService implements IUserService{
    
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User c) {
        return userRepository.save(c);
    }

    @Transactional
    @Override
    public User update(User c) {
        return userRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(User c) {
        userRepository.delete(c);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }
    
}

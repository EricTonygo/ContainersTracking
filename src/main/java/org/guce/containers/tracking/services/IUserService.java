/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.User;

/**
 *
 * @author penda
 */
public interface IUserService {
    public List<User> findAll();
    public User save(User c);
    public User update(User c);
    public void delete(User c);
    public User getUser(Long id);
}

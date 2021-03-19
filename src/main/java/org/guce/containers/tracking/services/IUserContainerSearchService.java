/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.UserContainerSearch;

/**
 *
 * @author penda
 */
public interface IUserContainerSearchService {
    public List<UserContainerSearch> findAll();
    public UserContainerSearch save(UserContainerSearch c);
    public UserContainerSearch update(UserContainerSearch c);
    public void delete(UserContainerSearch c);
    public UserContainerSearch getUserContainerSearch(Long id);
}

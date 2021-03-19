/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.Container;

/**
 *
 * @author penda
 */
public interface IContainerService {
    public List<Container> findAll();
    public Container save(Container c);
    public Container update(Container c);
    public void delete(Container c);
    public Container getContainer(Long id);
}

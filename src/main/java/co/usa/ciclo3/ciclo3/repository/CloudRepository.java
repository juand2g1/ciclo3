/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Cloud;
import co.usa.ciclo3.ciclo3.repository.crud.CloudCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juand2g
 */
@Repository

public class CloudRepository {
   
    @Autowired
    private CloudCrudRepository cloudCrudRepository;
    
    public List<Cloud> getAll(){
        return (List<Cloud>)cloudCrudRepository.findAll();
    }
    public Optional<Cloud> getCloud(int id){
    
        return cloudCrudRepository.findById(id);
    
    }
    
    public Cloud save(Cloud p){
        return cloudCrudRepository.save(p);
        
    }
    
}

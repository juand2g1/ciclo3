/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Cloud;
import co.usa.ciclo3.ciclo3.repository.CloudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juand2g
 */
@Service

public class CloudService {

    @Autowired
    private CloudRepository cloudRepository;
    
    public List<Cloud> getAll(){
        return cloudRepository.getAll();
        
    }
    
    public Optional<Cloud> getCloud(int id){
        return cloudRepository.getCloud(id);
    }
    
    public Cloud save(Cloud p){
        if(p.getId()==null){
            return cloudRepository.save(p);
            
        }else{
            Optional<Cloud> paux=cloudRepository.getCloud(p.getId());
            if(paux.isEmpty()){
                return cloudRepository.save(p);
            }else{
                return p;
            }
        }
    }
    
}

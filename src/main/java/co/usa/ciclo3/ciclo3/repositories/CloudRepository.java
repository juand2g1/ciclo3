package co.usa.ciclo3.ciclo3.repositories;

import co.usa.ciclo3.ciclo3.model.Cloud;
import co.usa.ciclo3.ciclo3.repositories.crud.CloudCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CloudRepository {
    @Autowired/*acá es como crear un objeto del interface CloudCrudRepository
    la cual usaremos para la conexión a la base de datos*/
    private CloudCrudRepository repository;


    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<Cloud> getAll(){
        return (List<Cloud>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<Cloud> getCloud(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public Cloud save(Cloud cloud){
        return repository.save(cloud);
    }

    /*delete from table*/
    public void delete(Cloud cloud){
        repository.delete(cloud);
    }
}

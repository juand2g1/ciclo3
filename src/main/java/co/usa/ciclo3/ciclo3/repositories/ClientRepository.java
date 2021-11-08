package co.usa.ciclo3.ciclo3.repositories;


import co.usa.ciclo3.ciclo3.model.Admin;
import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repositories.crud.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @Autowired/*acá es como crear un objeto del interface ClientCrudRepository
    la cual usaremos para la conexión a la base de datos*/
    private ClientCrudRepository repository;

    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<Client> getAll(){
        return (List<Client>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<Client> getClient(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public Client save(Client client){
        return repository.save(client);
    }

    /*delete from table*/
    public void delete(Client client){
        repository.delete(client);
    }
}

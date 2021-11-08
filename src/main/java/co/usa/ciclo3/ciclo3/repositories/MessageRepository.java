package co.usa.ciclo3.ciclo3.repositories;


import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repositories.crud.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {

    @Autowired/*acá es como crear un objeto del interface MessageCrudRepository
    la cual usaremos para la conexión a la base de datos*/
    private MessageCrudRepository repository;

    /* **********************************Creamos el CRUD *******************************************************************/


    /*Select * from
     * retorna todos los registros*/
    public List<Message> getAll(){
        return (List<Message>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<Message> getMessage(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public Message save(Message message){
        return repository.save(message);
    }

    /*delete from table*/
    public void delete(Message message){
        repository.delete(message);
    }
}

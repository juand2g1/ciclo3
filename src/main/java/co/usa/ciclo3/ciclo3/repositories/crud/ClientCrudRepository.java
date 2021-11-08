package co.usa.ciclo3.ciclo3.repositories.crud;

import co.usa.ciclo3.ciclo3.model.Client;
import org.springframework.data.repository.CrudRepository;

/*en la interface definimos un modelo pero no lo implementamos, la implementación la hacemos en el Repository*/

public interface ClientCrudRepository extends CrudRepository<Client,Integer> {//se hace referencia a la entidad que va a implementar el crud y el tipo de llave primaria Integer
    //post, get, put, delete==create,read,upload,delete
}

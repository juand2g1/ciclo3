package co.usa.ciclo3.ciclo3.repositories.crud;

import co.usa.ciclo3.ciclo3.model.Category;
import org.springframework.data.repository.CrudRepository;

/*en la interface definimos un modelo pero no lo implementamos, la implementación la hacemos en el Repository*/

//le damos un extends CrudRepository para crear el crud
public interface CategoryCrudRepository extends CrudRepository <Category,Integer>{//se hace referencia a la entidad que va a implementar el crud y el tipo de llave primaria Integer
    //post, get, put, delete==create,read,upload,delete
}

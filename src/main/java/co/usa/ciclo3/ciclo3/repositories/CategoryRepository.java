package co.usa.ciclo3.ciclo3.repositories;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.repositories.crud.CategoryCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    @Autowired /*acá es como crear un objeto del interface CategoryCrudRepository
    la cual usaremos para la conexión a la base de datos*/
    private CategoryCrudRepository repository;

    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<Category> getAll(){
        return (List<Category>) repository.findAll();//busca todos los registros de la tabla admin
    }

    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<Category> getCategory(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public Category save(Category category){

        return repository.save(category);
    }

    /*delete from table*/
    public void delete(Category category){
        repository.delete(category);
    }
}

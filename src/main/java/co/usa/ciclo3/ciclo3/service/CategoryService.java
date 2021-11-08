package co.usa.ciclo3.ciclo3.service;


import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired//creamos objeto de la clase repository y lo inyectamos
    private CategoryRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
/* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<Category> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<Category> getCategory(int categoryId){
        return repository.getCategory(categoryId);
    }
 /* *************************************Post o Read**********************************************/

    public Category save(Category category){
        //consulta si no se envía el Id
        if(category.getId()==null){
            return repository.save(category);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<Category> existCategory = repository.getCategory(category.getId());
            //si los datos ya existen retorne los datos enviados
            if(existCategory.isPresent()){
                return category;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(category);
            }
        }
    }

 /* ********************************Put o Update*************************************************************************/

 public Category update(Category category){
     //si el Id no es null, es decir si enviaron el Id
     if(category.getId()!=null){
         //obtener el admin por id creamos un objeto de la clase Optional de java.util
         Optional<Category> existCategory= repository.getCategory(category.getId());
         //si el id del  admin existe
         if(existCategory.isPresent()){
             //si el campo nombre no está vacío, reemplazar con los datos enviados
             if(category.getName()!=null){
                 existCategory.get().setName(category.getName());
             }

             //si el campo descripción no es null, reemplazar con los datos enviados
             if(category.getDescription()!=null){
                 existCategory.get().setDescription(category.getDescription());
             }
             //retorne los datos con el update implementado
             return repository.save(existCategory.get());

         }else{//si el admin no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
             return category;
         }

     }else{//si no se envío el Id retorne los datos enviados
         return category;
     }
 }
 /* ***************************************Delete**********************************************************/

 public boolean delete(int categoryId){
     //si obtiene el id, lo borramos y retornamos true
     return getCategory(categoryId).map(category->{
         repository.delete(category);//ejecutamos una función anónima para eliminarlo y retornamos true
         return true;
     }).orElse(false);//si no obtiene el id retorna false

     //si obtiene Id, los mapea a una variable
        /* boolean respuesta=getAdmin(adminId).map(admin->{
          repository.delete(admin);//ejecutamos una función anónima para eliminarlo y retornamos true
          return true;
       }).orElse(false);//ni no lo elimina devuelve un false
        return respuesta;
    }*/
 }
};





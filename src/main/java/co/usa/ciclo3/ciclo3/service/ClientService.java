package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired//creamos un objeto de la clase repository u le inyectamos los datos con @Autowired
    private ClientRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de clientes*/
    public List<Client> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<Client> getClient(int clientId){
        return repository.getClient(clientId);
    }
    /* *************************************Post o Read**********************************************/

    public Client save(Client client){
        //consulta si no se envía el Id
        if(client.getIdClient() == null){
            return repository.save(client);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<Client> existCategory = repository.getClient(client.getIdClient());
            //si los datos ya existen retorne los datos enviados
            if(existCategory.isPresent()){
                return client;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(client);
            }
        }
    }
    /* ********************************Put o Update*************************************************************************/

    public Client update(Client client){
        //si el Id no es null, es decir si enviaron el Id
        if(client.getIdClient()!=null){
            //obtener el admin por id creamos un objeto de la clase Optional de java.util
            Optional<Client> existCategory= repository.getClient(client.getIdClient());
            //si el id del cliente existe
            if(existCategory.isPresent()){
                //si el campo nombre no es null, reemplazar con los datos enviados
                if(client.getName()!=null){
                    existCategory.get().setName(client.getName());
                }
                //si el campo descripción no es null, reemplazar con los datos enviados
                if(client.getEmail()!=null){
                    existCategory.get().setEmail(client.getEmail());
                }
                if(client.getPassword()!=null){
                    existCategory.get().setPassword(client.getPassword());
                }
                if(client.getAge()!=null){
                    existCategory.get().setAge(client.getAge());
                }
                //retorne los datos con el update implementado
                return repository.save(existCategory.get());

            }else{//si el admin no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return client;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return client;
        }
    }
    /* ***************************************Delete**********************************************************/

    public boolean delete(int clientId){
        //si obtiene el id, lo borramos y retornamos true
        return getClient(clientId).map(client->{
            repository.delete(client);//ejecutamos una función anónima para eliminarlo y retornamos true
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
}

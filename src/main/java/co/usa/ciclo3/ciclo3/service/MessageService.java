package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired//creamos objeto de la clase repository y lo inyectamos con Autowired
    private MessageRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<Message> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<Message> getMessage(int messageId){
        return repository.getMessage(messageId);
    }
    /* *************************************Post o Read**********************************************/

    public Message save(Message message){
        //consulta si no se envía el Id
        if(message.getIdMessage()==null){
            return repository.save(message);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<Message> existMessage = repository.getMessage(message.getIdMessage());
            //si los datos ya existen retorne los datos enviados
            if(existMessage.isPresent()){
                return message;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(message);
            }
        }
    }
    /* ********************************Put o Update*************************************************************************/

    public Message update(Message message){
        //si el Id no es null, es decir si enviaron el Id
        if(message.getIdMessage()!=null){
            //obtener el admin por id creamos un objeto de la clase Optional de java.util
            Optional<Message> existMessage= repository.getMessage(message.getIdMessage());
            //si el id del  admin existe
            if(existMessage.isPresent()){
                //si el campo messageText no es null, reemplazar con los datos enviados
                if(message.getMessageText()!=null){
                    existMessage.get().setMessageText(message.getMessageText());
                }
                if(message.getClient()!=null){
                    existMessage.get().setClient(message.getClient());
                }
                if(message.getCloud()!=null){
                    existMessage.get().setCloud(message.getCloud());
                }


                //retorne los datos con el update implementado
                return repository.save(existMessage.get());

            }else{//si el admin no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return message;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return message;
        }
    }
    /* ***************************************Delete**********************************************************/

    public boolean delete(int messageId){
        //si obtiene el id, lo borramos y retornamos true
        return getMessage(messageId).map(message -> {
            repository.delete(message);//ejecutamos una función anónima para eliminarlo y retornamos true
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

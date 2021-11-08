package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.repositories.ReservationRepository;
import co.usa.ciclo3.reports.CountClient;
import co.usa.ciclo3.reports.CountCloud;
import co.usa.ciclo3.reports.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<Reservation> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<Reservation> getReservation(int reservationId){
        return repository.getReservation(reservationId);
    }
    /* *************************************Post o Read**********************************************/

    public Reservation save(Reservation reservation){
        //consulta si no se envía el Id
        if(reservation.getIdReservation()==null){
            return repository.save(reservation);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<Reservation> existReservation = repository.getReservation(reservation.getIdReservation());
            //si los datos ya existen retorne los datos enviados
            if(existReservation.isPresent()){
                return reservation;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(reservation);
            }
        }
    }
    /* ********************************Put o Update*************************************************************************/

    public Reservation update(Reservation reservation){
        //si el Id no es null, es decir si enviaron el Id
        if(reservation.getIdReservation()!=null){
            //obtener el admin por id creamos un objeto de la clase Optional de java.util
            Optional<Reservation> existReservation= repository.getReservation(reservation.getIdReservation());
            //si el id del  admin existe
            if(existReservation.isPresent()){
                //si el campo StartDate no es null, reemplazar con los datos enviados
                if(reservation.getStartDate()!=null){
                    existReservation.get().setStartDate(reservation.getStartDate());
                }
                //si los campos  no son  null o no están vacíos, reemplazar con los datos enviados
                if(reservation.getDevolutionDate()!=null){
                    existReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    existReservation.get().setStatus(reservation.getStatus());
                }
                if(reservation.getClient()!=null){
                    existReservation.get().setClient(reservation.getClient());
                }
                if(reservation.getCloud()!=null){
                    existReservation.get().setCloud(reservation.getCloud());
                }
                //retorne los datos con el update implementado
                return repository.save(existReservation.get());

            }else{//si  reservation no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return reservation;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return reservation;
        }
    }
    /* ***************************************Delete**********************************************************/

    public boolean delete(int reservationId){
        //si obtiene el id, lo borramos y retornamos true
        return getReservation(reservationId).map(reservation ->{
            repository.delete(reservation);//ejecutamos una función anónima para eliminarlo y retornamos true
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

    /*Métodos para los reportes*/

    /*Nos devuelve es estatus de la reservación cuántos cancelados, cuántos completos*/
    public ReservationStatus getReservationStatusReport(){
        /*creamos una lista de reservaciones completas y otra de canceladas*/
        List<Reservation> programado=repository.getReservationByStatus("programado");
        List<Reservation> cancelado=repository.getReservationByStatus("cancelado");
        List<Reservation> realizado=repository.getReservationByStatus("realizado");

        /*retorna un nuevo objeto con la cantidad de objetos de cada lista*/
        return new ReservationStatus(programado.size(),cancelado.size(),realizado.size());
    }
    /*nos devuelve las fechas de las reservaciones*/
    public List<Reservation> getReservationPeriod(String dateOne, String dateTwo){
        //hacemos el cambio de formato de la fecha que recibimos en el front
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try{
            /*aca el objeto toma la hora en que son creados
            * parseamos los datos de string a date*/
            Date startDate=dateFormat.parse(dateOne);
            Date endDate=dateFormat.parse(dateTwo);
            /*corroboramos que la fecha de inicio sea menor a la fecha final*/
            if(startDate.before(endDate)){
                return repository.getReservationPeriod(startDate,endDate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        /*si no se cumple el if retorna un arreglo vacío*/
        return new ArrayList<>();
    }

    /*llamamos al topClients*/
    public List<CountClient> getTopClients(){
        return repository.getTopClients();
    }
    /*llamamos al topClouds*/
    public List<CountCloud> getTopClouds(){
        return repository.getTopClouds();
    }


}

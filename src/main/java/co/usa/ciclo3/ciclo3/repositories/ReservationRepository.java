package co.usa.ciclo3.ciclo3.repositories;

import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.Cloud;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repositories.crud.ReservationCrudRepository;
import co.usa.ciclo3.reports.CountClient;
import co.usa.ciclo3.reports.CountCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired /*acá es como crear un objeto del interface ReservationCrudRepository
    la cual usaremos para la conexión a la base de datos*/
    private ReservationCrudRepository repository;


    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<Reservation> getAll(){
        return (List<Reservation>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<Reservation> getReservation(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public  Reservation save(Reservation reservation){
        reservation.setStatus("created");
        return repository.save(reservation);
    }

    /*delete from table*/
    public void delete(Reservation reservation){
        repository.delete(reservation);
    }

/*Métodos para los informes*/

    /*retorna una lista de reservaciones por estatus*/
    public List<Reservation>getReservationByStatus(String status){
        return repository.findAllByStatus(status);
    }

    /*retorna una lista de fechas*/
    public List<Reservation>getReservationPeriod(Date dateOne, Date dateTwo){
        return repository.findAllByStartDateAfterAndStartDateBefore(dateOne,dateTwo);
    }
    /*nos retorna un alista de countClient*/
    public List<CountClient>getTopClients(){
        //creamos una lista de countClient
        List<CountClient>clientList=new ArrayList<>();
        //creamos una lista report de objetos
        List<Object[]>report=repository.countTotalReservationByClient();
        for(int i=0;i<report.size();i++){
            //agregamos cada objeto a la lista número:cliente (CountClient((Long)report.get(i)[1],(client)report.get(i)[0])
            clientList.add(new CountClient((Long)report.get(i)[1],(Client)report.get(i)[0]));
        }
        return clientList;
    }

    public List<CountCloud>getTopClouds(){
        //creamos una lista de countClient
        List<CountCloud>cloudList=new ArrayList<>();
        //creamos una lista report de objetos
        List<Object[]>report=repository.countTotalReservationByCloud();
        for(int i=0;i<report.size();i++){
            //agregamos cada objeto a la lista número:cliente (CountClient((Long)report.get(i)[1],(client)report.get(i)[0])
            cloudList.add(new CountCloud((Long)report.get(i)[1],(Cloud)report.get(i)[0]));
        }
        return cloudList;
    }
}

package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.service.ReservationService;
import co.usa.ciclo3.reports.CountClient;
import co.usa.ciclo3.reports.CountCloud;
import co.usa.ciclo3.reports.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Reservation")
@CrossOrigin(origins = "*",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class ReservationController {

    @Autowired
    private ReservationService service;

    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/

    @GetMapping("/all")
    public List<Reservation> getReservation(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id}")            //pasamos el Id por parámetro a la variable clientId
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId){
        return service.getReservation(reservationId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public Reservation save(@RequestBody Reservation reservation){//requiéralo del cuerpo de la consulta

        return service.save(reservation);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public Reservation update(@RequestBody Reservation reservation){//requiéralo del cuerpo de la consulta
        return service.update(reservation);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id}")  //con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId){

        return service.delete(reservationId);
    }
    /*Métodos para los reportes*/

    /*nos retorna el estatus de cada reservación*/
    @GetMapping("/report-status")
    public ReservationStatus getReservationsStatusReport(){
      return service.getReservationStatusReport();
    }

    /*Nos retorna una lista de reservaciones, recibe dos fechas como parámetro en la url*/
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo
    ){
        return service.getReservationPeriod(dateOne, dateTwo);
    }
    /*Nos retorna una lista de clientes*/
    @GetMapping("/report-clients")
    public List<CountClient>getClients(){
        return service.getTopClients();
    }

    /*Nos retorna una lista de clientes*/
    @GetMapping("/report-clouds")
    public List<CountCloud>getClouds(){
        return service.getTopClouds();
    }





}

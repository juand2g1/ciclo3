package co.usa.ciclo3.ciclo3.repositories.crud;

import co.usa.ciclo3.ciclo3.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/*en la interface definimos un modelo pero no lo implementamos, la implementación la hacemos en el Repository*/

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {//se hace referencia a la entidad que va a implementar el crud y el tipo de llave primaria Integer
    //post, get, put, delete==create,read,upload,delete

    /*Query methods*/

   /*nos trae las reservaciones por su estatus
   * select * from reservation  were description like description*/
    public List<Reservation> findAllByStatus(String status);

    /*Nos busca por fecha de inicio y fecha de finalización, y le pasamos esas fechas como parámetro
    * select * from reservation where startDate > dateOne and startDate < dateTwo*/
    public  List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    /*Nos trae una lista de objetos con las reservaciones por cada cliente usando JPQL, lo devuelve categoria:número*/
    @Query(value = "SELECT r.client, COUNT(r.client) FROM reservation AS r group by r.client order by COUNT(r.client) DESC", nativeQuery = true)
    public List<Object[]> countTotalReservationByClient();

 /*Nos trae una lista de objetos con las reservaciones por cada nube usando JPQL, lo devuelve categoria:número*/
    @Query(value = "SELECT r.cloud, COUNT(r.cloud) FROM reservation AS r group by r.cloud order by COUNT(r.cloud) DESC", nativeQuery = true)
    public List<Object[]> countTotalReservationByCloud();

    /*
    esto es lo que hace el query, este resultado es el arreglo de objetos
    cliente a, 3 reservaciones
    cliente b, 2 reservaciones
    cliente c, 12 reservaciones
     */

}

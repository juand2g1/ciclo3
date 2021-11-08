/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo3.ciclo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status;

    /* *************************************** Empezamos a dar las relaciones entre tablas ****************************/
//muchas reservaciones una nube
    @ManyToOne
    @JoinColumn(name = "cloudId")
    @JsonIgnoreProperties("reservations")
    private Cloud cloud;
//muchas reservaciones un cliente
    @ManyToOne
    //le damos un innerJoin con la tabla de cliente en el campo id
    @JoinColumn(name = "idClient")
    //le decimos que al traer la lista de clientes ignore la lista de reservas y mensajes para evitar redundancia de datos
    @JsonIgnoreProperties({"reservations","messages"})
    private Client client;

    //una reservacion una calificaion
    @OneToOne(cascade = {CascadeType.REMOVE},mappedBy="reservation")
    //le decimos que al traer la lista de calificaciones ignore la lista de reservas para evitar redundancia de datos
    @JsonIgnoreProperties("reservation")
    private Score score;

}

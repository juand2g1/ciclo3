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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="message")

public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    @Column(name="messageText",length=250)
    private String messageText;

    /* *************************************** Empezamos a dar las relaciones entre tablas ****************************/

    @ManyToOne
    @JoinColumn(name="cloudId")
    @JsonIgnoreProperties({"messages","reservations"})
    //creamos un objeto de tipo cloud para mostrar la nube a la que pertenece el mensaje
    private Cloud cloud;

    @ManyToOne
    @JoinColumn(name="idClient")
    //le decimos que al traer la lista de cliente ignore la lista de messages y reservations para evitar redundancia de datos
    @JsonIgnoreProperties({"messages","reservations"})
    //creamos un objeto de tipo cliente para mostrar los datos del cliente que pone el mensaje
    private Client client;


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo3.ciclo3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity//definimos nuestras entidades
@Table(name="admin")//definimos a qué tabla hace referencia

public class Admin implements Serializable {
    //le damos una llave primaria al id de admin
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdmin;
    //le ponemos los demas atributos de la entidad admin
    @Column(length=45)
    private String email;
    @Column(length=45)
    private String password;
    @Column(length=250)
    private String name;

}

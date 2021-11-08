package co.usa.ciclo3.reports;
import co.usa.ciclo3.ciclo3.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*esta clase la hacemos para poder contar únicamente, no es una entidad es una especie de comodín */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountClient {

    //atributos
    private Long total;
    private Client Client;
}

package co.usa.ciclo3.reports;

/*ojo esto no es un a entidad, es solo un comodín para los reportes*/

public class ReservationStatus {
    //atributos para poder retornar el estatus de la reservación
    private Integer programado;
    private Integer cancelado;
    private Integer realizado;

    public ReservationStatus() {
    }

    public ReservationStatus(Integer programado, Integer cancelado, Integer realizado) {
        this.programado = programado;
        this.cancelado = cancelado;
        this.realizado = realizado;
    }

    public Integer getProgramado() {
        return programado;
    }

    public void setProgramado(Integer programado) {

    }

    public Integer getCancelado() {
        return cancelado;
    }

    public void setCancelado(Integer cancelado) {

    }

    public Integer getRealizado() {
        return realizado;
    }

    public void setRealizado(Integer realizado) {

    }
}

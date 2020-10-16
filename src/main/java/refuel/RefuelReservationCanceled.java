package refuel;

import lombok.Data;

@Data
public class RefuelReservationCanceled extends AbstractEvent {

    private Long id;
    private Long reservationId;
    private String fuelType;
    private int qty;
    private String customerId;
    private String stationId;
    private String reservationStatus;

    public RefuelReservationCanceled(){
        super();
    }

}

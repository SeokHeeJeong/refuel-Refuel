package refuel;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class RefuelReserved extends AbstractEvent {

    private Long id;
    private Long reservationId;
    private String fuelType;
    private int qty;
    private String customerId;
    private String stationId;
    private String reservationStatus;

    public RefuelReserved(){
        super();
    }

}

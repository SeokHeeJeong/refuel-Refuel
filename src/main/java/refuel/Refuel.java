package refuel;

import javax.persistence.*;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@Table(name="Refuel_table")
public class Refuel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long reservationId;
    private String fuelType;
    private int qty;
    private String customerId;
    private String stationId;

    //@ColumnDefault("REFUELLED")
    @Column(name = "refuel_status")
    private String refuelStatus;

    @PostPersist
    public void onPostPersist(){

        if (refuelStatus.equals("REFUELLED")){
            // 주유 등록
            Refuelled refuelled = new Refuelled();
            BeanUtils.copyProperties(this, refuelled);
            // 상태 변경
            refuelled.setReservationStatus("REFUELLED");
            refuelled.publishAfterCommit();// 주유건 취소

        }

    }

}

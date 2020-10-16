package refuel;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long>{

    List<Reservation> findByReservationId(Long reservationId);

}
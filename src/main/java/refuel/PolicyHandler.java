package refuel;

import refuel.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    ReservationRepository ReservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_Reserve(@Payload Paid paid){

        if(paid.isMe()){
            // 주유예약 -결제 완료 - refuel 예약정보 생성
            System.out.println("##### listener Reserve : " + paid.toJson());

            Reservation reservation = new Reservation();
            reservation.setReservationId(paid.getReservationId());
            reservation.setCustomerId(paid.getCustomerId());
            reservation.setFuelType(paid.getFuelType());
            reservation.setQty(paid.getQty());
            reservation.setStationId(paid.getStationId());
            reservation.setReservationStatus(paid.getReservationStatus());

            ReservationRepository.save(reservation);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_Cancel(@Payload PaymentCanceled paymentCanceled){

        if(paymentCanceled.isMe()){
            // 주유예약 예약취소
            System.out.println("##### listener Cancel : " + paymentCanceled.toJson());

            Optional<Reservation> reservationOptional = ReservationRepository.findById(paymentCanceled.getReservationId());
            Reservation reservation = reservationOptional.get();
            reservation.setReservationStatus("CANCELED");

            ReservationRepository.save(reservation);
        }
    }

}

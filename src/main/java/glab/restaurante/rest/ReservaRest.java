package glab.restaurante.rest;

import glab.restaurante.modelos.Reserva;
import glab.restaurante.modelos.User;
import glab.restaurante.service.ReservaService;
import glab.restaurante.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.PUT)
public class ReservaRest {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}")
    private ResponseEntity<Reserva> postReserva(@RequestBody Reserva reserva, @PathVariable("userId") Long userId) {
        try {
            User user = userService.getById(userId);
            if (user == null) {
                throw new RuntimeException("El usuario no existe");
            }
            reserva.setUser(user);
            reserva.setState(Reserva.State.PENDIENTE);
            reservaService.save(reserva);
            return ResponseEntity.ok(reserva);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/confirm/{id}")
    private ResponseEntity<Reserva> confirmReserva(@PathVariable("id") Long id) {
        try {
            Reserva reserva = reservaService.getById(id);
            if (reserva == null) {
                throw new RuntimeException("La reserva no existe");
            }
            reserva.setState(Reserva.State.CONFIRMADO);
            reservaService.save(reserva);
            return ResponseEntity.ok(reserva);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Reserva> updateReserva(@RequestBody Reserva reserva, @PathVariable("id") Long id) {
        try {
            Reserva exist = reservaService.getById(id);
            if (exist == null) {
                throw new RuntimeException("La reserva no existe");
            }
            exist.setReservationDate(reserva.getReservationDate());
            exist.setReservationType(reserva.getReservationType());
            exist.setPeople(reserva.getPeople());
            exist.setDescriptionObservations(reserva.getDescriptionObservations());
            reservaService.save(exist);
            return ResponseEntity.ok(exist);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

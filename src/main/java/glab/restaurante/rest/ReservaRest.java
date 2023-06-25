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

    @PutMapping("/{id}")
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
}

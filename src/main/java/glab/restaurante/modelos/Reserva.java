package glab.restaurante.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    private Date reservationDate;
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;
    private int people;
    private String descriptionObservations;
    @Enumerated(EnumType.STRING)
    private State state;

    public enum ReservationType {
        CENA,
        ALMUERZO,
        ONCES,
        CUMPLEANOS,
        OCASION_ESPECIAL,
    }

    public enum State {
        PENDIENTE,
        CONFIRMADO
    }

    public Reserva() {
    }

    public Reserva(User user, Date reservationDate, ReservationType reservationType, int people, String descriptionObservations, State state) {
        this.user = user;
        this.reservationDate = reservationDate;
        this.reservationType = reservationType;
        this.people = people;
        this.descriptionObservations = descriptionObservations;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getDescriptionObservations() {
        return descriptionObservations;
    }

    public void setDescriptionObservations(String descriptionObservations) {
        this.descriptionObservations = descriptionObservations;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", user=" + user.getUserId() +
                ", reservationDate=" + reservationDate +
                ", reservationType=" + reservationType +
                ", people=" + people +
                ", descriptionObservations='" + descriptionObservations + '\'' +
                ", state=" + state +
                '}';
    }
}

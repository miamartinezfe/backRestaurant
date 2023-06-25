package glab.restaurante.service;


import glab.restaurante.modelos.Reserva;
import glab.restaurante.modelos.Role;
import glab.restaurante.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ReservaService implements ReservaRepository {
    @Autowired
    private ReservaRepository reservaRepository;


    @Override
    public void flush() {

    }

    @Override
    public <S extends Reserva> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Reserva> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Reserva> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Reserva getOne(Long aLong) {
        return null;
    }

    @Override
    public Reserva getById(Long aLong) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(aLong);
        if (optionalReserva.isPresent()) {
            return optionalReserva.get();
        } else {
            return null;
        }
    }

    @Override
    public Reserva getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Reserva> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Reserva> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Reserva> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Reserva> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Reserva> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Reserva> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Reserva, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Reserva> S save(S entity) {
        return reservaRepository.save(entity);
    }

    @Override
    public <S extends Reserva> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Reserva> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Reserva> findAll() {
        return null;
    }

    @Override
    public List<Reserva> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Reserva entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Reserva> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Reserva> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Reserva> findAll(Pageable pageable) {
        return null;
    }
}

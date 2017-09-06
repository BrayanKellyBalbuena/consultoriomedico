package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Reservacion;
import edu.itla.consultoriomedico.business.repository.ReservacionRepository;

import java.util.ArrayList;
import java.util.List;

public class ReservacionRepositoryImpl implements ReservacionRepository {

    private List<Reservacion> medicoList=new ArrayList<>();

    @Override
    public void save(Reservacion entity) {
        medicoList.add(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Reservacion entity) {
        int index=medicoList.indexOf(entity);
        medicoList.set(index,entity);
    }

    @Override
    public Reservacion findById(Long id) {
        return medicoList.get(id.intValue());
    }

    @Override
    public List<Reservacion> findAll() {
        return medicoList;
    }
}

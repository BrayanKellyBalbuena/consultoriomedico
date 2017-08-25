package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Medico;
import edu.itla.consultoriomedico.business.repository.MedicoRepository;

import java.util.ArrayList;
import java.util.List;

public class MedicoRepositoryImpl implements MedicoRepository {


        private List<Medico> medicoList=new ArrayList<>();

        @Override
        public void save(Medico entity) {
            medicoList.add(entity);
        }

        @Override
        public void update(Medico entity) {
            int index=medicoList.indexOf(entity);
            medicoList.set(index,entity);
        }

        @Override
        public Medico findById(Long id) {
            return medicoList.get(id.intValue());
        }

        @Override
        public List<Medico> findAll() {
            return medicoList;
        }
}

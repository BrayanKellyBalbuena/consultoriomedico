package edu.itla.consultoriomedico.business.factory;

import edu.itla.consultoriomedico.business.enums.RepositoryEnum;
import edu.itla.consultoriomedico.business.repository.GenericRepository;
import edu.itla.consultoriomedico.business.repository.impl.CitaRepositoryImpl;
import edu.itla.consultoriomedico.business.repository.impl.MedicoRepositoryImpl;
import edu.itla.consultoriomedico.business.repository.impl.PacienteRepositoryImpl;
import edu.itla.consultoriomedico.business.repository.impl.ReservacionRepositoryImpl;

public class ApplicationContext {
    public static GenericRepository getReposiory(RepositoryEnum repositoryEnum) {
        switch(repositoryEnum) {
            case CITA_REPOSITORY:
                return new CitaRepositoryImpl();
            case MEDICO_REPOSITORY:
                return new MedicoRepositoryImpl();
            case PACIENTE_REPOSITORY:
                return new PacienteRepositoryImpl();
            case RESERVACION_REPOSITORY:
                return new ReservacionRepositoryImpl();
        }

        return null;
    }
}

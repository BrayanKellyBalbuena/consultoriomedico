package edu.itla.consultoriomedico.business.enums;

public enum ServiceEnum {
    PACIENTE_SERVICE("pacienteService"),
    MEDICO_SERVICE("medicoService"),
    RESERVACION_SERVICE("reservacionService"),
    PROCEDIMIENTO_SERVICE("procedimientoService"),
    ESPECIALIDAD_SERVICE("especialidadMedicaService");

    private final String value;

    ServiceEnum(String service) {
        this.value = service;
    }

    public String getValue() {
        return value;
    }
}

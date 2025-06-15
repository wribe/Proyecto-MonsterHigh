package es.iesteis;

public class PersonajeNoEncontrado extends RuntimeException {
    public PersonajeNoEncontrado(String message) {
        super(message);
    }
}

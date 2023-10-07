package org.example.logservice;

public class StringDatePair {
    private String cadena;
    private String fecha;

    public StringDatePair(String string, String date) {
        this.cadena = string;
        this.fecha = date;
    }

    @Override
    public String toString() {
        return "Cadena: " + cadena + ", Fecha: " + fecha;
    }
}

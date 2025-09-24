package com.mycompany.estudiantes;

import java.util.ArrayList;
import java.util.List;

public class Estudiantes {
    private String nombre;
    private List<Integer> notas;

    public Estudiantes(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(int nota) {
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100");
        }
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        int suma = 0;
        for (int n : notas) {
            suma += n;
        }
        return (double) suma / notas.size();
    }

    public boolean aprobo() {
        return calcularPromedio() >= 60;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Integer> getNotas() {
        return notas;
    }
}

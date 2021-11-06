package ar.com.reba.testproject.service;

import ar.com.reba.testproject.entity.Persona;
import ar.com.reba.testproject.entity.Relacion;

import java.util.*;
import java.util.stream.Collectors;

public class RelacionEntrePersonas {

    private static final String RESPUESTA_FORMATO = "%s %s es %s de %s %s";

    public static String relacion(Persona p1, Persona p2) {

        if(esHermano(p1, p2)) {
            return String.format(RESPUESTA_FORMATO,
                    p1.getNombre(), p1.getApellido(),
                    RelacionTipo.HERMANO.toString(),
                    p2.getNombre(), p2.getApellido());
        }

        if(esPrimo(p1, p2)) {
            return String.format(RESPUESTA_FORMATO,
                    p1.getNombre(), p1.getApellido(),
                    RelacionTipo.PRIMO.toString(),
                    p2.getNombre(), p2.getApellido());
        }

        if(esTio(p1, p2)) {
            return String.format(RESPUESTA_FORMATO,
                    p1.getNombre(), p1.getApellido(),
                    RelacionTipo.TIO.toString(),
                    p2.getNombre(), p2.getApellido());
        }

        if(esTio(p2, p1)) {
            return String.format(RESPUESTA_FORMATO,
                    p2.getNombre(), p2.getApellido(),
                    RelacionTipo.TIO.toString(),
                    p1.getNombre(), p1.getApellido());
        }
        return "No relacion entre las personas";
    }

    //p1 es hermano de p2
    //los hermanos comparten padres
    public static boolean esHermano(Persona p1, Persona p2) {
        if(p1.getPersonaId() == p2.getPersonaId()) {
            return  false;
        }

        try {
            Set<Persona> padresP1 = obtenerPredecesores(p1);
            Set<Persona> padresP2 = obtenerPredecesores(p2);

            List<Integer> ids1 = padresP1.stream()
                    .map(p -> p.getPersonaId())
                    .collect(Collectors.toList());

            List<Integer> ids2 = padresP2.stream()
                    .map(p -> p.getPersonaId())
                    .collect(Collectors.toList());

            return ids1.contains(ids2.get(0)) || ids1.contains(ids2.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //p1 es tio de p2
    //el tio(p1) y el padre(de p2) comparten abuelos
    public static boolean esTio(Persona p1, Persona p2) {
        Set<Persona> abuelosP1;
        Set<Persona> padresP2;
        Set<Persona> abuelosP2 = new HashSet<>();

        try {
            abuelosP1 = obtenerPredecesores(p1);
            padresP2 = obtenerPredecesores(p2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        for(Persona padre: padresP2) {
            try {
                abuelosP2.addAll(obtenerPredecesores(padre));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(Persona abuP2: abuelosP2) {
            if(abuelosP1.contains(abuP2)) {
                return true;
            }
        }

        return false;
    }

    //p1 es primo de p2
    //los primos comparten abuelos
    public static boolean esPrimo(Persona p1, Persona p2) {
        try {
            Set<Persona> padresP1 = obtenerPredecesores(p1);
            Set<Persona> padresP2 = obtenerPredecesores(p2);
            Set<Persona> abuelosP1 = new HashSet<>();
            Set<Persona> abuelosP2 = new HashSet<>();

            for(Persona padre: padresP1) {
                abuelosP1.addAll(obtenerPredecesores(padre));
            }

            for(Persona padre: padresP2) {
                abuelosP2.addAll(obtenerPredecesores(padre));
            }

            for(Persona abuP2: abuelosP2) {
                if(abuelosP1.contains(abuP2)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static Set<Persona> obtenerPredecesores(Persona p) throws Exception {
        Set<Relacion> relaciones = new HashSet<>(p.getPadres());

        if(relaciones.isEmpty()) {
            throw new Exception();
        } else {
            return relaciones.stream()
                    .map(r -> r.getPersonaByPadreId())
                    .collect(Collectors.toSet());
        }
    }

//    private static Set<Persona> obtenerSucesores(Persona p) {
//        Set<Relacion> relaciones = new HashSet<>(p.getHijos());
//
//        return relaciones.stream()
//                .map(r -> r.getPersonaByPadreId())
//                .collect(Collectors.toSet());
//    }

    public static boolean esPadreDe(Persona p1, Persona p2) {
        Set<Persona> padresP2;

        try {
            padresP2 = obtenerPredecesores(p2);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return padresP2.contains(p1);
    }
}
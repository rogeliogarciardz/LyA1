/*
 * Copyright (C) 2020 Rogelio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lya1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Rogelio
 */
public class ALexico {

    private Automata afd;
    private FileReader fr;

    private int simbolo_analizado;
    private boolean pendiente;
    private String componente;
    private int q;

    private ArrayList<String> PalabrasReservadas;
    private ArrayList<String> Variables;
    private ArrayList<String> ConstantesTexto;
    private ArrayList<Integer> ConstantesNumeros;
    private boolean eof = false;

    public ALexico() {
        PalabrasReservadas = new ArrayList();

        PalabrasReservadas.add("programa");
        PalabrasReservadas.add("inicio");
        PalabrasReservadas.add("fin");
        PalabrasReservadas.add("cadena");
        PalabrasReservadas.add("entero");
        PalabrasReservadas.add("imprimir");
        PalabrasReservadas.add("leer");
        PalabrasReservadas.add("si");
        PalabrasReservadas.add("sino");
        PalabrasReservadas.add("fins");
        PalabrasReservadas.add("repite");
        PalabrasReservadas.add("finr");

        afd = new Automata();

    }

    public void analizar(String archivo) throws FileNotFoundException, IOException {
        fr = new FileReader(archivo);

        siguiente_simbolo();

        pendiente = true;

        while (!eof) {
            scan();
        }
    }

    private void scan() throws IOException {

        if (!pendiente) {
            siguiente_simbolo();
        }

        pendiente = false;
        componente = "";
        q = 0;

        while (q < 100) {
            if (q == 0) {
                componente = "";
            }

            q = estados(q, simbolo_analizado);
            componente += (char) simbolo_analizado;
            //System.out.println(" -> " + q);
            
            if (q < 100) {
                siguiente_simbolo();
            }
        }

        /*while( estados ( q,simbolo_analizado) < 100){
            componente += (char) simbolo_analizado;
            simbolo_analizado =  fr.read();
        }*/
        switch (q) {
            case 100: // /
                System.out.println("Componente: [" + componente.strip().substring(0, componente.length() - 1) + "]");
                System.out.println("Clase: 5  Tipo 4");
                pendiente = true;
                break;
            case 101: // +
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 5  Tipo 1");
                break;
            case 102: // -
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 5  Tipo 2");
                break;
            case 103: // *
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 5  Tipo 3");
                break;
            case 104: // <=
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 6  Tipo 3");
                break;
            case 105: // <
                System.out.println("Componente: [" + componente.strip().substring(0, componente.length() - 1) + "]");
                System.out.println("Clase: 6  Tipo 1");
                pendiente = false;
                break;
            case 106: // >=
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 6  Tipo 2");
                break;
            case 107: // >
                System.out.println("Componente: [" + componente.strip().substring(0, componente.length() - 1) + "]");
                System.out.println("Clase: 6  Tipo 4");
                pendiente = true;
                break;
            case 108: // = 
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 6  Tipo 6");
                break;
            case 109: // !=
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 6  Tipo 5");
                break;
            case 110: // :=
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 7  Tipo 1");
                //pendiente = true;
                break;
            case 111: //ID
                System.out.println("Componente: [" + componente.strip().substring(0, componente.length() - 1) + "]");
                System.out.println("Clase: 1,2  Tipo ?");
                pendiente = true;
                break;
            case 112: //DIG
                System.out.println("Clase: 3  Tipo ?");
                System.out.println("Componente: [" + componente.strip().substring(0, componente.length() - 1) + "]");
                pendiente = true;
                break;
            case 113: //CTEX
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 4  Tipo ?");
                break;
            case 114: //;
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 8  Tipo 1");
                break;
            case 199: //eof
                //eof = true;
                System.out.println("Componente: [" + componente.strip() + "]");
                System.out.println("Clase: 9  Tipo 1");
                return;
            default:
                error(q);

        }

    }

    private int estados(int q, int s) {
        int is = 15;

        if (Character.isLetter(s)) {
            is = 0;
        } else if (Character.isDigit(s)) {
            is = 1;
        } else if (s == 13) {
            is = 13;
        } else if (s == 34) {
            is = 14;
        } else if (s == 9 || s == 10 || s == 32) {
            is = 2;
        } else if (afd.getAlpha().contains("" + (char) s)) {
            is = afd.getAlpha().indexOf("" + (char) s);
        } else if (s == -1) {
            eof = true;
            is = 16;
        }

        //System.out.print("q: " + q + ", S:" + is);
        return afd.getMte(q, is);
    }

    private void error(int i) {
        switch (i) {
            case 200:
                System.out.println("Error es necesario simbolo =");
                break;
            case 201:
                System.out.println("Error es necesario simbolo =");
                break;
            case 202:
                System.out.println("Error caracter no reconocido");
                break;
            case 203:
                System.out.println("Error necesita \"");
                break;
        }
    }

    private void siguiente_simbolo() throws IOException {
        simbolo_analizado = fr.read();
        //System.out.println("SA: [" + (char) simbolo_analizado + "](" + simbolo_analizado + ")" + pendiente);

    }
}

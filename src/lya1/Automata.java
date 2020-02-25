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

import java.util.ArrayList;

/**
 *
 * @author Rogelio
 */
public class Automata {

    /**
     * El numero de estados del AFD
     */
    public int Q;
    /*
    * El alfabeto del AFD
    */
    public String alpha;
    private int q0;
    //TODO cambiar cadena de estados finales por arreglo o lista
    private ArrayList<Integer> F;
    private int mte[][];

    /**
     * Constructor por default que carga un ejemplo basico de un AFD
     */
    public Automata() {
        Q=4;
        alpha="01";
        q0=0;
        F = new ArrayList<>();
        F.add(0);//q0
        mte = new int[Q][alpha.length()];
        mte[0][0] = 2;
        mte[0][1] = 1;
        mte[1][0] = 3;
        mte[1][1] = 0;
        mte[2][0] = 0;
        mte[2][1] = 3;
        mte[3][0] = 1;
        mte[3][1] = 2;
        System.out.println("Automata por default creado.");
    }

    /**
     * Número de estados del AFD
     * @return devuelve el numero de estados del AFD
     */
    public int getQ() {
        return Q;
    }

    /**
     *
     * @return
     */
    public String getAlpha() {
        return alpha;
    }

    /**
     *
     * @return
     */
    public int getQ0() {
        return q0;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF() {
        return F;
    }

    /**
     * 
     * @param q El estado anterior
     * @param s El indice del simbolo
     * @return El nuevo estado del AFD
     */
    public int getMte(int q, int s) {
        if(q<Q && s<alpha.length()){
            return mte[q][s];
        }else{
            return -1;
        }
    }
}

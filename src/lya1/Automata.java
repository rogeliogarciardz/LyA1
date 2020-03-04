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

    //                       L   D   B   +   -   *   /   <   >   =   !   :   ;   E  \"   O  EOF";
    private int mte[][] = {{  8,  9,  0,101,102,103,  1,  4,  5,108,  6,  7,113,  0, 10,202, 199},
                           {100,100,100,100,100,  2,100,100,100,100,100,100,100,100,100,100,100},
                           {  2,  2,  2,  2,  2,  3,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2},
                           {  2,  2,  2,  2,  2,  2,  0,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2},
                           {105,105,105,105,105,105,105,105,105,104,105,105,105,105,105,105,105},
                           {107,107,107,107,107,107,107,107,107,106,107,107,107,107,107,107,107},
                           {200,200,200,200,200,200,200,200,200,109,200,200,200,200,200,200,200},
                           {201,201,201,201,201,201,201,201,201,110,201,201,201,201,201,201,201},
                           {  8,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111},
                           {112,  9,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112},
                           { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,203,114, 10,203}};
    
    /**
     * Constructor por default que carga un ejemplo basico de un AFD
     */
    public Automata() {
        Q=11;
        alpha="LDB+-*/<>=!:;ECOF";
        q0=0;
        F = new ArrayList<>();
        F.add(100); F.add(104); F.add(108);
        F.add(101); F.add(105); F.add(109);
        F.add(102); F.add(106); F.add(110);
        F.add(103); F.add(107); F.add(111);
        F.add(112); F.add(113); F.add(114);
        F.add(200); F.add(201); F.add(202);
        F.add(203); 
        
        /*
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
        */
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

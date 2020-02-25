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


/**
 *
 * @author Rogelio
 */
public class Analizador {
    
    private String cadena;
    private Automata afd;
    private int simbolo_analizado;

    public Analizador() {
        cadena = "1010";
        simbolo_analizado = -1;
        afd = new Automata();
    }
    
    public boolean analizar(String w){
        cadena = w;
        int q = afd.getQ0();
        char s;
        int is;
        try{
            s = siguiente_simbolo();
            while(true){
                is =  afd.getAlpha().indexOf(""+s);
                if (is ==-1) error(102);
                q = afd.getMte(q,is);
                if(q==-1) error(101);
                s = siguiente_simbolo();
            }
            
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Fin de cadena!");
            
        }
        return afd.getF().contains(q);
    }
    
    char siguiente_simbolo() throws IndexOutOfBoundsException{
        simbolo_analizado ++;
        return cadena.charAt(simbolo_analizado);
    }

    private void error(int i) {
            switch (i){
                case 101: System.out.println("Error simbolo o estado no declarado!");
                break;
                case 102: System.out.println("Error simbolo no declarado!");
                break;
            }
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.state;

/**
 *
 * @author Caio
 */
public class opExponencial {

    public static atomo opExponencial(String y) {
        switch (state) {
            case 0:
                defMaq = "Exponencial";
                atm = y;
                state = 1;
                return new atomo(atm, "Exponencial");
            case 1:
                atm = atm +y;
                if(!"+".equals(y) || !"+".equals(y)){
                     System.out.print("ERROe");
                    System.exit(1);
                }else
                {
                    state = 2;
                    return new atomo(atm, "Exponencial");
                }
                return new atomo(atm, "Exponencial");
            case 2:
                 atm = atm +y;
                 return new atomo(atm, "Exponencial");
            case 3:
                return new atomo(atm, "Exponencial");
        }

        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.state;
import static analisadorlexico.maquina.defMaq;

public class opRelacional {

    public static atomo opRelacional(String y) {
        defMaq = "Relacional";
        switch (state) {
            case 0:
                atm = atm + y;
                switch (y) {
                    case "<":
                        state = 1;
                        break;
                    case "=":
                        state = 2;
                        break;
                    case ">":
                        state = 3;
                        break;
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("ERRO");
                break;
        }

        atm = atm + y;
        return null;
    }
}

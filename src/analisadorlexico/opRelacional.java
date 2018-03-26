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
                atm = y;
                switch (y) {
                    case "<":
                        state = 1;
                        return new atomo(atm, "OP Relacional");
                    case "=":
                        state = 2;
                        return new atomo(atm, "OP Relacional");
                    case ">":
                        state = 3;
                        return new atomo(atm, "OP Relacional");
                }
                break;
            case 1:
                if("=".equals(y) || ">".equals(y) ){
                    atm = atm + y;
                    state = 4;
                    return new atomo(atm, "OP Relacional");
                }
                else{
                    return new atomo(atm, "OP Relacional");
                }
            case 3:
                atm = atm + y;
                if("=".equals(y)){
                    state = 4;
                    return new atomo(atm, "OP Relacional");
                }
                else{
                    return new atomo(atm, "OP Relacional");
                }
            default:
                System.out.println("ERRO");
                break;
        }

        
        return null;
    }
}

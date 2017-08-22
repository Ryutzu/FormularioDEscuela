/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

/**
 *
 * @author Flores López -- Soria Lascarez
 */
public class cSecure {
    String _cad="";

    public cSecure() {
    }
    
    public Boolean VL(String cad,int Rang){
        String[] exc = {"'","<",">","=",";"};
        int ok=0;
        boolean oc = false;
        if(!cad.isEmpty()){
            for(int j=0;j<exc.length;j++){
                ok+=cad.indexOf(exc[j]);
            }
            if(ok==(-5)&&cad.length()<=Rang){
                oc=true;
            }
        }
        return oc;
    }
}

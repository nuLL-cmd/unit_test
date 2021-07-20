package teste_unitario.metchers;

import java.util.Calendar;


/**
 * 
 * @author Marco Aurélio
 * @date 20/07/2021
 * 
 * Classe responsavel por agrupar todos os matchers personalizados.
 * 
 */
public class MyMatcher {
    


    
    /** 
     * @param diaSemana
     * @return DiaSemanaMatcher
     * 
     * Invoca o metodo para verificar o dia da semana
     */
    public static DiaSemanaMatcher caEm(Integer diaSemana){

        return new DiaSemanaMatcher(diaSemana);
    }

    
    /** 
     * @return DiaSemanaMatcher
     * 
     * Invoca o metodo para verificar od ia da semana especificamente "Segunda"
     */
    public static DiaSemanaMatcher caiNaSegunda(){
        
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }
}

 package teste_unitario.metchers;


import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import teste_unitario.util.DataUtils;

/**
 * 
 * @author Marco Aurélio
 * @date 20/07/2021
 * 
 * Classe que herda de TypeSafeMatcher específica para comparação de data.
 * 
 */
public class DiaSemanaMatcher extends TypeSafeMatcher<Date>{

    private Integer diaSemana;

    public DiaSemanaMatcher(Integer diaSemana){
        this.diaSemana = diaSemana;
    }

    
    /** 
     * @param arg0
     */
    public void describeTo(Description arg0){

    }


    
    /** 
     * @param arg0
     * @return boolean
     * 
     * Verifica e compara a data passada.
     */
    @Override
    protected boolean matchesSafely(Date arg0){

        return DataUtils.verificarDiaSemana(arg0, diaSemana);
    }


 }
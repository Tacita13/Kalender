/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalender;

/**
 *
 * @author Isamar
 */
public class NoAppointmentException extends Exception {
    
    private boolean noNext = false ;
    private boolean noIndex = false ;

public NoAppointmentException ( boolean noNext, boolean noIndex) {

    this.noNext=noNext;
    this.noIndex=noIndex;
}

public String toString() {

StringBuffer buf = new StringBuffer () ;

if (noNext)
   buf.append ("There is no other appointment. ") ;
if (noIndex)
    buf.append ("There is no appointment at given index. ") ;

        return buf.toString () ;   
    }
}

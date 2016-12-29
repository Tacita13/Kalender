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
public class DateFormatException extends Exception{
    
    private boolean startFalse = false ;
    private boolean endFalse = false ;
    private boolean dateFalse = false;

public DateFormatException ( boolean startFalse, boolean endFalse, boolean dateFalse) {

    this.startFalse=startFalse;
    this.endFalse=endFalse;
    this.dateFalse=dateFalse;
}

public String toString() {

StringBuffer buf = new StringBuffer () ;

if (startFalse)
   buf.append ("Start date has wrong format. ") ;
if (endFalse)
    buf.append ("End date has wrong format. ") ;
if (dateFalse)
    buf.append ("Date has wrong format. ") ;

        return buf.toString () ;
 }
}


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
public class IntOutOfRangeException extends Exception {

    private boolean durationFalse = false, endBefStart = false, overwrite = false;

    public IntOutOfRangeException(boolean durationFalse, boolean endBefStart, boolean overwrite) {
        this.durationFalse = durationFalse;
        this.endBefStart = endBefStart;
        this.overwrite = overwrite;
    }

    public String toString() {

        StringBuffer buf = new StringBuffer();

        if (durationFalse) {
            buf.append("Duration cannot receive negative values. ");
        }
        if (endBefStart) {
            buf.append("End date cannot come before start date. ");
        }
        if (overwrite) {
            buf.append("Conflicting Appointments. ");
        }
        return buf.toString();
    }
}

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
import java.util.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.List;
/*
 This program is designed to serve as a schedule calendar in which the user 
 can add and surf through appointments. It eximplifies the usage of exceptions and
 the handling of time frames and formats.
 */
//Kalendar class which holds: Termin class, and five methods to manage the events.

public class Kalender {

    public static Scanner input = new Scanner(System.in);
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy HH:mm");
    static LinkedList<Termin> list = new LinkedList<>();
//Class Termin has 3 constructors

    public static class Termin {

        public int start, dauer, ende;
        public String beschr, ort;

        //#1 Constructor  
        public Termin(int start, int dauer, String beschr, String ort) throws IntOutOfRangeException {

            if (dauer < 0) {
                throw new IntOutOfRangeException(true, false, true);
            }
            this.start = start;
            this.dauer = dauer;
            this.beschr = beschr;
            this.ort = ort;
            this.ende = this.start + this.dauer * 60;
        }

        //#2 Constructor       
        public Termin(String start, int dauer, String beschr, String ort) throws IntOutOfRangeException, DateFormatException {

            try {
                if (dauer < 0) {
                    throw new IntOutOfRangeException(false, true, true);
                }

                Date startDate = sdf.parse(start);
                this.start = (int) startDate.getTime() / 1000;
                this.dauer = dauer;
                this.beschr = beschr;
                this.ort = ort;
                this.ende = this.start + this.dauer * 60;

            } catch (ParseException ex) {
                System.out.println("NO NO NO");
                throw new DateFormatException(true, false, false);
            }
        }

        //#3 Constructor    
        public Termin(String start, String ende, String beschr, String ort) throws IntOutOfRangeException, DateFormatException {
              System.out.println("___ende"+ende);
                 System.out.println("___start"+start);
            int tmpend = 0;
            boolean startNotValid = false, endNotValid = false;
            try {

                Date startDate = sdf.parse(start);
                this.start = (int) startDate.getTime() / 1000;

            } catch (ParseException ex) {
                startNotValid = true;

            }
            try {
                Date endDate = sdf.parse(ende);
                tmpend = (int) endDate.getTime() / 1000;

            } catch (ParseException ex) {
                endNotValid = true;
            }

            if (endNotValid || startNotValid) {

                throw new DateFormatException(startNotValid, endNotValid, false);
            }

            if (this.ende < this.start) {
                System.out.println("___ende"+ende);
                 System.out.println("___start"+start);
                throw new IntOutOfRangeException(false, true, false);
            }

            this.dauer = (tmpend - this.start) / 60;
        }
    }

    //#1 Method: adds an appointment to a list
    public static void trageTerminEin(Termin t) throws IntOutOfRangeException {

        for (Termin list1 : list) {
            if (list1.start > t.start && list1.start < t.ende) {
                throw new IntOutOfRangeException(false, false, true);
            }
        }
        ;
        list.add(t);

    }
//#2 Method: Attempts to show the next event, if any. 

    Termin nachfolger(Termin t) throws NoAppointmentException {
        Termin closest;
        int now = (int) new Date().getTime() / 1000;
        int min = now;
        if (list.isEmpty()) {
            throw new NoAppointmentException(true, false);
        }
        for (Termin node : list) {
            if (node.start > now) {
                if (node.start < min) {
                    closest = node;

                }
            }
        }
        if (min == now) {
            throw new NoAppointmentException(true, false);
        }

        return t;
    }
//#3 Method attempts to show the list of events in a given day

    public static List<Termin> terminTag(String datum) throws DateFormatException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd.mm.yyyy");
        LinkedList<Termin> dailylist = new LinkedList<Termin>();
        String date1 = "";
        int count = 1;

        try {
            Date TerminDate = fmt.parse(datum);
        } catch (ParseException ex) {
            throw new DateFormatException(false, false, true);

        }

        for (Termin node : list) {
            Date tmp = new Date((node.start) * 1000L);
            date1 = fmt.format(tmp);

            if (date1.equals(datum)) {
                dailylist.add(node);

            }
        }
        return dailylist;

    }
//#4 Method: Attempts to give an specific event of a day 

    public Termin terminTag(String datum, int I) throws DateFormatException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        LinkedList<Termin> dailylist = new LinkedList<Termin>();
        String date1 = "";
        int count = 1;

        try {
            Date TerminDate = fmt.parse(datum);
        } catch (ParseException ex) {
            throw new DateFormatException(false, false, true);
        }
        for (Termin node : list) {
            date1 = fmt.format(new Date((node.start) * 1000));

            if (date1.equals(datum)) {
                dailylist.add(node);
            }
        }
        return dailylist.get(I);
    }

    /* The Main holds the initial query of an event and then ilustrates the d
     ifferent methods and exceptions. */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int x = 0;
        do {
            System.out.println("Please enter a START date and time in format dd.MM.yyyy HH:mm ");
            String startdate = input.nextLine();

            System.out.println("Please enter a END date and time in format dd.MM.yyyy HH:mm ");
            String enddate = input.nextLine();

            System.out.println("Please DESCRIBE the type of appointment: ");
            String beschr = input.nextLine();

            System.out.println("Please enter WHERE the appointment will take place: ");
            String ort = input.nextLine();
              System.out.println("___start"+startdate);
                 System.out.println("___end"+enddate);
            try {
                Termin newTermin = new Termin(startdate, enddate, beschr, ort);
                trageTerminEin(newTermin);
                trageTerminEin(newTermin);

                System.out.println(list.size());
                x = 2;
            } catch (IntOutOfRangeException | DateFormatException ex) {

                System.out.println(ex.toString());
                System.out.println("Error: please try to create an appointment again. \n");
                x = 1;
            }
        } while (x == 1);

    }
}

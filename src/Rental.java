/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sizwe Ncikana
 */
import java.io.*;
import java.util.*;

public class Rental   implements Serializable
 {
    private int rentalNumber;
    private String dateRented;
    private String dateReturned;
    private int custNumber;
    private int dvdNumber;
    private double totalPenaltyCost;
    private static final double PENALTY_COST_PER_DAY = 5;

    public Rental() {
    }

    public Rental (int  rentalNumber, String dateRented, int custNumber , int dvdNumber) {
        this.rentalNumber = rentalNumber;
        this.dateRented = dateRented;
        this.dateReturned = "NA";
//        this.pricePerDay = pricePerDay;
        this.custNumber = custNumber;
        this.dvdNumber = dvdNumber;
//        determineTotalPenaltyCost();
    }

     public Rental (int  rentalNumber, String dateRented, String dateReturned,
                    int custNumber , int dvdNumber) {
        this.rentalNumber = rentalNumber;
        this.dateRented = dateRented;
//        this.pricePerDay = pricePerDay;
        setDateReturned(dateReturned);
        determineTotalPenaltyCost();
        this.custNumber = custNumber;
        this.dvdNumber = dvdNumber;
        
    }

     public int getCustNumber()
     {
         return custNumber;
     }

     public int getDvdNumber()
     {
         return dvdNumber;
     }

     public int getRentalNumber()
    {
        return this.rentalNumber;        
    }
    public String getDateRented()
    {
        return this.dateRented;        
    }
    public String getDateReturned()
    {
        return this.dateReturned;        
    }
//    public double getPricePerDay()
//    {
//        return this.pricePerDay;        
//    }
    public void setRentalNumber(int rn)
    {
        this.rentalNumber = rn;        
    }
    public void setDateRented(String rentD)
    {
        this.dateRented = rentD;
    }
//    public void setPricePerDay(double pricePerDay)
//    {
//        this.pricePerDay = pricePerDay;
//    }
    public void setDateReturned(String ret)
    {
        this.dateReturned = ret;
        determineTotalPenaltyCost();
    }
    public void setCustNumber(int cn)
    {
        this.custNumber = cn;        
    }
    public void setdvdNumber(int mov)
    {
        this.dvdNumber = mov;        
    }
    public double getTotalPenaltyCost()
    {
        return totalPenaltyCost;
    }

    public void determineTotalPenaltyCost()
    {
        totalPenaltyCost = numberOfDaysOverdue() * PENALTY_COST_PER_DAY;
        
    }
    private int dateDifference(String dateRented, String dateReturned) {
        int yyyy, mm, dd;
        StringTokenizer token;
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        
        token = new StringTokenizer(dateRented, "/");
        yyyy = Integer.parseInt(token.nextToken());
        mm = Integer.parseInt(token.nextToken());
        dd = Integer.parseInt(token.nextToken());
        cal1.set(yyyy, mm, dd); 
        if (!dateReturned.equalsIgnoreCase("NA")){
            token = new StringTokenizer(dateReturned, "/");
            yyyy = Integer.parseInt(token.nextToken());
            mm = Integer.parseInt(token.nextToken());
            dd = Integer.parseInt(token.nextToken());
            cal2.set(yyyy, mm, dd);
            return (daysBetween(cal1.getTime(),cal2.getTime()));
        }
        else
            return (0);
    }
    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)+1);
 }
 
    public int numberOfDaysOverdue()
    {        
        int days = dateDifference(dateRented, dateReturned)-2;
        if (days < 0)
            return 0;
        else
            return days;
    }

    public String toString() {
        return "Rental#:" + rentalNumber + "  Date Rented:" + dateRented + "   Date Returned:" + dateReturned + "\nPenalty cost per day:R" + PENALTY_COST_PER_DAY + "  Total Penalty Cost:R" +
            totalPenaltyCost + "  Customer#:" + custNumber + "  Movie#:" + dvdNumber +"\nNo of Days overdue:" + numberOfDaysOverdue()+"\n";
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs101.dz02.zadatak18;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author nik
 */
public class Zadatak18 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	new Zadatak18();
    }

    Zadatak18() {

	final float startTime = System.nanoTime();
	JOptionPane input = new JOptionPane(), output = new JOptionPane();
	LocalDate localDate = LocalDate.now();
	final int m = localDate.getMonthValue();
	float wage, income;
	try {

	    wage = Float.parseFloat(JOptionPane.showInputDialog(input, "Enter your daily wage: "));
	    input.setVisible(true);

	    income = wage * 20;

	    final float execTime = (System.nanoTime() - startTime) / 1000000;

	    String out = String.format("Your income for the month of %s is %.2fRSD\n"
		    + "Ececution time of the program is %.2f miliseconds\n", getMonth(m), income, execTime);
	    JOptionPane.showMessageDialog(output, out);
	    output.setVisible(true);
	    System.exit(0);

	} catch (NumberFormatException e) {

	    JOptionPane.showMessageDialog(output, "Invalid wage");
	    System.exit(0);

	}

    }

    private String getMonth(int month) {
	switch (month) {
	    case 1:
		return "January";
	    case 2:
		return "February";
	    case 3:
		return "March";
	    case 4:
		return "April";
	    case 5:
		return "May";
	    case 6:
		return "June";
	    case 7:
		return "July";
	    case 8:
		return "August";
	    case 9:
		return "September";
	    case 10:
		return "October";
	    case 11:
		return "November";
	    case 12:
		return "December";
	    default:
		return "Invalid month";
	}
    }
}

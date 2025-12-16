package banking;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;


public class Transaction{
    private String accountName;
    private String processedDate;
    private String description;
    private String checkNumber;
    private boolean isDebit;
    private double amount;

    public Transaction(String name, String date, String desc, String aCheckNumber, boolean isADebit, double anAmount){
        accountName = name;
        processedDate = date;
        description = desc;
        checkNumber = aCheckNumber;
        isDebit = isADebit;
        amount = anAmount;
    }

    public static List<Transaction> readFromFile(String filename) throws IOException{
        Scanner input = new Scanner(new FileInputStream(filename));
        input.useDelimiter("[,\n]|\r\n");
        List<Transaction> listOfTransactions = new ArrayList<Transaction>();
        input.nextLine();
        while(input.hasNext()){
            listOfTransactions.add(readFromScanner(input));
        }
        input.close();
        return listOfTransactions;
    }
    public static Transaction readFromScanner(Scanner inputScanner){
        String name = inputScanner.next();
        String date = inputScanner.next();
        String desc = inputScanner.next();
        String aCheckNumber = inputScanner.next();
        boolean isADebit = inputScanner.next().equals("Debit");
        double anAmount = inputScanner.nextDouble();
  
        return new Transaction(name, date, desc, aCheckNumber, isADebit, anAmount);
    }

    public boolean isDebit(){
        return isDebit;
    }

    public double getAmount(){
        return amount;
    }
    @Override
   public String toString(){
    StringWriter writer = new StringWriter();
    writer.write(processedDate);
    writer.write(": ");
    writer.write(description);
    return writer.toString();
   }
    
    public String toText(){
        Double refrenceAmount;
        refrenceAmount = amount;
        StringWriter writer = new StringWriter();
        writer.write("date: ");
        writer.write(processedDate);
        writer.write("   amount: ");
        writer.write(refrenceAmount.toString());
        writer.write("\n");
        writer.write("description: ");
        writer.write(description);
        return writer.toString();
    }
    
}
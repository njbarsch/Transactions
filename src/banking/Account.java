package banking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;


public class Account{
    private ObservableList<Transaction> transactions;
    
    public Account(String filename) throws IOException{
        transactions = FXCollections.observableList
        (Transaction.readFromFile(filename));
    }

    public double currentBalance(){
        return currentCredits() - currentDebits(); 
    }

    public double currentCredits(){
        double total = 0;
        for(int index = 0; index < transactions.size(); ++index){
            Transaction aTrans = transactions.get(index);

            if(aTrans.isDebit() == false){
                total = total + aTrans.getAmount();
            }
        }
        return total;
    }
    public double currentDebits(){
        double total = 0.0;
        for(int index = 0; index < transactions.size(); ++index){
            Transaction aTrans = transactions.get(index);

            if(aTrans.isDebit())
            {
                total = total + aTrans.getAmount();
            }
        }
        return total;
    }
    public ObservableList<Transaction> getTransactions(){
        return transactions;     
    }
}
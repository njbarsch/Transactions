import java.io.IOException;
import banking.Account;
import banking.Transaction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private Account checkingAccount;
    private Text aText;
    private String selectedTransaction;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage aStage) throws Exception {
        checkingAccount = new Account("checking_transactions.csv");
        VBox root = new VBox();
        buildContent(root);
        Scene aScene = new Scene(root, 600, 300);
        aStage.setScene(aScene);
        aStage.setTitle("Checkbook");
        aStage.show();
    }

    public void buildContent(VBox root) {
        buildListView(root);
        buildText(root);
    }

    public void buildListView(VBox root) {
        ListView<Transaction> listView = new ListView<Transaction>(checkingAccount.getTransactions());
        buildSelectionModel(listView);
        root.getChildren().add(listView);
    }

    public void buildText(VBox root) {
        aText = new Text(selectedTransaction);
        transferSelectedToText(null);
        root.getChildren().addAll(aText);
    }

    public void transferSelectedToText(Transaction aTransaction) {
        if (aTransaction == null) {
            aText.setText("Make a selection");
            return;
        }
        aText.setText(aTransaction.toText());
    }

    public void buildSelectionModel(ListView<Transaction> listView) {
        SelectionModel<Transaction> selectionModel = listView.getSelectionModel();
        selectionModel.selectedItemProperty()
                .addListener((model, oldValue, newValue) -> transferSelectedToText(newValue));
    }
}
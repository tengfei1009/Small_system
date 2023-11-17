package Tips;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AccountNumber {

    @FXML
    private Button rtn;
    @FXML
    public void set_return(MouseEvent mouseEvent) {
        Stage previousStage = (Stage) rtn.getScene().getWindow();
        previousStage.close();
    }
}

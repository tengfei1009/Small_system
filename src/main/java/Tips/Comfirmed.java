package Tips;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

//确定要删除吗
public class Comfirmed {
    @FXML
    private Button Yes;
    @FXML
    private Button No;

    //定义静态变量
    public static boolean a=true;

    public void set_Yes(MouseEvent mouseEvent) throws Exception {
        a=true;
        Stage previousStage = (Stage) Yes.getScene().getWindow();
        previousStage.close();
    }

    public void set_No(MouseEvent mouseEvent) {
        a=false;
        Stage previousStage = (Stage) No.getScene().getWindow();
        previousStage.close();
    }
}

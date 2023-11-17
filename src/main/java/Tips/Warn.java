package Tips;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//import java.awt.*;

//中转站,需要页面就调用方法
public class Warn {
    @FXML
    private Button rtn;
    @FXML
    public void set_return(MouseEvent mouseEvent) throws Exception{
//        Scene scene = rtn.getScene();
        Stage previousStage = (Stage) rtn.getScene().getWindow();
        previousStage.close();
    }
}

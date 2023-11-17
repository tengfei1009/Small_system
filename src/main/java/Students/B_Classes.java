package Students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class B_Classes {

    //返回按钮
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
//        JDBC
        Parent root = FXMLLoader.load(getClass().getResource("/Students/User.fxml"));
        rtn.getScene().setRoot(root);
    }


    @FXML
    private Button students;
    public void set_students(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Students/B_id.fxml"));
        rtn.getScene().setRoot(root);
    }

    @FXML
    private Button course;
    public void set_course(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Students/B_course.fxml"));
        rtn.getScene().setRoot(root);
    }

}

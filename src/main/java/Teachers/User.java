package Teachers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class User {
    //返回登录界面
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
        Stage stage = (Stage) rtn.getScene().getWindow();
        // 设置窗口的宽度和高度
        stage.setWidth(614);
        stage.setHeight(437);
        stage.setResizable(false);//窗口不可改变高度 宽度 这样就不用调节自适应了
        stage.setTitle("H");
        Parent root =FXMLLoader.load(getClass().getResource("/Students/Main.fxml"));
        rtn.getScene().setRoot(root);
    }

    //教师对学生信息增改删查页面
    @FXML
    private Button information;
    public void set_information(MouseEvent mouseEvent) throws Exception{
        Parent root =FXMLLoader.load(getClass().getResource("/Teachers/aaa.fxml"));
        rtn.getScene().setRoot(root);
    }


    public void set_courses(MouseEvent mouseEvent) throws Exception{
        Parent root =FXMLLoader.load(getClass().getResource("/Teachers/bbb.fxml"));
        rtn.getScene().setRoot(root);
    }
}

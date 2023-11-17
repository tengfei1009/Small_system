package Students;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class User {
    //在用户界面跳转到其他页面


    //退出登录
    @FXML
    private Button rtn;
    public void set_rtn (MouseEvent mouseEvent) throws Exception{
        Stage stage = (Stage) rtn.getScene().getWindow();
        // 设置窗口的宽度和高度
        stage.setWidth(614);
        stage.setHeight(437);
        stage.setResizable(false);//窗口不可改变高度 宽度 这样就不用调节自适应了
        stage.setTitle("H");
        Parent root =FXMLLoader.load(getClass().getResource("/Students/Main.fxml"));
        rtn.getScene().setRoot(root);
    }
    //学生信息
    @FXML
    private Button information;
    public void set_information(MouseEvent mouseEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Students/aaa.fxml"));
        information.getScene().setRoot(root);

    }

    //课表查询
    @FXML
    private Button course;
    public void setCourse (MouseEvent mouseEvent) throws Exception {
        Parent root =FXMLLoader.load(getClass().getResource("/Students/Bbb.fxml"));
        course.getScene().setRoot(root);
    }

    //学生分数查询
    @FXML
    private Button score;
    public void score(MouseEvent mouseEvent) throws Exception{
        Parent root =FXMLLoader.load(getClass().getResource("/Students/Ccc.fxml"));
        score.getScene().setRoot(root);
    }

}

package Students;

import dao.Administrators_operate;
import dao.Students_operate;
import dao.Teachers_operate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import javax.swing.*;
import java.io.IOException;

public class Main extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        //加载登录界面
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Students/Main.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
        Scene scene =new Scene(fxmlLoader.load());
        stage.setResizable(false);//窗口不可改变高度 宽度 这样就不用调节自适应了
        stage.setWidth(614);
        stage.setHeight(437);
        stage.setTitle("H");
        stage.setScene(scene);
        stage.show();
        System.out.println(stage.getWidth() + " " + stage.getHeight());
    }


    public static void main(String[] args){
        launch();
    }





    @FXML
    private RadioButton stu;
    @FXML
    private RadioButton teach;
    @FXML
    private RadioButton Admin;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    //登录按钮
    @FXML
    private Button loginBut;
    //忘记密码
    @FXML
    private Button reset;

    @FXML
    private void initialize() {
//        stu.setSelected(true); // 设置单选框初始状态为选中
//        stu.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue) {
//                reset.setDisable(false); // 显示reset按钮
//            } else {
//                reset.setDisable(true); // 隐藏reset按钮
//            }
//        });
    }

    //登录进入用户界面
    public void user(MouseEvent mouseEvent) throws Exception{

        try {
            if (username.getText() != "" && password.getText() != "") {
/**---------------------------------------------------------------*/
                //对于第一个 学生登录
                String use_1 = username.getText();
                int pwd_1 = Integer.parseInt(password.getText());
//            调用学生数据库
                boolean ok_1 = Students_operate.login(use_1, pwd_1);
/**---------------------------------------------------------------*/
                //对于第二个 教师登录
//            int use_2= Integer.parseInt(username.getText());
                String use_2 = username.getText();
                int pwd_2 = Integer.parseInt(password.getText());
                //调用教师数据库
                boolean ok_2 = Teachers_operate.login(use_2, pwd_2);
/**---------------------------------------------------------------*/
                //对于第三个 管理员登录
                String use_3 = username.getText();
                int pwd_3 = Integer.parseInt(password.getText());
                //调用管理员数据库
                boolean ok_3 = Administrators_operate.login(use_3, pwd_3);
/**---------------------------------------------------------------*/
                if (ok_1 && stu.isSelected()) {
                    Stage previousStage = (Stage) loginBut.getScene().getWindow();
//                 关闭前一个页面的舞台
                    previousStage.close();
                    //创建一个新的根节点
                    Parent root = FXMLLoader.load(getClass().getResource("/Students/User.fxml"));
                    Scene scene = new Scene(root, 700, 430); // 创建具有新宽度和高度的场景对象
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("User");
                    stage.setScene(scene);
                    stage.show();
                } else if (ok_2 && teach.isSelected()) {
                    Stage previousStage = (Stage) loginBut.getScene().getWindow();
//                 关闭前一个页面的舞台
                    previousStage.close();

                    //创建一个新的根节点
                    Parent root = FXMLLoader.load(getClass().getResource("/Teachers/user.fxml"));
                    Scene scene = new Scene(root, 700, 430); // 创建具有新宽度和高度的场景对象
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("User");
                    stage.setScene(scene);
                    stage.show();
                } else if (ok_3 && Admin.isSelected()) {
                    Stage previousStage = (Stage) loginBut.getScene().getWindow();
//                 关闭前一个页面的舞台
                    previousStage.close();

                    //创建一个新的根节点
                    Parent root = FXMLLoader.load(getClass().getResource("/Administrators/aaa.fxml"));
                    Scene scene = new Scene(root, 700, 430); // 创建具有新宽度和高度的场景对象
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("User");
                    stage.setScene(scene);
                    stage.show();
                } else {

                    Stage previousStage = (Stage) loginBut.getScene().getWindow();
//                loginBut.setDisable(true); // 禁用按钮

//                 关闭前一个页面的舞台
//                previousStage.close();

                    //重新获取刷新页面
                    previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Students/Main.fxml")));

                    System.out.println("55666");
                    //加载新页面
                    Parent root = FXMLLoader.load(getClass().getResource("/Students/Failed.fxml"));
                    Scene scene = new Scene(root, 350, 150); // 创建具有新宽度和高度的场景对象
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    // 将新窗口置于最高层级
                    stage.setAlwaysOnTop(true);

                    // 设置新窗口为应用程序模态对话框
                    stage.initModality(Modality.APPLICATION_MODAL);

                    // 设置新窗口的所有者为当前舞台
                    stage.initOwner(previousStage);  // currentStage 是当前窗口的 Stage 对象

                    stage.showAndWait();

                }
            } else {
                System.out.println("账号或密码为空");
                //java里面的一个类
//            JOptionPane.showMessageDialog(null,"用户名或密码不能为空","错误",JOptionPane.ERROR_MESSAGE);
                //如果什么都没填，就报错
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/Tips/account_number.fxml"));
                Scene scene = new Scene(root, 296, 295);
                Stage stage = new Stage();
                stage.setResizable(false);

                stage.setTitle("嘿嘿");
                stage.setScene(scene);


                // 获取当前窗口的 Stage 对象
                Stage currentStage = (Stage) loginBut.getScene().getWindow();

                // 将新窗口置于最高层级
                stage.setAlwaysOnTop(true);

                // 设置新窗口为应用程序模态对话框
                stage.initModality(Modality.APPLICATION_MODAL);

                // 设置新窗口的所有者为当前舞台
                stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

                stage.showAndWait();

                //点击按钮刷新页面
                Stage previousStage = (Stage) loginBut.getScene().getWindow();
                previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Students/Main.fxml")));
            }
        }catch (Exception e){
            Parent root = FXMLLoader.load(getClass().getResource("/Students/Failed.fxml"));
            Scene scene = new Scene(root, 350, 150); // 创建具有新宽度和高度的场景对象
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
//            stage.show();
            // 获取当前窗口的 Stage 对象
            Stage currentStage = (Stage) loginBut.getScene().getWindow();

            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);

            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);

            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

            stage.showAndWait();

            //点击按钮刷新页面
            Stage previousStage = (Stage) loginBut.getScene().getWindow();
            previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Students/Main.fxml")));

        }

    }



    //获取帮助
    @FXML
    private Hyperlink help;
    @FXML
    public void help(MouseEvent mouseEvent) {
        System.out.println("帮助");
    }


    //忘记密码,修改密码
//    @FXML
//    private Button reset;
    @FXML
    public void setReset(ActionEvent actionEvent) throws Exception{
            Parent root =FXMLLoader.load(getClass().getResource("/Students/Forget.fxml"));
            reset.getScene().setRoot(root);
        }


//     登录失败
    @FXML
    private Button okok;
    @FXML
    public void fail(MouseEvent mouseEvent) {
        Stage previousStage = (Stage) okok.getScene().getWindow();
        // 关闭此页面
        previousStage.close();
    }

}
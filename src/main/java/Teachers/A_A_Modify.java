package Teachers;

import dao.Teachers_operate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Student;

import java.io.IOException;

public class A_A_Modify {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField age;
    @FXML
    private TextField profession;
    @FXML
    private TextField phone;

    Student stu= A_Information.getSelectedData();
    //获取老ID,根据老ID修改学生信息
    static int oldId;

    //    初始化方法
    @FXML
    public void initialize() {
        //        setText(); 这个方法里面的值必须是String类型的,所以需要转化一下
        id.setText(String.valueOf(stu.getId()));
        name.setText(stu.getName());
        gender.setText(stu.getGender());
        age.setText(String.valueOf(stu.getAge()));
        profession.setText(stu.getProfession());
        phone.setText(String.valueOf(stu.getPhone()));

        //每次重新设置老id
        oldId = A_Information.getSelectedData().getId();
    }


    @FXML
    private Button commit;
    //将获取到文本框中的内容重新加载,并关闭修改页面
    public void get_commit(ActionEvent actionEvent) throws Exception{
        //执行修改操作
        upDate();
        //当点击此按钮时,关闭此页面
        Stage previousStage = (Stage) commit.getScene().getWindow();
        previousStage.close();
    }


    private void upDate() throws IOException {
        try {
        // 从文本框中获取内容
        int updatedId = Integer.parseInt(id.getText());
        String updatedName = name.getText();
        String updatedGender = gender.getText();
        int updatedAge = Integer.parseInt(age.getText());
        String updatedProfession = profession.getText();
        int updatedPhone = Integer.parseInt(phone.getText());


        // 更新 stu 对象的属性
        A_Information.getSelectedData().setId(updatedId);
        A_Information.getSelectedData().setName(updatedName);
        A_Information.getSelectedData().setGender(updatedGender);
        A_Information.getSelectedData().setAge(updatedAge);
        A_Information.getSelectedData().setProfession(updatedProfession);
        A_Information.getSelectedData().setPhone(updatedPhone);


        Student student = new Student(updatedId, updatedName, updatedGender, updatedAge, updatedProfession, updatedPhone);
        // 执行数据库操作

            Teachers_operate.edit(student,oldId);
        } catch (Exception e) {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/Tips/warn.fxml"));
            Scene scene =new Scene(root,280,270);
            Stage stage=new Stage();
            stage.setResizable(false);

            stage.setTitle("搞笑");
            stage.setScene(scene);


            // 获取当前窗口的 Stage 对象
            Stage currentStage = (Stage) commit.getScene().getWindow();

            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);

            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);

            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

            stage.showAndWait();
        }


        System.out.println(oldId);

        // 在数据库中对上面的值进行匹配，如果匹配成功就直接修改

    }

    //返回按钮
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
        Stage previousStage = (Stage) rtn.getScene().getWindow();
        // 关闭此页面
        previousStage.close();
        }
}
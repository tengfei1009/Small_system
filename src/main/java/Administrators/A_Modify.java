package Administrators;

import dao.Administrators_operate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojo.Teacher;

public class A_Modify {


    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField age;
    @FXML
    private TextField teach;
    @FXML
    private TextField password;

    Teacher tea = User.getSelectedData();
    //获取老ID,根据老ID修改教师信息
    static String oldId;

    //定义一个初始化方法,当每次获取到行时,更新行数据
    public void initialize() {
        id.setText(tea.getId());
        name.setText(tea.getName());
        gender.setText(tea.getGender());
        age.setText(String.valueOf(tea.getAge()));
        teach.setText(tea.getTeach());
        password.setText(String.valueOf(tea.getPassword()));


        //每次重新设置老id
        oldId= User.getSelectedData().getId();
        System.out.println(oldId);

    }

    //提交按钮
    @FXML
    Button commit;

    public void set_commit(MouseEvent mouseEvent) throws Exception{
        //调用修改的方法
        upDate();
//        当点击此按钮时,关闭此页面
        Stage previousStage = (Stage) commit.getScene().getWindow();
        previousStage.close();
//        Parent root = FXMLLoader.load(getClass().getResource("/Administrators/aaa.fxml"));
//        commit.getScene().setRoot(root);
    }



    @FXML
    private TextField input;
    private void upDate() throws Exception{
        // 从文本框中获取内容
        String updatedId = id.getText();
        String updatedName = name.getText();
        String updatedGender = gender.getText();
        int updatedAge = Integer.parseInt(age.getText());
        String updatedTeach = teach.getText();
        int updatedPassword = Integer.parseInt(password.getText());


        // 更新 stu 对象的属性
        User.getSelectedData().setId(updatedId);
        User.getSelectedData().setName(updatedName);
        User.getSelectedData().setGender(updatedGender);
        User.getSelectedData().setAge(updatedAge);
        User.getSelectedData().setTeach(updatedTeach);
        User.getSelectedData().setPassword(updatedPassword);


        Teacher teacher = new Teacher(updatedId, updatedName, updatedGender, updatedAge, updatedTeach, updatedPassword);
        // 执行数据库操作
        Administrators_operate.edit_teachers(teacher,oldId);


        System.out.println(oldId);

        // 在数据库中对上面的值进行匹配，如果匹配成功就直接修改

    }


    //返回按钮
    @FXML
    private Button rtn;

    public void set_return(MouseEvent mouseEvent) throws Exception {
//        JDBC
        Stage previousStage = (Stage) rtn.getScene().getWindow();
        // 关闭此页面
        previousStage.close();
    }
}

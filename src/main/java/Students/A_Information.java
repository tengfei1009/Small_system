package Students;

import dao.JDBC;
import dao.Teachers_operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pojo.Student;

import java.sql.ResultSet;


public class A_Information {

//    搜索框
    @FXML
    private  TableView<Student> tableView;;
    @FXML
    private TableColumn<Student,Integer>id;
    @FXML
    private TableColumn<Student,String> name;
    @FXML
    private TableColumn <Student,String> gender;
    @FXML
    private TableColumn <Student,Integer>age;
    @FXML
    private TableColumn<Student,String> profession;
    @FXML
    private TableColumn<Student,Integer> phone;





    /**初始化表格数据*/
    //当FXML文件加载并关联控制器类时，JavaFX会自动创建控制器类的实例，
    // 并自动调用initialize()方法。在initialize()方法内部，loadData()方法被调用，
    // 从而实现在加载FXML文件时自动加载数据。
    @FXML
    public void initialize() throws Exception{
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        loadData();
    }

    //获取表中数据,并设置每一列
    public  void loadData() throws Exception{
        String sql = "SELECT id, name, gender, age, profession, phone FROM small_systems.student";
        ResultSet rs = JDBC.executeQuery(sql);
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        while (rs.next()) {
            int studentId = rs.getInt("id");
            String studentName = rs.getString("name");
            String studentGender = rs.getString("gender");
            int studentAge = rs.getInt("age");
            String studentProfession = rs.getString("profession");
            int studentPhone = rs.getInt("phone");

            Student student = new Student(studentId, studentName, studentGender, studentAge, studentProfession, studentPhone);
            studentList.add(student);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableView.setItems(studentList);
    }


    //输入文本框
    @FXML
    private TextField content;
    //查询数据
    @FXML
    private Button search;
    public void set_search(MouseEvent mouseEvent) throws Exception {
        //获取查询文本框的内容
        String cot = content.getText();
        System.out.println(cot);

//        将文本框的内容传到查询语句中,并获取查询到的这一列
        ObservableList<Student> searchResult = Teachers_operate.search(cot);

        // 清空表格中所有数据
        tableView.getItems().clear();

        // 将这一列新数据插入表格
        tableView.setItems(searchResult);

        // 显示表格
        tableView.setVisible(true);
    }


    //返回上一个界面
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Students/User.fxml"));
        rtn.getScene().setRoot(root);
    }

}

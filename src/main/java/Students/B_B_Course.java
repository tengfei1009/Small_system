package Students;

import dao.JDBC;
import dao.Students_operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Student;

import java.sql.ResultSet;

public class B_B_Course {

    ////这个表格,定义用来初始化
    @FXML
    private TableView tableView;
    //课程
    @FXML
    private TableColumn course;
    //学号
    @FXML
    private TableColumn student_number;
    //姓名
    @FXML
    private TableColumn name;
    //班级
    @FXML
    private TableColumn classes;



    @FXML
    public void initialize () throws Exception{
        set_information();
    }

//    创建此集合用来存放表中所有数据
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    //初始化表格信息
    public void set_information () throws Exception{
        //使用左外查询
        //首先给表起一个别名
        //用别名.字段名 来表示一列
        //        查询课程所选的学生
        String sql = "SELECT c.course, s.id, s.name ,s.classes FROM course c JOIN student_course sc ON c.id = sc.course_id JOIN student s ON sc.student_id = s.id";
        //使用查询语句，然后进行遍历   因为是学生查询，所以写学生查询里面
        ResultSet rs = JDBC.executeQuery(sql);
        while (rs.next()) {
            String course =rs.getString("course");
            int student_number = Integer.parseInt(rs.getString("id"));
            String name =rs.getString("name");
            String classes =rs.getString("classes");
            Student student=new Student(course,student_number,name,classes);
            studentList.add(student);
        }
        course.setCellValueFactory(new PropertyValueFactory<>("course"));
        student_number.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        classes.setCellValueFactory(new PropertyValueFactory<>("classes"));

        tableView.setItems(studentList);
    }

    //搜索按钮
    //点击此按钮进行数据查询
    //课程查询
    @FXML
    private TextField input;
    @FXML
    private Button search;
    public void set_search(ActionEvent actionEvent) throws Exception {
        //现在表里有数据只需要获取文本，进行查询语句即可
        ObservableList<Student> originalData = tableView.getItems();
        String inputText = input.getText();
        String aaa=inputText+"输入为空";
        System.out.println(aaa);
        ObservableList<Student> searchResult = Students_operate.get_course(inputText);
        // 清空表格中所有数据
        tableView.getItems().clear();

        // 将这一列新数据插入表格
        tableView.setItems(searchResult);
//
        // 显示表格
        tableView.setVisible(true);


        if(searchResult.isEmpty() && aaa.equals("输入为空")){
            System.out.println(2222);
            Parent root = FXMLLoader.load(getClass().getResource("/Students/B_course.fxml"));
            rtn.getScene().setRoot(root);
        }

    }


    //返回上一个页面
    @FXML
    private Button rtn;
    public void set_rtn(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Students/Bbb.fxml"));
        rtn.getScene().setRoot(root);
    }
}

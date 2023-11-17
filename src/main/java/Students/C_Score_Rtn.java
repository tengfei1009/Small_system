package Students;

import dao.JDBC;
import dao.Students_operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pojo.Course;

import java.sql.ResultSet;

public class C_Score_Rtn {


    @FXML
    private TableView tableView;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn classes;
    @FXML
    private TableColumn Chinese;
    @FXML
    private TableColumn Math;
    @FXML
    private TableColumn English;
    @FXML
    private TableColumn Physics;
    @FXML
    private TableColumn Chemistry;


    //定义一个集合,用来存放表格数据
    private ObservableList<Course> studentList = FXCollections.observableArrayList();



    //    初始化页面
    //文本框中的内容
    @FXML
    private TextField input;
    @FXML
    public void initialize() throws Exception{
        set_information();
    }
    public void set_information () throws Exception{
        String sql="SELECT s.id ,s.name,s.classes,g.chinese,g.math,g.English,g.physics,g.Chemistry FROM student s JOIN grades g on s.id = g.user_id";
        //使用查询语句,然后进行遍历
        ResultSet rs= JDBC.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name =rs.getString("name");
            String classes =rs.getString("classes");
            int chinese =rs.getInt("Chinese");
            int math =rs.getInt("Math");
            int English =rs.getInt("English");
            int physics =rs.getInt("Physics");
            int chemistry=rs.getInt("Chemistry");

            Course course =new Course(id,name,classes,chinese,math,English,physics,chemistry);
            studentList.add(course);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        classes.setCellValueFactory(new PropertyValueFactory<>("classes"));
        Chinese.setCellValueFactory(new PropertyValueFactory<>("Chinese"));
        Math.setCellValueFactory(new PropertyValueFactory<>("Math"));
        English.setCellValueFactory(new PropertyValueFactory<>("English"));
        Physics.setCellValueFactory(new PropertyValueFactory<>("Physics"));
        Chemistry.setCellValueFactory(new PropertyValueFactory<>("Chemistry"));

        tableView.setItems(studentList);
    }

    //根据学号来查询分数
    @FXML
    private Button search;
    public void set_search(MouseEvent mouseEvent) throws Exception {
        try {
            //查询学生id获取学生成绩
            //获取文本框中id
            int get_search=Integer.parseInt(input.getText());
            ObservableList<Course> searchResult = Students_operate.get_score(get_search);

            //清空表中所有数据
            tableView.getItems().clear();

            //将这一列新数据插入表格
            tableView.setItems(searchResult);

            //显示表格
            tableView.setVisible(true);

        } catch (Exception e){
            Parent root = FXMLLoader.load(getClass().getResource("/Students/Ccc.fxml"));
            rtn.getScene().setRoot(root);
        }

    }


    //返回上一个页面
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("/Students/User.fxml"));
            rtn.getScene().setRoot(root);
        }


}

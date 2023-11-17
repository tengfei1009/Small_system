package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pojo.Student;
import pojo.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

//获取学生信息,连接JDBC
//学生登录
public class Students_operate {

/**--------------------------------------------------------------------------------*/
    public static boolean login (String username,int password)throws SQLException {
        String sql="select id,password , name from small_systems.student where id= ? and password=?";

//        ps.setInt(1,username);
//        ps.setInt(2,password);
        //查询语句
        ResultSet rs= JDBC.executeQuery(sql , username , password);
        while (rs.next()){
            return true;
        }
        return false;
    }
/**--------------------------------------------------------------------------------*/
    //学生信息查询
    public static ObservableList<Student> search (String username) throws Exception{
        String sql ="select id,name,gender,age,profession,phone from small_systems.student where id like ? or id= ?";

        //在这段代码中，String[] params 是一个字符串数组，用于存储 SQL 查询语句中的参数值。
        //params 数组中存储了两个参数值：
        //
        //"%"+username+"%"：这个参数值是模糊查询所用的学号模式。"%"+username+"%" 将会在查询时替换为一个包含 username 值的字符串，前后都带有 % 符号。
        // 例如，如果 username 的值是 "123"，那么 params[0] 的值将是 "%123%"，用于实现模糊查询。
        //username：这个参数值是精确匹配所用的学号。它保持了用户输入的原始值，用于在查询时进行精确匹配。
        //这样，通过将参数值存储在 params 数组中，你可以将它们作为参数传递给 JDBC.executeQuery() 方法，以便在执行 SQL 查询时使用这些参数。
        String[] params = { "%" + username + "%", username };
        ResultSet rs= JDBC.executeQuery(sql ,params);

        //临时放一个集合,存储查询到的数据
        ObservableList<Student> students = FXCollections.observableArrayList();


        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String profession = rs.getString("profession");
            int phone = rs.getInt("phone");


            Student student = new Student(id, name, gender, age, profession, phone);
            students.add(student);
            System.out.println("插入数据成功");
        }
        return students;
    }

/**--------------------------------------------------------------------------------*/
    //忘记密码 学生用户名查询    也可以是判断该用户是否已经注册,如果已经注册
    public static boolean get_forget (int id) throws Exception{
        String sql="select id from small_systems.student where id= ?";

        //查询语句
        ResultSet rs= JDBC.executeQuery(sql , id);
        while (rs.next()){
            return true;
        }
        return false;
    }
    //忘记密码 学生密码修改
    //参数是新密码,和老ID
    public static void update_student (int password,int id) {
        //根据查询到的id修改密码
        String sql = "UPDATE small_systems.student SET password = ? WHERE id = ?";

        //修改语句
        //根据老id设置新密码
        JDBC.exeUpdate(sql,password,id);
    }
/**--------------------------------------------------------------------------------*/
//输入学号,来查询数据,输入202301 就能看到对应id有上哪些课
    public static ObservableList<Student> get_id (int id) throws Exception {
        String sql="SELECT s.id, s.name,s.profession,s.classes,c.course FROM student s JOIN student_course sc ON s.id = sc.student_id JOIN course c ON sc.course_id = c.id where s.id=?";
        ResultSet rs=JDBC.executeQuery(sql,id);

        //临时放一个集合,存储查询到的数据
        ObservableList<Student> students =FXCollections.observableArrayList();

        int a=0;
        while (rs.next()) {
            int student_id=rs.getInt("id");
            String name =rs.getString("name");
            String profession=rs.getString("profession");
            String classes =rs.getString("classes");
            String course =rs.getString("course");

            Student student =new Student(student_id, name,profession,classes,course);
            students.add(student);
            a++;
        }
        System.out.println("获取到"+a+"行数据");
        return students;
    }

/**--------------------------------------------------------------------------------*/
//输入课程名，来查询数据，输入语文，就能看到上语文课的人有哪些
    public static ObservableList<Student> get_course (String course) throws Exception{
        String sql="SELECT c.course, s.id, s.name ,s.classes FROM course c JOIN student_course sc ON c.id = sc.course_id JOIN student s ON sc.student_id = s.id where c.course =?";
        ResultSet rs=JDBC.executeQuery(sql,course);

        //临时放一个集合,存放查询到的数据
        ObservableList<Student> students =FXCollections.observableArrayList();

        int a=0;
        while (rs.next()) {
            String courses=rs.getString("course");
            int ids = rs.getInt("id");
            String name=rs.getString("name");
            String classes=rs.getString("classes");

            Student student =new Student(courses,ids,name,classes);
            students.add(student);
            a++;
        }
        System.out.println("获取到"+a+"行数据");
        return students;
    }
/**--------------------------------------------------------------------------------*/
//输入学生id查询学生成绩
    public static ObservableList<Course> get_score(int id) throws Exception{
        String sql="SELECT s.id ,s.name,s.classes,g.chinese,g.math,g.English,g.physics,g.Chemistry FROM student s JOIN grades g on s.id = g.user_id where s.id=?";
        ResultSet rs=JDBC.executeQuery(sql,id);

        //临时放一个集合,存放查询到的数据
        ObservableList<Course> courses =FXCollections.observableArrayList();
        int a=0;
        while (rs.next()) {
            //因为传的参数为id,这里为了不混淆,获取的是id列 所以给这里id列用ids来表示
            int ids = rs.getInt("id");
            String name =rs.getString("name");
            String classes =rs.getString("classes");
            int chinese =rs.getInt("Chinese");
            int math =rs.getInt("Math");
            int English =rs.getInt("English");
            int physics =rs.getInt("Physics");
            int chemistry=rs.getInt("Chemistry");

            Course course =new Course(ids,name,classes,chinese,math,English,physics,chemistry);
            courses.add(course);
            a++;
        }
        System.out.println("获取到"+a+"行数据");
        return courses;
    }

}

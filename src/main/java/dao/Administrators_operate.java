package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pojo.Student;
import pojo.Teacher;

import java.sql.ResultSet;

public class Administrators_operate {

//    匹配管理员密钥,方便修改密码
public static boolean Secret_key(String pass)throws Exception{
    String sql="select pass from small_systems.administrator where pass= ?";
    //查询语句
    ResultSet rs= JDBC.executeQuery(sql , pass);
    while (rs.next()){
        return true;
    }
    return false;

}

//     登录查询
    public static boolean login(String username, int password) throws Exception{
        String sql="select id,password from small_systems.administrator where id= ? and password=?";

        //查询语句
        ResultSet rs= JDBC.executeQuery(sql , username , password);
        while (rs.next()){
            return true;
        }
        return false;
    }



    //查询教师信息
    public static ObservableList<Teacher> search(String username) throws Exception{

            String sql ="select id,name,gender,age,teach,password from small_systems.teacher where id like ? or id= ?";

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
            ObservableList<Teacher> teachers = FXCollections.observableArrayList();


            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String teach = rs.getString("teach");
                int password = rs.getInt("password");


                //用来存放教师,主要凌乱了,现在
                Teacher teacher =new Teacher(id, name, gender, age, teach, password);
                teachers.add(teacher);
            }
        System.out.println("查询数据成功");
        return teachers;
        }

        //增加教师
    public static boolean add_teachers(Teacher teacher) {
        String sql = "insert into small_systems.teacher (id, name, gender, age, teach, password) values (?, ?, ?, ?, ?, ?)";
        boolean rowsAffected = JDBC.exeUpdate(sql, teacher.getId(), teacher.getName(), teacher.getGender(), teacher.getAge(), teacher.getTeach(), teacher.getPassword());
        return rowsAffected;
    }

//    修改教师
public static void edit_teachers(Teacher teacher, String oldId) throws Exception {
//        String sql = "UPDATE small_systems.student SET id = ? , name = ? , gender = ? , age = ? , profession = ? , phone = ? where id=?";
//        JDBC.exeUpdate(sql,student,oldId);

    String sql = "UPDATE small_systems.teacher SET id = ?, name = ?, gender = ?, age = ?, teach = ?, password = ? WHERE id = ?";
    JDBC.exeUpdate(sql, teacher.getId(), teacher.getName(), teacher.getGender(), teacher.getAge(),
            teacher.getTeach(), teacher.getPassword(), oldId);
}

    //删除教师
    /**这里有bug,如果删除了教师,此时,中间教师课程表中的数据还在,虽然应该显示不出来,但是中间表关于被删除教师的数据还是一直存在的*/
    public static void delete_teachers(String teacher) throws Exception {
        String sql = "delete from small_systems.teacher WHERE id = ?";
        boolean rowsAffected = JDBC.exeUpdate(sql, teacher);
    }


}

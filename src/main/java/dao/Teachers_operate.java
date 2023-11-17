package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pojo.Student;
import pojo.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Teachers_operate {
    /**
     * ---------------------------------------------------------------------------
     */
    //登录查询
    public static boolean login(String username, int password) throws SQLException {
        String sql = "select id,password from small_systems.teacher where id= ? and password=?";

//        ps.setInt(1,username);
//        ps.setInt(2,password);

        //查询语句
        ResultSet rs = JDBC.executeQuery(sql, username, password);
        while (rs.next()) {
            return true;
        }
        return false;
    }


    /**
     * -------------------------------------------------------------------------------------------
     */
    //增加学生
    public static boolean add_students(Student student) throws SQLException {
        String sql = "insert into small_systems.student (id, name, gender, age, profession, phone) values (?, ?, ?, ?, ?, ?)";
        boolean rowsAffected = JDBC.exeUpdate(sql, student.getId(), student.getName(), student.getGender(), student.getAge(), student.getProfession(), student.getPhone());
        return rowsAffected;
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
    //修改学生信息
    public static void edit(Student student, int oldId) throws Exception {
//        String sql = "UPDATE small_systems.student SET id = ? , name = ? , gender = ? , age = ? , profession = ? , phone = ? where id=?";
//        JDBC.exeUpdate(sql,student,oldId);

        /**这里一点点细节,直接传Student对象好像不太行,我是一个一个传,然后就可以了,不然总报那个io异常*/
        // 修改学生
        String sql = "UPDATE small_systems.student SET id = ?, name = ?, gender = ?, age = ?, profession = ?, phone = ? WHERE id = ?";
        JDBC.exeUpdate(sql, student.getId(), student.getName(), student.getGender(), student.getAge(),
                student.getProfession(), student.getPhone(), oldId);
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
    //删除学生
    public static void delete_students(int stu) throws Exception {
        String sql = "delete from small_systems.student WHERE id = ?";
        JDBC.exeUpdate(sql, stu);
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
    //学生信息查询
    public static ObservableList<Student> search(String username) throws Exception {
        String sql = "select id,name,gender,age,profession,phone from small_systems.student where id like ? or id= ?";

        //在这段代码中，String[] params 是一个字符串数组，用于存储 SQL 查询语句中的参数值。
        //params 数组中存储了两个参数值：
        //
        //"%"+username+"%"：这个参数值是模糊查询所用的学号模式。"%"+username+"%" 将会在查询时替换为一个包含 username 值的字符串，前后都带有 % 符号。
        // 例如，如果 username 的值是 "123"，那么 params[0] 的值将是 "%123%"，用于实现模糊查询。
        //username：这个参数值是精确匹配所用的学号。它保持了用户输入的原始值，用于在查询时进行精确匹配。
        //这样，通过将参数值存储在 params 数组中，你可以将它们作为参数传递给 JDBC.executeQuery() 方法，以便在执行 SQL 查询时使用这些参数。
        String[] params = {"%" + username + "%", username};
        ResultSet rs = JDBC.executeQuery(sql, params);

        //临时放一个集合,存储查询到的数据
        ObservableList<Student> students = FXCollections.observableArrayList();

        int a = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String profession = rs.getString("profession");
            int phone = rs.getInt("phone");


            Student student = new Student(id, name, gender, age, profession, phone);
            students.add(student);
            a++;
        }
        System.out.println("获取到" + a + "行数据");
        return students;
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
    //老师信息查询
    public static ObservableList<Teacher> course_search(String username) throws Exception {
        String sql = "SELECT t.id, t.name, t.gender, t.age, c.course FROM teacher t JOIN teacher_course tc ON t.id=tc.teacher_id JOIN course c ON tc.course_id=c.id where t.id like ? or t.id=?  ORDER BY teacher_id ASC ;";

        //在这段代码中，String[] params 是一个字符串数组，用于存储 SQL 查询语句中的参数值。
        //params 数组中存储了两个参数值：
        //
        //"%"+username+"%"：这个参数值是模糊查询所用的学号模式。"%"+username+"%" 将会在查询时替换为一个包含 username 值的字符串，前后都带有 % 符号。
        // 例如，如果 username 的值是 "123"，那么 params[0] 的值将是 "%123%"，用于实现模糊查询。
        //username：这个参数值是精确匹配所用的学号。它保持了用户输入的原始值，用于在查询时进行精确匹配。
        //这样，通过将参数值存储在 params 数组中，你可以将它们作为参数传递给 JDBC.executeQuery() 方法，以便在执行 SQL 查询时使用这些参数。
        String[] params = {"%" + username + "%", username};
        ResultSet rs = JDBC.executeQuery(sql, params);

        //临时放一个集合,存储查询到的数据
        ObservableList<Teacher> teachers = FXCollections.observableArrayList();

        int a = 0;
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String course = rs.getString("course");


            Teacher teacher = new Teacher(id, name, gender, age, course);
            teachers.add(teacher);
            a++;
        }
        System.out.println("获取到" + a + "行数据");
        return teachers;
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
//查询老师给老师增加课程
    public static boolean add_course(String teacher_id ,int course_id) throws SQLException {
        String sql = "insert into small_systems.teacher_course (teacher_id,course_id) values (?, ?)";
        boolean rowsAffected = JDBC.exeUpdate(sql, teacher_id, course_id);
        return rowsAffected;
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
    //删除课程,利用中间表的主键,删除中间表中的某一行
    public static void delete_course (String teacher_id,int course_id) {
        String sql = "delete from small_systems.teacher_course WHERE teacher_id = ? and course_id = ?";
        JDBC.exeUpdate(sql, teacher_id,course_id);
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
//    修改课程
//    首先我们需要清楚,修改的是中间表的数据
    public static void edit_course (String teachers_id,int courses_id) {
        String sql ="UPDATE small_systems.teacher_course SET course_id=? WHERE teacher_id = ?";
        JDBC.exeUpdate(sql,courses_id,teachers_id);
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
    //只查询,教师工号,并返回教师工号
    public static String select_teacher_id(String user) throws Exception {
        String sql = "select name from small_systems.teacher where id=?";
        String result = null;

        // 查询语句
        ResultSet rs = JDBC.executeQuery(sql, user);
        while (rs.next()) {
            result = rs.getString("name");
        }
        return result;
    }
    /**
     * -------------------------------------------------------------------------------------------
     */
    //查询老师和对应课程,判断是否存在,不存在返回true
    public static  Boolean select_teacher_course (String id, int course_id)  {
        String sql = "select id from small_systems.teacher_course where teacher_id=? and course_id=?";
        ResultSet rs=JDBC.executeQuery(sql,id,course_id);

        //如果说遍历的集合为空，说明不存在
        try {
            if (rs != null && rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("课程已存在，请重新插入!!!");
        }
        return true;
    }

    /**
     * -------------------------------------------------------------------------------------------
     */
    //查询课程表，返回对应的id,
    public static int select_course(String course) throws Exception {
        int courseId = 0;
        String sql = "SELECT id FROM small_systems.course WHERE course = ?";
        ResultSet rs = JDBC.executeQuery(sql, course);
        if (rs.next()) {
            courseId = rs.getInt("id");
            System.out.println(courseId);
        } else {
            String insertSql = "INSERT INTO small_systems.course (course) VALUES (?)";
            JDBC.exeUpdate(insertSql, course);
            ResultSet generatedKeys = JDBC.executeQuery(sql,course);
            if (generatedKeys.next()) {
                courseId = generatedKeys.getInt("id");
            }
//            System.out.println(courseId);
        }
        return courseId;
    }


}


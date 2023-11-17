package pojo;

import java.sql.*;

public class A_demo {
    private static final String URL ="jdbc:mysql://localhost:3306/数据库名";
    private static final String USERNAME ="root";
    private static final String PWD ="123456";

        public static void Set() throws Exception{
            Connection conn =null;
//            防止sql注入
            PreparedStatement pstmt =null;
            //获取连接:如果连接的是本机的mysql并且端口是默认的3306  可以简化书写
            String url ="jdbc:mysql:///db1";
//            a.导入驱动,加载具体的驱动类
            Class.forName("com.mysql.jdbc.Driver");//加载具体的驱动类
//        b.与数据库建立连接
            conn = DriverManager.getConnection(URL,USERNAME,PWD);

            //接收用户输入  用户名和密码
            String id ="202301";
            String password="6666";

            //SQL语句
            String sql="select * from students where id=? and password=? ";

            //接收变量
            pstmt = conn.prepareStatement(sql);
            //执行SQL
            ResultSet rs = pstmt.executeQuery();

            //判断登录是否成功
            if(rs.next()){
                System.out.println("登录成功");
            }else {
                System.out.println("登录失败");
            }

            //释放资源
            rs.close();
            pstmt.close();
            conn.close();
        }
}

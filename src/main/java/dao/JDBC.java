package dao;

import java.sql.*;
import java.util.Objects;

public class JDBC {
        /**
         * URL地址
         */
        private static final String URL = "jdbc:mysql://127.0.0.1:3306/small_systems?serverTimezone=UTC";
        /**
         * 登录数据库服务器的账号
         */
        private static final String USER = "root";
        /**
         * 登录数据库服务器的密码
         */
        private static final String PASSWORD = "123456";

        /**
         * 返回数据库连接对象
         *
         * @return
         */
        public static Connection getConn() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("连接失败");
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 关闭资源
         *
         * @param rs
         * @param stat
         * @param conn
         */
        public static void close(ResultSet rs, Statement stat, Connection conn) {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * 封装通用的更新操作（即通过该方法实现对弈任意数据表的insert，update，delete操作）
         *
         * @param sql    需要执行的SQL语句
         * @param params 执行SQL语句时需要传递进去参数
         * @return 执行结果
         */
        public static boolean exeUpdate(String sql, Object... params) {
            //获取连接对象
            Connection conn = getConn();
            PreparedStatement ps = null;
            try {
                //获取预编译对象
                ps = conn.prepareStatement(sql);
                //执行参数赋值操作
                if (Objects.nonNull(params)) {
                    //循环将所有的参数赋值
                    for (int i = 0; i < params.length; i++) {
                        ps.setObject(i + 1, params[i]);
                    }
                }
                //执行更新
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //关闭资源
//                close(null, ps, conn);
            }
            return false;
        }



        //查询
    public static ResultSet executeQuery(String sql , Object... params) {
        Connection conn = getConn();
        PreparedStatement ps = null;
        try {
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            //执行参数赋值操作
            if (Objects.nonNull(params)) {
                //循环将所有的参数赋值
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            //执行更新
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
//            close(null, ps, conn);
        }
        return null;
    }
}

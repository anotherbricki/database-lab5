import java.sql.*;
import java.util.*;
public class admin {
    private static final String URL = "jdbc:sqlserver://10.214.6.33;DatabaseName=db05";
    private static String USER;
    private static String PASSWORD;
    
    static{try {//1.反射加载驱动程序类
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    } catch (ClassNotFoundException e) {
        System.out.println("class not found！");}
    try {//2.通过驱动类DriverManager获取与数据库的连接
        System.out.println("Please enter your USERNAME:");
        Scanner sc = new Scanner(System.in);
        //不关闭扫描器，不然会关闭system.in流导致异常。
        USER = sc.nextLine();
        System.out.println("Please enter your password:");
        PASSWORD = sc.nextLine();
    } catch (Exception e) {
        e.printStackTrace();}}

    public static Connection GetConn(int flag) throws SQLException{
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if(flag == 1)
            {System.out.println("用户：" + USER + " 登陆成功！");}
        } catch (SQLException e) {
            System.out.println("连接失败！用户名或密码错误！");
            connection = null;
        }
        return connection;
    }
    public static void close(Statement statement,Connection connection,ResultSet resultSet)
    {
        if(statement!=null)
        try {
            statement.close();
        } catch (SQLException e) {
        e.printStackTrace();}

        if(connection!=null)
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(resultSet!=null)
        {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();}
        }
    }
         
    }  

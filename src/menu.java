import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
public class menu {
    static{
        admin ad = new admin();
    }
    public Scanner s = new Scanner(System.in);
    public int count = 0;
    public void init( ) throws Exception
    {
        Connection connection = admin.GetConn(1);
        if(connection == null)  return;
        //获取连接，并进入主菜单。（如果连接成功）
        try {
        System.out.println("欢迎来到图书管理系统！使用愉快！");
        Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("打开异常！");
        }
        main_menu();
    }
    public void main_menu( ) throws Exception
    {
        System.out.println("-------------------------------------------------");
        System.out.println("|-----------------图书管理系统-------------------|");
        System.out.println("|                 -1.图书入库                    |");
        System.out.println("|                 -2.图书查询                    |");
        System.out.println("|                 -3.借书                        |");
        System.out.println("|                 -4.还书                        |");
        System.out.println("|                 -5.借书证管理                  |");
        System.out.println("|                 -0.退出系统                    |");
        System.out.println("|------------按下对应的数字执行操作--------------|");
        System.out.println("-------------------------------------------------");
        int input = s.nextInt();
        int flag = 0;
        switch (input) {
            case 1:book_into_storage();break;
            case 2:book_search();break;
            case 3:book_borrow();break;
            case 4:book_return();break;
            case 5:scard_admin();break;
            case 0: System.out.println("Thanks for using!"); flag = 1; break;
            case 9:empty_book();break;//管理员函数：直接将全部图书清除，便于展示操作。
            default:break;
        }
        if(flag == 1)
        {
            s.close();
            return;
        }
        else main_menu();
    }
    public void book_into_storage( )
    {
        System.out.println("-------------------------------------------------");
        System.out.println("|-----------------图书管理系统-------------------|");
        System.out.println("|-------------------图书入库---------------------|");
        System.out.println("|                 -1.单本入库                    |");
        System.out.println("|                 -2.批量入库                    |");
        System.out.println("|                 -0.返回菜单                    |");
        System.out.println("|------------按下对应的数字执行操作--------------|");
        System.out.println("-------------------------------------------------");
        int input = s.nextInt();
        switch (input) {
            case 1:single_book();break;
            case 2:multiple_book();break;
            default:break;
        }
    }
    public void book_search( )
    {
        System.out.println("-------------------------------------------------");
        System.out.println("|-----------------图书管理系统-------------------|");
        System.out.println("|-------------------图书查询---------------------|");
        System.out.println("|                 -1.按类别查询                  |");
        System.out.println("|                 -2.按书名查询                  |");
        System.out.println("|                 -3.按出版社查询                |");
        System.out.println("|                 -4.按年份查询                  |");
        System.out.println("|                 -5.按作者查询                  |");
        System.out.println("|                 -6.按价格查询                  |");
        System.out.println("|                 -0.退出系统                    |");
        System.out.println("|------------按下对应的数字执行操作--------------|");
        System.out.println("-------------------------------------------------");
        int input = s.nextInt();
        switch (input) {
            case 1:search_category();break;
            case 2:search_bookname();break;
            case 3:search_press();break;
            case 4:search_year();break;
            case 5:search_author();break;
            case 6:search_price();break;
            default:break;
        }
    }
    public void book_borrow( )
    {
        System.out.println("-------------------------------------------------");
        System.out.println("|-----------------图书管理系统-------------------|");
        System.out.println("|-------------------借用图书---------------------|");
        System.out.println("|                 -1.显示已借书籍                |");
        System.out.println("|                 -2.输入书号借书                |");
        System.out.println("|                 -0.返回菜单                    |");
        System.out.println("|------------按下对应的数字执行操作--------------|");
        System.out.println("-------------------------------------------------");
        int input = s.nextInt();
        switch (input) {
            case 1:borrow_input_cno();break;
            case 2:borrow_input_bno();break;
            default:break;
        }
    }
    public void book_return( )
    {
        System.out.println("-------------------------------------------------");
        System.out.println("|-----------------图书管理系统-------------------|");
        System.out.println("|-------------------还回图书---------------------|");
        System.out.println("|                 -1.显示已借书籍                |");
        System.out.println("|                 -2.输入书号还书                |");
        System.out.println("|                 -0.返回菜单                    |");
        System.out.println("|------------按下对应的数字执行操作--------------|");
        System.out.println("-------------------------------------------------");
        int input = s.nextInt();
        switch (input) {
            case 1:borrow_input_cno();break;
            case 2:return_input_bno();break;
            default:break;
        }
    }
    public void scard_admin( )
    {
        System.out.println("-------------------------------------------------");
        System.out.println("|-----------------图书管理系统-------------------|");
        System.out.println("|------------------借书证管理--------------------|");
        System.out.println("|                 -1.添加借书证                  |");
        System.out.println("|                 -2.删除借书证                  |");
        System.out.println("|                 -0.返回菜单                    |");
        System.out.println("|------------按下对应的数字执行操作--------------|");
        System.out.println("-------------------------------------------------");
        int input = s.nextInt();
        switch (input) {
            case 1:scard_add();break;
            case 2:scard_del();break;
            default:break;
        }
    }

    public void single_book( )
    {
        File fp = new File("book.txt");
        String bno=null,category=null,title=null,press=null,year=null
        ,price=null,author=null,total=null,stock=null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fp),"utf-8"));
           for(int i = 0;i <= count;i++)
           {
            bno = bufferedReader.readLine();
            category = bufferedReader.readLine();
            title = bufferedReader.readLine();
            press = bufferedReader.readLine();
            year = bufferedReader.readLine();
            author = bufferedReader.readLine();
            price = bufferedReader.readLine();
            total = bufferedReader.readLine();
            stock = bufferedReader.readLine(); 
           }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();}
        String sqlquery = null;
        Statement stmt = null;
        Connection connection = null;
        try{
            connection = admin.GetConn(0);
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        sqlquery = "insert into book values ('" + bno + "','" + category + "','" + title
                        + "','" + press + "','" + year + "','" + author + "','" 
                        + price + "','" + total + "','" + stock + "')";
        System.out.println(sqlquery);
        try {
            stmt.executeUpdate(sqlquery);
            System.out.println("存书成功！");
        } catch (SQLException e) {
            System.out.println("执行失败！");
        }
        count++;
        if(count>=100) count = 0;
    }
    public void multiple_book( )
    {
        File fp = new File("book.txt");
        String bno=null,category=null,title=null,press=null,year=null
        ,price=null,author=null,total=null,stock=null;
        BufferedReader bufferedReader = null;
        try {
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fp),"utf-8"));  
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("请输入想入库的本数");
        s.nextLine();
        int nums = s.nextInt();
        String sqlquery = null;
            Statement stmt = null;
            Connection connection = null;
            try{
                connection = admin.GetConn(0);
                stmt = connection.createStatement();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        for(int i = 0;i<count;i++)
        {
            try {
                bno = bufferedReader.readLine();
                category = bufferedReader.readLine();
                title = bufferedReader.readLine();
                press = bufferedReader.readLine();
                year = bufferedReader.readLine();
                author = bufferedReader.readLine();
                price = bufferedReader.readLine();
                total = bufferedReader.readLine();
                stock = bufferedReader.readLine(); 
            } catch (Exception e) {
                e.printStackTrace();}
        }
        for(int i = 0;i<nums;i++)
        {
            try {
                bno = bufferedReader.readLine();
                category = bufferedReader.readLine();
                title = bufferedReader.readLine();
                press = bufferedReader.readLine();
                year = bufferedReader.readLine();
                author = bufferedReader.readLine();
                price = bufferedReader.readLine();
                total = bufferedReader.readLine();
                stock = bufferedReader.readLine(); 
            } catch (Exception e) {
                e.printStackTrace();}
            sqlquery = "insert into book values ('" + bno + "','" + category + "','" + title
                            + "','" + press + "','" + year + "','" + author + "','" 
                            + price + "','" + total + "','" + stock + "')";
            System.out.println(sqlquery);
            try {
                stmt.executeUpdate(sqlquery);
                System.out.println("存书成功！");
            } catch (SQLException e) {
                System.out.println("执行失败！");
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            count += nums;
            if(count>=100) count = 0;
            return;
    }
    public void borrow_input_cno( )
    {
        String sqlquery = null;
        Statement stmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = admin.GetConn(0);
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("请输入借书证卡号：");
        s.nextLine();
        String cno = s.nextLine();
        sqlquery = "select * from borrow where cno = '" + cno + "'";
        System.out.println(sqlquery);
        try {
            resultSet = stmt.executeQuery(sqlquery);
            while(resultSet.next())
            {   
                for(int i = 0;i<5;i++)
                System.out.print(resultSet.getString(i+1) + "  ");

                System.out.println();
                
            }
        } catch (SQLException e) {
            System.out.println("结果为空！没有借书。");
        }
    }
    public void borrow_input_bno( )
    {
        String sqlquery = null;
        Statement stmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = admin.GetConn(0);
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("请输入您的卡号：");
        s.nextLine();
        String cno = s.nextLine();
        System.out.println("请输入想借的图书编号：");
        String bno = s.nextLine();
        sqlquery = "select * from book where bno = '" + bno + "'";
        System.out.println(sqlquery);
        try {
            resultSet = stmt.executeQuery(sqlquery);
            resultSet.next();
            System.out.println("本书库存还有" + resultSet.getInt("stock") + "本");
        } catch (SQLException e) {
            System.out.println("未查到此图书！");
            return;
        }
        try {
            int stock = resultSet.getInt(9);
            if(stock > 0)
            {
                stock--;
                sqlquery = "update book set stock = " + stock + " where bno = '" + bno + "'";
                System.out.println(sqlquery);
                try {
                    stmt.executeUpdate(sqlquery);
                } catch (SQLException e) {
                    System.out.println("更新失败！");
                    return;
                }
            }
            else
            {
                System.out.println("库存已为 0 ！不能再借！");
                sqlquery = "select return_date from borrow where bno = '" + bno + "' order by return_date desc";
                try {
                    resultSet = stmt.executeQuery(sqlquery);
                    System.out.println("最近的归还时间");
                    resultSet.next();
                    System.out.println(resultSet.getString(1));
                    return;
                } catch (SQLException e) {
                    System.out.println("查询异常！");
                    e.printStackTrace();
                    return;
                }
            }
        } catch (SQLException e) {
            System.out.println("操作失败！");
            e.printStackTrace();
            return;
        }
        Date d = new Date();
        java.sql.Date date = new java.sql.Date(d.getYear(),d.getMonth(), d.getDay());
        Time time = new Time(d.getHours(), d.getMinutes(), d.getSeconds());
        //need to be updated because of deprecated constructor.
        sqlquery = "insert into borrow values('" + cno + "','" + bno + "','" + date.toString() + " " + time.toString() + "',null,'admin')";
        System.out.println(sqlquery);
        try {
            stmt.executeUpdate(sqlquery);
        } catch (SQLException e) {
            System.out.println("插入借阅数据出现异常！");
            e.printStackTrace();
        }
    }
     /**test**/
    public void return_input_bno( )
    {
        String sqlquery = null;
        Statement stmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = admin.GetConn(0);
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("请输入你的卡号：");
        s.nextLine();
        String cno = s.nextLine();
        System.out.println("请输入想还的图书编号：");
        String bno = s.nextLine();
        sqlquery = "select * from borrow where bno = '" + bno + "' and cno = '" + cno +
         "'and return_date is null";
        try {
            resultSet = stmt.executeQuery(sqlquery);
            resultSet.next();
            System.out.println("您借书的日期为：" + resultSet.getString(3));
        } catch (SQLException e) {
            System.out.println("您没有借这本书！或者已归还！");
            return;
        }
        sqlquery = "select * from book where bno = '" + bno + "'";
        try {
            resultSet = stmt.executeQuery(sqlquery);
            resultSet.next();
            System.out.println("本书库存为：" + resultSet.getInt("stock"));
        } catch (SQLException e) {
            System.out.println("未查到此图书！");
            return;
        }
        try {
            int stock = resultSet.getInt("stock");
            stock ++;
            sqlquery = "update book set stock = " + stock + "where bno = '" + bno + "'";
            stmt.executeUpdate(sqlquery);
            System.out.println("还书成功！");
        } catch (SQLException e) {
            System.out.println("ERROR！还书失败！");
            e.printStackTrace();
        }
        Date d = new Date();
        java.sql.Date date = new java.sql.Date(d.getYear(),d.getMonth(), d.getDay());
        System.out.println(d.getDay());
        Time time = new Time(d.getHours(), d.getMinutes(), d.getSeconds());
        try {
            sqlquery = "update borrow set return_date = '" + date.toString() + " " + 
            time.toString() + "' where bno = '" + bno + "' and cno = '" + cno + "'";
            stmt.executeUpdate(sqlquery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    public void scard_add( )
    { 
        String sqlquery = null;
        Statement stmt = null;
        Connection connection = null;
        try{
            connection = admin.GetConn(0);
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("请输入卡号：");
        s.nextLine();            //空一行。
        final String cno = s.nextLine();
        System.out.println("请输入姓名：");
        final String name = s.nextLine();
        System.out.println("请输入部门：");
        final String department = s.nextLine();
        char type;
        while(true)
        {
            System.out.println("请输入类型：{T,G,U,O}");
            type = s.nextLine().charAt(0);
            if(type=='T'||type=='G'||type=='U'||type=='O')
            break;
            else
            {
                System.out.println("类型名输入错误！请重新输入!");
                continue;
            }
        }
        sqlquery = "insert into scard values('" + cno + "','" + name + "','" + department + "','" + type + "')";
        try {
        stmt.executeUpdate(sqlquery); 
        } catch (SQLException e) {
            System.out.println("插入失败！该用户名已存在！请您重新插入！");
            return;
        }
        try {
            System.out.println("插入成功！");
            System.out.println("请确认您的个人信息：");
            System.out.println("卡号："+cno);
            System.out.println("姓名："+name);
            System.out.println("部门："+department);
            System.out.println("类型："+type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void scard_del( )
    {
        String sqlquery = null;
        Statement stmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = admin.GetConn(0);
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("请输入待注销卡号：");
        s.nextLine();            //空一行。
        final String cno = s.nextLine();
        try {
            sqlquery = "select * from scard where cno = '" + cno + "'";
            resultSet = stmt.executeQuery(sqlquery);
            resultSet.next();
            System.out.println("待注销卡号为：" + resultSet.getString(1));
        } catch (SQLException e) {
            System.out.println("未找到该卡！");
            return;
        }
        try {
            sqlquery = "select * from borrow where cno = '" + cno + "' where return_date is null";
            System.out.println(sqlquery);
            resultSet = stmt.executeQuery(sqlquery);
            resultSet.next();
            if(resultSet.getString(1) != null)
            {System.out.println("该卡有未还图书，不能注销卡！");
            return;}
        } catch (SQLException e) {}
        try {
            sqlquery = "delete from scard where cno = '" + cno + "'";
            stmt.executeUpdate(sqlquery);
            System.out.println("注销卡成功！");
        } catch (Exception e) {
            System.out.println("出现异常！注销失败！");
        }
    }
    public void search_category( )
    {
        System.out.println("请输入想搜索的类别：");
        s.nextLine();
        String category = s.nextLine();
        category.strip();
        System.out.println("是否需要排序？是的话请键入1，否则键入0：");
        int needSort = s.nextInt();
        String sortquery = "order by title";

        try {
            Connection connection = admin.GetConn(0);
            Statement stmt = connection.createStatement();
            String sqlcommand = "select * from book where category = '" + category + "' ";
            if(needSort == 1) sqlcommand += sortquery;
            System.out.println(sqlcommand);
            ResultSet resultSet = stmt.executeQuery(sqlcommand);
            while(resultSet.next())
            {
                for(int i = 0;i<9;i++)
                System.out.print(resultSet.getString(i+1)+' ');

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("search failed!!!");
            e.printStackTrace();
        }
    }
    public void search_bookname( )
    {
        System.out.println("请输入想搜索的书名：");
        s.nextLine();
        String title = s.nextLine();
        title.strip();
        System.out.println("是否需要排序？是的话请键入1，否则键入0：");
        int needSort = s.nextInt();
        String sortquery = "order by title";

        try {
            Connection connection = admin.GetConn(0);
            Statement stmt = connection.createStatement();
            String sqlcommand = "select * from book where title = '" + title + "' ";
            if(needSort == 1) sqlcommand += sortquery;
            System.out.println(sqlcommand);
            ResultSet resultSet = stmt.executeQuery(sqlcommand);
            while(resultSet.next())
            {
                for(int i = 0;i<9;i++)
                System.out.print(resultSet.getString(i+1)+' ');

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("search failed!!!");
        } 
    }
    public void search_press( )
    {
        System.out.println("请输入想搜索的出版社名：");
        s.nextLine();
        String press = s.nextLine();
        press.strip();
        System.out.println("是否需要排序？是的话请键入1，否则键入0：");
        int needSort = s.nextInt();
        String sortquery = "order by title";

        try {
            Connection connection = admin.GetConn(0);
            Statement stmt = connection.createStatement();
            String sqlcommand = "select * from book where press = '" + press + "' ";
            if(needSort == 1) sqlcommand += sortquery;
            System.out.println(sqlcommand);
            ResultSet resultSet = stmt.executeQuery(sqlcommand);
            while(resultSet.next())
            {
                for(int i = 0;i<9;i++)
                System.out.print(resultSet.getString(i+1)+' ');

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("search failed!!!");
        } 
    }
    public void search_author( )
    {
        System.out.println("请输入想搜索的作者名：");
        s.nextLine();
        String author = s.nextLine();
        author.strip();
        System.out.println("是否需要排序？是的话请键入1，否则键入0：");
        int needSort = s.nextInt();
        String sortquery = "order by title";

        try {
            Connection connection = admin.GetConn(0);
            Statement stmt = connection.createStatement();
            String sqlcommand = "select * from book where author = '" + author + "' ";
            if(needSort == 1) sqlcommand += sortquery;
            System.out.println(sqlcommand);
            ResultSet resultSet = stmt.executeQuery(sqlcommand);
            while(resultSet.next())
            {
                for(int i = 0;i<9;i++)
                System.out.print(resultSet.getString(i+1)+' ');

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("search failed!!!");
        } 
    }
    public void search_year( )
    {
        System.out.println("请输入想搜索的书出版年份区间的下界：");
        s.nextLine();
        int year_min = s.nextInt();
        System.out.println("请输入想搜索的书出版年份区间的上界：");
        int year_max = s.nextInt();
        System.out.println("是否需要排序？是的话请键入1，否则键入0：");
        int needSort = s.nextInt();
        String sortquery = "order by title";

        try {
            Connection connection = admin.GetConn(0);
            Statement stmt = connection.createStatement();
            String sqlcommand = "select * from book where year >= " + year_min + 
                                " and year <= " + year_max + " ";
            if(needSort == 0) sqlcommand += sortquery;
            else if(needSort == 1) sqlcommand += "order by year";
            System.out.println(sqlcommand);
            ResultSet resultSet = stmt.executeQuery(sqlcommand);
            while(resultSet.next())
            {
                for(int i = 0;i<9;i++)
                System.out.print(resultSet.getString(i+1)+' ');

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("search failed!!!");
        } 
    }
    public void search_price( )
    {
        System.out.println("请输入想搜索的书出版价格区间的下界：");
        s.nextLine();
        float price_min = s.nextFloat();
        System.out.println("请输入想搜索的书出版价格区间的上界：");
        float price_max = s.nextFloat();
        System.out.println("是否需要排序？是的话请键入1，否则键入0：");
        int needSort = s.nextInt();
        String sortquery = "order by title";

        try {
            Connection connection = admin.GetConn(0);
            Statement stmt = connection.createStatement();
            String sqlcommand = "select * from book where price >= " + price_min + 
                                " and price <= " + price_max + " ";
            if(needSort == 0) sqlcommand += sortquery;
            else if(needSort == 1) sqlcommand += "order by price";
            System.out.println(sqlcommand);
            ResultSet resultSet = stmt.executeQuery(sqlcommand);
            while(resultSet.next())
            {
                for(int i = 0;i<9;i++)
                System.out.print(resultSet.getString(i+1)+' ');

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("search failed!!!");
        } 
    }
    public void empty_book()
    {
        String sqlquery = null;
        Statement stmt = null;
        Connection connection = null;
        try{
            connection = admin.GetConn(0);
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            sqlquery = "delete from book";
            stmt.executeUpdate(sqlquery);
            System.out.println("图书清库成功！");
        } catch (SQLException e) {
            System.out.println("发生错误！");
        }
    }
}

//仅供参考变量声明用。
public class borrow {
    private String cno; //卡号
    private String bno; //书号
    private String borrow_date; //借书日期
    private String return_date; //归还日期
    private String adminno;     //经手人（管理员）

    public void Insert_Borrow(String cno,String bno,String borrow_date,
    String return_date,String adminno)
    {
        this.adminno = adminno;
        this.bno = bno;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.cno = cno;
    }

    public String Get_bno()
    {
        return bno;
    }
    public String Get_cno()
    {
        return cno;
    }
    public String Get_adminno()
    {
        return adminno;
    }
    public String Get_borrow_date()
    {
        return borrow_date;
    }
    public String Get_return_date()
    {
        return return_date;
    }
}

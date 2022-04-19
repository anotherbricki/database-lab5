//仅供参考变量声明用。
public class scard {
    private String cno; //书号
    private String name;//用户名
    private String department;//部门
    private char type;//类型

    public void Insert_Scard(String cno,String name,String department,char type)
    {
        if(cno == null)
        {System.out.println("ERROR! Primary key 'cno' can't accept 'null' value.");
        return;}
        this.cno = cno;
        this.department = department;
        this.name = name;
        this.type = type;
    }

    public String Get_cno()
    {
        return cno;
    }
    public String Get_name()
    {
        return name;
    }
    public String Get_department()
    {
        return department;
    }
    public char Get_type()
    {
        return type;
    }
}

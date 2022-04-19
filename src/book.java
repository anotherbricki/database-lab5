//仅供参考变量声明用。

public class book {
    private String bno; //书号
    private String category; //类别
    private String title;    //书名
    private String press;    //出版社
    private int year;        //出版年份
    private String author;   //作者
    private String price;    //价格
    private int total;       //总量
    private int stock;       //库存
    public void Insert_Book(String bno,String category,String title,
    String press,int year,String author,String price,int total,int stock)
    {
        if(bno == null)
        {System.out.println("ERROR! Primary key 'bno' can't accept 'null' value.");
        return;}
        if(total<0)
        {System.out.println("ERROR! Total can't be negative.");
        return;}
        if(stock<0)
        {System.out.println("ERROR! Stock can't be negative.");
        return;}
        this.bno = bno;
        this.category = category;
        this.title = title;
        this.press = press;
        this.year = year;
        this.author = author;
        this.price = price;
        this.total = total;
        this.stock = stock;
    }
    public String Get_bno()
    {
        return bno;
    }
    public String Get_category()
    {
        return category;
    }
    public String Get_title()
    {
        return title;
    }
    public String Get_press()
    {
        return press;
    }
    public int Get_year()
    {
        return year;
    }
    public String Get_author()
    {
        return author;
    }
    public String Get_price()
    {
        return price;
    }
    public int Get_total()
    {
        return total;
    }
    public int Get_stock()
    {
        return stock;
    }
}

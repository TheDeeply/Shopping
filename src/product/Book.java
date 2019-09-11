package product;

import java.util.regex.Pattern;

public class Book extends Product{
    private String press;
    private String author;
    public Book(int id,String name,double price,int num,String press,String author,String type){
        setId(id);
        setName(name);
        setPrice(price);
        setNum(num);
        setPress(press);
        setAuthor(author);
        setType(type);
    }
    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public void search(String info) {
        String pattern = ".*"+info+".*";
        boolean nameMatch = Pattern.matches(pattern,super.getName());
        boolean pressMatch = Pattern.matches(pattern,this.press);
        boolean authorMatch = Pattern.matches(pattern,this.author);
        if(nameMatch||pressMatch||authorMatch) show();
    }
    public void show() {
        System.out.println("id："+getId()+"  名称："+getName()+"  出版社："+getPress()+"  作者："+getAuthor()+"  价格："+getPrice()+"  库存："+getNum());
    }

}

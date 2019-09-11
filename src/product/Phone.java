package product;

import java.util.regex.Pattern;

public class Phone extends Product {
    private String system;
    private String brand;
    private String color;

    public Phone(int id,String name,double price,int num,String system,String brand,String color,String  type) {
        setId(id);
        setName(name);
        setPrice(price);
        setNum(num);
        setSystem(system);
        setBrand(brand);
        setColor(color);
        setType(type);
    }
    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public void search(String info) {
        String pattern = ".*"+info+".*";
        boolean nameMatch = Pattern.matches(pattern,getName());
        boolean systemMatch = Pattern.matches(pattern,this.system);
        boolean brandMatch = Pattern.matches(pattern,this.brand);
        boolean colorMatch = Pattern.matches(pattern,this.color);
        if(nameMatch||systemMatch||brandMatch||colorMatch) show();
    }
    public void show() {
        System.out.println("id："+getId()+"  名称："+getName()+"  品牌："+getBrand()+"  操作系统："+getSystem()+"  颜色："+getColor()+"  价格："+getPrice()+"  库存："+getNum());
    }
}

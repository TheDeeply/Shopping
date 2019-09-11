package product;

public class Product {
    private Integer id;
    private String name;
    private Integer num;
    private double price;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void search(String info){
    };
    public void search_1(){};
    public void print() {
        System.out.println("商品id："+getId()+"  名称："+getName()+"  价格："+getPrice()+"  库存："+getNum());
    }
    public void show() {};
}

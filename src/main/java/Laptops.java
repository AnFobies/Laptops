public class Laptops {
    int ram;

    int hd;

    String os;

    String color;


    public Laptops(int ram, int hd, String os, String color) {
        this.ram = ram;
        this.hd = hd;
        this.os = os;
        this.color = color;
    }


    @Override
    public String toString() {
        return "ram: " + ram + ", hd: " + hd + ", os: " + os + ", color: " + color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Laptops)){
            return false;
        }
        Laptops laptops = (Laptops) obj;
        return ram == laptops.ram && hd == laptops.hd && os.equals(laptops.os) && color.equals(laptops.color);
    }

    @Override
    public int hashCode() {
        return 11*ram + 7*hd + 13*os.hashCode() + 17*color.hashCode();
    }
}

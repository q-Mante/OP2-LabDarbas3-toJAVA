package ProductAnalysis;

import java.util.*;
import java.text.*;
import java.util.Objects;

public class ShopProductInfo implements Comparable<ShopProductInfo>, TableGeneric {
    private String Name;
    private Date Arrived;
    private int Sold;
    private int Stock;
    private GeneralProductInfo ProductInfo;

    public Date getExpire() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Arrived);
        calendar.add(Calendar.DAY_OF_MONTH, ProductInfo.getValidity());
        return calendar.getTime();
    }

    public float getTotalPrice() {
        return Stock * ProductInfo.getPrice();
    }

    public ShopProductInfo() {
        // Default constructor
    }

    public ShopProductInfo(String name, Date arrived, int sold, int stock, GeneralProductInfo productInfo) {
        Name = name;
        Arrived = arrived;
        Sold = sold;
        Stock = stock;
        ProductInfo = productInfo;
    }

    @Override
    public int compareTo(ShopProductInfo o) {
        if (o == null)
            return 1;

        if (Name.compareTo(o.Name) == 0)
            return Float.compare(ProductInfo.getPrice(), o.ProductInfo.getPrice());

        return Name.compareTo(o.Name);
    }

    public boolean equals(ShopProductInfo o) {
        if (o == null)
            return false;

        return Name.equals(o.Name) && Arrived.equals(o.Arrived);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj instanceof ShopProductInfo) {
            ShopProductInfo o = (ShopProductInfo) obj;
            return this.equals(o);
        }
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Arrived);
    }

    @Override
    public String toString() {
        return toString("DEFAULT");
    }

    public String toString(String format) {

        switch (format.toUpperCase()) {
            case "DEFAULT":
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return String.format("| %-30s | %17s | %7.2f |",
                        Name, dateFormat.format(getExpire()), ProductInfo.getPrice());
            default:
                throw new IllegalArgumentException(String.format("The %s format string is not supported.", format));
        }
    }

    public String Header() {
        return this.Header("DEFAULT");
    }

    @Override
    public String Header(String format) {

        switch (format.toUpperCase()) {
            case "DEFAULT":
                return String.format("| %-30s | %17s | %7s |",
                        "Prekės pavadinimas", "Galiojimo pabaiga", "Kaina \u20AC");
            default:
                throw new IllegalArgumentException(String.format("The %s format string is not supported.", format));
        }
    }
}


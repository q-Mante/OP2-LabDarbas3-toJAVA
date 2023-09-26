package ProductAnalysis;

import java.util.*;
import java.text.*;

public class Shop implements Comparable<Shop>, TableGeneric {
    private String name;
    private LinkList<ShopProductInfo> shopProductInfos;

    public Shop() {
        // Default constructor
    }

    public Shop(String name) {
        this.name = name;
        shopProductInfos = new LinkList<ShopProductInfo>();
    }

    public String getName() {
        return name;
    }

    public int getAllStock() {
        int sum = 0;
        for (ShopProductInfo info : shopProductInfos) {
            sum += info.getStock();
        }
        return sum;
    }

    public float getValue() {
        float sum = 0f;
        for (ShopProductInfo info : shopProductInfos) {
            sum += info.getTotalPrice();
        }
        return sum;
    }

    public ShopProductInfo favoriteProduct() {
        ShopProductInfo favorite = new ShopProductInfo();
        float ratio = -1f;

        for (ShopProductInfo info : shopProductInfos) {
            float selectedRatio = (float) info.getSold() / ((float) (new Date().getTime() - info.getArrived().getTime()) / (24 * 60 * 60 * 1000) + 1);
            if (selectedRatio > ratio) {
                favorite = info;
                ratio = selectedRatio;
            }
        }
        return favorite;
    }

    public void findExpires(LinkList<ShopProductInfo> expires, int days) {
        for (ShopProductInfo info : shopProductInfos) {
            if (info.getExpire().compareTo(new Date()) >= 0 && info.getExpire().compareTo(new Date(new Date().getTime() + (long) days * 24 * 60 * 60 * 1000)) <= 0) {
                expires.Add(info);
            }
        }
    }

    public int productsCount() {
        return shopProductInfos.getCount();
    }

    public void productsAdd(ShopProductInfo product) {
        shopProductInfos.Add(product);
    }

    public void productsSort() {
        shopProductInfos.Sort();
    }

    @Override
    public int compareTo(Shop other) {
        if (other == null)
            return 1;
        return name.compareTo(other.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        Shop other = (Shop) obj;
        return Objects.equals(name, other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return toString("DEFAULT");
    }

    @Override
    public String toString(String format) {
        switch (format.toUpperCase()) {
            case "DEFAULT":
                return String.format("| %1$-40s | %2$7d | %3$10.2f |", name, getAllStock(), getValue());
            case "INPUT":
                StringBuilder newString = new StringBuilder();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (ShopProductInfo info : shopProductInfos) {
                    newString.append(String.format("| %1$-40s | %2$-30s | %3$13s | %4$8d | %5$7d |\n",
                            name, info.getName(), dateFormat.format(info.getArrived()), info.getSold(), info.getStock()));
                }
                newString.deleteCharAt(newString.length() - 1);
                return newString.toString();
            default:
                throw new IllegalArgumentException(String.format("The %s format string is not supported.", format));
        }
    }

    public String Header() {
        return this.Header("DEFAULT");
    }

    public String Header(String format) {
        switch (format.toUpperCase()) {
            case "DEFAULT":
                return String.format("| %1$-40s | %2$-7s | %3$-10s |", "Parduotuvės pavadinimas", "Likutis", "Vertė");
            case "INPUT":
                return String.format("| %1$-40s | %2$-30s | %3$-13s | %4$-8s | %5$-7s |",
                        "Parduotuvės pavadinimas", "Prekės pavadinimas", "Atvykimo data", "Parduota", "Likutis");
            default:
                throw new IllegalArgumentException(String.format("The %s format string is not supported.", format));
        }
    }

    public boolean equals(Shop other) {
        if (other == null)
            return false;
        return Objects.equals(name, other.getName());
    }
}

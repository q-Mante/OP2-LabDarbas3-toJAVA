package ProductAnalysis;

import java.util.Objects;

public class GeneralProductInfo implements Comparable<GeneralProductInfo>, TableGeneric {

    String Name;
    int Validity;
    Float Price;
    GeneralProductInfo() {}

    public GeneralProductInfo(String name, int validity, Float price) {
        Name = name;
        Validity = validity;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public int getValidity() {
        return Validity;
    }

    public Float getPrice() {
        return Price;
    }

    @Override
    public int compareTo(GeneralProductInfo o) {
        if (o == null) return 1;

        if (Name.compareTo(o.Name) == 0)
            return Price.compareTo(o.Price);

        return Name.compareTo(o.Name);
    }

    public boolean equals(GeneralProductInfo o) {
        if (o == null)
            return false;

        return Name.equals(o.Name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj instanceof GeneralProductInfo) {
            GeneralProductInfo o = (GeneralProductInfo) obj;
            return this.equals(o);
        }
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name);
    }

    @Override
    public String toString() {
        return this.toString("DEFAULT");
    }

    @Override
    public String toString(String format) {
        switch (format.toUpperCase())
        {
            case "DEFAULT":
                return String.format("| %-30s | %25s | %7s |",
                        Name, Validity, Price);
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
                return String.format("| %-30s | %-25s | %-7s |",
                        "Prekės pavadinimas", "Galiojimo laikotarpis (d)", "Kaina \u20AC");
            default:
                throw new IllegalArgumentException(String.format("The %s format string is not supported.", format));
        }
    }
}

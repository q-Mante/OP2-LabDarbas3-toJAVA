package ProductAnalysis;

import java.util.Objects;

public class GeneralProductInfo implements Comparable<GeneralProductInfo> {

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
        if (o == null) return false;

        return Name.equals(o.Name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

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
}

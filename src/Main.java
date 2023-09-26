import ProductAnalysis.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static String fileFirstPath = "src\\Data\\U19a.txt";
    private final static String fileSecondPath = "src\\Data\\U19b.txt";
    private final static String fileResultPath = "src\\Data\\Results.txt";

    public static void main(String[] args) throws IOException {

        if (Files.exists(Path.of(fileResultPath)))
            Files.delete(Path.of(fileResultPath));

        LinkList<GeneralProductInfo> AllInformations = InOutUtils.readInformations(fileSecondPath);
        LinkList<Shop> AllShops = InOutUtils.readShops(fileFirstPath, AllInformations);

        if (AllShops != null && AllShops.getCount() != 0) {

            // Writing results to file
            InOutUtils.print(fileResultPath, "Duomenys: U19a.txt", AllShops, "INPUT");
        } else {

            // Writing results to file
            Files.write(Paths.get(fileResultPath), "Duomenų nėra.\n\n".getBytes(), StandardOpenOption.APPEND);
        }

        if (AllInformations != null && AllInformations.getCount() != 0) {

            // Writing results to file
            InOutUtils.print(fileResultPath, "Duomenys: U19b.txt", AllInformations, "DEFAULT");
        } else {

            // Writing results to file
            Files.write(Paths.get(fileResultPath), "Duomenų nėra.\n\n".getBytes(), StandardOpenOption.APPEND);
        }

        System.out.print("Pinigų suma: ");
        Scanner sc = new Scanner(System.in);
        float MaximumValue = sc.nextFloat();

        Files.write(Paths.get(fileResultPath), String.format("Pinigų suma: %s\n\n", MaximumValue).getBytes(), StandardOpenOption.APPEND);

        LinkList<GeneralProductInfo> Favorites = TaskUtils.findFavorites(AllShops);

        LinkList<ShopProductInfo> Expires = TaskUtils.findProductsThatExpireIn(AllShops, 30);
        Expires.Sort();

        Shop Biggest = TaskUtils.findShopWithBiggestAssortment(AllShops);

        LinkList<Shop> Shops = TaskUtils.findShopsThatAreBelowSpecifiedValue(AllShops, MaximumValue);


        if (Favorites != null && Favorites.getCount() != 0) {

            // Writing results to file
            InOutUtils.print(fileResultPath, "Rezultatai: Perkamiausių prekių sąrašas", Favorites, "DEFAULT");
        } else {

            // Writing results to file
            Files.write(Paths.get(fileResultPath), "Perkamiausių prekių sąraše nėra.\n\n".getBytes(), StandardOpenOption.APPEND);
        }

        if (Expires != null && Expires.getCount() != 0) {

            // Writing results to file
            InOutUtils.print(fileResultPath, "Rezultatai: Baigiantis galioti prekių sąrašas", Expires, "DEFAULT");
        } else {

            // Writing results to file
            Files.write(Paths.get(fileResultPath), "Nėra prekių, kurių galiojimo laikotarpis pasibaigs.\n\n".getBytes(), StandardOpenOption.APPEND);
        }

        if (Biggest.getName() != null) {

            // Writing results to file
            Files.write(Paths.get(fileResultPath), String.format("Parduotuvė, kuri turi didžiausią prekių asortimentą: %s.\n\n", Biggest.getName()).getBytes(), StandardOpenOption.APPEND);
        } else {

            // Writing results to file
            Files.write(Paths.get(fileResultPath), "Nėra parduotuvės su didžiausiu prekių asortimentu.\n\n".getBytes(), StandardOpenOption.APPEND);
        }

        if (Shops != null && Shops.getCount() != 0) {

            // Writing results to file
            InOutUtils.print(fileResultPath, "Rezultatai: Biudžeto parduotuvių sąrašas", Shops, "DEFAULT");
        } else {

            // Writing results to file
            Files.write(Paths.get(fileResultPath), String.format("Nėra parduotuvių, kurių vertė neviršija %s €\n\n", MaximumValue).getBytes(), StandardOpenOption.APPEND);
        }
    }
}
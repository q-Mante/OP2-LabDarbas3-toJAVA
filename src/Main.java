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
    }
}
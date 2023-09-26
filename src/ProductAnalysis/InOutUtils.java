package ProductAnalysis;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InOutUtils {
    public static LinkList<GeneralProductInfo> readInformations(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            LinkList<GeneralProductInfo> linkedInformations = new LinkList<GeneralProductInfo>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0];
                int validity = Integer.parseInt(parts[1]);
                float price = Float.parseFloat(parts[2]);
                GeneralProductInfo information = new GeneralProductInfo(name, validity, price);
                linkedInformations.Add(information);
            }
            return linkedInformations;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LinkList<Shop> readShops(String fileName, LinkList<GeneralProductInfo> linkedInformations) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            LinkList<Shop> linkedShops = new LinkList<Shop>();
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String shopName = parts[0];
                String productName = parts[1];
                Date arrived = dateFormat.parse(parts[2]);
                int sold = Integer.parseInt(parts[3]);
                int stock = Integer.parseInt(parts[4]);

                GeneralProductInfo requiredInfo = new GeneralProductInfo(productName, 0, 0f);
                if (linkedInformations.Contains(requiredInfo)) {
                    requiredInfo = linkedInformations.Find(requiredInfo).Value;
                }

                ShopProductInfo product = new ShopProductInfo(productName, arrived, sold, stock, requiredInfo);

                Shop requiredShop = new Shop(shopName);
                if (linkedShops.Contains(requiredShop)) {
                    linkedShops.Find(requiredShop).Value.productsAdd(product);
                    linkedShops.Find(requiredShop).Value.productsSort();
                } else {
                    requiredShop.productsAdd(product);
                    linkedShops.Add(requiredShop);
                }
            }
            return linkedShops;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Comparable<T> & TableGeneric> void print(
            String fileName, String title, Iterable<T> list, String format) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Iterator<T> iterator = list.iterator();

            if (!iterator.hasNext()) {
                return; // Empty list, nothing to print.
            }

            T firstItem = iterator.next();
            String header = firstItem.Header(format);
            String line = new String(new char[header.length()]).replace('\0', '-');
            String titleLine = "| " + title + new String(new char[header.length() - title.length() - 3]).replace('\0', ' ') + '|';

            writer.write(line);
            writer.newLine();
            writer.write(titleLine);
            writer.newLine();
            writer.write(line);
            writer.newLine();
            writer.write(header);
            writer.newLine();
            writer.write(line);

            writer.newLine();
            writer.write(firstItem.toString(format));

            while (iterator.hasNext()) {
                T item = iterator.next();
                writer.newLine();
                writer.write(item.toString(format));
            }

            writer.newLine();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

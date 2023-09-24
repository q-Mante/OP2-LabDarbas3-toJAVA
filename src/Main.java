import ProductAnalysis.GeneralProductInfo;
import ProductAnalysis.LinkList;
import ProductAnalysis.ShopProductInfo;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        GeneralProductInfo a = new GeneralProductInfo("Duona", 10, 9.99f);
        Date date = new Date(123, Calendar.SEPTEMBER, 10);
        ShopProductInfo A = new ShopProductInfo("Duona", date, 100, 1000, a);

        System.out.println(date);

        //GeneralProductInfo class print test
        System.out.println(a.Header());
        System.out.println(a);

        //Space
        System.out.println();

        //ShopProductInfo class print test
        System.out.println(A.Header());
        System.out.println(A);

        //Space
        System.out.println();

        // LinkList Test
        LinkList<String> list = new LinkList<>();
        list.Add("C");
        list.Add("B");
        list.Add("D");

        for (String i:list) {
            System.out.println(i);
        }

        //Space
        System.out.println();

        System.out.println(list.Contains("B"));
        System.out.println(list.Contains("J"));

        //Space
        System.out.println();

        list.Remove("D");
        list.Add("E");
        list.Add("A");

        for (String i:list) {
            System.out.println(i);
        }

        //Space
        System.out.println();

        list.Sort();

        for (String i:list) {
            System.out.println(i);
        }
    }
}
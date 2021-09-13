package kz.special.specialfirstspring.db;

import kz.special.specialfirstspring.entites.Items;
import org.apache.tomcat.util.http.fileupload.impl.FileItemStreamImpl;

import java.util.ArrayList;

public class DBManager {


    private static ArrayList<Items> items = new ArrayList<>();

    private static Long id = 4L ;
    static {
        items.add(new Items(1l,"OnePLus",250000));
        items.add(new Items(2l,"Iphone",300000));
        items.add(new Items(3l,"Samsung",200000));

    }
    public static void addItem(Items item){
        item.setId(id);
        items.add(item);
        id++;
    }
    public static ArrayList<Items> getAllItems(){
        return items;
    }

    public static  Items getItem(Long id){
        for (Items it:items){
            if (it.getId()==id) return it;
        }
        return null;
    }
}

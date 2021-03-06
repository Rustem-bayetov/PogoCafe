package models.dao;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class DataAccess {
    static String mainPath = "c:\\DataFiles";   // change according the location of your database in you system
  //  static String mainPath = "c:\\DataFiles";//John Mac Computer


    public static enum StorageType {
        USERS, MENUS, ITEMS, CATEGORIES, COUNTERS, ORDERS
    }

    public void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {

            Path path = Paths.get(mainPath,type.toString());

            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch(Exception e) {}
            }
        }
    }

    public Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
             Path path = Paths.get(mainPath,type.toString());

            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (EOFException eof){
            return null;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(Exception e) {}
            }
        }
        return retVal;
    }

}

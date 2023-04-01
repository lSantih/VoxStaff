package us.boxpvp.boxstaff.util;

import org.bukkit.inventory.ItemStack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String getArmor(final ItemStack stack) {
        final String type = stack.getType().name();
        if(type.contains("HELMET")) return "HELMET";
        else if(type.contains("CHESTPLATE")) return "CHESTPLATE";
        else if(type.contains("LEGGINGS")) return "LEGGINGS";
        else if(type.contains("BOOTS")) return "BOOTS";
        else return "";
    }

    public static String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}

package us.boxpvp.boxstaff.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CC {

    public static final String BLUE = ChatColor.BLUE.toString();
    public static final String AQUA = ChatColor.AQUA.toString();
    public static final String YELLOW = ChatColor.YELLOW.toString();
    public static final String RED = ChatColor.RED.toString();
    public static final String GRAY = ChatColor.GRAY.toString();
    public static final String GOLD = ChatColor.GOLD.toString();
    public static final String GREEN = ChatColor.GREEN.toString();
    public static final String WHITE = ChatColor.WHITE.toString();
    public static final String BLACK = ChatColor.BLACK.toString();
    public static final String BOLD = ChatColor.BOLD.toString();
    public static final String ITALIC = ChatColor.ITALIC.toString();
    public static final String UNDER_LINE = ChatColor.UNDERLINE.toString();
    public static final String STRIKE_THROUGH = ChatColor.STRIKETHROUGH.toString();
    public static final String RESET = ChatColor.RESET.toString();
    public static final String MAGIC = ChatColor.MAGIC.toString();
    public static final String DARK_BLUE = ChatColor.DARK_BLUE.toString();
    public static final String DARK_AQUA = ChatColor.DARK_AQUA.toString();
    public static final String DARK_GRAY = ChatColor.DARK_GRAY.toString();
    public static final String DARK_GREEN = ChatColor.DARK_GREEN.toString();
    public static final String DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
    public static final String DARK_RED = ChatColor.DARK_RED.toString();
    public static final String PINK = ChatColor.LIGHT_PURPLE.toString();
    public static final String UNICODE_VERTICAL_BAR = CC.GRAY + StringEscapeUtils.unescapeJava("\u2503");
    public static final String UNICODE_CAUTION = StringEscapeUtils.unescapeJava("\u26a0");
    public static final String UNICODE_ARROW_LEFT = StringEscapeUtils.unescapeJava("\u25C0");
    public static final String UNICODE_ARROW_RIGHT = StringEscapeUtils.unescapeJava("\u25B6");
    public static final String UNICODE_ARROWS_LEFT = StringEscapeUtils.unescapeJava("\u00AB");
    public static final String UNICODE_ARROWS_RIGHT = StringEscapeUtils.unescapeJava("\u00BB");
    public static final String UNICODE_HEART = StringEscapeUtils.unescapeJava("\u2764");
    public static final String MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------";
    public static final String CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------";
    public static final String SB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "----------------------";

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#[A-Fa-f0-9]{6})");
    private static final char COLOR_CHAR = ChatColor.COLOR_CHAR;

    private static final Pattern NICER_HEX_COLOR_PATTERN = Pattern.compile("&#([0-9a-fA-F]{6})");
    private final Pattern TAG_PATTERN = Pattern.compile("@([a-zA-Z0-9_]{1,16})");

    public static String translate(String string) {
        if (string == null) {
            return "null";
        }

        Matcher matcher = NICER_HEX_COLOR_PATTERN.matcher(string);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            StringBuilder replacement = new StringBuilder(14).append("&x");
            for (char character : matcher.group(1).toCharArray()) {
                replacement.append('&').append(character);
            }
            matcher.appendReplacement(sb, replacement.toString());
        }
        matcher.appendTail(sb);
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', sb.toString());
    }

    public static List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        return toReturn;
    }

    public static List<String> translate(String[] lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            if (line != null) {
                toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
            }
        }

        return toReturn;
    }
    public static String colorize(String string) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        for (Matcher matcher = pattern.matcher(string); matcher.find(); matcher = pattern.matcher(string)) {
            String color = string.substring(matcher.start(), matcher.end());
            string = string.replace(color, net.md_5.bungee.api.ChatColor.of(color) + ""); // You're missing this replacing
        }
        string = ChatColor.translateAlternateColorCodes('&', string); // Translates any & codes too
        return string;
    }

    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }


}
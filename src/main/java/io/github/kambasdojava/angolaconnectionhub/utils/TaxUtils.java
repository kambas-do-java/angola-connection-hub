package io.github.kambasdojava.angolaconnectionhub.utils;

import java.util.regex.Pattern;

public abstract class TaxUtils {
  private static final Pattern personalTaxIdPattern = Pattern.compile("\\d{9}(LA|HO|HA|BE)\\d{3}");
  public static String getProvince(String taxId) {
    if (taxId == null) {
      return null;
    }

    var matcher = personalTaxIdPattern.matcher(taxId.toUpperCase());
    if (!matcher.matches()) {
      return null;
    }

    var provinceCode = matcher.group(1);

    return switch (provinceCode) {
      case "LA" -> "LUANDA";
      case "HO" -> "HUAMBO";
      case "HA" -> "HUILA";
      case "BE" -> "BIE";
      case "ME" -> "MALANJE";
      case "BO" -> "BENGO";
      case "BA" -> "BENGUELA";
      case "KN" -> "KWANZA NORTE";
      case "KS" -> "KWANZA SUL";
      case "LS" -> "LUNDA SUL";
      case "LN" -> "LUNDA NORTE";
      default -> null;
    };
  }
}

package io.github.kambasdojava.angolaconnectionhub.utils;

import org.jspecify.annotations.NonNull;

import java.util.regex.Pattern;

public abstract class TaxUtils {
  private static final Pattern personalTaxIdPattern =
      Pattern.compile("^\\d{9}(LA|BO|BA|ZE|UE|MO|ME|HO|KN|KS|LS|LN|CE|HA|BE|CA|NE)\\d{3}$");

  public static String getProvince(@NonNull String taxId) {
    var matcher = personalTaxIdPattern.matcher(taxId.toUpperCase());
    if (!matcher.matches()) {
      return null;
    }

    var provinceCode = matcher.group(1);

    return switch (provinceCode) {
      case "LA" -> "LUANDA";
      case "UE" -> "UIGE";
      case "ZE" -> "ZAIRE";
      case "MO" -> "MOXICO";
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
      case "CE" -> "CUNENE";
      case "NE" -> "NAMIBE";
      case "CA" -> "CABINDA";
      default -> null;
    };
  }
}

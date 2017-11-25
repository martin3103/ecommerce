package sg.apptreme.e_commerce.util.common;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by martinluternainggolan on 11/4/16.
 */

public class CurrencyConverter {

    public String toRupiahFormat(int nominal) {
        String temp = NumberFormat.getNumberInstance(Locale.US).format(nominal);

        temp = temp.replace(",", ".");
        temp = "Rp " + temp;

        return temp;
    }

    public String getMonth(int position) {
        switch (position) {
            case 1:
                return "Januari";
            case 2:
                return "Februari";
            case 3:
                return "Maret";
            case 4:
                return "April";
            case 5:
                return "Mei";
            case 6:
                return "Juni";
            case 7:
                return "Juli";
            case 8:
                return "Agustus";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "November";
            case 12:
                return "Desember";
            default:
                return "";
        }
    }
}

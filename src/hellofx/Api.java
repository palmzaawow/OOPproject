package hellofx;

import java.util.HashMap;

interface Api {
    String Allurl = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-all";
    String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-by-provinces";
    public HashMap<String,String> getApiProvince(String province);
}





import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitialTask {

    public  static Map<String, String[]> countryCity = new HashMap<>();



    public static void printCities(String country){

        if(country.equals(""))
            System.out.println("Country name cannot be empty");
        if(countryCity.containsKey(country)){
            System.out.println("Country: " + country + "\nCities:" );
            for(String city : countryCity.get(country))
                System.out.println(city);
        }
        else
            System.out.println(country + " -> Country not found!");

    }

    public static String findCountry(String htmlContent){
        String[] lines = htmlContent.split("\n");
        String country = "";
        for(String line : lines)
            if(line.contains("title")){
                country = line.replaceAll("\\<.*?\\>", "");
                return country.trim();
            }
        return country;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {

        Path path = Paths.get(InitialTask.class.getClassLoader().getResource("sample.html").toURI());

        String contents = new String(Files.readAllBytes(path));

        countryCity.put("Japan", new String[]{"Nagoya", "Tokyo", "Date", "Hiroshima", "Kure"});
        countryCity.put("India", new String[]{"Mumbai", "Bangalore", "Chennai", "Pune", "Patna"});
        countryCity.put("Nepal", new String[]{"Kathamandu", "Pokhara", "Lalithpur", "Bharatpur", "Birgunj"});
        countryCity.put("Somalia", new String[]{"Cadale", "Ceel Huur", "Dhahar", "Dhamasa", "Docol"});
        countryCity.put("Vietnam", new String[]{"Can Tho", "Da Nang", "Hanoi", "Haiphong", "Ho Chi Minh City"});

        String country = findCountry(contents);
        printCities(country);

    }
}

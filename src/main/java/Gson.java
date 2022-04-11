import com.google.gson.GsonBuilder;

public class Gson {
    com.google.gson.Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
}

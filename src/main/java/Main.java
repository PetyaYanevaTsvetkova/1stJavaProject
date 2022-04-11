import DTO.CategoryDTO;
import DTO.ProductDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        ModelMapper modelMapper = new ModelMapper();

        Writer categoryWriter = new FileWriter(new File("src/main/resources/toJsonOutput/outputCategories.json"));
        Writer productWriter = new FileWriter(new File("src/main/resources/toJsonOutput/outputProducts.json"));

        List<Category> categories = new ArrayList<>();
        List<Product> products = new ArrayList<>();


        //Deserialization:
        try {
            String pathFileCategories = "src/main/resources/json/categories.json";
            String readCategory = Files.readString(Path.of(pathFileCategories));

            String pathFileProducts = "src/main/resources/json/products.json";
            String readProducts = Files.readString((Path.of(pathFileProducts)));


            CategoryDTO[] categoryDTOS = gson.fromJson(readCategory, CategoryDTO[].class);

            Arrays
                    .stream(categoryDTOS)
                    .map(categoryDTO -> modelMapper.map(categoryDTO, Category.class))
                    .forEach(categories::add);
            // .forEach(category -> System.out.println(category.toString()));

            System.out.println();

            ProductDTO[] productDTOS = gson.fromJson(readProducts, ProductDTO[].class);

            Arrays
                    .stream(productDTOS)
                    .map(productDTO -> modelMapper.map(productDTO, Product.class))
                    .forEach(products::add);
            //  .forEach(product -> System.out.println(product.toString()));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        //Serialization:
        try {
            gson.toJson(categories, categoryWriter);
            gson.toJson(products, productWriter);

            categoryWriter.close();
            productWriter.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}


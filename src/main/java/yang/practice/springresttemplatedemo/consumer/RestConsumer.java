package yang.practice.springresttemplatedemo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import yang.practice.springresttemplatedemo.model.entity.Product;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestConsumer {

    private final RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8080/api/v1/product";

    public void getProductsAsJson() {

        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);

        String productJson = response.getBody();

        System.out.println("**********");
        System.out.println("GetProductsAsJson:");
        System.out.println(productJson);
        System.out.println("**********");
    }

    public void getProductsUseForEntity() {

        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl, List.class);
        List<Product> products = response.getBody();

        System.out.println("**********");
        System.out.println("GetProductsUseForEntity:");
        System.out.println(products);
        System.out.println("**********");
    }

    public void getProductsUseForObj() {

        List<Product> products = restTemplate.getForObject(baseUrl, List.class);

        System.out.println("GetProductsUseForObj:");
        System.out.println(products);
        System.out.println("**********");
    }

    public void createProduct() {

        Product request = new Product("Airpods", "Apple", 34000, 7);
        Product response = restTemplate.postForObject(baseUrl, request, Product.class);

        System.out.println("CreateProduct:");
        System.out.println(response);
        System.out.println("**********");
    }

    public void createProductWithExchange() {

        HttpEntity<Product> request = new HttpEntity(new Product("Airpods", "Apple", 34000, 7));
        ResponseEntity<Product> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, Product.class);

        System.out.println("CreateProductWithExchange:");
        System.out.println(response);
        System.out.println("**********");
    }

    public void getProductsWithExecute() {
        final Product fetchProductRequest =
                new Product("Television", "Samsung", 1145.67, 123);

        // Set HTTP headers in the request callback
        RequestCallback requestCallback = request -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(request.getBody(),
                    fetchProductRequest);
            request.getHeaders()
                    .setAccept(Arrays.asList(
                            MediaType.APPLICATION_OCTET_STREAM,
                            MediaType.ALL));
        };

        // Processing the response
        ResponseExtractor<Void> responseExtractor = response -> {

            System.out.println("GetProductsWithExecute:");
            System.out.println(response.getBody());
            System.out.println("**********");
            return null;
        };

        restTemplate.execute(baseUrl, HttpMethod.GET, requestCallback, responseExtractor);
    }


}

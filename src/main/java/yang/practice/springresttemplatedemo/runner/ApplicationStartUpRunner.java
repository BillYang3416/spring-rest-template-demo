package yang.practice.springresttemplatedemo.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import yang.practice.springresttemplatedemo.consumer.RestConsumer;

@Component
@RequiredArgsConstructor
public class ApplicationStartUpRunner implements CommandLineRunner {

    private final RestConsumer restConsumer;

    @Override
    public void run(String... args) {

        restConsumer.getProductsUseForEntity();
        restConsumer.getProductsUseForObj();
        restConsumer.createProduct();
        restConsumer.createProductWithExchange();
        restConsumer.getProductsWithExecute();
    }
}

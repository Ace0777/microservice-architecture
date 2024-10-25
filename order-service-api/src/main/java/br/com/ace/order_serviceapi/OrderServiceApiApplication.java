package br.com.ace.order_serviceapi;

import br.com.ace.order_serviceapi.entities.Order;
import br.com.ace.order_serviceapi.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@SpringBootApplication
@RequiredArgsConstructor
public class OrderServiceApiApplication implements CommandLineRunner {

	private final OrderRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(Order.builder()
				.requesterId("123")  // Corrigido para requestId
				.customerId("456") // Corrigido para customerId
				.title("Order 1")
				.description("Order 1 description")
				.createdAt(now())
				.build());
	}
}

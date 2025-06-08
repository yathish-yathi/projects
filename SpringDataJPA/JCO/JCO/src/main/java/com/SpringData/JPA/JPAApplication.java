package com.SpringData.JPA;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.SpringData.JPA.entity.Lecture;
import com.SpringData.JPA.entity.Order;
import com.SpringData.JPA.entity.Resource;
import com.SpringData.JPA.entity.Video;
import com.SpringData.JPA.entity.embedded.Address;
import com.SpringData.JPA.entity.embedded.OrderId;
import com.SpringData.JPA.repository.LectureRepository;
import com.SpringData.JPA.repository.OrderRepository;
import com.SpringData.JPA.repository.ResourceRepository;
import com.SpringData.JPA.repository.VideoRepository;

import jakarta.annotation.PostConstruct;


@SpringBootApplication
public class JPAApplication {

	@Autowired
	LectureRepository lectureRepository;

	@Autowired
	VideoRepository videoRepository;

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(JPAApplication.class, args);

		
	}

	@PostConstruct
	public void init() {
		Lecture lecture = Lecture.builder()
				.name("Spring Data JPA")
				.createdAt(LocalDateTime.of(2021
				, 02, 01, 10, 10, 10))
				.build();

				lectureRepository.save(lecture);

		Video video = Video.builder()
		.length(120)
		.name("JPA Video")
		.build();

		//videoRepository.save(video);

		Resource resource = Resource.builder()
				.name("Resource 1")
				.size(1024)
				.url("http://example.com/resource1")
				//.lecture(lecture)
				.build();

		//resourceRepository.save(resource);
		System.out.println("printing resource2");
		Resource resource2 = resourceRepository.findById(2).get();

		System.out.println(resource2);

		System.out.println("printing video");
		System.out.println(videoRepository.findById(1).get());


		Order order = Order.builder()
				.id(OrderId.builder()
						.orderDate(LocalDateTime.of(2025, 6, 7, 0, 0, 0))
						.userName("user1")
						.build())
				.productName("iPhone 17 Pro")
				.quantity(1)
				.perm_OrderAddress(
						Address.builder()
						.houseName("House 1")
						.street("Street 1")
						.city("City 1")
						.build())
				.temp_OrderAddress(
						Address.builder()
						.houseName("Temp House 1")
						.street("Temp Street 1")
						.city("Temp City 1")
						.build())
				.build();

		System.out.println("saving order");
		//orderRepository.save(order);

		System.out.println("printing order by OrderId");
		OrderId orderId = OrderId.builder()
				.orderDate(LocalDateTime.of(2025, 6, 7, 0, 0, 0))
				.userName("user1")
				.build();
		 System.out.println(orderRepository.findById(orderId).get());

		 System.out.println("printing order by username");
		 System.out.println(orderRepository.findByIdUserName("user1"));
	}
}

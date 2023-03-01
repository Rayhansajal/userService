package com.user.service.userService;

import com.user.service.userService.entity.Ratings;
import com.user.service.userService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating() {
//
//		Ratings ratings = Ratings.builder().rating(10).userId("").hotelId("").feedback("This is created using fieng").build();
//		ratingService.createRating(ratings)
//		System.out.println("New rating created");
//	}

	}

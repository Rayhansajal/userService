package com.user.service.userService.services.impl;


import com.user.service.userService.entity.Hotel;
import com.user.service.userService.entity.Ratings;
import com.user.service.userService.entity.User;
import com.user.service.userService.exception.ResourceNotfoundException;
import com.user.service.userService.external.services.HotelServices;
import com.user.service.userService.repositories.UserRepository;
import com.user.service.userService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelServices hotelServices;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public User saveUser(User user) {
        // generate uniq userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
// get single user
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotfoundException("user not fonund with given id" + userId));

//        http://localhost:8083/ratings/users/8a7479a9-f7b9-4593-aa04-538a2376fdb3

       Ratings[] ratingsofUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Ratings[].class);
        logger.info("{} ",ratingsofUser);
        List<Ratings> rating = Arrays.stream(ratingsofUser).toList();

        List<Ratings> ratingsList = rating.stream().map(ratings -> {

//            http://localhost:8082/hotels/9adabcae-5988-47fe-bbc9-188abf264b65
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ratings.getHotelId(), Hotel.class);
            Hotel hotel = hotelServices.gethotel(ratings.getHotelId());
//            logger.info("response entity code:{}",forEntity.getStatusCode());
            ratings.setHotel(hotel);

            return ratings;

        }).collect(Collectors.toList());

        user.setRatings(ratingsList);

        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(String userId) {
        return null;
    }



}

package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.wishlist.dto.WishListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    WishListService wishListService;

    @Test
    public void naverServiceTest() {

        WishListDto result = wishListService.search("갈비집");

        Assertions.assertNotNull(result);

        System.out.println("==============================\n");
        System.out.println(result);
        System.out.println("\n==============================");
    }
}

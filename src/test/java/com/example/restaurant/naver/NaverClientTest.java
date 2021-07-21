package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImgReq;
import com.example.restaurant.naver.dto.SearchImgRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void localSearchTest() {
        SearchLocalReq req = new SearchLocalReq();
        req.setQuery("중국집");

        System.out.println("==============================\n");
        System.out.println(req.toMutiMultiValueMap());
        System.out.println("\n==============================");

        SearchLocalRes searchLocalRes = naverClient.localSearch(req);

        System.out.println("==============================\n");
        System.out.println("result ::: " + searchLocalRes);
        System.out.println("\n==============================");
    }

    @Test
    public void multiValueTest() {
        SearchImgReq req = new SearchImgReq();
        req.setQuery("중국집");

        SearchImgRes searchLocalRes = naverClient.imageSearch(req);

        System.out.println("==============================\n");
        System.out.println("result ::: " + searchLocalRes);
        System.out.println("\n==============================");
    }


}
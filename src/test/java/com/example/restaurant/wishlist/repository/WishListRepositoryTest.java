package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    WishListRepository wishListRepository;

    public WishListEntity createEntity() {
        WishListEntity entity = new WishListEntity();
        entity.setAddress("address");
        entity.setReadAddress("readAdress");
        entity.setCategory("category");
        entity.setHomepageLink("homepageLink");
        entity.setImageLink("imageLink");
        entity.setTitle("title");
        entity.setVisit(false);
        entity.setVisitCount(0);
        return entity;
    }

    @Test
    public void saveTest() {
        var entity = createEntity();
        wishListRepository.save(entity);

        Optional<WishListEntity> byId = wishListRepository.findById(0);
        System.out.println("====================================\n");
        byId.ifPresent(temp -> System.out.println(temp.getTitle()));
        System.out.println("\n====================================");

        Assertions.assertNotNull(entity);
    }

    @Test
    public void findByIdTest() {
        var entity = createEntity();
        wishListRepository.save(entity);

        System.out.println("====================================\n");
        wishListRepository.findById(1).ifPresent(obj -> System.out.println(obj.getTitle()));
        System.out.println("\n====================================\n");
    }

    @Test
    public void deleteTest() {
        var entity = createEntity();
        wishListRepository.save(entity);


        System.out.println("dfhashdkfsdasdfas\n" + wishListRepository.findAll().size() + "\n================");

        wishListRepository.deleteById(1);

        Assertions.assertEquals(0 , wishListRepository.findAll().size());
    }

    @Test
    public void findAll() {
        var entity = createEntity();
        var entity2 = createEntity();

        wishListRepository.save(entity);
        wishListRepository.save(entity2);

        Assertions.assertEquals(2 , wishListRepository.findAll().size());
    }
}

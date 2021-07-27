package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImgReq;
import com.example.restaurant.naver.dto.SearchImgRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query) {
        SearchLocalReq localReq = new SearchLocalReq();
        localReq.setQuery(query);

        // 지역 검색
        SearchLocalRes resultLocalRes = naverClient.localSearch(localReq);
        if (resultLocalRes.getTotal() > 0) {
            var searchLocalItem = resultLocalRes.getItems().stream().findFirst().get();

            var imageQuery = searchLocalItem.getTitle().replaceAll("<[^>]*>", "");
            var searchImgReq = new SearchImgReq();
            searchImgReq.setQuery(imageQuery);

            // 이미지 검색
            SearchImgRes searchImgRes = naverClient.imageSearch(searchImgReq);

            if (searchImgRes.getTotal() > 0) {
                var searchImgItem = searchImgRes.getItems().stream().findFirst().get();

                var wishListDto = new WishListDto();
                wishListDto.setAddress(searchLocalItem.getAddress());
                wishListDto.setCategory(searchLocalItem.getCategory());
                wishListDto.setHomepageLink(searchLocalItem.getLink());
                wishListDto.setImageLink(searchImgItem.getLink());
//                wishListDto.setIndex();
                wishListDto.setTitle(searchLocalItem.getTitle());
                wishListDto.setReadAddress(searchLocalItem.getRoadAddress());

                return wishListDto;
            }
        }
        return new WishListDto();
    }

    public WishListDto entityToDto(WishListEntity entity) {
        var dto = new WishListDto();
        dto.setIndex(entity.getIndex());
        dto.setTitle(entity.getTitle());
        dto.setReadAddress(entity.getReadAddress());
        dto.setImageLink(entity.getImageLink());
        dto.setHomepageLink(entity.getHomepageLink());
        dto.setAddress(entity.getAddress());
        dto.setVisit(entity.isVisit());
        dto.setVisitCount(entity.getVisitCount());
        dto.setLastVisitDate(entity.getLastVisitDate());
        dto.setCategory(entity.getCategory());

        return dto;
    }

    public WishListEntity dtoToEntity(WishListDto dto) {
        var entity = new WishListEntity();
        entity.setAddress(dto.getAddress());
        entity.setTitle(dto.getTitle());
        entity.setImageLink(dto.getImageLink());
        entity.setHomepageLink(dto.getHomepageLink());
        entity.setReadAddress(dto.getReadAddress());
        entity.setIndex(dto.getIndex());
        entity.setVisitCount(dto.getVisitCount());
        entity.setCategory(dto.getCategory());
        entity.setLastVisitDate(dto.getLastVisitDate());
        return entity;
    }

    public void addVisit(Integer index) {
        Optional<WishListEntity> entity = wishListRepository.findById(index);

        if(entity.isPresent()) {
            WishListEntity wishListEntity = entity.get();
            wishListEntity.setVisit(true);
            wishListEntity.setVisitCount(wishListEntity.getVisitCount() + 1);
            wishListEntity.setLastVisitDate(LocalDateTime.now());

            wishListRepository.save(wishListEntity);
        }
    }

    public void deleteById(Integer index) {
        Optional<WishListEntity> entity = wishListRepository.findById(index);

        if(entity.isPresent()) {
            wishListRepository.deleteById(index);
        }
    }
}

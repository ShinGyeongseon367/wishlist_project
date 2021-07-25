package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImgReq;
import com.example.restaurant.naver.dto.SearchImgRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import com.example.restaurant.wishlist.dto.WishListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

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

}

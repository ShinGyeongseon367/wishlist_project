package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImgRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchImgItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImgItem {
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}

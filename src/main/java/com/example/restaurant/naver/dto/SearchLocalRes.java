package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchLocalRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchLocalItem> items;

    /**
     * naver API를 보게되면 items에는 내부 데이터들이 포함되어서 노출이 된다고 한다. 내부 데이터이기 때문에 내부
     * 클래스를 만들어 표현할 수 있도록 해준다.
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchLocalItem{
        private String title;
        private String link;
        private String description;
        private String telephone;
        private String address;
        private String category;
        private String roadAddress;
        private String mapx;
        private String mapy;
    }
}


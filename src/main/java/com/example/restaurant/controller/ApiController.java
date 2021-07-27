package com.example.restaurant.controller;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.service.WishListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "음식점 검색 API")
@RestController
@RequestMapping("/api/restaurant")
@Data
@RequiredArgsConstructor
public class ApiController {

    private final WishListService wishListService;
    private final MemoryDbRepositoryAbstract<WishListEntity> db;

    @ApiOperation(value = "음식점 검색기능")
    @GetMapping("/search")
    public WishListDto search(
            @RequestParam String query) {
        WishListDto searchResult = wishListService.search(query);
        return searchResult;
    }

    @ApiOperation(value = "음식점 저장")
    @PostMapping("/add")
    public WishListEntity add (@RequestBody WishListDto dto) {
        WishListEntity wishListEntity = wishListService.dtoToEntity(dto);
        WishListEntity save = db.save(wishListEntity);
        return save;
    }

    @ApiOperation(value = "전체 데이터 호출")
    @GetMapping("/all")
    public List<WishListDto> findAll() {
        return db.findAll().stream()
                .map(it -> wishListService.entityToDto(it))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable Integer index) {
        log.info("delete ::: {}", index);
        wishListService.deleteById(index);
    }

    @PostMapping("/{index}")
    public void visit(@PathVariable Integer index) {
        log.info("update visit index ::: {}", index);
        wishListService.addVisit(index);
    }

}

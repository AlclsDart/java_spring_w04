package com.sparta.w04.controller;

import com.sparta.w04.models.ItemDto;
import com.sparta.w04.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // final 로 선언된 클래스를 자동으로 생성합니다.
@RestController // JSON으로 응답함을 선언합니다.
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> getItems(@RequestParam String query) {
        String resultString = naverShopSearch.search(query);
//        System.out.println("debug1 : " + resultString);
//        System.out.println("debug2 : "+ naverShopSearch.fromJSONtoItems(resultString));
        return naverShopSearch.fromJSONtoItems(resultString);
    }
}
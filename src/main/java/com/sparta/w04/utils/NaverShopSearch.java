package com.sparta.w04.utils;
import com.sparta.w04.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component // 이제부터, @RequiredArgsConstructor 와 함께 사용할 경우 스프링이 자동으로 생성합니다.
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "w0qWy9aHJ1NkzQKL_Jnc");
        headers.add("X-Naver-Client-Secret", "U5AN19IzPQ");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatusCode httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
//        System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result){
        JSONObject rjson = new JSONObject(result);
//        System.out.println("fromJSONtoItems-result : " + rjson);

        JSONArray items = rjson.getJSONArray("items");

        List<ItemDto> itemDtoList = new ArrayList<>();
//        System.out.println("items.length : " + items.length());
        for (int i=0; i<items.length(); i++) {
//            JSONObject itemJson = (JSONObject) items.get(i);
//            System.out.println(itemJson);
            JSONObject itemJson = items.getJSONObject(i);
            String title = itemJson.getString("title");
            String image = itemJson.getString("image");
            int lprice = itemJson.getInt("lprice");
            String link = itemJson.getString("link");

            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;
    }

//    public static void main(String[] args) {
//        NaverShopSearch naverShopSearch = new NaverShopSearch();
//        String result = naverShopSearch.search("아이맥");
//
//
//    }
}

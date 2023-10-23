//package com.dxvalley.creditscoring.controllers;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.dxvalley.creditscoring.models.response.ApiResponse;
//import com.google.gson.Gson;
//
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/api/v1/score")
//@RequiredArgsConstructor
//public class ScoreController {
//
//    @GetMapping("/{accountNumber}")
//    public ResponseEntity<?> getCustomerScore(@PathVariable String accountNumber) {
//
//        try {
//
//            // reach out to transactions api using account number
//            String uri = "http://mekebbede.pythonanywhere.com/predict";
//
//            String transactions = "{\"numerical\":{\"2022-06-24\":23,\"2022-06-25\":0,\"2022-06-27\":321,\"2022-06-28\":89,\"2022-06-29\":90,\"2022-06-30\":98,\"2022-07-01\":0,\"2022-07-02\":0,\"2022-07-04\":0,\"2022-07-05\":0,\"2022-07-06\":0,\"2022-07-07\":0,\"2022-07-08\":0,\"2022-07-11\":0,\"2022-07-12\":0,\"2022-07-13\":0,\"2022-07-14\":897,\"2022-07-15\":0,\"2022-07-16\":0,\"2022-07-18\":0,\"2022-07-19\":0,\"2022-07-20\":0,\"2022-07-21\":0,\"2022-07-22\":0,\"2022-07-23\":0,\"2022-07-25\":4654,\"2022-07-26\":0,\"2022-07-27\":0,\"2022-07-28\":0,\"2022-07-29\":0,\"2022-07-30\":0,\"2022-08-01\":0,\"2022-08-02\":0,\"2022-08-03\":7754,\"2022-08-04\":0,\"2022-08-05\":0,\"2022-08-06\":0,\"2022-08-08\":0,\"2022-08-09\":0,\"2022-08-10\":0,\"2022-08-11\":0,\"2022-08-12\":0,\"2022-08-13\":0,\"2022-08-15\":0,\"2022-08-16\":0,\"2022-08-17\":0,\"2022-08-18\":0,\"2022-08-19\":0,\"2022-08-20\":0,\"2022-08-22\":1110,\"2022-08-23\":0,\"2022-08-24\":0,\"2022-08-25\":0,\"2022-08-26\":0,\"2022-08-27\":0,\"2022-08-29\":9065,\"2022-08-30\":0,\"2022-08-31\":0,\"2022-09-01\":0,\"2022-09-02\":0,\"2022-09-03\":0,\"2022-09-05\":0,\"2022-09-06\":0,\"2022-09-07\":0,\"2022-09-08\":0,\"2022-09-09\":0,\"2022-09-10\":9665,\"2022-09-12\":0,\"2022-09-13\":0,\"2022-09-14\":0,\"2022-09-15\":0,\"2022-09-16\":0,\"2022-09-17\":0,\"2022-09-19\":0,\"2022-09-20\":0,\"2022-09-21\":0,\"2022-09-22\":4345,\"2022-09-23\":0,\"2022-09-24\":0,\"AGE\":67,\"Av_monthly_income\":897},\"categorical\":{\"GENDER\":\"MALE\",\"Mar_status\":\"single\",\"Education_level\":\"bsc\",\"Occupation\":\"farmer\"}}";
//            // call models api and give it transactions
//
//            // return score from models api
//            // int random = (int)(Math.random() * 10 + 1);
//
//            // return new ResponseEntity<>(random, HttpStatus.OK);
//
//            RestTemplate restTemplate = new RestTemplate();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<String> request = new HttpEntity<String>(transactions, headers);
//
//            ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.POST, request,
//                    String.class);
//
//            Gson g = new Gson();
//
//            return new ResponseEntity<>(res.getBody(), HttpStatus.OK);
//        } catch (Exception e) {
//            ApiResponse<?> response = new ApiResponse<>(500, " Can't find your creditworthness!", null);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }
//
//}

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
//@RequestMapping("/api/v2/score")
//@RequiredArgsConstructor
//public class ScoreControllerLatest {
//
//    @GetMapping("/{accountNumber}")
//    public ResponseEntity<?> getCustomerScore(@PathVariable String accountNumber){
//
//
//        try {
//
//            // reach out to transactions api using account number
//        String uri = "http://127.0.0.1:5000/result";
//
//        String transactions = "{\"total_0\":\"1.23782710e+05\",\"average_0\":\"1.23782710e+04\",\"d3\":\"1.00000000e+01\",\"sf\":\"1.11622310e+05\",\"sgf\":\"1.11622310e+04\",\"er\":\"1.00000000e+01\",\"hgf\":\"1.01232290e+05\",\"jfgd\":\"1.01232290e+04\",\"hfhg\":\"1.00000000e+01\",\"wwww\":\"1.30410250e+05\",\"kgg\":\"1.30410250e+04\",\"urter\":\"1.00000000e+01\",\"fhff\":\"8.74611400e+04\",\"eeaeh\":\"8.74611400e+03\",\"hjh\":\"1.00000000e+01\",\"ghert\":\"1.80929350e+05\",\"ffff\":\"1.80929350e+04\",\"eeer\":\"1.00000000e+01\",\"jgtyr\":\"1.32594920e+05\",\"jtyr\":\"1.32594920e+04\",\"hhhh\":\"1.00000000e+01\",\"eeeh\":\"7.92756700e+04\",\"mggh\":\"8.80840778e+03\",\"fhtr\":\"9.00000000e+00\"}";
//        // call models api and give it transactions
//
//        // return score from models api
//        // int random = (int)(Math.random() * 10 + 1);
//
//        // return new ResponseEntity<>(random, HttpStatus.OK);
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

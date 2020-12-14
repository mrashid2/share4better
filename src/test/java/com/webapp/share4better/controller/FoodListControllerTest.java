package com.webapp.share4better.controller;

import com.webapp.share4better.model.*;
import com.webapp.share4better.repository.IFoodRepository;
import com.webapp.share4better.service.IFoodService;
import com.webapp.share4better.service.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.service.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
public class FoodListControllerTest {

    @Autowired
    private FoodListController foodListController;

    @Autowired
    private IFoodRepository foodRepository;

    @Autowired
    private TestUtil testUtil;

    @BeforeEach
    public void setUpStart() {
        testUtil.addFoodListTestData();

    }

    @AfterEach
    public void setUpEnd(){
        testUtil.removeFoodListTestData();
    }


    @Test
    public void contributeFood() throws IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9992);
        foodListController.contributeFood("cookies", "snack", "fresh", "20", mockRequest,  mockResponse);
        Optional<Food> retriveFoodFromDb = foodRepository.findById(9992);
        assertTrue("is Present in DB ",retriveFoodFromDb.isPresent());
        assertEquals("Food Name: " , "cookies", retriveFoodFromDb.get().getName());

        //TODO:finish assertEquals for contributed food
        foodRepository.delete(retriveFoodFromDb.get());
    }

    @Test
    public void getAllContributedFood() {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999);
        List<ReceiverFoodList> foodList = foodListController.getAllContributedFoods(mockRequest).getBody();

        assertEquals("First Food Item : " + foodList.get(0).getName(), 111111111, foodList.get(0).getId());
        assertEquals("First Food Item : " + foodList.get(0).getName(), 9999, foodList.get(0).getContributorID());
        assertEquals("First Food Item : " + foodList.get(0).getName(), 8888, foodList.get(0).getReceiverID());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "bagels", foodList.get(0).getName());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "bread", foodList.get(0).getType());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "10", foodList.get(0).getQuantity());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "fresh", foodList.get(0).getQuality());

        assertEquals("Second Food Item : " + foodList.get(1).getName(), 111111112, foodList.get(1).getId());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), 9991, foodList.get(1).getContributorID());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), 8881, foodList.get(1).getReceiverID());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "squash", foodList.get(1).getName());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "vegetable", foodList.get(1).getType());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "5", foodList.get(1).getQuantity());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "good", foodList.get(1).getQuality());


    }

    @Test
    public void getMyPendingFoods() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999);
        List<ReceiverFoodList> foodList = foodListController.getMyPendingFoods(mockRequest).getBody();

        assertEquals("First Food Item : " + foodList.get(0).getName(), 111111111, foodList.get(0).getId());
        assertEquals("First Food Item : " + foodList.get(0).getName(), 9999, foodList.get(0).getContributorID());
        assertEquals("First Food Item : " + foodList.get(0).getName(), 8888, foodList.get(0).getReceiverID());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "bagels", foodList.get(0).getName());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "bread", foodList.get(0).getType());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "10", foodList.get(0).getQuantity());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "fresh", foodList.get(0).getQuality());

        assertEquals("Second Food Item : " + foodList.get(1).getName(), 111111112, foodList.get(1).getId());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), 9991, foodList.get(1).getContributorID());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), 8881, foodList.get(1).getReceiverID());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "squash", foodList.get(1).getName());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "vegetable", foodList.get(1).getType());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "5", foodList.get(1).getQuantity());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "good", foodList.get(1).getQuality());


    }

    @Test
    public void requestFoodBooking() {

    }

    @Test
    public void removeBooking () {

    }

    @Test
    public void getAllReceivedFood () {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999);
        List<ReceiverFoodList> foodList = foodListController.getAllReceivedFood(mockRequest).getBody();

        assertEquals("First Food Item : " + foodList.get(0).getName(), 111111111, foodList.get(0).getId());
        assertEquals("First Food Item : " + foodList.get(0).getName(), 9999, foodList.get(0).getContributorID());
        assertEquals("First Food Item : " + foodList.get(0).getName(), 8888, foodList.get(0).getReceiverID());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "bagels", foodList.get(0).getName());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "bread", foodList.get(0).getType());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "10", foodList.get(0).getQuantity());
        assertEquals("First Food Item : " + foodList.get(0).getName(), "fresh", foodList.get(0).getQuality());

        assertEquals("Second Food Item : " + foodList.get(1).getName(), 111111112, foodList.get(1).getId());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), 9991, foodList.get(1).getContributorID());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), 8881, foodList.get(1).getReceiverID());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "squash", foodList.get(1).getName());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "vegetable", foodList.get(1).getType());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "5", foodList.get(1).getQuantity());
        assertEquals("Second Food Item : " + foodList.get(1).getName(), "good", foodList.get(1).getQuality());


    }

}

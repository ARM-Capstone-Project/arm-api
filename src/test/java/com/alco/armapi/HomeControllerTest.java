package com.alco.armapi;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.alco.armapi.infrastructure.adapter.api.HomeController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class HomeControllerTest {
    @InjectMocks
    private HomeController homeController;

    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = mock(Model.class); // Create a mock for the Model
    }

    @Test
    void homePage_ShouldAddAppNameAndReturnHomeView() {
        String viewName = homeController.homePage(model);

        // verify model.addAttribute("appName", "MyApp");
        verify(model, times(1)).addAttribute("appName", "MyApp");

        //return home
        assertEquals("home", viewName);
    }
}

package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Disabled;

@Disabled("Servlet test requires database - skipped for unit testing")
class LoginServletTest {



    @Test
    void doPost_ShouldLoginUserSuccessfully() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        UserService userService = mock(UserService.class);

        LoginServlet servlet = new LoginServlet();

        // inject mock service
        java.lang.reflect.Field field =
                LoginServlet.class.getDeclaredField("userService");
        field.setAccessible(true);
        field.set(servlet, userService);

        // mock login input
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("123");

        when(request.getSession()).thenReturn(session);

        // fake user
        User user = User.builder()
                .username("admin")
                .password("123")
                .role("ADMIN")
                .build();

        when(userService.login("admin","123")).thenReturn(user);

        // run servlet
        servlet.doPost(request, response);

        // verify session created
        verify(session).setAttribute("userId", user.getUserId());
        verify(session).setAttribute("role", user.getRole());

        // verify redirect
        verify(response).sendRedirect(contains("dashboard"));
    }
}
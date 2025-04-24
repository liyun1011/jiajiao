

import org.springframework.web.servlet.DispatcherServlet;

public class springMVCTest {
    public static void main(String[] args) {
        // 尝试实例化 DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        System.out.println("Spring MVC is correctly configured!");
    }
}
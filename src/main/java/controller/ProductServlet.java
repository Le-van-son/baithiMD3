package controller;

import model.Product;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "search":
                formSearch(request,response);
                break;
            case "searchprice":
                searchByPrice(request,response);
                break;
            default:
                showList(request,response);
        }
    }

    private void searchByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int begin = Integer.parseInt(request.getParameter("begin"));
        int end = Integer.parseInt(request.getParameter("end"));
        List<Product> products = productService.findByPrice(begin, end);
        request.setAttribute("products", products);
        request.getRequestDispatcher("product/searchprice.jsp").forward(request, response);
    }

    private void formSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/search.jsp");
        String name = request.getParameter("name");
        List<Product>productList = productService.findByName(name);
        request.setAttribute("products",productList);
        requestDispatcher.forward(request,response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        List<Product>productList = productService.findAll();
        request.setAttribute("products",productList);
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null){
//            action = "";
//        }
//        switch (action){
//            case "search":
//                formSearch(request,response);
//                break;
//        }
    }
}

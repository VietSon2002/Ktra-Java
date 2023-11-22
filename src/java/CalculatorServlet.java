import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calculator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Máy tính đơn giản</h1>");
            out.println("<form action='CalculatorServlet' method='post'>");
            out.println("Số 1: <input type='text' name='num1'><br><br>");
            out.println("Số 2: <input type='text' name='num2'><br><br>");
            out.println("<input type='submit' name='operation' value='Tổng'>");
            out.println("<input type='submit' name='operation' value='Hiệu'>");
            out.println("<input type='submit' name='operation' value='Tích'>");
            out.println("<input type='submit' name='operation' value='Thương'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        if (num1Str == null || num2Str == null || num1Str.isEmpty() || num2Str.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Vui lòng nhập giá trị cho 2 số !!!");
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);

            double result = 0;
            String operation = request.getParameter("operation");

            switch (operation) {
                case "Tổng":
                    result = num1 + num2;
                    break;
                case "Hiệu":
                    result = num1 - num2;
                    break;
                case "Tích":
                    result = num1 * num2;
                    break;
                case "Thương":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không thể chia một số cho 0 !!!.");
                        return;
                    }
                    break;
            }

            // Lưu kết quả vào request để truyền sang trang kết quả
            request.setAttribute("result", result);

            // Chuyển hướng đến trang kết quả
            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input. Please enter valid numbers.");
        }
    }
}

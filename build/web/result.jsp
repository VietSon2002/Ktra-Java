<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator Result</title>
</head>
<body>
    <h1>Kết quả: ${result}</h1>
    <form action="CalculatorServlet" method="get">
        <input type="submit" value="Back">
    </form>
</body>
</html>
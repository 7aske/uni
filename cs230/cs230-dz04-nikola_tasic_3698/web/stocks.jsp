<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stocks</title>
    <jsp:include page="include/materialize.jsp"/>
</head>
<body>
<div class="container center">
    <jsp:include page="include/stockSelect.jsp"/>
    <%
        String stocks = request.getParameter("stocks");
        String count = request.getParameter("count");

        if (stocks == null) {
            out.print("<span>Invalid stock company</span><br>");
        }

        if (count == null) {
            out.print("<span>Invalid stock count</span><br>");
        }

        try {
            out.print(String.format("<h6>Stocks price: %.2f</h6>", beans.stocks.StocksCalculator.getStocksPrice(stocks, Integer.parseInt(count))));
        } catch (NumberFormatException e) {
            out.print("<span>Invalid stock count</span><br>");
        }
    %>
</div>
</body>
</html>

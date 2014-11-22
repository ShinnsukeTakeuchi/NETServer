<%@ page contentType="text/html; charset=utf-8" import="java.sql.*" %>

<html>
<head>
<title>Test</title>
</head>

<body>
<h1>Test</h1>

<tr>
<td>ID.</td><td>Owner</td><td>Pair</td>
</tr>

<%
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/net_db?" +
        "user=netuser&password=netpass&useUnicode=true&characterEncoding=utf-8");
    Statement st=conn.createStatement();
    ResultSet res = st.executeQuery("select * from one_to_one");

    while(res.next()){
        out.println("<tr>");
        out.println("<td>" + res.getString("id") + "</td>");
        out.println("<td>" + res.getString("owner_user") + "</td>");
        out.println("<td>" + res.getString("pair_user") + "</td>");
        out.println("</tr>");
    }
    st.close();
    conn.close();

%>
</table>
</body>
</html>
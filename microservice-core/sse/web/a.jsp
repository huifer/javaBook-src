<%--
  Created by IntelliJ IDEA.
  User: huifer
  Date: 2019/6/2
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>


</head>


<script>
    console.log("afaf");
    // 创建 eventSource
    var eventSource = new EventSource("sse/default");
    eventSource.onmessage = function (evt) {

        console.log(evt.data);

        document.getElementById("J_List").innerHTML += "<li>" + evt.data + "</li>";


    };
</script>
<body>
<ul id="J_List">
</ul>
</body>

</html>

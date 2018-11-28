<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="welcome"/>
    <title>登录</title>
</head>

<body>
<div class="welcomePanel">
    <div class="title">
        ${cn.edu.cup.system.SystemTitle.last()?.applicationTitle}
    </div>

    <form controller="home" action="login" method="post">
        <div class="welcomeForm">
            <ul>
                <li>
                    <label>用户名</label>
                    <g:textField name="userName" class="name"/>
                </li>
                <li>
                    <label>密&nbsp;&nbsp;&nbsp;码</label>
                    <g:field type="password" name="password" class="psw"/>
                </li>
            </ul>
            <ul>
                <li style="padding-left: 100px">
                    <button type="submit" class="btn">提交</button>
                    <button type="reset" class="btn">重置</button>
                </li>
            </ul>
        </div>
    </form>
</div>
</body>
</html>

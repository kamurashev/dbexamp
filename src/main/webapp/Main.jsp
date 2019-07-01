<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Аудиокниги скачать бесплатно mp3 - ReedManiac</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Main.css"/>
</head>

<body>
<header>
    <div class="container">
        <h1 class="m_heading">ReadManiac</h1>
        <h2>Всегда свежие книги на любой вкус, ежедневное пополнение новинками</h2>
    </div>
</header>
<section>
    <c:set var="manage" value="${sessionScope.supervisor==null?false:true}"/>
    <div class="container">
        <div style="display: ${manage?"block":"none"}">
            <p class="message">${param.message}</p>
            <p class="admin">Logged in as ${sessionScope.supervisor.login} - ${sessionScope.supervisor.role}</p>
        </div>
        <p class="message">${param.searchfail}</p>
        <div class="items_cont">
            <c:forEach var="book" items="${requestScope.books}">
                <div class="item_cont">
                    <div class="cov_short">
                        <a href="showfull?id=${book.book_id}" title="Аудиокнига ${book.name}">
                            <img src="${pageContext.request.contextPath}${book.cover}"
                                 alt="Аудиокнига ${book.name}"> </a>
                    </div>
                    <div class="short">
                        <h2>${book.name}</h2>
                        <h4 style="display: ${manage?"block":"none"}">id = ${book.book_id}</h4>
                        <ul class="info">
                            <li>
                                Автор: <a href="?author=${book.author}">${book.author}</a>
                            </li>
                            <li>
                                Читает: <a href="?speaker=${book.speaker}">${book.speaker}</a>
                            </li>
                            <li>
                                Жанр: <a href="?genre=${book.genre}">${book.genre}</a>
                            </li>
                            <li>
                                <p>Длительность: ${book.duration}</p>
                            </li>
                        </ul>
                        <div class="s_description">
                            <p>
                                    ${book.description}...
                            </p>
                        </div>
                        <div class="views">
                            <img src="img/views.png" alt="views">
                            <p>${book.views}</p>
                        </div>
                        <div class="button">
                            <a href="showfull?id=${book.book_id}">Подробнее</a>
                        </div>
                        <div style="display: ${manage?"block":"none"}">
                            <form action="delete" method="post">
                                <input type="hidden" name="book_id" value="${book.book_id}">
                                <input class="button" type="submit" value="удалить">
                            </form>
                            <form action="add-update" method="get">
                                <input type="hidden" name="book_id" value="${book.book_id}">
                                <input type="hidden" name="update">
                                <input class="button" type="submit" value="изменить">
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="menu">
            <form method="get">
                <h3 class="menu_headings_font">Поиск</h3>
                <select name="genre">
                    <option selected disabled>Жанр</option>
                    <option>Фэнтези</option>
                    <option>Историческое</option>
                    <option>Научное</option>
                    <option>Научная фантастика</option>
                    <option>Детектив</option>
                    <option>Драма</option>
                    <option>Жизнь</option>
                </select>
                <input type="text" name="author" placeholder="Автор">
                <input type="text" name="speaker" placeholder="Читает">
                <input type="text" name="name" placeholder="Название">
                <p class="menu_headings_font">Сортировать по</p>
                <select name="order">
                    <option selected value="book_id">Дате добавления на сайт</option>
                    <option value="views">Просмотрам</option>
                    <option value="rate">Рейтингу</option>
                </select>
                <input type="reset" value="Очистить">
                <input type="submit" value="Поиск">
            </form>
        </div>
        <div class="n/b">
            <form>
                <input type="hidden" name="genre" value=${requestScope.genre}>
                <input type="hidden" name="author" value=${requestScope.author}>
                <input type="hidden" name="speaker" value=${requestScope.speaker}>
                <input type="hidden" name="name" value=${requestScope.name}>
                <input type="hidden" name="order" value=${requestScope.order}>
                <button class="button2" type="submit" name="page_n"
                        value="${requestScope.page_n <= 1 ? 1 : requestScope.page_n-1}">back
                </button>
                <div class="page_n"><b>${requestScope.page_n}</b></div>
                <button class="button2" type="submit" name="page_n" value="${requestScope.page_n+1}">next</button>
            </form>
        </div>
    </div>
</section>
<footer>
    <div class="container">
    </div>
</footer>


</body>
</html>


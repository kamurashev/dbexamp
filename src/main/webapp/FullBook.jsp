<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Аудиокнига - ${requestScope.book.name} - ReedManiac</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FullBook.css"/>
</head>

<body>
<header>
    <div class="container">
        <h1 class="m_heading">ReadManiac</h1>
        <h2>Всегда свежие книги на любой вкус, ежедневное пополнение новинками</h2>
    </div>
</header>
<section>
    <div class="container">
        <div class="items_cont">
            <div class="item_cont">
                <div class="cov">
                    <a href="#" title="Аудиокнига ${requestScope.book.name}">
                        <img src="${pageContext.request.contextPath}${requestScope.book.cover}"
                             alt="Аудиокнига - ${requestScope.book.name}"> </a>
                </div>
                <div class="full">
                    <h2>${requestScope.book.name}</h2>
                    <ul class="info">
                        <li>
                            Автор: <a
                                href="${pageContext.request.contextPath}/?author=${requestScope.book.author}">${requestScope.book.author}</a>
                        </li>
                        <li>
                            Читает: <a
                                href="${pageContext.request.contextPath}/?speaker=${requestScope.book.speaker}">${requestScope.book.speaker}</a>
                        </li>
                        <li>
                            Жанр: <a
                                href="${pageContext.request.contextPath}/?genre=${requestScope.book.genre}">${requestScope.book.genre}</a>
                        </li>
                        <li>
                            <p>Длительность: ${requestScope.book.duration}</p>
                        </li>
                    </ul>
                    <div class="description">
                        <p>
                            ${requestScope.book.description}
                        </p>
                    </div>
                    <div class="b">
                        <a href="${pageContext.request.contextPath}${requestScope.book.content}">
                            <div class="button">Download</div>
                        </a>
                    </div>
                </div>
            </div>

        </div>
        <div class="menu">
            <form action="${pageContext.request.contextPath}/" method="get">
                <h3 class="menu_headings_font">Поиск:</h3>
                <select name="genre">
                    <option selected disabled>Жанр</option>
                    <option>Фэнтези</option>
                    <option>Историческое</option>
                    <option>Научное</option>
                    <option>Научная фантастика</option>
                    <option>Детектив</option>
                    <option>Драма</option>
                </select>
                <input type="text" name="author" placeholder="Автор">
                <input type="text" name="speaker" placeholder="Читает">
                <input type="text" name="name" placeholder="Название">
                <p class="menu_headings_font">Сортировать по:</p>
                <select name="order">
                    <option selected value="book_id">Дате добавления на сайт</option>
                    <option value="views">Просмотрам</option>
                    <option value="rate">Рейтингу</option>
                </select>
                <input type="reset" value="Очистить">
                <input type="submit" value="Поиск">
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


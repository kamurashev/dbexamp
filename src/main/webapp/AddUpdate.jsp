<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration - ${param.update!=null?"update":"upload new book"}</title>
</head>
<body>
 <form method="post" enctype="multipart/form-data" accept-charset="UTF-8">
     <p>Enter book name</p>
     <input type="text" name="name" value="${requestScope.book.name}">
     <p>Enter genre</p>
     <select name="genre">
         <option selected>${requestScope.book.genre}</option>
         <option>Фэнтези</option>
         <option>Историческое</option>
         <option>Научное</option>
         <option>Научная фантастика</option>
         <option>Детектив</option>
         <option>Драма</option>
         <option>Жизнь</option>
     </select>
     <p>Enter author full name</p>
     <input type="text" name="author" value="${requestScope.book.author}">
     <P>Enter speaker full name</P>
     <input type="text" name="speaker" value="${requestScope.book.speaker}">
     <p>Enter total recording duration</p>
     <input type="time" name="duration" value="${requestScope.book.duration}">
     <p>Enter description</p>
     <input type="text" name="description" style="height: 100px; width: 250px" value="${requestScope.book.description}">
     <p>Select cover pic file</p>
     <input type="file" name="cover">
     <P>Select content mp3 file</P>
     <input type="file" name="content">
     <input type="reset" value="reset">
     <input type="submit" value="save">
 </form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="contentWrapper">
  <div class="col-md-6 col-md-offset-3">
    <div id="content">
      <form action="${pageContext.request.contextPath}/rss/rss.xml" method="post">
        <label for="pageUrl">Insert url here</label>
        <input type="text" id="pageUrl" class="form-control" name="pageUrl"/>
        <input type="submit" class="btn btn-success" id="submitButton"/>
      </form>
    </div>
  </div>
</div>
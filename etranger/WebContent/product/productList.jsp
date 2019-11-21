<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
<script type="text/javascript">
<%
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<CategoryBean> productList = (ArrayList<CategoryBean>)request.getAttribute("productList");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
%>
</script>

  </head>
  <body>
    
<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('images/bg_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="./index.jsp">Home</a></span> <span>Packages</span></p>
              <h1 class="mb-3">Packages</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->
    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-8">
            <div class="row">
			<%if (productList != null && listCount > 0) { %>
            <%for (int i = 0; i < productList.size(); i++) { %>
              <div class="col-md-6 col-lg-6 mb-4 ftco-animate">
                <a href="#" class="block-5" style="background-image: url('ManagerImgUpload/<%=productList.get(i).getPackage_category_image()%>');">
                </a>
                  <div class="text">
                    <span class="price">$399</span>
                    <h3 class="heading"><%=productList.get(i).getPackage_category_name()%></h3>
                    <div class="post-meta">
<%--                     <%String content = productList.get(i).getPackage_category_content();%> //.substring(0, Math.min(content.length(), 20)) --%>
                      <span><%=productList.get(i).getPackage_category_content() %></span>
                    </div>
                    <p class="star-rate"><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star-half-full"></span> <span>500 reviews</span></p>
                  </div>
              </div>
            <%}} %>
<!-- 상품한칸           
              <div class="col-md-6 col-lg-6 mb-4 ftco-animate">
                <a href="#" class="block-5" style="background-image: url('images/tour-1.jpg');">
                  <div class="text">
                    <span class="price">$399</span>
                    <h3 class="heading">Group Tour in Maldives</h3>
                    <div class="post-meta">
                      <span>Ameeru Ahmed Magu Male’, Maldives</span>
                    </div>
                    <p class="star-rate"><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star-half-full"></span> <span>500 reviews</span></p>
                  </div>
                </a>
              </div>
 -->             
            </div>
            <div class="row mt-5">
              <div class="col text-center">
                <div class="block-27">
                  <ul>
                    <li><a href="#">&lt;</a></li>
                    <li class="active"><span>1</span></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&gt;</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- END -->

          <div class="col-lg-4 sidebar">
            <div class="sidebar-box ftco-animate">
              <div class="search-tours bg-light p-4">
                <h3>Find your tour</h3>
                <form action="" method="post">
                  <div class="fields">
                    <div class="row flex-column">

                      <div class="textfield-search col-sm-12 group mb-3"><input type="text" class="form-control" placeholder="Search Location"></div>

                      <div class="check-in col-sm-12 group mb-3"><input type="text" id="checkin_date" class="form-control" placeholder="Check-in date"></div>

                      <div class="check-out col-sm-12 group mb-3"><input type="text" id="checkout_date" class="form-control" placeholder="Check-out date"></div>
                      <div class="select-wrap col-sm-12 group mb-3">
                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                        <select name="" id="" class="form-control">
                          <option value="">Guest</option>
                          <option value="">1</option>
                          <option value="">2</option>
                          <option value="">3</option>
                          <option value="">4+</option>
                        </select>
                      </div>
                      <div class="col-sm-12 group mb-3">
                        <input type="submit" class="search-submit btn btn-primary" value="Find Flights">
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
              <div class="categories">
                <h3>Categories</h3>
                <li><a href="#">Tours <span>(12)</span></a></li>
                <li><a href="#">Hotels <span>(22)</span></a></li>
                <li><a href="#">Cruises <span>(37)</span></a></li>
                <li><a href="#">Restaurant <span>(42)</span></a></li>
                <li><a href="#">Destination <span>(14)</span></a></li>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3>Tag Cloud</h3>
              <div class="tagcloud">
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3>Paragraph</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numquam!</p>
            </div>
          </div>
        </div>
      </div>
    </section>

	<!-- footer 인클루드 -->
<jsp:include page="../include/footer.jsp"/>

   <!-- loader 인클루드 -->
<jsp:include page="../include/loader.jsp"/>
    
  </body>
</html>
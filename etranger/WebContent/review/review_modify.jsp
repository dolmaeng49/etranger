<%@page import="review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String nowPage = (String)request.getParameter("page");
	ReviewBean article = (ReviewBean) request.getAttribute("article");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp"/>
<link rel="stylesheet" href="css/css/summernote-lite.css">
<script src="js/summernote-lite.js"></script>
  </head>
  <body>
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="/include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('../images/bg_3.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span><a href="blog.html">Blog</a></span> <span>Single Blog</span></p>
              <h1 class="mb-3">Blog details</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate">
            <h2 class="mb-5">작성글 수정</h2> <!-- 볼드체 제목 -->
           		<!-- 글 수정 폼 시작 -->
                <form action="ReviewModifyPro.rv" method="post" enctype="multipart/form-data" name="review_write_form">
                <input type="hidden" name="review_num" value="<%=article.getReview_num() %>">
                <input type="hidden" name="page" value="<%=nowPage%>" />
				<input type="hidden" id="review_package_category_code" name="review_package_category_code" value="1">
				<input type="hidden" id="review_readcount" value=0>
				
                  <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="review_member_id"id="review_member_id" value="<%=article.getReview_member_id() %>" readonly="readonly" />
                  </div>
<!--                   <div class="form-group"> -->
<!--                     <label for="email">Email</label> -->
<!--                     <input type="text" class="form-control"> -->
<!--                   </div> -->
                  <div class="form-group">
                    <label for="subject">Subject</label>
                    <input type="text" class="form-control" name="subject" id="subject" value="<%=article.getReview_subject()%>"/>
                  </div>
                  <div class="form-group">
                    <label for="image">이미지 첨부</label>
                    <input name="image" type="file" class="form-control" multiple="multiple" accept="image/*" id="image" value="<%=article.getReview_image()%>"/>
                  </div>
                  <div class="form-group">
                    <label for="message">Message</label>
                    <textarea name="content" id="content" cols="30" rows="20" class="form-control"><%=article.getReview_content() %></textarea>
                  </div>
              
                  <div class="form-group">
                    <input type="submit" value="수정" class="btn py-3 px-4 btn-primary">
                    <input type="reset" value="글목록" class="btn py-3 px-4 btn-primary" onclick="location.href='review_list.jsp'">
                    <input type="button" value="뒤로가기" class="btn py-3 px-4 btn-primary" onclick="history.back()">
                    <input type="submit" value="Join" class="btn py-3 px-4 btn-primary">
                  </div>

                </form>
              </div>
           
           		<!-- 글 입력 폼 -->
<!--                 <form action="#" class="p-5 bg-light"> -->
<!--                   <div class="form-group"> -->
<!--                     <label for="name">작성자</label> -->
<!--                     <input type="text" class="form-control" id="name"> -->
<!--                   </div> -->
<!--                   <div class="form-group"> -->
<!--                     <label for="subject">제목</label> -->
<!--                     <input type="url" class="form-control" id="website"> -->
<!--                   </div> -->
<!--                   <div class="form-group"> -->
<!--                     <label for="content">내용</label> -->
<!--                     <textarea name="summernote" name="editordata" id="summernote" cols="30" rows="10" class="form-control"></textarea> -->
<!--                   </div> -->
<!--                   <div class="form-group"> -->
<!--                     <label for="image"><span id="fileName"></span></label> -->
<!--                   <input type="file" name="upFile" multiple="multiple" accept="image/*" id="image" -->
<!--                   onchange="javascript:document.getElementById('fileName').value = this.value"> -->
<!--                   </div> -->
<!--                   <div class="form-group"> -->
<!--                     <input type="submit" value="글쓰기" class="btn py-3 px-4 btn-primary"> -->
<!--                   </div> -->
                  

<!--                 </form> -->
              
            
            <!-- 본문에 삽입된 태그 -->
            <div class="tag-widget post-tag-container mb-5 mt-5">
              <div class="tagcloud">
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
              </div>
            </div>
            <!-- 본문에 삽입된 태그 -->
            
            
            <!-- 글쓴이 정보 -->
            <div class="about-author d-flex pt-5">
              <div class="bio align-self-md-center mr-4">
                <img src="../images/person_1.jpg" alt="Image placeholder" class="img-fluid mb-4">
              </div>
              <div class="desc align-self-md-center">
                <h3>작성자 정보</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numquam!</p>
              </div>
            </div>
            <!-- 글쓴이 정보 -->
            

			코멘트 전체 패널
            <div class="pt-5 mt-5">
              <h3 class="mb-5">6 Comments</h3>
              <ul class="comment-list">
                <li class="comment">
                  <div class="vcard bio">
                    <img src="../images/person_1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>Jean Doe</h3>
                    <div class="meta">June 27, 2018 at 2:21pm</div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                    <p><a href="#" class="reply">Reply</a></p>
                  </div>
                </li>

                <li class="comment">
                  <div class="vcard bio">
                    <img src="../images/person_1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>Jean Doe</h3>
                    <div class="meta">June 27, 2018 at 2:21pm</div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                    <p><a href="#" class="reply">Reply</a></p>
                  </div>

                  <ul class="children">
                    <li class="comment">
                      <div class="vcard bio">
                        <img src="../images/person_1.jpg" alt="Image placeholder">
                      </div>
                      <div class="comment-body">
                        <h3>Jean Doe</h3>
                        <div class="meta">June 27, 2018 at 2:21pm</div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                        <p><a href="#" class="reply">Reply</a></p>
                      </div>


                      <ul class="children">
                        <li class="comment">
                          <div class="vcard bio">
                            <img src="../images/person_1.jpg" alt="Image placeholder">
                          </div>
                          <div class="comment-body">
                            <h3>Jean Doe</h3>
                            <div class="meta">June 27, 2018 at 2:21pm</div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                            <p><a href="#" class="reply">Reply</a></p>
                          </div>

                            <ul class="children">
                              <li class="comment">
                                <div class="vcard bio">
                                  <img src="../images/person_1.jpg" alt="Image placeholder">
                                </div>
                                <div class="comment-body">
                                  <h3>Jean Doe</h3>
                                  <div class="meta">June 27, 2018 at 2:21pm</div>
                                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                                  <p><a href="#" class="reply">Reply</a></p>
                                </div>
                              </li>
                            </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>

                <li class="comment">
                  <div class="vcard bio">
                    <img src="../images/person_1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>Jean Doe</h3>
                    <div class="meta">June 27, 2018 at 2:21pm</div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                    <p><a href="#" class="reply">Reply</a></p>
                  </div>
                </li>
              </ul>
              <!-- END comment-list -->
              
              
              <!-- comment 작성폼 div -->
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">댓글 작성</h3>
                <form action="#" class="p-5 bg-light">
                  <div class="form-group">
                    <label for="name">Name *</label>
                    <input type="text" class="form-control" id="name">
                  </div>
                  <div class="form-group">
                    <label for="email">Email *</label>
                    <input type="email" class="form-control" id="email">
                  </div>
                  <div class="form-group">
                    <label for="website">Website</label>
                    <input type="url" class="form-control" id="website">
                  </div>

                  <div class="form-group">
                    <label for="message">Message</label>
                    <textarea name="" id="message" cols="30" rows="10" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <input type="submit" value="Post Comment" class="btn py-3 px-4 btn-primary">
                  </div>

                </form>
              </div>
              <!-- comment 작성폼 div -->
            </div>
          <!-- 코멘트 전체 패널 끝-->
          </div><!-- col-md-8 ftco-animate 끝 -->
          
          
          <!-- 사이드바 전체 패널 -->
          <div class="col-md-4 sidebar">
            <div class="sidebar-box">
              <form action="#" class="search-form">
                <div class="form-group">
                  <span class="icon fa fa-search"></span>
                  <input type="text" class="form-control" placeholder="Type a keyword and hit enter">
                </div>
              </form>
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

        </div> <!-- row 끝 -->
      </div><!-- container 끝 -->
    </section> <!-- .section -->

	<!-- footer 인클루드 -->
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
    
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Target Material Design Bootstrap Admin Template</title> 
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="assets/materialize/css/materialize.min.css" media="screen,projection" />
    <!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet" />  
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css"> 
</head>

<body>

	<!-- <img src = "assets/img/java-brands2.jpg"> -->
     <div style = "background-color:#1F2837;">
      <div style= margin: 0 auto; ">
		<div id="page-wrapper">
		<div id="page-inner">

			<div class="row">
		<div class="dashboard-cards" style="width:55%;"> 
                
					<h4 align="center"> <b>전체 기술 스택 순위 TOP4</b></h4>
					<br>
					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="card horizontal cardIcon waves-effect waves-dark">
						
						<div class="card-image red">
						<i class="material-icons dp48"><iframe src="assets/img/java.svg"  style="width:100%; height: 60px; border: 0"></iframe></i>
						</div>
						<div class="card-stacked red">
						<div class="card-content">
						<h3>${sessionTT[0].count}</h3> 
						</div>
						<div class="card-action">
						<strong>${sessionTT[0].word}</strong>
						</div>
						</div>
						</div>
	 
                    </div>
                    
                    <div class="col-xs-12 col-sm-6 col-md-3">
					
						<div class="card horizontal cardIcon waves-effect waves-dark">
						<div class="card-image orange">
						<i class="material-icons dp48"><iframe src="assets/img/javascript.svg"  style="width:100%; height: 60px; border: 0"></iframe></i>
						</div>
						<div class="card-stacked orange">
						<div class="card-content">
						<h3>${sessionTT[1].count}</h3> 
						</div>
						<div class="card-action">
						<strong>${sessionTT[1].word}</strong>
						</div>
						</div>
						</div> 
                    </div>
                    
                    <div class="col-xs-12 col-sm-6 col-md-3">
					
						<div class="card horizontal cardIcon waves-effect waves-dark">
						<div class="card-image blue">
						<i class="material-icons dp48"><iframe src="assets/img/python.svg"  style="width:100%; height: 60px; border: 0"></iframe></i>
						</div>
						<div class="card-stacked blue">
						<div class="card-content">
						<h3>${sessionTT[2].count}</h3> 
						</div>
						<div class="card-action">
						<strong>${sessionTT[2].word}</strong>
						</div>
						</div>
						</div> 
	                </div>
	                
                    <div class="col-xs-12 col-sm-6 col-md-3">
                    
					<div class="card horizontal cardIcon waves-effect waves-dark">
						<div class="card-image green">
						<i class="material-icons dp48"><iframe src="assets/img/amazonaws.svg"  style="width:100%; height: 60px; border: 0"></iframe></i>
						</div>
						<div class="card-stacked green">
						<div class="card-content">
						<h3>${sessionTT[3].count}</h3> 
						</div>
						<div class="card-action">
						<strong>${sessionTT[3].word}</strong>
						</div>
						</div>
						</div> 
                    </div>
							
						
			</div>
		
		
		
					<!--/.row-->
						<div class="col-xs-12 col-sm-12 col-md-5"> 
							
							<div class="card"><div class="card-action">
							  <table>
								  <tr>
								  	<td><b>자격조건 순위</b></td>
								  	<td><b>우대사항 순위</b></td>
								  </tr>
							  </table>
					</div>
					<div class="card-image">
					  <div class="collection">
					  	<table>
						  	<tr>
							  	 <td><a href="#!" class="collection-item">${sessionTQ[0].word}<span class="new badge red" data-badge-caption="st">1</span></a></td>
							  	 <td><a href="#!" class="collection-item">${sessionTP[0].word}<span class="new badge red" data-badge-caption="st">1</span></a></td>
						  	</tr>
						  	<tr>
							  	 <td><a href="#!" class="collection-item">${sessionTQ[1].word}<span class="badge" data-badge-caption="nd">2</span></a></td>
							  	 <td><a href="#!" class="collection-item">${sessionTP[1].word}<span class="badge" data-badge-caption="nd">2</span></a></td>
						  	</tr>
						  	<tr>
							  	 <td><a href="#!" class="collection-item">${sessionTQ[2].word}<span class="new badge" data-badge-caption="rd">3</span></a></td>
							  	 <td><a href="#!" class="collection-item">${sessionTP[2].word}<span class="new badge" data-badge-caption="rd">3</span></a></td>
						  	</tr>
						  	<tr>
							  	 <td><a href="#!" class="collection-item">${sessionTQ[3].word}<span class="new badge blue" data-badge-caption="th">4</span></a></td>
							  	 <td><a href="#!" class="collection-item">${sessionTP[3].word}<span class="new badge blue" data-badge-caption="th">4</span></a></td>
						  	</tr>
						  	<tr>
							  	 <td><a href="#!" class="collection-item">${sessionTQ[4].word}<span class="new badge pink" data-badge-caption="th">5</span></a></td>
							  	 <td><a href="#!" class="collection-item">${sessionTP[4].word}<span class="new badge pink" data-badge-caption="th">5</span></a></td>
						  	</tr>
						</table>
						</div>
					</div> 
					</div>
					</div>
				</div>
					
		
			
		
		  <div> 
                        <h1 class="page-header">
                        
                           ${deptName} 
                        </h1>
						<ol class="breadcrumb">
						
					  <li>
					  <form method="GET" action="/search">
					  <input width="100%" name="search"/>
					  <button type="submit"> 검색</button>
					  </form>
					  </li>
					</ol> 
									
			</div> 
            
		
		
				<!-- /. ROW  --> 
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-7"> 
					<div class="cirStats">
						  	<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-6"> 
										<div class="card-panel text-center">
											<h4>${deptName}의 전체 공고에서의 비율 ${deptVal}%</h4>
											<div class="easypiechart" id="easypiechart-blue" data-percent=${deptVal} ><span class="percent">${deptVal}%</span>
											</div> 
										</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6"> 
										<div class="card-panel text-center">
											<h4>${tech_one_name}</h4>
											<div class="easypiechart" id="easypiechart-red" data-percent=${tech_one} ><span class="percent">${tech_one}%</span>
											</div>
										</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6"> 
										<div class="card-panel text-center">
											<h4>${tech_two_name}</h4>
											<div class="easypiechart" id="easypiechart-teal" data-percent=${tech_two} ><span class="percent">${tech_two}%</span>
											</div> 
										</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6"> 
										<div class="card-panel text-center">
											<h4>${tech_three_name}</h4>
											<div class="easypiechart" id="easypiechart-orange" data-percent=${tech_three}"><span class="percent">${tech_three}%</span>
											</div>
										</div>
								</div>  
							</div>
					</div>							
						</div><!--/.row-->
						<div class="col-xs-12 col-sm-12 col-md-5"> 
							
							<div class="card"><div class="card-action">
							  <table>
								  <tr>
								  	<td><b>자격조건 순위</b></td>
								  	<td><b>우대사항 순위</b></td>
								  </tr>
							  </table>
					</div>
					<div class="card-image">
					  <div class="collection">
					  	<table>
						  	<tr height="65px">
							  	 <td><a href="#!" class="collection-item">${qualList[0].word}<span class="new badge red" data-badge-caption="st">1</span></a></td>
							  	 <td><a href="#!" class="collection-item">${prefList[0].word}<span class="new badge red" data-badge-caption="st">1</span></a></td>
						  	</tr>
						  	<tr height="65px">
							  	 <td><hr><a href="#!" class="collection-item">${qualList[1].word}<span class="badge" data-badge-caption="nd">2</span></a></td>
							  	 <td><hr><a href="#!" class="collection-item">${prefList[1].word}<span class="badge" data-badge-caption="nd">2</span></a></td>
						  	</tr>
						  	<tr height="65px">
							  	 <td><hr><a href="#!" class="collection-item">${qualList[2].word}<span class="new badge" data-badge-caption="rd">3</span></a></td>
							  	 <td><hr><a href="#!" class="collection-item">${prefList[2].word}<span class="new badge" data-badge-caption="rd">3</span></a></td>
						  	</tr>
						  	<tr height="65px">
							  	 <td><hr><a href="#!" class="collection-item">${qualList[3].word}<span class="new badge blue" data-badge-caption="th">4</span></a></td>
							  	 <td><hr><a href="#!" class="collection-item">${prefList[3].word}<span class="new badge blue" data-badge-caption="th">4</span></a></td>
						  	</tr>
						  	<tr height="65px">
							  	 <td><hr><a href="#!" class="collection-item">${qualList[4].word}<span class="new badge pink" data-badge-caption="th">5</span></a></td>
							  	 <td><hr><a href="#!" class="collection-item">${prefList[4].word}<span class="new badge pink" data-badge-caption="th">5</span></a></td>
						  	</tr>
					  	</table>
						</div>
					</div> 
				</div>
				</div>
			</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-5">
						     <div class="row">
									<div class="col-xs-12"> 
									<div class="card">
										<div class="card-image donutpad">
										  <div id="morris-donut-chart"></div>
										</div> 
										<div class="card-action">
										  <b>경력 조건</b>
										  <script language=javascript>
										  
										  var WE2 = '${exep[0].word}';
										  var WE3 = '${exep[1].word}';
										  var WE4 = '${exep[2].word}';
										  var WE5 = '${exep[3].word}';
										  
										  var CE1 = ${exepZero};
										  var CE2 = '${exep[0].count}';
										  var CE3 = '${exep[1].count}';
										  var CE4 = '${exep[2].count}';
										  var CE5 = '${exep[3].count}'; 
										  </script>
										</div>
									</div>	
								</div>
							 </div> 
						</div>
					
				 <div class="col-md-7"> 
					<div class="card">
					<div class="card-image">
					  <div id="morris-bar-chart"></div>
					</div> 
					<div class="card-action">
					  <b> 직무 간 기술 비율 비교</b>
					  
					  <script language=javascript> 
						  var fCount1 = ${firG[0].count};
						  var fCount2 = ${firG[1].count};
						  var fWord1 = '${firG[0].word}';
						  var fWord2 = '${firG[1].word}';
						  var sCount1 = ${SecF[0].count};
						  var sCount2 = ${SecS[0].count};
						  var tCount1 = ${ThF[0].count};
						  var tCount2 = ${ThS[0].count};
						  var foCount1 = ${FoF[0].count};
						  var foCount2 = ${FoS[0].count};
						  var fiCount1 = ${FiF[0].count};
						  var fiCount2 = ${FiS[0].count};
						  var dName1 = '${dName[4]}';
						  var dName2 = '${dName[0]}';
						  var dName3 = '${dName[1]}';
						  var dName4 = '${dName[2]}';
						  var dName5 = '${dName[3]}';
					  </script>
					</div>
					</div>					
					</div> 
				</div>
				
			 
			
					</div>	 
					
                       

                    </div>
                </div>
                </div>
                
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
   
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="assets/js/jquery-1.10.2.js"></script>
	
	<!-- Bootstrap Js -->
    <script src="assets/js/bootstrap.min.js"></script>
	
	<script src="assets/materialize/js/materialize.min.js"></script>
	
    <!-- Metis Menu Js -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/js/morris/morris.js"></script>
	
	
	<script src="assets/js/easypiechart.js"></script>
	<script src="assets/js/easypiechart-data.js"></script>
	
	 <script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>
	
    <!-- Custom Js -->
    <script src="assets/js/custom-scripts.js"></script> 
 

</body>
</html>
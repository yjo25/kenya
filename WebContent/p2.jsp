<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="generator" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<link href="css/haiersoft.css" rel="stylesheet" type="text/css"
	media="screen,print" />
<link href="css/print.css" rel="stylesheet" type="text/css"
	media="print" />
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/side.js" type="text/javascript"></script>

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body>
	<!-- Popup -->
	<div id="Popup">

		<!-- SubPopup -->
		<div id="SubPopup">
			<script type="text/javascript">
				$(
						function() {
							$(".select").each(
									function() {
										var s = $(this);
										var z = parseInt(s.css("z-index"));
										var dt = $(this).children("dt");
										var dd = $(this).children("dd");
										var _show = function() {
											dd.slideDown(200);
											dt.addClass("cur");
											s.css("z-index", z + 1);
										};
										var _hide = function() {
											dd.slideUp(200);
											dt.removeClass("cur");
											s.css("z-index", z);
										};
										dt.click(function() {
											dd.is(":hidden") ? _show()
													: _hide();
										});
										dd.find("a").click(function() {
											dt.jsp($(this).jsp());
											_hide();
										});
										$("body").click(
												function(i) {
													!$(i.target).parents(
															".select").first()
															.is(s) ? _hide()
															: "";
												});
									})
						})
			</script>
			<style>
.img-container {
	width: 293px;
	height: 150px;
	background: #F2F2F2;
	margin-bottom: 35px;
	overflow: hidden;
	border: 1px solid #000;
}

.img-container>img {
	width: 293px;
	height: 150px;
}

.img {
	width: 293px;
	height: 150px;
}
</style>
			<div class="form_boxC">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<th width="100">轮播主图</th>
					<tr>
						<div>
							<div class="img-container"></div>
							<div>
								<input class="img-btn" type="file" id="drivingLicence"
									name="drivingLicence">
							</div>
						</div>
						<div>
							<div class="img-container"></div>
							<input class="img-btn" type="file" id="drivingLicence"
								name="drivingLicence">
						</div>
						<div>
							<div class="img-container"></div>
							<input class="img-btn" type="file" id="drivingLicence"
								name="drivingLicence">
						</div>
						<div>
							<div class="img-container"></div>
							<input class="img-btn" type="file" id="drivingLicence"
								name="drivingLicence">
						</div>
						<div>
							<div class="img-container"></div>
							<input class="img-btn" type="file" id="drivingLicence"
								name="drivingLicence">
						</div>
						<div>
							<div class="img-container"></div>
							<input class="img-btn" type="file" id="drivingLicence"
								name="drivingLicence">
						</div>
						<div>
							<div class="img-container"></div>
							<input class="img-btn" type="file" id="drivingLicence"
								name="drivingLicence">
						</div>
						<div>
							<div class="img-container"></div>
							<input class="img-btn" type="file" id="drivingLicence"
								name="drivingLicence">
						</div>
					</tr>

					<!-- /selectbox -->
					</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="BtmBtn">
			<div class="btn_boxB floatR mag_l20">
				<input name="" type="button" value="取消"
					onmousemove="this.className='input_move'"
					onmouseout="this.className='input_out'">
			</div>
			<div class="btn_box floatR">
				<input name="" type="button" value="提交"
					onmousemove="this.className='input_move'"
					onmouseout="this.className='input_out'">
			</div>
		</div>
	</div>
	<!-- /Popup -->
</body>
<script>
	/**
	 * Created by Administrator on 2016/4/28.
	 */
	//上传图片并预览
	function previewImg(fileInput, imgDiv) {
		if (window.FileReader) {//支持FileReader的时候
			var reader = new FileReader();
			reader.readAsDataURL(fileInput.files[0]);
			reader.onload = function(evt) {
				imgDiv.innerHTML = "\<img src="+evt.target.result+"\>";
			}
		} else {//兼容ie9-
			imgDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
					+ fileInput.value + '\)\';"></div>';
		}
	}
	function selectImg(fileInputs, imgDivs) {
		var checkImg = new RegExp("(.jpg$)|(.png$)|(.bmp$)|(.jpeg$)", "i");
		var i = 0;
		for (; i < fileInputs.length && i < imgDivs.length; i++) {
			(function(i) {//立即执行函数；保存i
				fileInputs[i].onchange = function() {
					if (checkImg.test(fileInputs[i].value)) {
						previewImg(this, imgDivs[i]);
					} else {
						alert("只支持上传.jpg .png .bmp .jpeg;你的选择有误");
					}
				};
			})(i);
		}

	}
	/* 为IE6 IE7 IE8增加document.getElementsByClassName函数 */
	/MSIE\s*(\d+)/i.test(navigator.userAgent);
	var isIE = parseInt(RegExp.$1 ? RegExp.$1 : 0);
	if (isIE > 0 && isIE < 9) {
		document.getElementsByClassName = function(cls) {
			var els = this.getElementsByTagName('*');
			var ell = els.length;
			var elements = [];
			for (var n = 0; n < ell; n++) {
				var oCls = els[n].className || '';
				if (oCls.indexOf(cls) < 0)
					continue;
				oCls = oCls.split(/\s+/);
				var oCll = oCls.length;
				for (var j = 0; j < oCll; j++) {
					if (cls == oCls[j]) {
						elements.push(els[n]);
						break;
					}
				}
			}
			return elements;
		}
	}
	var fileInputs = document.getElementsByClassName("img-btn");//文件选择按钮
	var imgDivs = document.getElementsByClassName("img-container");//图片容器
	selectImg(fileInputs, imgDivs);
</script>
</html>
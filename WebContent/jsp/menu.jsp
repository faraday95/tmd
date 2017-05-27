<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li class='active '><a
				href="<%=request.getContextPath()%>/jsp/welcome.jsp"><span>Home</span></a></li>

			<li class='has-sub '><a href='#'>HashTag<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/hashtagsubmission.jsp'><span>
								Submit HashTag</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewhashtags.jsp'><span>
								View Hash Tags </span></a></li>

				</ul></li>

			<li class='has-sub '><a href='#'>Twitter<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/tweetsubmission.jsp'><span>
								Tweet Retrival</span></a></li>
					<li><a href='<%=request.getContextPath()%>/jsp/viewtweets.jsp'><span>
								View Tweets </span></a></li>

				</ul></li>

			<li class='has-sub '><a href='#'>Stopword Analysis<span></span></a>
				<ul>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/addStopword.jsp"><span>Add
								Stopword</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewstopwords.jsp"><span>View
								Stopword</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/removeStopword.jsp"><span>Remove
								Stopword</span></a></li>
				</ul>
			<li class='has-sub '><a href='#'>Remove Noise<span></span></a>
				<ul>
					<li class='has-sub '><a
						href="<%=request.getContextPath()%>/jsp/noisereduction.jsp"><span>Reduce
								Noise</span></a></li>
					<li class='has-sub '><a
						href="<%=request.getContextPath()%>/jsp/viewtcleantweets.jsp"><span>View
								Clean Tweets</span></a></li>
				</ul>
			<li class='has-sub '><a href='#'>Delete Data<span></span></a>
				<ul>
					<li><a href='<%=request.getContextPath()%>/jsp/deleteData.jsp'><span>
								Delete Data</span></a></li>

				</ul>
			<li class='has-sub '><a href='#'>Category<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewcategorywords.jsp'><span>
								View Category Words</span></a></li>
					<li><a href='<%=request.getContextPath()%>/jsp/addcatword.jsp'><span>
								Add Category Word</span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/jsp/removeCatWord.jsp'><span>
								Remove Category Words</span></a></li>

				</ul>
			<li class='has-sub '><a href='#'><span>Classification</span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/tweet/doProbability.do'><span>
								Probability</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewprobability.jsp'><span>
								View Probability</span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/tweet/doContigency.do'><span>
								Perform Contingency</span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/jsp/viewcontigency.jsp'><span>
								View Contingency</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/tweet/doEnhanceContigency.do'><span>
								Enhance Contingency</span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/jsp/viewenhancecontigency.jsp'><span>
								View Enhance Contingency</span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/tweet/doClassifier.do'><span>
								Do Classifier</span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/jsp/viewclassifier.jsp'><span>
								View Classifier</span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/jsp/viewclassifiercount.jsp'><span>
								View Classifier Count</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewclassifiergraph.jsp'><span>
								View Classifier Graph</span></a></li>


				</ul>
		</ul>
	</div>
</body>
</html>
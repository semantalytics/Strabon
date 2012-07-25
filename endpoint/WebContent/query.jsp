<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="style.css" type="text/css" /> 
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript">
		function toggleMe(a) {
			var e = document.getElementById(a);
			if (!e) {
				return true;
			}
			if (e.style.display == "none") {
				e.style.display = "block";
			} else {
				e.style.display = "none";
			}
			return true;
		}
	</script>
	<script type="text/javascript">
		function initialize() {
		  var brahames = new google.maps.LatLng(37.92253, 23.72275);
		  var myOptions = {
		    zoom: 11,
		    center: brahames,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		  };
		
		  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
		
		  var ctaLayer = new google.maps.KmlLayer('');
		  ctaLayer.setMap(map);
		}
	</script> 
	<title>TELEIOS: Strabon Endpoint</title>
</head>
<body topmargin="0" leftmargin="0" link="#FFFFFF" vlink="#FFFFFF" alink="#FFFFFF" onload="initialize()">

<!-- include TELEIOS header and description -->
<%@ include file="teleios-header.html"%>
<!-- include TELEIOS header and description -->

<form enctype="UTF-8" accept-charset="UTF-8" method="post" action="Query">
<table border="0" width="100%"><tr> 
<td width="90" valign="top" bgcolor="#dfe8f0"> 
<table border="0" cellspacing="0" cellpadding="0" width="165" id="navigation">  
<tr><td id="twidth">
<a href="Query?SPARQLQuery=%23+Discovering+raw+data+and+products%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+clc%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FclcOntology.owl%23%3E%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E%0APREFIX+teleios%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+gag%3A+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fontologies%2F2011%2FgagKallikratis.rdf%23%3E%0APREFIX+geo%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2003%2F01%2Fgeo%2Fwgs84_pos%23%3E%0APREFIX+georss%3A+%3Chttp%3A%2F%2Fwww.georss.org%2Fgeorss%2F%3E%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX+lgdo%3A+%3Chttp%3A%2F%2Flinkedgeodata.org%2Fontology%2F%3E%0APREFIX+gn%3A+%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0A%0ASELECT+%3Ffilename%0AWHERE+%7B%3Ffile+rdf%3Atype+noa%3AShpFile+%3B%0A%09+++++++++noa%3AhasFilename+%3Ffilename+%3B%0A%09+++++++++noa%3AhasAcquisitionTime+%3FsensingTime+.%0A%09+FILTER%28+str%28%3FsensingTime%29+%3E%3D+%222007-08-26T12%3A00%3A00%22+%29+.%0A%09+FILTER%28+str%28%3FsensingTime%29+%3C%3D+%222007-08-26T12%3A30%3A00%22+%29+.%0A%09+%3Ffile+noa%3AisDerivedFromSensor+%3Fsensor+.%0A%09+FILTER%28+str%28%3Fsensor%29+%3D+%22MSG2%22+%29+.%0A%09+%3Ffile+noa%3AproducedFromProcessingChain+%3Fchain+.%0A%09+FILTER%28+str%28%3Fchain%29+%3D+%22StaticThresholds%22+%29+.+%0A%7D&format=HTML"title="Find all shapefiles derived from sensor MSG2 between [2007-08-26T12:00:00,2007-08-26T12:30:00], using dynamic thresholds.">&nbsp;&middot;&nbsp;Query 1: Discover raw data and products</a><br/>
<a href="Query?SPARQLQuery=%23+Get+all+hotspots+%0A%23+-+in+Pelloponesus+%0A%23+-+at+2007-08-24%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+clc%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FclcOntology.owl%23%3E%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E%0APREFIX+teleios%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+gag%3A+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fontologies%2F2011%2FgagKallikratis.rdf%23%3E%0APREFIX+geo%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2003%2F01%2Fgeo%2Fwgs84_pos%23%3E%0APREFIX+georss%3A+%3Chttp%3A%2F%2Fwww.georss.org%2Fgeorss%2F%3E%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX+lgdo%3A+%3Chttp%3A%2F%2Flinkedgeodata.org%2Fontology%2F%3E%0APREFIX+gn%3A+%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0A%0ASELECT+%3Fh+%3FhAcqTime+%3FhConfidence+%3FhConfirmation+%3FhProvider+%3FhSensor+%3FhSatellite+%28strdf%3Atransform%28%3FhGeo%2C+%3Chttp%3A%2F%2Fwww.opengis.net%2Fdef%2Fcrs%2FEPSG%2F0%2F4326%3E%29+as+%3Fgeo%29+%0AWHERE+%7B+%3Fh+%09rdf%3Atype+noa%3AHotspot%3B%0A%09%09%09noa%3AhasGeometry+%3FhGeo%3B%0A%09%09%09noa%3AhasAcquisitionTime+%3FhAcqTime%3B%0A%09%09%09noa%3AhasConfidence+%3FhConfidence%3B%0A%09%09%09noa%3AisProducedBy+%3FhProvider%3B%0A%09%09%09noa%3AhasConfirmation+%3FhConfirmation%3B%0A%09%09%09noa%3AisDerivedFromSensor+%3FhSensor%3B%0A%09%09%09noa%3AisDerivedFromSatellite+%3FhSatellite+%3B%0A%09%09%09noa%3AproducedFromProcessingChain+%3FhChain+.%0A%09FILTER%28str%28%3FhChain%29+%3D+%22StaticThresholds%22%29.%0A%09FILTER%28%3FhAcqTime+%3D+%222007-08-24T14%3A45%3A00%22%5E%5Exsd%3AdateTime%29+.%0A%09FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C+21.027+38.36%29%29%22%5E%5E+strdf%3AWKT%2C+%3FhGeo%29%29+.+%0A%7D&format=KMZMAP"title="Find all hotspots in Pelloponesus derived from the dynamic thresholds processing chain at 2007-08-24T14:45:00.  ">&nbsp;&middot;&nbsp;Query 2: Discover Hotspots</a><br/>
<a href="Update?SPARQLQuery=%23+Delete+hotspots+that+lie+in+sea%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+clc%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FclcOntology.owl%23%3E%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E%0APREFIX+teleios%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+gag%3A+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fontologies%2F2011%2FgagKallikratis.rdf%23%3E%0APREFIX+geo%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2003%2F01%2Fgeo%2Fwgs84_pos%23%3E%0APREFIX+georss%3A+%3Chttp%3A%2F%2Fwww.georss.org%2Fgeorss%2F%3E%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX+lgdo%3A+%3Chttp%3A%2F%2Flinkedgeodata.org%2Fontology%2F%3E%0APREFIX+gn%3A+%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0ADELETE+%7B%3Fh+%3Fproperty+%3Fobject%7D%0AWHERE+%7B+%3Fh+%09rdf%3Atype+noa%3AHotspot%3B%0A++++++++++++noa%3AhasAcquisitionTime+%3FhAcqTime%3B%0A++++++++++++noa%3AproducedFromProcessingChain+%3FhChain%3B%0A++++++++++++noa%3AhasGeometry+%3FhGeo%3B%0A++++++++++++%3Fproperty+%3Fobject.%0A++++++++FILTER%28str%28%3FhChain%29+%3D+%22StaticThresholds%22%29.%0A++++++++FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C+21.027+38.36%29%29%22%5E%5E+strdf%3AWKT%2C+%3FhGeo%29%29.%0A++++++++FILTER%28%222007-08-24T14%3A45%3A00%22%5E%5Exsd%3AdateTime+%3D+%3FhAcqTime%29.%0A++++++++OPTIONAL+%7B%0A++++++++++++%3Fc+rdf%3Atype+noa%3ACoastline%3B%0A+++++++++++++++noa%3AhasGeometry+%3FcGeo.%0A++++++++++++FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C+21.027+38.36%29%29%22%5E%5E+strdf%3AWKT%2C+%3FcGeo%29%29.%0A++++++++++++FILTER%28strdf%3AanyInteract%28%3FhGeo%2C+%3FcGeo%29%29+.+%0A++++++++%7D%0A++++++++FILTER%28%21bound%28%3Fc%29%29.+%0A%7D&format=HTML"title="Mark as invalid all hotspots produced by the static thresholds processing chain and are acquired at 2007-08-24T14:45:00 which are located in the sea">&nbsp;&middot;&nbsp;Update A: Invalidate hotspots in sea</a><br/>
<a href="Update?SPARQLQuery=%23+Refine+the+geometry+of+hotspots+that+partially+lie+in+sea%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E+%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E+%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E+%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E+%0A%0A%0AINSERT+%7B+%3Fh+noa%3AisDiscarded+%221%22%5E%5Exsd%3Aint+.+%0A%09+%3Fvalid+rdf%3Atype+noa%3AHotspot+%3B%0A%09%09noa%3AhasConfidence+%3Fconf+%3B%0A%09++++++++noa%3AhasGeometry+%3Fdif+%3B%0A%09%09noa%3AhasAcquisitionTime+%22TIMESTAMP%22%5E%5Exsd%3AdateTime+%3B+%0A%09++++++++noa%3AisDerivedFromSensor+%22SENSOR%22%5E%5Exsd%3Astring+%3B%0A%09%09noa%3AhasConfirmation+noa%3Aunknown+%3B%0A%09++++++++noa%3AproducedFromProcessingChain+%22PROCESSING_CHAIN%22%5E%5Exsd%3Astring+%3B+%0A%09%09noa%3AisProducedBy+noa%3Anoa+%3B%0A%09%09noa%3AisDerivedFromSatellite+%3Fsat+.%0A%7D%0AWHERE+%7B+%0A++SELECT+DISTINCT+%3Fh+%3FhGeo+%28strdf%3Aintersection%28%3FhGeo%2C+strdf%3Aunion%28%3FcGeo%29%29+AS+%3Fdif%29+%28BNODE%28%29+AS+%3Fvalid%29+%3Fconf+%3Fsat%0A++WHERE+%7B+%0A++++%3Fh+rdf%3Atype+noa%3AHotspot+%3B+%0A+++++++noa%3AhasAcquisitionTime+%3FhAcqTime+%3B+%0A+++++++noa%3AproducedFromProcessingChain+%22PROCESSING_CHAIN%22%5E%5Exsd%3Astring+%3B+%0A+++++++noa%3AisDerivedFromSensor+%22SENSOR%22%5E%5Exsd%3Astring+%3B%0A+++++++noa%3AhasGeometry+%3FhGeo+%3B%0A+++++++noa%3AhasConfidence+%3Fconf+%3B%0A+++++++noa%3AisDerivedFromSatellite+%3Fsat+.%0A++++FILTER%28%22TIMESTAMP%22%5E%5Exsd%3AdateTime+%3D+%3FhAcqTime%29+.%0A++++%3Fc+rdf%3Atype+noa%3ACoastline+%3B%0A+++++++noa%3AhasGeometry+%3FcGeo+.%0A++++FILTER%28strdf%3AanyInteract%28%3FhGeo%2C+%3FcGeo%29%29+.+%0A++%7D%0A++GROUP+BY+%3Fh+%3FhGeo+%3Fconf+%3Fsat%0A++HAVING+strdf%3Aoverlap%28%3FhGeo%2C+strdf%3Aunion%28%3FcGeo%29%29%0A%7D%0A&format=HTML"title="Refine hotspots acquired at 2007-08-24T14:45:00 removing the part that lies in the sea. ">&nbsp;&middot;&nbsp;Update B: Refine hotspots in sea</a><br/>
<a href="Query?SPARQLQuery=%23Get+all+coniferous+forests+in+Peloponnese%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+clc%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FclcOntology.owl%23%3E%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E%0APREFIX+teleios%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+gag%3A+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fontologies%2F2011%2FgagKallikratis.rdf%23%3E%0APREFIX+geo%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2003%2F01%2Fgeo%2Fwgs84_pos%23%3E%0APREFIX+georss%3A+%3Chttp%3A%2F%2Fwww.georss.org%2Fgeorss%2F%3E%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX+lgdo%3A+%3Chttp%3A%2F%2Flinkedgeodata.org%2Fontology%2F%3E%0APREFIX+gn%3A+%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0A%0ASELECT+%3Fa+%3FaGeo%0AWHERE%7B+%3Fa+rdf%3Atype+clc%3AArea%3B%0A++++++++++clc%3AhasLandUse+%3FaLandUse%3B%0A++++++++++noa%3AhasGeometry+%3FaGeo.%0A+++++++%3FaLandUse+rdf%3Atype+%3FaLandUseType.%0A+++++++FILTER%28%3FaLandUseType+%3D+clc%3AConiferousForest%29.%0A+++++++FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C+21.027+38.36%29%29%22%5E%5Estrdf%3AWKT%2C%3FaGeo%29%29.+%0A%7D&format=KMZMAP"title="Discover all coniferous forests using the clc ontology ">&nbsp;&middot;&nbsp;Query 3: Discover coniferous forests</a><br/>
<a href="Query?SPARQLQuery=%23+Get+all+municipalities+of+Pelloponnese%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+clc%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FclcOntology.owl%23%3E%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E%0APREFIX+teleios%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+gag%3A+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fontologies%2F2011%2FgagKallikratis.rdf%23%3E%0APREFIX+geo%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2003%2F01%2Fgeo%2Fwgs84_pos%23%3E%0APREFIX+georss%3A+%3Chttp%3A%2F%2Fwww.georss.org%2Fgeorss%2F%3E%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX+lgdo%3A+%3Chttp%3A%2F%2Flinkedgeodata.org%2Flontology%2F%3E%0APREFIX+gn%3A+%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0A%0ASELECT++%3Fd+%3FdGeo%0AWHERE+%7B+%3Fd+rdf%3Atype+gag%3ADhmos%3B+%0A+++++++++++strdf%3AhasGeometry+%3FdGeo%3B+%0A+++++++++++rdfs%3Alabel+%3FdLabel.+%0A+++++++++++FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C+21.027+38.36%29%29%22%5E%5E+strdf%3AWKT%2C+%3FdGeo%29%29.+%0A%7D&format=KMZMAP"title="Find all municipalities of Pelloponnese using kallikratis ontology ">&nbsp;&middot;&nbsp;Query 4: Discover primary roads</a><br/>
<a href="Query?SPARQLQuery=%23+Get+all+primary+roads+in+Pelloponnese%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+clc%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FclcOntology.owl%23%3E%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E%0APREFIX+teleios%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+gag%3A+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fontologies%2F2011%2FgagKallikratis.rdf%23%3E%0APREFIX+geo%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2003%2F01%2Fgeo%2Fwgs84_pos%23%3E%0APREFIX+georss%3A+%3Chttp%3A%2F%2Fwww.georss.org%2Fgeorss%2F%3E%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX+lgdo%3A+%3Chttp%3A%2F%2Flinkedgeodata.org%2Fontology%2F%3E%0APREFIX+gn%3A+%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0A%0ASELECT++%3Fr+%3FrGeo%0AWHERE+%7B+%3Fr+rdf%3Atype+%3FrType+%3B%0A+++++++++++noa%3AhasGeometry+%3FrGeo+.%0A++++++++FILTER%28%3FrType+%3D+lgdo%3APrimary%29+.%0A++++++++FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C21.027+38.36%29%29%22%5E%5Estrdf%3AWKT%2C+%3FrGeo%29+%29.+%0A%7D&format=KMZMAP"title="Get all primary roads in Pelloponnese using the linked geodata ontology ">&nbsp;&middot;&nbsp;Query 5: Get all primary roads in Pelloponnese</a><br/>
<a href="Query?SPARQLQuery=%23+Get+all+hotspots+%0A%23+-+in+Pelloponesus+%0A%23+-+at+2007-08-24%0APREFIX+noa%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+clc%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FclcOntology.owl%23%3E%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX+strdf%3A+%3Chttp%3A%2F%2Fstrdf.di.uoa.gr%2Fontology%23%3E%0APREFIX+teleios%3A+%3Chttp%3A%2F%2Fteleios.di.uoa.gr%2Fontologies%2FnoaOntology.owl%23%3E%0APREFIX+gag%3A+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fontologies%2F2011%2FgagKallikratis.rdf%23%3E%0APREFIX+geo%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2003%2F01%2Fgeo%2Fwgs84_pos%23%3E%0APREFIX+georss%3A+%3Chttp%3A%2F%2Fwww.georss.org%2Fgeorss%2F%3E%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX+lgdo%3A+%3Chttp%3A%2F%2Flinkedgeodata.org%2Fontology%2F%3E%0APREFIX+gn%3A+%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0A%0ASELECT++%3Fh+%3FhAcqTime+%28strdf%3Atransform%28%3FhGeo%2C+%3Chttp%3A%2F%2Fwww.opengis.net%2Fdef%2Fcrs%2FEPSG%2F0%2F4326%3E%29+as+%3Fgeo%29+%0AWHERE+%7B+%3Fh+%09rdf%3Atype+noa%3AHotspot%3B%0A++++++++++++noa%3AhasGeometry+%3FhGeo%3B%0A++++++++++++noa%3AhasAcquisitionTime+%3FhAcqTime%3B%0A++++++++++++noa%3AproducedFromProcessingChain+%3FhChain+.%0A++++++++FILTER%28str%28%3FhChain%29+%3D+%22StaticThresholds%22%29.%0A++++++++FILTER%28%3FhAcqTime+%3D+%222007-08-24T14%3A45%3A00%22%5E%5Exsd%3AdateTime%29+.%0A++++++++FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C+21.027+38.36%29%29%22%5E%5E+strdf%3AWKT%2C+%3FhGeo%29%29+.+%0A++++++++%3Fa+rdf%3Atype+clc%3AArea%3B%0A+++++++++++clc%3AhasLandUse+%3FaLandUse%3B%0A+++++++++++noa%3AhasGeometry+%3FaGeo.%0A++++++++%3FaLandUse+rdf%3Atype+%3FaLandUseType.%0A++++++++FILTER%28%3FaLandUseType+%3D+clc%3AForest%29.%0A++++++++FILTER%28strdf%3Acontains%28%22POLYGON%28%2821.027+38.36%2C+23.77+38.36%2C+23.77+36.05%2C+21.027+36.05%2C+21.027+38.36%29%29%22%5E%5Estrdf%3AWKT%2C%3FaGeo%29%29.+%0A++++++++FILTER%28strdf%3Aoverlap%28%3FaGeo%2C+%3FhGeo%29%29+.+%0A%7D&format=KMZMAP"title="Find all hotspots located in Pelloponnesian forests and acquired at 2007-08-24T14:45:00 using static thresholds processing chain ">&nbsp;&middot;&nbsp;Query 6: Discover hotspots</a><br/>
</td>

</tr>
<tr><td width="90" class="style4"><a href="describe.jsp" class="navText">Describe</a></td></tr>
<tr><td width="90" class="style4"><a href="store.jsp" class="navText" title="Store triples">Store</a></td></tr> 
</table>
</td>
<td width="*" valign="top" >
<table cellspacing="5">
<tr>
<td id="output">stSPARQL Query:</td>
<td id="output"><textarea name="SPARQLQuery" title="pose your query/update here" rows="15" cols="100">
</textarea></td>
</tr>
<tr>
<td id="output"><center>Output Format:<br/><select name="format" title="select one of the following output format types">
<option  value="KMLMAP">HTML with google maps (kml)</option>
<option  value="GEOJSON">GeoJSON</option>
<option selected value="HTML">HTML</option>
<option  value="KMZMAP">HTML with google maps (kmz)</option>
<option  value="XML">XML</option>
<option  value="KML">KML plain text</option>
<option  value="KML file">KML file</option>
</select></center></td>
<td colspan=2><br/><center>
<input type="submit" title="execute query" value="Query" name="submit" /><br/><input type="submit" title="execute update" value="Update" name="submit" style="width: 400px"/></center><br/></td>
</tr>
</table></td></tr></table><br/><br/>
</form>
</body>
</html>

package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object signin extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html lang="en" ng-app="MAdmin">
<head>
<title>Ardent Job Portal</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,700" />
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Oswald:400,700,300" />
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*13.31*/routes/*13.37*/.Assets.at("app/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css"))),format.raw/*13.135*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*14.31*/routes/*14.37*/.Assets.at("app/vendors/font-awesome/css/font-awesome.min.css"))),format.raw/*14.100*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*15.31*/routes/*15.37*/.Assets.at("app/vendors/bootstrap/css/bootstrap.min.css"))),format.raw/*15.94*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*16.31*/routes/*16.37*/.Assets.at("app/vendors/intro.js/introjs.css"))),format.raw/*16.83*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*17.31*/routes/*17.37*/.Assets.at("app/vendors/calendar/zabuto_calendar.min.css"))),format.raw/*17.95*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*18.31*/routes/*18.37*/.Assets.at("app/vendors/sco.message/sco.message.css"))),format.raw/*18.90*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*19.31*/routes/*19.37*/.Assets.at("app/vendors/animate.css/animate.css"))),format.raw/*19.86*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*20.31*/routes/*20.37*/.Assets.at("app/vendors/iCheck/skins/all.css"))),format.raw/*20.83*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*21.31*/routes/*21.37*/.Assets.at("app/vendors/jquery-notific8/jquery.notific8.min.css"))),format.raw/*21.102*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*22.31*/routes/*22.37*/.Assets.at("app/vendors/sco.message/sco.message.css"))),format.raw/*22.90*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*23.31*/routes/*23.37*/.Assets.at("app/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.1.1.min.css"))),format.raw/*23.124*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*24.31*/routes/*24.37*/.Assets.at("app/vendors/bootstrap-markdown/css/bootstrap-markdown.min.css"))),format.raw/*24.112*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*25.31*/routes/*25.37*/.Assets.at("app/vendors/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"))),format.raw/*25.111*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*26.31*/routes/*26.37*/.Assets.at("app/vendors/summernote/summernote.css"))),format.raw/*26.88*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*27.31*/routes/*27.37*/.Assets.at("app/vendors/ion.rangeSlider/css/ion.rangeSlider.css"))),format.raw/*27.102*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*28.31*/routes/*28.37*/.Assets.at("app/vendors/nouislider/jquery.nouislider.css"))),format.raw/*28.95*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*29.31*/routes/*29.37*/.Assets.at("app/vendors/jquery-nestable/nestable.css"))),format.raw/*29.91*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*30.31*/routes/*30.37*/.Assets.at("app/vendors/jquery-toastr/toastr.min.css"))),format.raw/*30.91*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*31.31*/routes/*31.37*/.Assets.at("app/vendors/jstree/dist/themes/default/style.min.css"))),format.raw/*31.103*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*32.31*/routes/*32.37*/.Assets.at("app/vendors/jquery-treetable/stylesheets/jquery.treetable.css"))),format.raw/*32.112*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*33.31*/routes/*33.37*/.Assets.at("app/vendors/jquery-treetable/stylesheets/jquery.treetable.theme.custom.css"))),format.raw/*33.125*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*34.31*/routes/*34.37*/.Assets.at("app/vendors/bootstrap-datepicker/css/datepicker.css"))),format.raw/*34.102*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*35.31*/routes/*35.37*/.Assets.at("app/vendors/fullcalendar/fullcalendar.css"))),format.raw/*35.92*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*36.31*/routes/*36.37*/.Assets.at("app/vendors/fullcalendar/fullcalendar.print.css"))),format.raw/*36.98*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*37.31*/routes/*37.37*/.Assets.at("app/vendors/lightbox/css/lightbox.css"))),format.raw/*37.88*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*38.31*/routes/*38.37*/.Assets.at("app/vendors/DataTables/media/css/jquery.dataTables.css"))),format.raw/*38.105*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*39.31*/routes/*39.37*/.Assets.at("app/vendors/DataTables/media/css/dataTables.bootstrap.css"))),format.raw/*39.108*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*40.31*/routes/*40.37*/.Assets.at("app/vendors/jquery-tablesorter/themes/blue/style-custom.css"))),format.raw/*40.110*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*41.31*/routes/*41.37*/.Assets.at("app/vendors/DataTables/media/css/dataTables.bootstrap.css"))),format.raw/*41.108*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*42.31*/routes/*42.37*/.Assets.at("app/vendors/bootstrap-datepicker/css/datepicker3.css"))),format.raw/*42.103*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*43.31*/routes/*43.37*/.Assets.at("app/vendors/dropzone/css/dropzone.css"))),format.raw/*43.88*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*44.31*/routes/*44.37*/.Assets.at("app/vendors/jquery-file-upload/css/jquery.fileupload.css"))),format.raw/*44.107*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*45.31*/routes/*45.37*/.Assets.at("app/vendors/jquery-file-upload/css/jquery.fileupload-ui.css"))),format.raw/*45.110*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*46.31*/routes/*46.37*/.Assets.at("app/vendors/jquery-file-upload/css/blueimp-gallery.min.css"))),format.raw/*46.109*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*47.31*/routes/*47.37*/.Assets.at("app/vendors/jquery-steps/css/jquery.steps.css"))),format.raw/*47.96*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*48.31*/routes/*48.37*/.Assets.at("app/vendors/bootstrap-colorpicker/css/colorpicker.css"))),format.raw/*48.104*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*49.31*/routes/*49.37*/.Assets.at("app/vendors/bootstrap-daterangepicker/daterangepicker-bs3.css"))),format.raw/*49.112*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*50.31*/routes/*50.37*/.Assets.at("app/vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"))),format.raw/*50.130*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*51.31*/routes/*51.37*/.Assets.at("app/vendors/bootstrap-timepicker/css/bootstrap-timepicker.min.css"))),format.raw/*51.116*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*52.31*/routes/*52.37*/.Assets.at("app/vendors/bootstrap-clockface/css/clockface.css"))),format.raw/*52.100*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*53.31*/routes/*53.37*/.Assets.at("app/vendors/bootstrap-switch/css/bootstrap-switch.css"))),format.raw/*53.104*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*54.31*/routes/*54.37*/.Assets.at("app/css/style.css"))),format.raw/*54.68*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*55.31*/routes/*55.37*/.Assets.at("app/vendors/jplist/html/css/jplist-custom.css"))),format.raw/*55.96*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*56.31*/routes/*56.37*/.Assets.at("app/vendors/select2/select2-madmin.css"))),format.raw/*56.89*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*57.31*/routes/*57.37*/.Assets.at("app/vendors/bootstrap-select/bootstrap-select.min.css"))),format.raw/*57.104*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*58.31*/routes/*58.37*/.Assets.at("app/vendors/multi-select/css/multi-select-madmin.css"))),format.raw/*58.103*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*59.31*/routes/*59.37*/.Assets.at("app/vendors/x-editable/select2/lib/select2-madmin.css"))),format.raw/*59.104*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*60.31*/routes/*60.37*/.Assets.at("app/vendors/x-editable/bootstrap3-editable/css/bootstrap-editable.css"))),format.raw/*60.120*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*61.31*/routes/*61.37*/.Assets.at("app/vendors/x-editable/inputs-ext/address/address.css"))),format.raw/*61.104*/(""""/>
 <link rel="stylesheet" href=""""),_display_(Seq[Any](/*62.32*/routes/*62.38*/.Assets.at("app/css/themes/style1/pink-blue.css"))),format.raw/*62.87*/(""""/>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*63.31*/routes/*63.37*/.Assets.at("app/css/themes/{{style}}/{{theme}}.css"))),format.raw/*63.89*/(""""/>
<link rel="shortcut icon" href=""""),_display_(Seq[Any](/*64.34*/routes/*64.40*/.Assets.at("app/images/icons/favicon.ico"))),format.raw/*64.82*/(""""/>
<link rel="apple-touch-icon" href=""""),_display_(Seq[Any](/*65.37*/routes/*65.43*/.Assets.at("app/images/icons/favicon.png"))),format.raw/*65.85*/(""""/>
<link rel="apple-touch-icon" sizes="72x72" href=""""),_display_(Seq[Any](/*66.51*/routes/*66.57*/.Assets.at("app/images/icons/favicon-72x72.png"))),format.raw/*66.105*/(""""/>
<link rel="apple-touch-icon" sizes="114x114" href=""""),_display_(Seq[Any](/*67.53*/routes/*67.59*/.Assets.at("app/images/icons/favicon-114x114.png"))),format.raw/*67.109*/(""""/>
<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
			<!-- <script src=""""),_display_(Seq[Any](/*69.23*/routes/*69.29*/.Assets.at("app/vendors/ckeditor/ckeditor.js"))),format.raw/*69.75*/(""""></script> -->
			<!-- <script src=""""),_display_(Seq[Any](/*70.23*/routes/*70.29*/.Assets.at("app/js/lib.js"))),format.raw/*70.56*/(""""></script> -->
			 <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

  
<script src=""""),_display_(Seq[Any](/*75.15*/routes/*75.21*/.Assets.at("app/bower_components/angular/jquery.min.js"))),format.raw/*75.77*/(""""></script>
    	    <script src=""""),_display_(Seq[Any](/*76.24*/routes/*76.30*/.Assets.at("app/bower_components/angular/angular.min.js"))),format.raw/*76.87*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*77.27*/routes/*77.33*/.Assets.at("app/bower_components/angular/angular-route.min.js"))),format.raw/*77.96*/(""""></script>
         
			<script src=""""),_display_(Seq[Any](/*79.18*/routes/*79.24*/.Assets.at("app/bower_components/angular/jquery.min.js"))),format.raw/*79.80*/(""""></script>
			<script src=""""),_display_(Seq[Any](/*80.18*/routes/*80.24*/.Assets.at("app/vendors/iCheck/icheck.min.js"))),format.raw/*80.70*/(""""></script>
			<script src=""""),_display_(Seq[Any](/*81.18*/routes/*81.24*/.Assets.at("app/vendors/iCheck/custom.min.js"))),format.raw/*81.70*/(""""></script>
 			<!--   <script src=""""),_display_(Seq[Any](/*82.26*/routes/*82.32*/.Assets.at("app/bower_components/angular/jquery.min.js"))),format.raw/*82.88*/(""""></script> -->
    			<script src=""""),_display_(Seq[Any](/*83.22*/routes/*83.28*/.Assets.at("app/bower_components/angular/angular.min.js"))),format.raw/*83.85*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*84.27*/routes/*84.33*/.Assets.at("app/bower_components/angular/angular-route.min.js"))),format.raw/*84.96*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*85.27*/routes/*85.33*/.Assets.at("app/bower_components/angular-bootstrap/ui-bootstrap.min.js"))),format.raw/*85.105*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*86.27*/routes/*86.33*/.Assets.at("app/vendors/jquery-migrate-1.2.1.min.js"))),format.raw/*86.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*87.27*/routes/*87.33*/.Assets.at("app/vendors/jquery-ui.js"))),format.raw/*87.71*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*88.27*/routes/*88.33*/.Assets.at("app/vendors/bootstrap/js/bootstrap.min.js"))),format.raw/*88.88*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*89.27*/routes/*89.33*/.Assets.at("app/vendors/jquery-bootstrap-wizard/jquery.bootstrap.wizard.min.js"))),format.raw/*89.113*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*90.27*/routes/*90.33*/.Assets.at("app/vendors/lightbox/js/lightbox.min.js"))),format.raw/*90.86*/(""""></script>
            <!-- <script src=""""),_display_(Seq[Any](/*91.32*/routes/*91.38*/.Assets.at("app/vendors/iCheck/icheck.min.js"))),format.raw/*91.84*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*92.27*/routes/*92.33*/.Assets.at("app/vendors/iCheck/custom.min.js"))),format.raw/*92.79*/(""""></script> -->
          <!--   <script src=""""),_display_(Seq[Any](/*93.32*/routes/*93.38*/.Assets.at("app/vendors/flot-chart/jquery.flot.js"))),format.raw/*93.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*94.27*/routes/*94.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.categories.js"))),format.raw/*94.95*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*95.27*/routes/*95.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.pie.js"))),format.raw/*95.88*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*96.27*/routes/*96.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.tooltip.js"))),format.raw/*96.92*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*97.27*/routes/*97.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.resize.js"))),format.raw/*97.91*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*98.27*/routes/*98.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.fillbetween.js"))),format.raw/*98.96*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*99.27*/routes/*99.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.stack.js"))),format.raw/*99.90*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*100.27*/routes/*100.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.spline.js"))),format.raw/*100.91*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*101.31*/routes/*101.37*/.Assets.at("app/vendors/calendar/zabuto_calendar.min.js"))),format.raw/*101.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*102.27*/routes/*102.33*/.Assets.at("app/vendors/slimScroll/jquery.slimscroll.js"))),format.raw/*102.90*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*103.27*/routes/*103.33*/.Assets.at("app/vendors/responsive-tabs/responsive-tabs.js"))),format.raw/*103.93*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*104.27*/routes/*104.33*/.Assets.at("app/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"))),format.raw/*104.109*/(""""></script>
         <!--   <!--  <script src=""""),_display_(Seq[Any](/*105.37*/routes/*105.43*/.Assets.at("app/vendors/bootstrap-markdown/js/bootstrap-markdown.js"))),format.raw/*105.112*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*106.27*/routes/*106.33*/.Assets.at("app/vendors/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"))),format.raw/*106.110*/(""""></script> -->
            <script src=""""),_display_(Seq[Any](/*107.27*/routes/*107.33*/.Assets.at("app/vendors/summernote/summernote.js"))),format.raw/*107.83*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*108.27*/routes/*108.33*/.Assets.at("app/vendors/jquery-notific8/jquery.notific8.min.js"))),format.raw/*108.97*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*109.27*/routes/*109.33*/.Assets.at("app/vendors/sco.message/sco.message.js"))),format.raw/*109.85*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*110.27*/routes/*110.33*/.Assets.at("app/vendors/jquery-notific8/notific8.js"))),format.raw/*110.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*111.27*/routes/*111.33*/.Assets.at("app/vendors/jquery-toastr/toastr.min.js"))),format.raw/*111.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*112.27*/routes/*112.33*/.Assets.at("app/vendors/iCheck/color_change.js"))),format.raw/*112.81*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*113.27*/routes/*113.33*/.Assets.at("app/vendors/jstree/dist/jstree.min.js"))),format.raw/*113.84*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*114.27*/routes/*114.33*/.Assets.at("app/vendors/jquery-treetable/javascripts/src/jquery.treetable.js"))),format.raw/*114.111*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*115.27*/routes/*115.33*/.Assets.at("app/vendors/ion.rangeSlider/js/ion.rangeSlider.min.js"))),format.raw/*115.100*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*116.27*/routes/*116.33*/.Assets.at("app/vendors/nouislider/jquery.nouislider.min.js"))),format.raw/*116.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*117.27*/routes/*117.33*/.Assets.at("app/vendors/jquery-nestable/jquery.nestable.js"))),format.raw/*117.93*/(""""></script>
           <script src=""""),_display_(Seq[Any](/*118.26*/routes/*118.32*/.Assets.at("app/vendors/DataTables/media/js/jquery.dataTables.js"))),format.raw/*118.98*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*119.27*/routes/*119.33*/.Assets.at("app/vendors/DataTables/media/js/dataTables.bootstrap.js"))),format.raw/*119.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*120.27*/routes/*120.33*/.Assets.at("app/vendors/DataTables/extensions/TableTools/js/dataTables.tableTools.min.js"))),format.raw/*120.123*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*121.27*/routes/*121.33*/.Assets.at("app/vendors/bootstrap-hover-dropdown/bootstrap-hover-dropdown.js"))),format.raw/*121.111*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*122.27*/routes/*122.33*/.Assets.at("app/vendors/DataTables/jquery.jeditable.js"))),format.raw/*122.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*123.27*/routes/*123.33*/.Assets.at("app/vendors/jquery-tablesorter/jquery.tablesorter.js"))),format.raw/*123.99*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*124.27*/routes/*124.33*/.Assets.at("app/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"))),format.raw/*124.106*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*125.27*/routes/*125.33*/.Assets.at("app/vendors/jquery-cookie/jquery.cookie.js"))),format.raw/*125.89*/(""""></script>
          <!--   <script src=""""),_display_(Seq[Any](/*126.32*/routes/*126.38*/.Assets.at("app/vendors/jquery-highcharts/highchart.js"))),format.raw/*126.94*/(""""></script> -->
         <!--    <script src=""""),_display_(Seq[Any](/*127.32*/routes/*127.38*/.Assets.at("app/vendors/jquery-highcharts/funnel.js"))),format.raw/*127.91*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*128.27*/routes/*128.33*/.Assets.at("app/vendors/jquery-highcharts/highcharts-more.js"))),format.raw/*128.95*/(""""></script>
          -->   
      <!--     <script src=""""),_display_(Seq[Any](/*130.30*/routes/*130.36*/.Assets.at("app/vendors/jquery-highcharts/data.js"))),format.raw/*130.87*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*131.27*/routes/*131.33*/.Assets.at("app/vendors/jquery-highcharts/exporting.js"))),format.raw/*131.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*132.27*/routes/*132.33*/.Assets.at("app/vendors/chart.js/Chart.min.js"))),format.raw/*132.80*/(""""></script>
        -->   <!--   <script src=""""),_display_(Seq[Any](/*133.36*/routes/*133.42*/.Assets.at("app/vendors/fullcalendar/fullcalendar.min.js"))),format.raw/*133.100*/(""""></script> -->
          <!--   <script src=""""),_display_(Seq[Any](/*134.32*/routes/*134.38*/.Assets.at("app/vendors/mixitup/src/jquery.mixitup.js"))),format.raw/*134.93*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*135.27*/routes/*135.33*/.Assets.at("app/vendors/jplist/html/js/vendor/modernizr.min.js"))),format.raw/*135.97*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*136.27*/routes/*136.33*/.Assets.at("app/vendors/jplist/html/js/jplist.min.js"))),format.raw/*136.87*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*137.31*/routes/*137.37*/.Assets.at("app/vendors/jquery-validate/jquery.validate.min.js"))),format.raw/*137.101*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*138.27*/routes/*138.33*/.Assets.at("app/vendors/jquery-steps/js/jquery.steps.min.js"))),format.raw/*138.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*139.27*/routes/*139.33*/.Assets.at("app/vendors/bootstrap-daterangepicker/daterangepicker.js"))),format.raw/*139.103*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*140.27*/routes/*140.33*/.Assets.at("app/vendors/moment/moment.js"))),format.raw/*140.75*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*141.27*/routes/*141.33*/.Assets.at("app/vendors/bootstrap-datepicker/js/bootstrap-datetimepicker.js"))),format.raw/*141.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*142.27*/routes/*142.33*/.Assets.at("app/vendors/bootstrap-timepicker/js/bootstrap-timepicker.js"))),format.raw/*142.106*/(""""></script>
           <!--  <script src=""""),_display_(Seq[Any](/*143.32*/routes/*143.38*/.Assets.at("app/vendors/bootstrap-clockface/js/clockface.js"))),format.raw/*143.99*/(""""></script>
          -->   <script src=""""),_display_(Seq[Any](/*144.31*/routes/*144.37*/.Assets.at("app/vendors/bootstrap-colorpicker/js/bootstrap-colorpicker.js"))),format.raw/*144.112*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*145.27*/routes/*145.33*/.Assets.at("app/vendors/bootstrap-switch/js/bootstrap-switch.min.js"))),format.raw/*145.102*/(""""></script>
           <!--  <script src=""""),_display_(Seq[Any](/*146.32*/routes/*146.38*/.Assets.at("app/vendors/jquery-maskedinput/jquery-maskedinput.js"))),format.raw/*146.104*/(""""></script>
 -->     <!--        <script src=""""),_display_(Seq[Any](/*147.36*/routes/*147.42*/.Assets.at("app/vendors/dropzone/js/dropzone.js"))),format.raw/*147.91*/(""""></script> -->
            <script src=""""),_display_(Seq[Any](/*148.27*/routes/*148.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/jquery.ui.widget.js"))),format.raw/*148.107*/(""""></script>
          <!--   <script src=""""),_display_(Seq[Any](/*149.32*/routes/*149.38*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/tmpl.min.js"))),format.raw/*149.104*/(""""></script>
           -->  <!-- <script src=""""),_display_(Seq[Any](/*150.36*/routes/*150.42*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/load-image.min.js"))),format.raw/*150.114*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*151.27*/routes/*151.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/canvas-to-blob.min.js"))),format.raw/*151.109*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*152.27*/routes/*152.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/jquery.blueimp-gallery.min.js"))),format.raw/*152.117*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*153.27*/routes/*153.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.iframe-transport.js"))),format.raw/*153.107*/(""""></script>
            --> <!-- <script src=""""),_display_(Seq[Any](/*154.36*/routes/*154.42*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload.js"))),format.raw/*154.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*155.27*/routes/*155.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-process.js"))),format.raw/*155.109*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*156.27*/routes/*156.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-image.js"))),format.raw/*156.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*157.27*/routes/*157.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-audio.js"))),format.raw/*157.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*158.27*/routes/*158.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-video.js"))),format.raw/*158.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*159.27*/routes/*159.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-validate.js"))),format.raw/*159.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*160.27*/routes/*160.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-ui.js"))),format.raw/*160.104*/(""""></script>
       -->    <!--   <script src=""""),_display_(Seq[Any](/*161.36*/routes/*161.42*/.Assets.at("app/vendors/jquery-file-upload/js/cors/jquery.xdr-transport.js"))),format.raw/*161.118*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*162.27*/routes/*162.33*/.Assets.at("app/vendors/gmaps/gmaps.js"))),format.raw/*162.73*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*163.27*/routes/*163.33*/.Assets.at("app/vendors/charCount.js"))),format.raw/*163.71*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*164.27*/routes/*164.33*/.Assets.at("app/vendors/jquery-news-ticker/jquery.newsTicker.min.js"))),format.raw/*164.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*165.27*/routes/*165.33*/.Assets.at("app/vendors/select2/select2.min.js"))),format.raw/*165.81*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*166.31*/routes/*166.37*/.Assets.at("app/vendors/bootstrap-select/bootstrap-select.min.js"))),format.raw/*166.103*/(""""></script>
          <!--   <script src=""""),_display_(Seq[Any](/*167.32*/routes/*167.38*/.Assets.at("app/vendors/multi-select/js/jquery.multi-select.js"))),format.raw/*167.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*168.27*/routes/*168.33*/.Assets.at("app/vendors/x-editable/jquery.mockjax.js"))),format.raw/*168.87*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*169.27*/routes/*169.33*/.Assets.at("app/vendors/x-editable/select2/lib/select2.js"))),format.raw/*169.92*/(""""></script>
           -->  <script src=""""),_display_(Seq[Any](/*170.31*/routes/*170.37*/.Assets.at("app/vendors/x-editable/bootstrap3-editable/js/bootstrap-editable.min.js"))),format.raw/*170.122*/(""""></script>
           <!--  <script src=""""),_display_(Seq[Any](/*171.32*/routes/*171.38*/.Assets.at("app/vendors/x-editable/inputs-ext/typeaheadjs/lib/typeahead.js"))),format.raw/*171.114*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*172.27*/routes/*172.33*/.Assets.at("app/vendors/x-editable/inputs-ext/typeaheadjs/typeaheadjs.js"))),format.raw/*172.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*173.27*/routes/*173.33*/.Assets.at("app/vendors/x-editable/inputs-ext/wysihtml5/wysihtml5.js"))),format.raw/*173.103*/(""""></script>
          -->  <!--  <script src=""""),_display_(Seq[Any](/*174.36*/routes/*174.42*/.Assets.at("app/vendors/x-editable/inputs-ext/address/address.js"))),format.raw/*174.108*/(""""></script>
         -->    <script src=""""),_display_(Seq[Any](/*175.31*/routes/*175.37*/.Assets.at("app/vendors/x-editable/demo-mock.js"))),format.raw/*175.86*/(""""></script>
     <script src=""""),_display_(Seq[Any](/*176.20*/routes/*176.26*/.Assets.at("app/js/main.js"))),format.raw/*176.54*/(""""></script>
            </head>
<body  ng-controller="loginController" class="[header.menu_style, header.header_topbar, header.effect]"
	class="animated" >
	<div id="signin-page">
		<div class="page-form-wrapper"></div>
		<div class="page-form">
			<form action="/SignIn" method = "POST" class="form">
				<p style = "color:red">"""),_display_(Seq[Any](/*184.29*/flash/*184.34*/.get("error"))),format.raw/*184.47*/("""</p>
				<p style = "color:red">"""),_display_(Seq[Any](/*185.29*/flash/*185.34*/.get("AccountError"))),format.raw/*185.54*/("""</p>
				<p   id="success" style = "color:red">"""),_display_(Seq[Any](/*186.44*/flash/*186.49*/.get("email_success"))),format.raw/*186.70*/(""" </p>
				<div class="header-content">
					<h1>Log In</h1>
				</div>
				<div class="body-content">
					
					<div class="form-group">
						<div class="input-icon right">
							<i class="fa fa-user"></i> <input type="text"
								placeholder="Username" name="username" id="username" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-icon right">
							<i class="fa fa-key"></i> <input type="password"
								placeholder="Password" name="password" class="form-control" />
						</div>
					</div>
					<!-- <div class="form-group pull-left">
						<div class="checkbox-list">
							<label> <input type="checkbox" />&nbsp; Keep me signed
								in
							</label>
						</div>
					</div> -->
					<div class="form-group pull-right">
						<button type="submit"
							class="btn btn-success">
							Log In &nbsp;<i class="fa fa-chevron-circle-right"></i>
						</button>
					</div>
					<div class="clearfix"></div>
				 <div class="forget-password">
						<h4>Forgotten your Password?</h4>
						<p>
							no worries, click <a onclick='forgetPassword(username.val)' 
								class='btn-forgot-pwd'>here to reset your password.</a>
						</p>
					</div>
					<hr />
				 <p>
						Don't have an account? <a id="btn-register" href="/signup">Register
							Now</a>
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	function forgetPassword(username)"""),format.raw/*236.35*/("""{"""),format.raw/*236.36*/("""
		var username = $('#username').val();
    	$.ajax("""),format.raw/*238.13*/("""{"""),format.raw/*238.14*/("""url:"/forgetPassword",
    		 type: 'POST',
    	     data: """),format.raw/*240.17*/("""{"""),format.raw/*240.18*/(""" email: username"""),format.raw/*240.34*/("""}"""),format.raw/*240.35*/(""" ,
    	success:function(result)"""),format.raw/*241.30*/("""{"""),format.raw/*241.31*/("""
        console.log("success");
        $("#success").show();
        
    """),format.raw/*245.5*/("""}"""),format.raw/*245.6*/("""}"""),format.raw/*245.7*/(""");
  """),format.raw/*246.3*/("""}"""),format.raw/*246.4*/("""

</script>
</html>"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 06 11:05:07 UTC 2015
                    SOURCE: /home/jobby/app/views/signin.scala.html
                    HASH: 0f8db817874db15025baaabb0a61c99caf117d99
                    MATRIX: 856->0|1410->518|1425->524|1546->622|1616->656|1631->662|1717->725|1787->759|1802->765|1881->822|1951->856|1966->862|2034->908|2104->942|2119->948|2199->1006|2269->1040|2284->1046|2359->1099|2429->1133|2444->1139|2515->1188|2585->1222|2600->1228|2668->1274|2738->1308|2753->1314|2841->1379|2911->1413|2926->1419|3001->1472|3071->1506|3086->1512|3196->1599|3266->1633|3281->1639|3379->1714|3449->1748|3464->1754|3561->1828|3631->1862|3646->1868|3719->1919|3789->1953|3804->1959|3892->2024|3962->2058|3977->2064|4057->2122|4127->2156|4142->2162|4218->2216|4288->2250|4303->2256|4379->2310|4449->2344|4464->2350|4553->2416|4623->2450|4638->2456|4736->2531|4806->2565|4821->2571|4932->2659|5002->2693|5017->2699|5105->2764|5175->2798|5190->2804|5267->2859|5337->2893|5352->2899|5435->2960|5505->2994|5520->3000|5593->3051|5663->3085|5678->3091|5769->3159|5839->3193|5854->3199|5948->3270|6018->3304|6033->3310|6129->3383|6199->3417|6214->3423|6308->3494|6378->3528|6393->3534|6482->3600|6552->3634|6567->3640|6640->3691|6710->3725|6725->3731|6818->3801|6888->3835|6903->3841|6999->3914|7069->3948|7084->3954|7179->4026|7249->4060|7264->4066|7345->4125|7415->4159|7430->4165|7520->4232|7590->4266|7605->4272|7703->4347|7773->4381|7788->4387|7904->4480|7974->4514|7989->4520|8091->4599|8161->4633|8176->4639|8262->4702|8332->4736|8347->4742|8437->4809|8507->4843|8522->4849|8575->4880|8645->4914|8660->4920|8741->4979|8811->5013|8826->5019|8900->5071|8970->5105|8985->5111|9075->5178|9145->5212|9160->5218|9249->5284|9319->5318|9334->5324|9424->5391|9494->5425|9509->5431|9615->5514|9685->5548|9700->5554|9790->5621|9861->5656|9876->5662|9947->5711|10017->5745|10032->5751|10106->5803|10179->5840|10194->5846|10258->5888|10334->5928|10349->5934|10413->5976|10503->6030|10518->6036|10589->6084|10681->6140|10696->6146|10769->6196|10902->6293|10917->6299|10985->6345|11059->6383|11074->6389|11123->6416|11330->6587|11345->6593|11423->6649|11494->6684|11509->6690|11588->6747|11662->6785|11677->6791|11762->6854|11837->6893|11852->6899|11930->6955|11995->6984|12010->6990|12078->7036|12143->7065|12158->7071|12226->7117|12299->7154|12314->7160|12392->7216|12465->7253|12480->7259|12559->7316|12633->7354|12648->7360|12733->7423|12807->7461|12822->7467|12917->7539|12991->7577|13006->7583|13081->7636|13155->7674|13170->7680|13230->7718|13304->7756|13319->7762|13396->7817|13470->7855|13485->7861|13588->7941|13662->7979|13677->7985|13752->8038|13831->8081|13846->8087|13914->8133|13988->8171|14003->8177|14071->8223|14154->8270|14169->8276|14242->8327|14316->8365|14331->8371|14415->8433|14489->8471|14504->8477|14581->8532|14655->8570|14670->8576|14751->8635|14825->8673|14840->8679|14920->8737|14994->8775|15009->8781|15094->8844|15168->8882|15183->8888|15262->8945|15337->8983|15353->8989|15434->9047|15513->9089|15529->9095|15609->9152|15684->9190|15700->9196|15780->9253|15855->9291|15871->9297|15954->9357|16029->9395|16045->9401|16145->9477|16230->9525|16246->9531|16339->9600|16414->9638|16430->9644|16531->9721|16610->9763|16626->9769|16699->9819|16774->9857|16790->9863|16877->9927|16952->9965|16968->9971|17043->10023|17118->10061|17134->10067|17210->10120|17285->10158|17301->10164|17377->10217|17452->10255|17468->10261|17539->10309|17614->10347|17630->10353|17704->10404|17779->10442|17795->10448|17897->10526|17972->10564|17988->10570|18079->10637|18154->10675|18170->10681|18254->10742|18329->10780|18345->10786|18428->10846|18502->10883|18518->10889|18607->10955|18682->10993|18698->10999|18791->11068|18866->11106|18882->11112|18996->11202|19071->11240|19087->11246|19189->11324|19264->11362|19280->11368|19359->11424|19434->11462|19450->11468|19539->11534|19614->11572|19630->11578|19727->11651|19802->11689|19818->11695|19897->11751|19977->11794|19993->11800|20072->11856|20156->11903|20172->11909|20248->11962|20323->12000|20339->12006|20424->12068|20519->12126|20535->12132|20609->12183|20684->12221|20700->12227|20779->12283|20854->12321|20870->12327|20940->12374|21024->12421|21040->12427|21122->12485|21206->12532|21222->12538|21300->12593|21375->12631|21391->12637|21478->12701|21553->12739|21569->12745|21646->12799|21725->12841|21741->12847|21829->12911|21904->12949|21920->12955|22004->13016|22079->13054|22095->13060|22189->13130|22264->13168|22280->13174|22345->13216|22420->13254|22436->13260|22537->13337|22612->13375|22628->13381|22725->13454|22805->13497|22821->13503|22905->13564|22984->13606|23000->13612|23099->13687|23174->13725|23190->13731|23283->13800|23363->13843|23379->13849|23469->13915|23553->13962|23569->13968|23641->14017|23720->14059|23736->14065|23834->14139|23914->14182|23930->14188|24020->14254|24104->14301|24120->14307|24216->14379|24291->14417|24307->14423|24407->14499|24482->14537|24498->14543|24606->14627|24681->14665|24697->14671|24795->14745|24879->14792|24895->14798|24987->14866|25062->14904|25078->14910|25178->14986|25253->15024|25269->15030|25367->15104|25442->15142|25458->15148|25556->15222|25631->15260|25647->15266|25745->15340|25820->15378|25836->15384|25937->15461|26012->15499|26028->15505|26123->15576|26207->15623|26223->15629|26323->15705|26398->15743|26414->15749|26477->15789|26552->15827|26568->15833|26629->15871|26704->15909|26720->15915|26813->15984|26888->16022|26904->16028|26975->16076|27054->16118|27070->16124|27160->16190|27240->16233|27256->16239|27344->16303|27419->16341|27435->16347|27512->16401|27587->16439|27603->16445|27685->16504|27764->16546|27780->16552|27889->16637|27969->16680|27985->16686|28085->16762|28160->16800|28176->16806|28274->16880|28349->16918|28365->16924|28459->16994|28543->17041|28559->17047|28649->17113|28728->17155|28744->17161|28816->17210|28884->17241|28900->17247|28951->17275|29318->17605|29333->17610|29369->17623|29439->17656|29454->17661|29497->17681|29582->17729|29597->17734|29641->17755|31100->19185|31130->19186|31211->19238|31241->19239|31330->19299|31360->19300|31405->19316|31435->19317|31496->19349|31526->19350|31630->19426|31659->19427|31688->19428|31721->19433|31750->19434
                    LINES: 29->1|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|48->20|48->20|48->20|49->21|49->21|49->21|50->22|50->22|50->22|51->23|51->23|51->23|52->24|52->24|52->24|53->25|53->25|53->25|54->26|54->26|54->26|55->27|55->27|55->27|56->28|56->28|56->28|57->29|57->29|57->29|58->30|58->30|58->30|59->31|59->31|59->31|60->32|60->32|60->32|61->33|61->33|61->33|62->34|62->34|62->34|63->35|63->35|63->35|64->36|64->36|64->36|65->37|65->37|65->37|66->38|66->38|66->38|67->39|67->39|67->39|68->40|68->40|68->40|69->41|69->41|69->41|70->42|70->42|70->42|71->43|71->43|71->43|72->44|72->44|72->44|73->45|73->45|73->45|74->46|74->46|74->46|75->47|75->47|75->47|76->48|76->48|76->48|77->49|77->49|77->49|78->50|78->50|78->50|79->51|79->51|79->51|80->52|80->52|80->52|81->53|81->53|81->53|82->54|82->54|82->54|83->55|83->55|83->55|84->56|84->56|84->56|85->57|85->57|85->57|86->58|86->58|86->58|87->59|87->59|87->59|88->60|88->60|88->60|89->61|89->61|89->61|90->62|90->62|90->62|91->63|91->63|91->63|92->64|92->64|92->64|93->65|93->65|93->65|94->66|94->66|94->66|95->67|95->67|95->67|97->69|97->69|97->69|98->70|98->70|98->70|103->75|103->75|103->75|104->76|104->76|104->76|105->77|105->77|105->77|107->79|107->79|107->79|108->80|108->80|108->80|109->81|109->81|109->81|110->82|110->82|110->82|111->83|111->83|111->83|112->84|112->84|112->84|113->85|113->85|113->85|114->86|114->86|114->86|115->87|115->87|115->87|116->88|116->88|116->88|117->89|117->89|117->89|118->90|118->90|118->90|119->91|119->91|119->91|120->92|120->92|120->92|121->93|121->93|121->93|122->94|122->94|122->94|123->95|123->95|123->95|124->96|124->96|124->96|125->97|125->97|125->97|126->98|126->98|126->98|127->99|127->99|127->99|128->100|128->100|128->100|129->101|129->101|129->101|130->102|130->102|130->102|131->103|131->103|131->103|132->104|132->104|132->104|133->105|133->105|133->105|134->106|134->106|134->106|135->107|135->107|135->107|136->108|136->108|136->108|137->109|137->109|137->109|138->110|138->110|138->110|139->111|139->111|139->111|140->112|140->112|140->112|141->113|141->113|141->113|142->114|142->114|142->114|143->115|143->115|143->115|144->116|144->116|144->116|145->117|145->117|145->117|146->118|146->118|146->118|147->119|147->119|147->119|148->120|148->120|148->120|149->121|149->121|149->121|150->122|150->122|150->122|151->123|151->123|151->123|152->124|152->124|152->124|153->125|153->125|153->125|154->126|154->126|154->126|155->127|155->127|155->127|156->128|156->128|156->128|158->130|158->130|158->130|159->131|159->131|159->131|160->132|160->132|160->132|161->133|161->133|161->133|162->134|162->134|162->134|163->135|163->135|163->135|164->136|164->136|164->136|165->137|165->137|165->137|166->138|166->138|166->138|167->139|167->139|167->139|168->140|168->140|168->140|169->141|169->141|169->141|170->142|170->142|170->142|171->143|171->143|171->143|172->144|172->144|172->144|173->145|173->145|173->145|174->146|174->146|174->146|175->147|175->147|175->147|176->148|176->148|176->148|177->149|177->149|177->149|178->150|178->150|178->150|179->151|179->151|179->151|180->152|180->152|180->152|181->153|181->153|181->153|182->154|182->154|182->154|183->155|183->155|183->155|184->156|184->156|184->156|185->157|185->157|185->157|186->158|186->158|186->158|187->159|187->159|187->159|188->160|188->160|188->160|189->161|189->161|189->161|190->162|190->162|190->162|191->163|191->163|191->163|192->164|192->164|192->164|193->165|193->165|193->165|194->166|194->166|194->166|195->167|195->167|195->167|196->168|196->168|196->168|197->169|197->169|197->169|198->170|198->170|198->170|199->171|199->171|199->171|200->172|200->172|200->172|201->173|201->173|201->173|202->174|202->174|202->174|203->175|203->175|203->175|204->176|204->176|204->176|212->184|212->184|212->184|213->185|213->185|213->185|214->186|214->186|214->186|264->236|264->236|266->238|266->238|268->240|268->240|268->240|268->240|269->241|269->241|273->245|273->245|273->245|274->246|274->246
                    -- GENERATED --
                */
            
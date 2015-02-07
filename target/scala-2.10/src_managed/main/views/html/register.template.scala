
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
object register extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html lang="en">
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
<!-- <script src=""""),_display_(Seq[Any](/*69.20*/routes/*69.26*/.Assets.at("app/vendors/ckeditor/ckeditor.js"))),format.raw/*69.72*/(""""></script> -->
<!-- <script src=""""),_display_(Seq[Any](/*70.20*/routes/*70.26*/.Assets.at("app/js/lib.js"))),format.raw/*70.53*/(""""></script> -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
            <script src=""""),_display_(Seq[Any](/*72.27*/routes/*72.33*/.Assets.at("app/bower_components/angular/jquery.min.js"))),format.raw/*72.89*/(""""></script>
    	    <script src=""""),_display_(Seq[Any](/*73.24*/routes/*73.30*/.Assets.at("app/bower_components/angular/angular.min.js"))),format.raw/*73.87*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*74.27*/routes/*74.33*/.Assets.at("app/bower_components/angular/angular-route.min.js"))),format.raw/*74.96*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*75.27*/routes/*75.33*/.Assets.at("app/bower_components/angular-bootstrap/ui-bootstrap.min.js"))),format.raw/*75.105*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*76.27*/routes/*76.33*/.Assets.at("app/vendors/jquery-migrate-1.2.1.min.js"))),format.raw/*76.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*77.27*/routes/*77.33*/.Assets.at("app/vendors/jquery-ui.js"))),format.raw/*77.71*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*78.27*/routes/*78.33*/.Assets.at("app/vendors/bootstrap/js/bootstrap.min.js"))),format.raw/*78.88*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*79.27*/routes/*79.33*/.Assets.at("app/vendors/jquery-bootstrap-wizard/jquery.bootstrap.wizard.min.js"))),format.raw/*79.113*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*80.27*/routes/*80.33*/.Assets.at("app/vendors/lightbox/js/lightbox.min.js"))),format.raw/*80.86*/(""""></script>
            <!-- <script src=""""),_display_(Seq[Any](/*81.32*/routes/*81.38*/.Assets.at("app/vendors/iCheck/icheck.min.js"))),format.raw/*81.84*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*82.27*/routes/*82.33*/.Assets.at("app/vendors/iCheck/custom.min.js"))),format.raw/*82.79*/(""""></script> -->
            <!--   <script src=""""),_display_(Seq[Any](/*83.34*/routes/*83.40*/.Assets.at("app/vendors/flot-chart/jquery.flot.js"))),format.raw/*83.91*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*84.27*/routes/*84.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.categories.js"))),format.raw/*84.95*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*85.27*/routes/*85.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.pie.js"))),format.raw/*85.88*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*86.27*/routes/*86.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.tooltip.js"))),format.raw/*86.92*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*87.27*/routes/*87.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.resize.js"))),format.raw/*87.91*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*88.27*/routes/*88.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.fillbetween.js"))),format.raw/*88.96*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*89.27*/routes/*89.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.stack.js"))),format.raw/*89.90*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*90.27*/routes/*90.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.spline.js"))),format.raw/*90.91*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*91.31*/routes/*91.37*/.Assets.at("app/vendors/calendar/zabuto_calendar.min.js"))),format.raw/*91.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*92.27*/routes/*92.33*/.Assets.at("app/vendors/slimScroll/jquery.slimscroll.js"))),format.raw/*92.90*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*93.27*/routes/*93.33*/.Assets.at("app/vendors/responsive-tabs/responsive-tabs.js"))),format.raw/*93.93*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*94.27*/routes/*94.33*/.Assets.at("app/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"))),format.raw/*94.109*/(""""></script>
            <!--    <script src=""""),_display_(Seq[Any](/*95.35*/routes/*95.41*/.Assets.at("app/vendors/bootstrap-markdown/js/bootstrap-markdown.js"))),format.raw/*95.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*96.27*/routes/*96.33*/.Assets.at("app/vendors/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"))),format.raw/*96.110*/(""""></script> -->
            <!--  <script src=""""),_display_(Seq[Any](/*97.33*/routes/*97.39*/.Assets.at("app/vendors/summernote/summernote.js"))),format.raw/*97.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*98.27*/routes/*98.33*/.Assets.at("app/vendors/jquery-notific8/jquery.notific8.min.js"))),format.raw/*98.97*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*99.27*/routes/*99.33*/.Assets.at("app/vendors/sco.message/sco.message.js"))),format.raw/*99.85*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*100.27*/routes/*100.33*/.Assets.at("app/vendors/jquery-notific8/notific8.js"))),format.raw/*100.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*101.27*/routes/*101.33*/.Assets.at("app/vendors/jquery-toastr/toastr.min.js"))),format.raw/*101.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*102.27*/routes/*102.33*/.Assets.at("app/vendors/iCheck/color_change.js"))),format.raw/*102.81*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*103.27*/routes/*103.33*/.Assets.at("app/vendors/jstree/dist/jstree.min.js"))),format.raw/*103.84*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*104.27*/routes/*104.33*/.Assets.at("app/vendors/jquery-treetable/javascripts/src/jquery.treetable.js"))),format.raw/*104.111*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*105.27*/routes/*105.33*/.Assets.at("app/vendors/ion.rangeSlider/js/ion.rangeSlider.min.js"))),format.raw/*105.100*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*106.27*/routes/*106.33*/.Assets.at("app/vendors/nouislider/jquery.nouislider.min.js"))),format.raw/*106.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*107.27*/routes/*107.33*/.Assets.at("app/vendors/jquery-nestable/jquery.nestable.js"))),format.raw/*107.93*/(""""></script>
             -->       <script src=""""),_display_(Seq[Any](/*108.38*/routes/*108.44*/.Assets.at("app/vendors/DataTables/media/js/jquery.dataTables.js"))),format.raw/*108.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*109.27*/routes/*109.33*/.Assets.at("app/vendors/DataTables/media/js/dataTables.bootstrap.js"))),format.raw/*109.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*110.27*/routes/*110.33*/.Assets.at("app/vendors/DataTables/extensions/TableTools/js/dataTables.tableTools.min.js"))),format.raw/*110.123*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*111.27*/routes/*111.33*/.Assets.at("app/vendors/bootstrap-hover-dropdown/bootstrap-hover-dropdown.js"))),format.raw/*111.111*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*112.27*/routes/*112.33*/.Assets.at("app/vendors/DataTables/jquery.jeditable.js"))),format.raw/*112.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*113.27*/routes/*113.33*/.Assets.at("app/vendors/jquery-tablesorter/jquery.tablesorter.js"))),format.raw/*113.99*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*114.27*/routes/*114.33*/.Assets.at("app/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"))),format.raw/*114.106*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*115.27*/routes/*115.33*/.Assets.at("app/vendors/jquery-cookie/jquery.cookie.js"))),format.raw/*115.89*/(""""></script>
            <!-- <script src=""""),_display_(Seq[Any](/*116.32*/routes/*116.38*/.Assets.at("app/vendors/jquery-highcharts/highchart.js"))),format.raw/*116.94*/(""""></script> -->
             <!--    <script src=""""),_display_(Seq[Any](/*117.36*/routes/*117.42*/.Assets.at("app/vendors/jquery-highcharts/funnel.js"))),format.raw/*117.95*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*118.27*/routes/*118.33*/.Assets.at("app/vendors/jquery-highcharts/highcharts-more.js"))),format.raw/*118.95*/(""""></script>
             -->   
            <!--     <script src=""""),_display_(Seq[Any](/*120.36*/routes/*120.42*/.Assets.at("app/vendors/jquery-highcharts/data.js"))),format.raw/*120.93*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*121.27*/routes/*121.33*/.Assets.at("app/vendors/jquery-highcharts/exporting.js"))),format.raw/*121.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*122.27*/routes/*122.33*/.Assets.at("app/vendors/chart.js/Chart.min.js"))),format.raw/*122.80*/(""""></script>
            -->     <!-- <script src=""""),_display_(Seq[Any](/*123.40*/routes/*123.46*/.Assets.at("app/vendors/fullcalendar/fullcalendar.min.js"))),format.raw/*123.104*/(""""></script> -->
            <!--   <script src=""""),_display_(Seq[Any](/*124.34*/routes/*124.40*/.Assets.at("app/vendors/mixitup/src/jquery.mixitup.js"))),format.raw/*124.95*/(""""></script>
             <script src=""""),_display_(Seq[Any](/*125.28*/routes/*125.34*/.Assets.at("app/vendors/jplist/html/js/vendor/modernizr.min.js"))),format.raw/*125.98*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*126.27*/routes/*126.33*/.Assets.at("app/vendors/jplist/html/js/jplist.min.js"))),format.raw/*126.87*/(""""></script>
             --> <script src=""""),_display_(Seq[Any](/*127.32*/routes/*127.38*/.Assets.at("app/vendors/jquery-validate/jquery.validate.min.js"))),format.raw/*127.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*128.27*/routes/*128.33*/.Assets.at("app/vendors/jquery-steps/js/jquery.steps.min.js"))),format.raw/*128.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*129.27*/routes/*129.33*/.Assets.at("app/vendors/bootstrap-daterangepicker/daterangepicker.js"))),format.raw/*129.103*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*130.27*/routes/*130.33*/.Assets.at("app/vendors/moment/moment.js"))),format.raw/*130.75*/(""""></script>
            <!--  <script src=""""),_display_(Seq[Any](/*131.33*/routes/*131.39*/.Assets.at("app/vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"))),format.raw/*131.130*/(""""></script>
           -->   <script src=""""),_display_(Seq[Any](/*132.32*/routes/*132.38*/.Assets.at("app/vendors/bootstrap-timepicker/js/bootstrap-timepicker.js"))),format.raw/*132.111*/(""""></script>
            <!--  <script src=""""),_display_(Seq[Any](/*133.33*/routes/*133.39*/.Assets.at("app/vendors/bootstrap-clockface/js/clockface.js"))),format.raw/*133.100*/(""""></script>
            -->   <script src=""""),_display_(Seq[Any](/*134.33*/routes/*134.39*/.Assets.at("app/vendors/bootstrap-colorpicker/js/bootstrap-colorpicker.js"))),format.raw/*134.114*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*135.27*/routes/*135.33*/.Assets.at("app/vendors/bootstrap-switch/js/bootstrap-switch.min.js"))),format.raw/*135.102*/(""""></script>
           <!--  <script src=""""),_display_(Seq[Any](/*136.32*/routes/*136.38*/.Assets.at("app/vendors/jquery-maskedinput/jquery-maskedinput.js"))),format.raw/*136.104*/(""""></script>
             -->     <!--        <script src=""""),_display_(Seq[Any](/*137.48*/routes/*137.54*/.Assets.at("app/vendors/dropzone/js/dropzone.js"))),format.raw/*137.103*/(""""></script> -->
            <script src=""""),_display_(Seq[Any](/*138.27*/routes/*138.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/jquery.ui.widget.js"))),format.raw/*138.107*/(""""></script>
           <!--   <script src=""""),_display_(Seq[Any](/*139.33*/routes/*139.39*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/tmpl.min.js"))),format.raw/*139.105*/(""""></script>
           -->  <!-- <script src=""""),_display_(Seq[Any](/*140.36*/routes/*140.42*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/load-image.min.js"))),format.raw/*140.114*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*141.27*/routes/*141.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/canvas-to-blob.min.js"))),format.raw/*141.109*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*142.27*/routes/*142.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/jquery.blueimp-gallery.min.js"))),format.raw/*142.117*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*143.27*/routes/*143.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.iframe-transport.js"))),format.raw/*143.107*/(""""></script>
            --> <!-- <script src=""""),_display_(Seq[Any](/*144.36*/routes/*144.42*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload.js"))),format.raw/*144.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*145.27*/routes/*145.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-process.js"))),format.raw/*145.109*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*146.27*/routes/*146.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-image.js"))),format.raw/*146.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*147.27*/routes/*147.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-audio.js"))),format.raw/*147.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*148.27*/routes/*148.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-video.js"))),format.raw/*148.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*149.27*/routes/*149.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-validate.js"))),format.raw/*149.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*150.27*/routes/*150.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-ui.js"))),format.raw/*150.104*/(""""></script>
             -->    <!--   <script src=""""),_display_(Seq[Any](/*151.42*/routes/*151.48*/.Assets.at("app/vendors/jquery-file-upload/js/cors/jquery.xdr-transport.js"))),format.raw/*151.124*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*152.27*/routes/*152.33*/.Assets.at("app/vendors/gmaps/gmaps.js"))),format.raw/*152.73*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*153.27*/routes/*153.33*/.Assets.at("app/vendors/charCount.js"))),format.raw/*153.71*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*154.27*/routes/*154.33*/.Assets.at("app/vendors/jquery-news-ticker/jquery.newsTicker.min.js"))),format.raw/*154.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*155.27*/routes/*155.33*/.Assets.at("app/vendors/select2/select2.min.js"))),format.raw/*155.81*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*156.31*/routes/*156.37*/.Assets.at("app/vendors/bootstrap-select/bootstrap-select.min.js"))),format.raw/*156.103*/(""""></script>
            <!--   <script src=""""),_display_(Seq[Any](/*157.34*/routes/*157.40*/.Assets.at("app/vendors/multi-select/js/jquery.multi-select.js"))),format.raw/*157.104*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*158.27*/routes/*158.33*/.Assets.at("app/vendors/x-editable/jquery.mockjax.js"))),format.raw/*158.87*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*159.27*/routes/*159.33*/.Assets.at("app/vendors/x-editable/select2/lib/select2.js"))),format.raw/*159.92*/(""""></script>
            -->  <script src=""""),_display_(Seq[Any](/*160.32*/routes/*160.38*/.Assets.at("app/vendors/x-editable/bootstrap3-editable/js/bootstrap-editable.min.js"))),format.raw/*160.123*/(""""></script>
            <!--  <script src=""""),_display_(Seq[Any](/*161.33*/routes/*161.39*/.Assets.at("app/vendors/x-editable/inputs-ext/typeaheadjs/lib/typeahead.js"))),format.raw/*161.115*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*162.27*/routes/*162.33*/.Assets.at("app/vendors/x-editable/inputs-ext/typeaheadjs/typeaheadjs.js"))),format.raw/*162.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*163.27*/routes/*163.33*/.Assets.at("app/vendors/x-editable/inputs-ext/wysihtml5/wysihtml5.js"))),format.raw/*163.103*/(""""></script>
             -->  <!--  <script src=""""),_display_(Seq[Any](/*164.39*/routes/*164.45*/.Assets.at("app/vendors/x-editable/inputs-ext/address/address.js"))),format.raw/*164.111*/(""""></script>
            -->   <!--  <script src=""""),_display_(Seq[Any](/*165.39*/routes/*165.45*/.Assets.at("app/vendors/x-editable/demo-mock.js"))),format.raw/*165.94*/(""""></script>
            -->
   
    
     </head>
<body class="[header.menu_style, header.header_topbar, header.effect]"
	class="animated">
	
<div id="signup-page">
  <div class="page-form">
    <form id="signup-form" method="POST" action="/createNewUser" class="form signup-form">
      <div class="header-content">
        <h1>Register</h1>
      </div>
      """),_display_(Seq[Any](/*179.8*/flash/*179.13*/.get("error"))),format.raw/*179.26*/(""";
      <div class="body-content">
       <!--  <div class="form-group">
          <div class="input-icon right"><i class="fa fa-user"></i>
            <input type="text" placeholder="Username" name="username" class="form-control"/>
          </div>
        </div> -->
        <div class="form-group">
          <div class="input-icon right"><i class="fa fa-envelope"></i>
            <input type="email" placeholder="Email address" name="email" class="form-control"/>
          </div>
        </div>
        <div class="form-group">
          <div class="input-icon right"><i class="fa fa-key"></i>
            <input id="password" type="password" placeholder="Password" name="password" onkeyup="validatePasswordstrong(this.value,document.getElementById('strendth'),document.getElementById('passError'));" minlength = "10"  class="form-control" />
          	<span id="passError"></span><span id="strendth"></span>
          </div>
        </div>
        <div class="form-group">
          <div class="input-icon right"><i class="fa fa-key"></i>
            <input type="password" placeholder="Confirm Password" name="passwordConfirm"  onClick="validatePassword();" class="form-control"/>
          </div>
        </div>
        <hr/>
       <div class="form-group">
          <div class="input-icon right">
              <input type="text" placeholder="First Name" name="firstname" class="form-control"/>
               <!-- <input type="password" placeholder="Confirm Password" name="passwordConfirm" class="form-control"/> -->
          </div>
        </div>
         <div class="form-group">
          <div class="input-icon right">
              <input type="text" placeholder="Middle Name" name="middlename" class="form-control"/>
               <!-- <input type="password" placeholder="Confirm Password" name="passwordConfirm" class="form-control"/> -->
          </div>
        </div>
         <div class="form-group">
          <div class="input-icon right">
              <input type="text" placeholder="Last Name" name="lastname" class="form-control"/>
               <!-- <input type="password" placeholder="Confirm Password" name="passwordConfirm" class="form-control"/> -->
          </div>
        </div>
       
         <!--  <div class="col-lg-6">
            <label>
              <input type="text" placeholder="Last Name" name="lastname" class="form-control"/>
            </label>
          </div> -->
       
        <div class="form-group">
          <label style="display: block" class="select">
            <select name="gender" class="form-control">
              <option value="0" selected="selected" disabled="disabled">Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
          </label>
        </div>
         </div>
        <hr/>
        <div class="form-group mbn"><a href="/login" class="btn btn-warning"><i class="fa fa-chevron-circle-left"></i>&nbsp;
            Back</a>
          <button type="submit" class="btn btn-info pull-right">
            Submit
            &nbsp;<i class="fa fa-chevron-circle-right"></i>
          </button>
        </div>
        </form>
      </div>
    
  </div>
 <script>

function validatePassword()"""),format.raw/*253.28*/("""{"""),format.raw/*253.29*/(""" 
 var validator = $("#signup-form").validate("""),format.raw/*254.45*/("""{"""),format.raw/*254.46*/("""
  rules: """),format.raw/*255.10*/("""{"""),format.raw/*255.11*/("""                   
	  password :"required",
   passwordConfirm:"""),format.raw/*257.20*/("""{"""),format.raw/*257.21*/("""
    equalTo: "#password"
      """),format.raw/*259.7*/("""}"""),format.raw/*259.8*/("""  
     """),format.raw/*260.6*/("""}"""),format.raw/*260.7*/(""",                             
     messages: """),format.raw/*261.16*/("""{"""),format.raw/*261.17*/("""
      password :" Enter Password",
      passwordConfirm :" Enter Confirm Password Same as Password"
     """),format.raw/*264.6*/("""}"""),format.raw/*264.7*/("""
 """),format.raw/*265.2*/("""}"""),format.raw/*265.3*/(""");
 
"""),format.raw/*267.1*/("""}"""),format.raw/*267.2*/("""


function validatePasswordstrong(password,passwordStrength,errorField)"""),format.raw/*270.70*/("""{"""),format.raw/*270.71*/("""
	console.log(passwordStrength);
		var desc = new Array();
		desc[0] = "Very Weak";
		desc[1] = "Weak";
		desc[2] = "Better";
		desc[3] = "Medium";
		desc[4] = "Strong";
		desc[5] = "Strongest";

		var score   = 0;

		//if password bigger than 6 give 1 point
		if (password.length > 6) score++;

		//if password has both lower and uppercase characters give 1 point
		if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/) ) ) score++;

		//if password has at least one number give 1 point
		if (password.match(/\d+/)) score++;

		//if password has at least one special caracther give 1 point
		 if ( password.match(/.[!)]/) ) score++;

		//if password bigger than 12 give another 1 point
		if (password.length > 12) score++;

		passwordStrength.innerHTML = desc[score];
		passwordStrength.className = "strength" + score;

	
"""),format.raw/*301.1*/("""}"""),format.raw/*301.2*/("""


 </script>
 <style>
 
 .strength0
"""),format.raw/*308.1*/("""{"""),format.raw/*308.2*/("""
width:250px;
background:#cccccc;
"""),format.raw/*311.1*/("""}"""),format.raw/*311.2*/("""

.strength1
"""),format.raw/*314.1*/("""{"""),format.raw/*314.2*/("""
width:50px;
background:#ff0000;
"""),format.raw/*317.1*/("""}"""),format.raw/*317.2*/("""

.strength2
"""),format.raw/*320.1*/("""{"""),format.raw/*320.2*/("""
width:100px;
background:#ff5f5f;
"""),format.raw/*323.1*/("""}"""),format.raw/*323.2*/("""

.strength3
"""),format.raw/*326.1*/("""{"""),format.raw/*326.2*/("""
width:150px;
background:#56e500;
"""),format.raw/*329.1*/("""}"""),format.raw/*329.2*/("""

.strength4
"""),format.raw/*332.1*/("""{"""),format.raw/*332.2*/("""
background:#4dcd00;
width:200px;
"""),format.raw/*335.1*/("""}"""),format.raw/*335.2*/("""

.strength5
"""),format.raw/*338.1*/("""{"""),format.raw/*338.2*/("""
background:#399800;
width:250px;
"""),format.raw/*341.1*/("""}"""),format.raw/*341.2*/("""
 
 
 </style>
<!-- <script>
alert("fh");
$('#passwordConfirm').on('keyup', function () """),format.raw/*347.47*/("""{"""),format.raw/*347.48*/("""
    if ($(this).val() == $('#password').val()) """),format.raw/*348.48*/("""{"""),format.raw/*348.49*/("""
        $('#message').html('matching').css('color', 'green');
    """),format.raw/*350.5*/("""}"""),format.raw/*350.6*/(""" else $('#message').html('not matching').css('color', 'red');
"""),format.raw/*351.1*/("""}"""),format.raw/*351.2*/(""");

</script> -->
</body>
</html>"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 06 11:05:06 UTC 2015
                    SOURCE: /home/jobby/app/views/register.scala.html
                    HASH: 0af818f0da005e2314c47d95b3de273b25149a40
                    MATRIX: 858->0|1396->502|1411->508|1532->606|1602->640|1617->646|1703->709|1773->743|1788->749|1867->806|1937->840|1952->846|2020->892|2090->926|2105->932|2185->990|2255->1024|2270->1030|2345->1083|2415->1117|2430->1123|2501->1172|2571->1206|2586->1212|2654->1258|2724->1292|2739->1298|2827->1363|2897->1397|2912->1403|2987->1456|3057->1490|3072->1496|3182->1583|3252->1617|3267->1623|3365->1698|3435->1732|3450->1738|3547->1812|3617->1846|3632->1852|3705->1903|3775->1937|3790->1943|3878->2008|3948->2042|3963->2048|4043->2106|4113->2140|4128->2146|4204->2200|4274->2234|4289->2240|4365->2294|4435->2328|4450->2334|4539->2400|4609->2434|4624->2440|4722->2515|4792->2549|4807->2555|4918->2643|4988->2677|5003->2683|5091->2748|5161->2782|5176->2788|5253->2843|5323->2877|5338->2883|5421->2944|5491->2978|5506->2984|5579->3035|5649->3069|5664->3075|5755->3143|5825->3177|5840->3183|5934->3254|6004->3288|6019->3294|6115->3367|6185->3401|6200->3407|6294->3478|6364->3512|6379->3518|6468->3584|6538->3618|6553->3624|6626->3675|6696->3709|6711->3715|6804->3785|6874->3819|6889->3825|6985->3898|7055->3932|7070->3938|7165->4010|7235->4044|7250->4050|7331->4109|7401->4143|7416->4149|7506->4216|7576->4250|7591->4256|7689->4331|7759->4365|7774->4371|7890->4464|7960->4498|7975->4504|8077->4583|8147->4617|8162->4623|8248->4686|8318->4720|8333->4726|8423->4793|8493->4827|8508->4833|8561->4864|8631->4898|8646->4904|8727->4963|8797->4997|8812->5003|8886->5055|8956->5089|8971->5095|9061->5162|9131->5196|9146->5202|9235->5268|9305->5302|9320->5308|9410->5375|9480->5409|9495->5415|9601->5498|9671->5532|9686->5538|9776->5605|9847->5640|9862->5646|9933->5695|10004->5730|10019->5736|10093->5788|10166->5825|10181->5831|10245->5873|10321->5913|10336->5919|10400->5961|10490->6015|10505->6021|10576->6069|10668->6125|10683->6131|10756->6181|10886->6275|10901->6281|10969->6327|11040->6362|11055->6368|11104->6395|11266->6521|11281->6527|11359->6583|11430->6618|11445->6624|11524->6681|11598->6719|11613->6725|11698->6788|11772->6826|11787->6832|11882->6904|11956->6942|11971->6948|12046->7001|12120->7039|12135->7045|12195->7083|12269->7121|12284->7127|12361->7182|12435->7220|12450->7226|12553->7306|12627->7344|12642->7350|12717->7403|12796->7446|12811->7452|12879->7498|12953->7536|12968->7542|13036->7588|13121->7637|13136->7643|13209->7694|13283->7732|13298->7738|13382->7800|13456->7838|13471->7844|13548->7899|13622->7937|13637->7943|13718->8002|13792->8040|13807->8046|13887->8104|13961->8142|13976->8148|14061->8211|14135->8249|14150->8255|14229->8312|14303->8350|14318->8356|14398->8414|14476->8456|14491->8462|14570->8519|14644->8557|14659->8563|14738->8620|14812->8658|14827->8664|14909->8724|14983->8762|14998->8768|15097->8844|15179->8890|15194->8896|15286->8965|15360->9003|15375->9009|15475->9086|15559->9134|15574->9140|15646->9190|15720->9228|15735->9234|15821->9298|15895->9336|15910->9342|15984->9394|16059->9432|16075->9438|16151->9491|16226->9529|16242->9535|16318->9588|16393->9626|16409->9632|16480->9680|16555->9718|16571->9724|16645->9775|16720->9813|16736->9819|16838->9897|16913->9935|16929->9941|17020->10008|17095->10046|17111->10052|17195->10113|17270->10151|17286->10157|17369->10217|17455->10266|17471->10272|17561->10338|17636->10376|17652->10382|17745->10451|17820->10489|17836->10495|17950->10585|18025->10623|18041->10629|18143->10707|18218->10745|18234->10751|18313->10807|18388->10845|18404->10851|18493->10917|18568->10955|18584->10961|18681->11034|18756->11072|18772->11078|18851->11134|18931->11177|18947->11183|19026->11239|19114->11290|19130->11296|19206->11349|19281->11387|19297->11393|19382->11455|19486->11522|19502->11528|19576->11579|19651->11617|19667->11623|19746->11679|19821->11717|19837->11723|19907->11770|19995->11821|20011->11827|20093->11885|20179->11934|20195->11940|20273->11995|20349->12034|20365->12040|20452->12104|20527->12142|20543->12148|20620->12202|20700->12245|20716->12251|20804->12315|20879->12353|20895->12359|20979->12420|21054->12458|21070->12464|21164->12534|21239->12572|21255->12578|21320->12620|21401->12664|21417->12670|21532->12761|21612->12804|21628->12810|21725->12883|21806->12927|21822->12933|21907->12994|21988->13038|22004->13044|22103->13119|22178->13157|22194->13163|22287->13232|22367->13275|22383->13281|22473->13347|22569->13406|22585->13412|22658->13461|22737->13503|22753->13509|22851->13583|22932->13627|22948->13633|23038->13699|23122->13746|23138->13752|23234->13824|23309->13862|23325->13868|23425->13944|23500->13982|23516->13988|23624->14072|23699->14110|23715->14116|23813->14190|23897->14237|23913->14243|24005->14311|24080->14349|24096->14355|24196->14431|24271->14469|24287->14475|24385->14549|24460->14587|24476->14593|24574->14667|24649->14705|24665->14711|24763->14785|24838->14823|24854->14829|24955->14906|25030->14944|25046->14950|25141->15021|25231->15074|25247->15080|25347->15156|25422->15194|25438->15200|25501->15240|25576->15278|25592->15284|25653->15322|25728->15360|25744->15366|25837->15435|25912->15473|25928->15479|25999->15527|26078->15569|26094->15575|26184->15641|26266->15686|26282->15692|26370->15756|26445->15794|26461->15800|26538->15854|26613->15892|26629->15898|26711->15957|26791->16000|26807->16006|26916->16091|26997->16135|27013->16141|27113->16217|27188->16255|27204->16261|27302->16335|27377->16373|27393->16379|27487->16449|27574->16499|27590->16505|27680->16571|27767->16621|27783->16627|27855->16676|28254->17039|28269->17044|28305->17057|31646->20369|31676->20370|31751->20416|31781->20417|31820->20427|31850->20428|31943->20492|31973->20493|32033->20525|32062->20526|32098->20534|32127->20535|32202->20581|32232->20582|32367->20689|32396->20690|32426->20692|32455->20693|32488->20698|32517->20699|32618->20771|32648->20772|33507->21603|33536->21604|33601->21641|33630->21642|33692->21676|33721->21677|33762->21690|33791->21691|33852->21724|33881->21725|33922->21738|33951->21739|34013->21773|34042->21774|34083->21787|34112->21788|34174->21822|34203->21823|34244->21836|34273->21837|34335->21871|34364->21872|34405->21885|34434->21886|34496->21920|34525->21921|34642->22009|34672->22010|34749->22058|34779->22059|34874->22126|34903->22127|34993->22189|35022->22190
                    LINES: 29->1|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|48->20|48->20|48->20|49->21|49->21|49->21|50->22|50->22|50->22|51->23|51->23|51->23|52->24|52->24|52->24|53->25|53->25|53->25|54->26|54->26|54->26|55->27|55->27|55->27|56->28|56->28|56->28|57->29|57->29|57->29|58->30|58->30|58->30|59->31|59->31|59->31|60->32|60->32|60->32|61->33|61->33|61->33|62->34|62->34|62->34|63->35|63->35|63->35|64->36|64->36|64->36|65->37|65->37|65->37|66->38|66->38|66->38|67->39|67->39|67->39|68->40|68->40|68->40|69->41|69->41|69->41|70->42|70->42|70->42|71->43|71->43|71->43|72->44|72->44|72->44|73->45|73->45|73->45|74->46|74->46|74->46|75->47|75->47|75->47|76->48|76->48|76->48|77->49|77->49|77->49|78->50|78->50|78->50|79->51|79->51|79->51|80->52|80->52|80->52|81->53|81->53|81->53|82->54|82->54|82->54|83->55|83->55|83->55|84->56|84->56|84->56|85->57|85->57|85->57|86->58|86->58|86->58|87->59|87->59|87->59|88->60|88->60|88->60|89->61|89->61|89->61|90->62|90->62|90->62|91->63|91->63|91->63|92->64|92->64|92->64|93->65|93->65|93->65|94->66|94->66|94->66|95->67|95->67|95->67|97->69|97->69|97->69|98->70|98->70|98->70|100->72|100->72|100->72|101->73|101->73|101->73|102->74|102->74|102->74|103->75|103->75|103->75|104->76|104->76|104->76|105->77|105->77|105->77|106->78|106->78|106->78|107->79|107->79|107->79|108->80|108->80|108->80|109->81|109->81|109->81|110->82|110->82|110->82|111->83|111->83|111->83|112->84|112->84|112->84|113->85|113->85|113->85|114->86|114->86|114->86|115->87|115->87|115->87|116->88|116->88|116->88|117->89|117->89|117->89|118->90|118->90|118->90|119->91|119->91|119->91|120->92|120->92|120->92|121->93|121->93|121->93|122->94|122->94|122->94|123->95|123->95|123->95|124->96|124->96|124->96|125->97|125->97|125->97|126->98|126->98|126->98|127->99|127->99|127->99|128->100|128->100|128->100|129->101|129->101|129->101|130->102|130->102|130->102|131->103|131->103|131->103|132->104|132->104|132->104|133->105|133->105|133->105|134->106|134->106|134->106|135->107|135->107|135->107|136->108|136->108|136->108|137->109|137->109|137->109|138->110|138->110|138->110|139->111|139->111|139->111|140->112|140->112|140->112|141->113|141->113|141->113|142->114|142->114|142->114|143->115|143->115|143->115|144->116|144->116|144->116|145->117|145->117|145->117|146->118|146->118|146->118|148->120|148->120|148->120|149->121|149->121|149->121|150->122|150->122|150->122|151->123|151->123|151->123|152->124|152->124|152->124|153->125|153->125|153->125|154->126|154->126|154->126|155->127|155->127|155->127|156->128|156->128|156->128|157->129|157->129|157->129|158->130|158->130|158->130|159->131|159->131|159->131|160->132|160->132|160->132|161->133|161->133|161->133|162->134|162->134|162->134|163->135|163->135|163->135|164->136|164->136|164->136|165->137|165->137|165->137|166->138|166->138|166->138|167->139|167->139|167->139|168->140|168->140|168->140|169->141|169->141|169->141|170->142|170->142|170->142|171->143|171->143|171->143|172->144|172->144|172->144|173->145|173->145|173->145|174->146|174->146|174->146|175->147|175->147|175->147|176->148|176->148|176->148|177->149|177->149|177->149|178->150|178->150|178->150|179->151|179->151|179->151|180->152|180->152|180->152|181->153|181->153|181->153|182->154|182->154|182->154|183->155|183->155|183->155|184->156|184->156|184->156|185->157|185->157|185->157|186->158|186->158|186->158|187->159|187->159|187->159|188->160|188->160|188->160|189->161|189->161|189->161|190->162|190->162|190->162|191->163|191->163|191->163|192->164|192->164|192->164|193->165|193->165|193->165|207->179|207->179|207->179|281->253|281->253|282->254|282->254|283->255|283->255|285->257|285->257|287->259|287->259|288->260|288->260|289->261|289->261|292->264|292->264|293->265|293->265|295->267|295->267|298->270|298->270|329->301|329->301|336->308|336->308|339->311|339->311|342->314|342->314|345->317|345->317|348->320|348->320|351->323|351->323|354->326|354->326|357->329|357->329|360->332|360->332|363->335|363->335|366->338|366->338|369->341|369->341|375->347|375->347|376->348|376->348|378->350|378->350|379->351|379->351
                    -- GENERATED --
                */
            
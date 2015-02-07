
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
object saIndex extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<style>
#loading-id """),format.raw/*3.13*/("""{"""),format.raw/*3.14*/("""
			position: absolute;
			z-index: 100;
			top: 50%;
			left: 50%;
"""),format.raw/*8.1*/("""}"""),format.raw/*8.2*/("""
</style>
<html lang="en" ng-app="MAdmin">
  <head>
    <title>Ardent Job Portal</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
   <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,700"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,700,300"/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*19.35*/routes/*19.41*/.Assets.at("app/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css"))),format.raw/*19.139*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*20.35*/routes/*20.41*/.Assets.at("app/vendors/font-awesome/css/font-awesome.min.css"))),format.raw/*20.104*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*21.35*/routes/*21.41*/.Assets.at("app/vendors/bootstrap/css/bootstrap.min.css"))),format.raw/*21.98*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*22.35*/routes/*22.41*/.Assets.at("app/vendors/intro.js/introjs.css"))),format.raw/*22.87*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*23.35*/routes/*23.41*/.Assets.at("app/vendors/calendar/zabuto_calendar.min.css"))),format.raw/*23.99*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*24.35*/routes/*24.41*/.Assets.at("app/vendors/sco.message/sco.message.css"))),format.raw/*24.94*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*25.35*/routes/*25.41*/.Assets.at("app/vendors/animate.css/animate.css"))),format.raw/*25.90*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*26.35*/routes/*26.41*/.Assets.at("app/vendors/iCheck/skins/all.css"))),format.raw/*26.87*/(""""/>
    <!-- <link rel="stylesheet" href=""""),_display_(Seq[Any](/*27.40*/routes/*27.46*/.Assets.at("app/vendors/jquery-notific8/jquery.notific8.min.css"))),format.raw/*27.111*/(""""/> -->
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*28.35*/routes/*28.41*/.Assets.at("app/vendors/sco.message/sco.message.css"))),format.raw/*28.94*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*29.35*/routes/*29.41*/.Assets.at("app/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.1.1.min.css"))),format.raw/*29.128*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*30.35*/routes/*30.41*/.Assets.at("app/vendors/bootstrap-markdown/css/bootstrap-markdown.min.css"))),format.raw/*30.116*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*31.35*/routes/*31.41*/.Assets.at("app/vendors/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"))),format.raw/*31.115*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*32.35*/routes/*32.41*/.Assets.at("app/vendors/summernote/summernote.css"))),format.raw/*32.92*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*33.35*/routes/*33.41*/.Assets.at("app/vendors/ion.rangeSlider/css/ion.rangeSlider.css"))),format.raw/*33.106*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*34.35*/routes/*34.41*/.Assets.at("app/vendors/nouislider/jquery.nouislider.css"))),format.raw/*34.99*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*35.35*/routes/*35.41*/.Assets.at("app/vendors/jquery-nestable/nestable.css"))),format.raw/*35.95*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*36.35*/routes/*36.41*/.Assets.at("app/vendors/jquery-toastr/toastr.min.css"))),format.raw/*36.95*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*37.35*/routes/*37.41*/.Assets.at("app/vendors/jstree/dist/themes/default/style.min.css"))),format.raw/*37.107*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*38.35*/routes/*38.41*/.Assets.at("app/vendors/jquery-treetable/stylesheets/jquery.treetable.css"))),format.raw/*38.116*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*39.35*/routes/*39.41*/.Assets.at("app/vendors/jquery-treetable/stylesheets/jquery.treetable.theme.custom.css"))),format.raw/*39.129*/(""""/>
     <link rel="stylesheet" href=""""),_display_(Seq[Any](/*40.36*/routes/*40.42*/.Assets.at("app/vendors/bootstrap-datepicker/css/datepicker.css"))),format.raw/*40.107*/(""""/> 
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*41.35*/routes/*41.41*/.Assets.at("app/vendors/fullcalendar/fullcalendar.css"))),format.raw/*41.96*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*42.35*/routes/*42.41*/.Assets.at("app/vendors/fullcalendar/fullcalendar.print.css"))),format.raw/*42.102*/(""""/>
    <!-- <link rel="stylesheet" href=""""),_display_(Seq[Any](/*43.40*/routes/*43.46*/.Assets.at("app/vendors/lightbox/css/lightbox.css"))),format.raw/*43.97*/(""""/> -->
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*44.35*/routes/*44.41*/.Assets.at("app/vendors/DataTables/media/css/jquery.dataTables.css"))),format.raw/*44.109*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*45.35*/routes/*45.41*/.Assets.at("app/vendors/DataTables/media/css/dataTables.bootstrap.css"))),format.raw/*45.112*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*46.35*/routes/*46.41*/.Assets.at("app/vendors/jquery-tablesorter/themes/blue/style-custom.css"))),format.raw/*46.114*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*47.35*/routes/*47.41*/.Assets.at("app/vendors/DataTables/media/css/dataTables.bootstrap.css"))),format.raw/*47.112*/(""""/>
     <link rel="stylesheet" href=""""),_display_(Seq[Any](/*48.36*/routes/*48.42*/.Assets.at("app/vendors/bootstrap-datepicker/css/datepicker3.css"))),format.raw/*48.108*/(""""/> 
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*49.35*/routes/*49.41*/.Assets.at("app/vendors/dropzone/css/dropzone.css"))),format.raw/*49.92*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*50.35*/routes/*50.41*/.Assets.at("app/vendors/jquery-file-upload/css/jquery.fileupload.css"))),format.raw/*50.111*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*51.35*/routes/*51.41*/.Assets.at("app/vendors/jquery-file-upload/css/jquery.fileupload-ui.css"))),format.raw/*51.114*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*52.35*/routes/*52.41*/.Assets.at("app/vendors/jquery-file-upload/css/blueimp-gallery.min.css"))),format.raw/*52.113*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*53.35*/routes/*53.41*/.Assets.at("app/vendors/jquery-steps/css/jquery.steps.css"))),format.raw/*53.100*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*54.35*/routes/*54.41*/.Assets.at("app/vendors/bootstrap-colorpicker/css/colorpicker.css"))),format.raw/*54.108*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*55.35*/routes/*55.41*/.Assets.at("app/vendors/bootstrap-daterangepicker/daterangepicker-bs3.css"))),format.raw/*55.116*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*56.35*/routes/*56.41*/.Assets.at("app/vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"))),format.raw/*56.134*/(""""/> 
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*57.35*/routes/*57.41*/.Assets.at("app/vendors/bootstrap-timepicker/css/bootstrap-timepicker.min.css"))),format.raw/*57.120*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*58.35*/routes/*58.41*/.Assets.at("app/vendors/bootstrap-clockface/css/clockface.css"))),format.raw/*58.104*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*59.35*/routes/*59.41*/.Assets.at("app/vendors/bootstrap-switch/css/bootstrap-switch.css"))),format.raw/*59.108*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*60.35*/routes/*60.41*/.Assets.at("app/css/style.css"))),format.raw/*60.72*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*61.35*/routes/*61.41*/.Assets.at("app/vendors/jplist/html/css/jplist-custom.css"))),format.raw/*61.100*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*62.35*/routes/*62.41*/.Assets.at("app/vendors/select2/select2-madmin.css"))),format.raw/*62.93*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*63.35*/routes/*63.41*/.Assets.at("app/vendors/bootstrap-select/bootstrap-select.min.css"))),format.raw/*63.108*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*64.35*/routes/*64.41*/.Assets.at("app/vendors/multi-select/css/multi-select-madmin.css"))),format.raw/*64.107*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*65.35*/routes/*65.41*/.Assets.at("app/vendors/x-editable/select2/lib/select2-madmin.css"))),format.raw/*65.108*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*66.35*/routes/*66.41*/.Assets.at("app/vendors/x-editable/bootstrap3-editable/css/bootstrap-editable.css"))),format.raw/*66.124*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*67.35*/routes/*67.41*/.Assets.at("app/vendors/x-editable/inputs-ext/address/address.css"))),format.raw/*67.108*/(""""/>
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*68.35*/routes/*68.41*/.Assets.at("app/css/themes/style1/pink-blue.css"))),format.raw/*68.90*/(""""/> 
    <link rel="stylesheet" href=""""),_display_(Seq[Any](/*69.35*/routes/*69.41*/.Assets.at("app/css/themes/{{style}}/{{theme}}.css"))),format.raw/*69.93*/(""""/>
    <link rel="shortcut icon" href=""""),_display_(Seq[Any](/*70.38*/routes/*70.44*/.Assets.at("app/images/icons/favicon.ico"))),format.raw/*70.86*/(""""/>
    <link rel="apple-touch-icon" href=""""),_display_(Seq[Any](/*71.41*/routes/*71.47*/.Assets.at("app/images/icons/favicon.png"))),format.raw/*71.89*/(""""/>
    <link rel="apple-touch-icon" sizes="72x72" href=""""),_display_(Seq[Any](/*72.55*/routes/*72.61*/.Assets.at("app/images/icons/favicon-72x72.png"))),format.raw/*72.109*/(""""/>
    <link rel="apple-touch-icon" sizes="114x114" href=""""),_display_(Seq[Any](/*73.57*/routes/*73.63*/.Assets.at("app/images/icons/favicon-114x114.png"))),format.raw/*73.113*/(""""/>
    <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <!--   <script src=""""),_display_(Seq[Any](/*75.26*/routes/*75.32*/.Assets.at("app/vendors/ckeditor/ckeditor.js"))),format.raw/*75.78*/(""""></script> -->
    <!--  <script src=""""),_display_(Seq[Any](/*76.25*/routes/*76.31*/.Assets.at("app/js/lib.js"))),format.raw/*76.58*/(""""></script>  -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
            <script src=""""),_display_(Seq[Any](/*78.27*/routes/*78.33*/.Assets.at("app/bower_components/angular/jquery.min.js"))),format.raw/*78.89*/(""""></script>
    	    <script src=""""),_display_(Seq[Any](/*79.24*/routes/*79.30*/.Assets.at("app/bower_components/angular/angular.min.js"))),format.raw/*79.87*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*80.27*/routes/*80.33*/.Assets.at("app/bower_components/angular/angular-route.min.js"))),format.raw/*80.96*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*81.27*/routes/*81.33*/.Assets.at("app/bower_components/angular-bootstrap/ui-bootstrap.min.js"))),format.raw/*81.105*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*82.27*/routes/*82.33*/.Assets.at("app/vendors/jquery-migrate-1.2.1.min.js"))),format.raw/*82.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*83.27*/routes/*83.33*/.Assets.at("app/vendors/jquery-ui.js"))),format.raw/*83.71*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*84.27*/routes/*84.33*/.Assets.at("app/vendors/bootstrap/js/bootstrap.min.js"))),format.raw/*84.88*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*85.27*/routes/*85.33*/.Assets.at("app/vendors/jquery-bootstrap-wizard/jquery.bootstrap.wizard.min.js"))),format.raw/*85.113*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*86.27*/routes/*86.33*/.Assets.at("app/vendors/lightbox/js/lightbox.min.js"))),format.raw/*86.86*/(""""></script>
            <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.0.js"></script>
            
            <!-- <script src=""""),_display_(Seq[Any](/*89.32*/routes/*89.38*/.Assets.at("app/vendors/iCheck/icheck.min.js"))),format.raw/*89.84*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*90.27*/routes/*90.33*/.Assets.at("app/vendors/iCheck/custom.min.js"))),format.raw/*90.79*/(""""></script> -->
            <!--   <script src=""""),_display_(Seq[Any](/*91.34*/routes/*91.40*/.Assets.at("app/vendors/flot-chart/jquery.flot.js"))),format.raw/*91.91*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*92.27*/routes/*92.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.categories.js"))),format.raw/*92.95*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*93.27*/routes/*93.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.pie.js"))),format.raw/*93.88*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*94.27*/routes/*94.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.tooltip.js"))),format.raw/*94.92*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*95.27*/routes/*95.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.resize.js"))),format.raw/*95.91*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*96.27*/routes/*96.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.fillbetween.js"))),format.raw/*96.96*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*97.27*/routes/*97.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.stack.js"))),format.raw/*97.90*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*98.27*/routes/*98.33*/.Assets.at("app/vendors/flot-chart/jquery.flot.spline.js"))),format.raw/*98.91*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*99.31*/routes/*99.37*/.Assets.at("app/vendors/calendar/zabuto_calendar.min.js"))),format.raw/*99.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*100.27*/routes/*100.33*/.Assets.at("app/vendors/slimScroll/jquery.slimscroll.js"))),format.raw/*100.90*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*101.27*/routes/*101.33*/.Assets.at("app/vendors/responsive-tabs/responsive-tabs.js"))),format.raw/*101.93*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*102.27*/routes/*102.33*/.Assets.at("app/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"))),format.raw/*102.109*/(""""></script>
             <!--    <script src=""""),_display_(Seq[Any](/*103.36*/routes/*103.42*/.Assets.at("app/vendors/bootstrap-markdown/js/bootstrap-markdown.js"))),format.raw/*103.111*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*104.27*/routes/*104.33*/.Assets.at("app/vendors/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"))),format.raw/*104.110*/(""""></script> -->
            <!--  <script src=""""),_display_(Seq[Any](/*105.33*/routes/*105.39*/.Assets.at("app/vendors/summernote/summernote.js"))),format.raw/*105.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*106.27*/routes/*106.33*/.Assets.at("app/vendors/jquery-notific8/jquery.notific8.min.js"))),format.raw/*106.97*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*107.27*/routes/*107.33*/.Assets.at("app/vendors/sco.message/sco.message.js"))),format.raw/*107.85*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*108.27*/routes/*108.33*/.Assets.at("app/vendors/jquery-notific8/notific8.js"))),format.raw/*108.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*109.27*/routes/*109.33*/.Assets.at("app/vendors/jquery-toastr/toastr.min.js"))),format.raw/*109.86*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*110.27*/routes/*110.33*/.Assets.at("app/vendors/iCheck/color_change.js"))),format.raw/*110.81*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*111.27*/routes/*111.33*/.Assets.at("app/vendors/jstree/dist/jstree.min.js"))),format.raw/*111.84*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*112.27*/routes/*112.33*/.Assets.at("app/vendors/jquery-treetable/javascripts/src/jquery.treetable.js"))),format.raw/*112.111*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*113.27*/routes/*113.33*/.Assets.at("app/vendors/ion.rangeSlider/js/ion.rangeSlider.min.js"))),format.raw/*113.100*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*114.27*/routes/*114.33*/.Assets.at("app/vendors/nouislider/jquery.nouislider.min.js"))),format.raw/*114.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*115.27*/routes/*115.33*/.Assets.at("app/vendors/jquery-nestable/jquery.nestable.js"))),format.raw/*115.93*/(""""></script>
             -->       <script src=""""),_display_(Seq[Any](/*116.38*/routes/*116.44*/.Assets.at("app/vendors/DataTables/media/js/jquery.dataTables.js"))),format.raw/*116.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*117.27*/routes/*117.33*/.Assets.at("app/vendors/DataTables/media/js/dataTables.bootstrap.js"))),format.raw/*117.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*118.27*/routes/*118.33*/.Assets.at("app/vendors/DataTables/extensions/TableTools/js/dataTables.tableTools.min.js"))),format.raw/*118.123*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*119.27*/routes/*119.33*/.Assets.at("app/vendors/bootstrap-hover-dropdown/bootstrap-hover-dropdown.js"))),format.raw/*119.111*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*120.27*/routes/*120.33*/.Assets.at("app/vendors/DataTables/jquery.jeditable.js"))),format.raw/*120.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*121.27*/routes/*121.33*/.Assets.at("app/vendors/jquery-tablesorter/jquery.tablesorter.js"))),format.raw/*121.99*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*122.27*/routes/*122.33*/.Assets.at("app/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"))),format.raw/*122.106*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*123.27*/routes/*123.33*/.Assets.at("app/vendors/jquery-cookie/jquery.cookie.js"))),format.raw/*123.89*/(""""></script>
            <!--    <script src=""""),_display_(Seq[Any](/*124.35*/routes/*124.41*/.Assets.at("app/vendors/jquery-highcharts/highchart.js"))),format.raw/*124.97*/(""""></script> -->
            <!--    <script src=""""),_display_(Seq[Any](/*125.35*/routes/*125.41*/.Assets.at("app/vendors/jquery-highcharts/funnel.js"))),format.raw/*125.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*126.27*/routes/*126.33*/.Assets.at("app/vendors/jquery-highcharts/highcharts-more.js"))),format.raw/*126.95*/(""""></script>
             -->   
            <!--  <script src=""""),_display_(Seq[Any](/*128.33*/routes/*128.39*/.Assets.at("app/vendors/jquery-highcharts/data.js"))),format.raw/*128.90*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*129.27*/routes/*129.33*/.Assets.at("app/vendors/jquery-highcharts/exporting.js"))),format.raw/*129.89*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*130.27*/routes/*130.33*/.Assets.at("app/vendors/chart.js/Chart.min.js"))),format.raw/*130.80*/(""""></script>
            -->    <!--  <script src=""""),_display_(Seq[Any](/*131.40*/routes/*131.46*/.Assets.at("app/vendors/fullcalendar/fullcalendar.min.js"))),format.raw/*131.104*/(""""></script> -->
            <!--   <script src=""""),_display_(Seq[Any](/*132.34*/routes/*132.40*/.Assets.at("app/vendors/mixitup/src/jquery.mixitup.js"))),format.raw/*132.95*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*133.27*/routes/*133.33*/.Assets.at("app/vendors/jplist/html/js/vendor/modernizr.min.js"))),format.raw/*133.97*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*134.27*/routes/*134.33*/.Assets.at("app/vendors/jplist/html/js/jplist.min.js"))),format.raw/*134.87*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*135.31*/routes/*135.37*/.Assets.at("app/vendors/jquery-validate/jquery.validate.min.js"))),format.raw/*135.101*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*136.27*/routes/*136.33*/.Assets.at("app/vendors/jquery-steps/js/jquery.steps.min.js"))),format.raw/*136.94*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*137.27*/routes/*137.33*/.Assets.at("app/vendors/bootstrap-daterangepicker/daterangepicker.js"))),format.raw/*137.103*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*138.27*/routes/*138.33*/.Assets.at("app/vendors/moment/moment.js"))),format.raw/*138.75*/(""""></script>
            <!--  <script src=""""),_display_(Seq[Any](/*139.33*/routes/*139.39*/.Assets.at("app/vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"))),format.raw/*139.130*/(""""></script>
            -->    <script src=""""),_display_(Seq[Any](/*140.34*/routes/*140.40*/.Assets.at("app/vendors/bootstrap-timepicker/js/bootstrap-timepicker.js"))),format.raw/*140.113*/(""""></script>
            <!--  <script src=""""),_display_(Seq[Any](/*141.33*/routes/*141.39*/.Assets.at("app/vendors/bootstrap-clockface/js/clockface.js"))),format.raw/*141.100*/(""""></script>
            -->   <script src=""""),_display_(Seq[Any](/*142.33*/routes/*142.39*/.Assets.at("app/vendors/bootstrap-colorpicker/js/bootstrap-colorpicker.js"))),format.raw/*142.114*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*143.27*/routes/*143.33*/.Assets.at("app/vendors/bootstrap-switch/js/bootstrap-switch.min.js"))),format.raw/*143.102*/(""""></script>
            <!--  <script src=""""),_display_(Seq[Any](/*144.33*/routes/*144.39*/.Assets.at("app/vendors/jquery-maskedinput/jquery-maskedinput.js"))),format.raw/*144.105*/(""""></script>
             -->        <!--        <script src=""""),_display_(Seq[Any](/*145.51*/routes/*145.57*/.Assets.at("app/vendors/dropzone/js/dropzone.js"))),format.raw/*145.106*/(""""></script> -->
            <script src=""""),_display_(Seq[Any](/*146.27*/routes/*146.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/jquery.ui.widget.js"))),format.raw/*146.107*/(""""></script>
            <!--   <script src=""""),_display_(Seq[Any](/*147.34*/routes/*147.40*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/tmpl.min.js"))),format.raw/*147.106*/(""""></script>
            -->  <!-- <script src=""""),_display_(Seq[Any](/*148.37*/routes/*148.43*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/load-image.min.js"))),format.raw/*148.115*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*149.27*/routes/*149.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/canvas-to-blob.min.js"))),format.raw/*149.109*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*150.27*/routes/*150.33*/.Assets.at("app/vendors/jquery-file-upload/js/vendor/jquery.blueimp-gallery.min.js"))),format.raw/*150.117*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*151.27*/routes/*151.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.iframe-transport.js"))),format.raw/*151.107*/(""""></script>
            --> <!-- <script src=""""),_display_(Seq[Any](/*152.36*/routes/*152.42*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload.js"))),format.raw/*152.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*153.27*/routes/*153.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-process.js"))),format.raw/*153.109*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*154.27*/routes/*154.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-image.js"))),format.raw/*154.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*155.27*/routes/*155.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-audio.js"))),format.raw/*155.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*156.27*/routes/*156.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-video.js"))),format.raw/*156.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*157.27*/routes/*157.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-validate.js"))),format.raw/*157.110*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*158.27*/routes/*158.33*/.Assets.at("app/vendors/jquery-file-upload/js/jquery.fileupload-ui.js"))),format.raw/*158.104*/(""""></script>
             -->    <!--   <script src=""""),_display_(Seq[Any](/*159.42*/routes/*159.48*/.Assets.at("app/vendors/jquery-file-upload/js/cors/jquery.xdr-transport.js"))),format.raw/*159.124*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*160.27*/routes/*160.33*/.Assets.at("app/vendors/gmaps/gmaps.js"))),format.raw/*160.73*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*161.27*/routes/*161.33*/.Assets.at("app/vendors/charCount.js"))),format.raw/*161.71*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*162.27*/routes/*162.33*/.Assets.at("app/vendors/jquery-news-ticker/jquery.newsTicker.min.js"))),format.raw/*162.102*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*163.27*/routes/*163.33*/.Assets.at("app/vendors/select2/select2.min.js"))),format.raw/*163.81*/(""""></script>
            --> <script src=""""),_display_(Seq[Any](/*164.31*/routes/*164.37*/.Assets.at("app/vendors/bootstrap-select/bootstrap-select.min.js"))),format.raw/*164.103*/(""""></script>
             <!--   <script src=""""),_display_(Seq[Any](/*165.35*/routes/*165.41*/.Assets.at("app/vendors/multi-select/js/jquery.multi-select.js"))),format.raw/*165.105*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*166.27*/routes/*166.33*/.Assets.at("app/vendors/x-editable/jquery.mockjax.js"))),format.raw/*166.87*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*167.27*/routes/*167.33*/.Assets.at("app/vendors/x-editable/select2/lib/select2.js"))),format.raw/*167.92*/(""""></script>
             -->  <script src=""""),_display_(Seq[Any](/*168.33*/routes/*168.39*/.Assets.at("app/vendors/x-editable/bootstrap3-editable/js/bootstrap-editable.min.js"))),format.raw/*168.124*/(""""></script>
             <!--  <script src=""""),_display_(Seq[Any](/*169.34*/routes/*169.40*/.Assets.at("app/vendors/x-editable/inputs-ext/typeaheadjs/lib/typeahead.js"))),format.raw/*169.116*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*170.27*/routes/*170.33*/.Assets.at("app/vendors/x-editable/inputs-ext/typeaheadjs/typeaheadjs.js"))),format.raw/*170.107*/(""""></script>
            <script src=""""),_display_(Seq[Any](/*171.27*/routes/*171.33*/.Assets.at("app/vendors/x-editable/inputs-ext/wysihtml5/wysihtml5.js"))),format.raw/*171.103*/(""""></script>
             -->  <!--  <script src=""""),_display_(Seq[Any](/*172.39*/routes/*172.45*/.Assets.at("app/vendors/x-editable/inputs-ext/address/address.js"))),format.raw/*172.111*/(""""></script>
             -->   <!--  <script src=""""),_display_(Seq[Any](/*173.40*/routes/*173.46*/.Assets.at("app/vendors/x-editable/demo-mock.js"))),format.raw/*173.95*/(""""></script>
             -->
    
   
    <link   href=""""),_display_(Seq[Any](/*177.20*/routes/*177.26*/.Assets.at("app/js-lib/ng-dialog/css/ngDialog.min.css"))),format.raw/*177.81*/("""" rel="stylesheet" type="text/css" />
    <script src=""""),_display_(Seq[Any](/*178.19*/routes/*178.25*/.Assets.at("app/js-lib/ng-table.js"))),format.raw/*178.61*/(""""></script>
    <link href=""""),_display_(Seq[Any](/*179.18*/routes/*179.24*/.Assets.at("app/css/ng-table.css"))),format.raw/*179.58*/("""" rel="stylesheet" type="text/css" />
    <script src=""""),_display_(Seq[Any](/*180.19*/routes/*180.25*/.Assets.at("app/js-lib/angular-file-upload-shim.min.js"))),format.raw/*180.81*/(""""></script>
	<script src=""""),_display_(Seq[Any](/*181.16*/routes/*181.22*/.Assets.at("app/js-lib/angular-file-upload.js"))),format.raw/*181.69*/(""""></script>
    <script  src=""""),_display_(Seq[Any](/*182.20*/routes/*182.26*/.Assets.at("app/js-lib/ng-dialog/js/ngDialog.min.js"))),format.raw/*182.79*/(""""></script>
    <script src=""""),_display_(Seq[Any](/*183.19*/routes/*183.25*/.Assets.at("app/vendors/iCheck/icheck.min.js"))),format.raw/*183.71*/(""""></script>
    <script src=""""),_display_(Seq[Any](/*184.19*/routes/*184.25*/.Assets.at("app/vendors/iCheck/custom.min.js"))),format.raw/*184.71*/(""""></script>
    <script src=""""),_display_(Seq[Any](/*185.19*/routes/*185.25*/.Assets.at("app/js/main.js"))),format.raw/*185.53*/(""""></script>
	<link href=""""),_display_(Seq[Any](/*186.15*/routes/*186.21*/.Assets.at("app/js-lib/ng-dialog/css/ngDialog-theme-default.min.css"))),format.raw/*186.90*/("""" rel="stylesheet" type="text/css" />
   
        <link   href=""""),_display_(Seq[Any](/*188.24*/routes/*188.30*/.Assets.at("app/js-lib/ng-dialog/css/ngDialog.min.css"))),format.raw/*188.85*/("""" rel="stylesheet" type="text/css" />
    <script src=""""),_display_(Seq[Any](/*189.19*/routes/*189.25*/.Assets.at("app/js-lib/ng-table.js"))),format.raw/*189.61*/(""""></script>
    <link href=""""),_display_(Seq[Any](/*190.18*/routes/*190.24*/.Assets.at("app/css/ng-table.css"))),format.raw/*190.58*/("""" rel="stylesheet" type="text/css" />
        <script src=""""),_display_(Seq[Any](/*191.23*/routes/*191.29*/.Assets.at("app/js-lib/angular-file-upload-shim.min.js"))),format.raw/*191.85*/(""""></script>
	<script src=""""),_display_(Seq[Any](/*192.16*/routes/*192.22*/.Assets.at("app/js-lib/angular-file-upload.js"))),format.raw/*192.69*/(""""></script>
    <script  src=""""),_display_(Seq[Any](/*193.20*/routes/*193.26*/.Assets.at("app/js-lib/ng-dialog/js/ngDialog.min.js"))),format.raw/*193.79*/(""""></script>
    <script src=""""),_display_(Seq[Any](/*194.19*/routes/*194.25*/.Assets.at("app/vendors/iCheck/icheck.min.js"))),format.raw/*194.71*/(""""></script>
    <script src=""""),_display_(Seq[Any](/*195.19*/routes/*195.25*/.Assets.at("app/vendors/iCheck/custom.min.js"))),format.raw/*195.71*/(""""></script>
    <script src=""""),_display_(Seq[Any](/*196.19*/routes/*196.25*/.Assets.at("app/js/main.js"))),format.raw/*196.53*/(""""></script>

           
	<link href=""""),_display_(Seq[Any](/*199.15*/routes/*199.21*/.Assets.at("app/js-lib/ng-dialog/css/ngDialog-theme-default.min.css"))),format.raw/*199.90*/("""" rel="stylesheet" type="text/css" />
  </head>
  <body ng-controller="SuperAdminController" ng-class="[header.menu_style, header.header_topbar, header.effect]" class="animated">
    <span id="loading-id"><img src="assets/app/images/loading.gif"/></span>
    <div ng-class="header.boxed" class="default-page">
      <div class="news-ticker bg-orange">
        <div class="container">
          <ul id="news-ticker-content" class="list-unstyled mbn">
          </ul><a id="news-ticker-close" href="javascript:;"><i class="fa fa-times"></i></a>
        </div>
      </div><a id="totop" href="dashboard#/superAdmin_dashboard"><i class="fa fa-angle-up"></i></a>
      <div id="header-topbar-option-demo" ng-include="'assets/app/templates/states/header.html'" onload="load_header()" class="page-header-topbar"></div>
      <div id="wrapper" ng-class="header.layout_menu">
        <nav id="sidebar" role="navigation" data-step="3" data-intro="Template has &lt;b&gt;many navigation styles&lt;/b&gt;" data-position="right" ng-include="'assets/app/templates/states/sitebar.html'" ng-menu="" class="navbar-default navbar-static-side"></nav>
        <div id="chat-form" ng-include="'assets/app/templates/states/_includes/chat-form.html'" ng-class=""""),format.raw/*213.107*/("""{"""),format.raw/*213.108*/("""show:header.chat"""),format.raw/*213.124*/("""}"""),format.raw/*213.125*/("""" class="fixed"></div>
        <div id="page-wrapper" ng-class="header.animation" class="animated">
          <div id="title-breadcrumb-option-demo" ng-include="'assets/app/templates/states/breadcrumb.html'" class="page-title-breadcrumb"></div>
          <div ng-view="" class="page-content"></div>
          <!--BEGIN CONTENT QUICK SIDEBAR-->
          <div ng-include="'assets/app/templates/states/_includes/quick-sidebar.html'" ng-class=""""),format.raw/*218.98*/("""{"""),format.raw/*218.99*/("""show:header.history"""),format.raw/*218.118*/("""}"""),format.raw/*218.119*/("""" class="quick-sidebar"></div>
          <!--END CONTENT QUICK SIDEBAR-->
        </div>
        <div id="footer" ng-include="'assets/app/templates/states/footer.html'" ng-show="header.footer" class="hide"></div>
      </div>
    </div>
    <div hide="hide" class="extra-page"></div>
    <!--BEGIN MODAL CONFIG PORTLET-->
    <div id="modal-config" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 class="modal-title">Modal title</h4>
          </div>
          <div class="modal-body">
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eleifend et nisl eget porta. Curabitur elementum sem molestie nisl varius, eget tempus odio molestie. Nunc vehicula sem arcu, eu pulvinar neque cursus ac. Aliquam ultricies lobortis magna et aliquam. Vestibulum egestas eu urna sed ultricies. Nullam pulvinar dolor vitae quam dictum condimentum. Integer a sodales elit, eu pulvinar leo. Nunc nec aliquam nisi, a mollis neque. Ut vel felis quis tellus hendrerit placerat. Vivamus vel nisl non magna feugiat dignissim sed ut nibh. Nulla elementum, est a pretium hendrerit, arcu risus luctus augue, mattis aliquet orci ligula eget massa. Sed ut ultricies felis.</p>
          </div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <!--END MODAL CONFIG PORTLET-->
    <!--Modal Default-->
    <div id="modal-default" tabindex="-1" role="dialog" aria-labelledby="modal-default-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-default-label" class="modal-title">Modal Default</h4>
          </div>
          <div class="modal-body">Modal body goes here</div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Left Footer-->
    <div id="modal-left-footer" tabindex="-1" role="dialog" aria-labelledby="modal-left-footer-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-left-footer-label" class="modal-title">Modal Left Footer</h4>
          </div>
          <div class="modal-body">Modal body goes here</div>
          <div class="modal-footer modal-footer-left">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Header Color Primary-->
    <div id="modal-header-primary" tabindex="-1" role="dialog" aria-labelledby="modal-header-primary-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header modal-header-primary">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-header-primary-label" class="modal-title">Modal Header Primary</h4>
          </div>
          <div class="modal-body">Modal body goes here</div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Responsive-->
    <div id="modal-responsive" tabindex="-1" role="dialog" aria-labelledby="modal-responsive-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-responsive-label" class="modal-title">Modals Responsive</h4>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-md-6">
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
              </div>
              <div class="col-md-6">
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
                <div class="mbm">
                  <input type="text" class="form-control"/>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Stackable-->
    <div id="modal-stackable" tabindex="-1" role="dialog" aria-labelledby="modal-stackable-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-stackable-label" class="modal-title">Modal Stackable</h4>
          </div>
          <div class="modal-body">
            <p>One fine body…</p>
            <p>One fine body…</p>
            <p>One fine body…</p>
            <input type="text" data-tabindex="1" class="form-control mbm"/>
            <input type="text" data-tabindex="2" class="form-control mbm"/><a data-toggle="modal" data-target="#stack2" class="btn btn-primary">Launch modal</a>
          </div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <div id="stack2" tabindex="-1" data-focus-on="input:first" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4>Stack Two</h4>
          </div>
          <div class="modal-body">
            <p>One fine body…</p>
            <p>One fine body…</p>
            <input type="text" data-tabindex="1" class="form-control mbm"/>
            <input type="text" data-tabindex="2" class="form-control mbm"/><a data-toggle="modal" href="#stack3" class="btn btn-default">Launch modal</a>
          </div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Ok</button>
          </div>
        </div>
      </div>
    </div>
    <div id="stack3" tabindex="-1" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4>Stack Three</h4>
          </div>
          <div class="modal-body">
            <p>One fine body…</p>
            <input type="text" data-tabindex="1" class="form-control mbm"/>
            <input type="text" data-tabindex="2" class="form-control mbm"/>
          </div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Ok</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Full Width-->
    <div id="modal-full-width" tabindex="-1" role="dialog" aria-labelledby="modal-full-width-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog modal-full-width">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-full-width-label" class="modal-title">Modal Full Width</h4>
          </div>
          <div class="modal-body">Modal body goes here</div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Wide Width-->
    <div id="modal-wide-width" tabindex="-1" role="dialog" aria-labelledby="modal-wide-width-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog modal-wide-width">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-wide-width-label" class="modal-title">Modal Wide Width</h4>
          </div>
          <div class="modal-body">Modal body goes here</div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Static-->
    <div id="modal-static" tabindex="-1" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <p>Would you like to continue with some arbitrary task?</p>
          </div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
            <button type="button" data-dismiss="modal" class="btn btn-primary">Continue Task</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Long-->
    <div id="modal-long" tabindex="-1" data-replace="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4>A Fairly Long Modal</h4>
          </div>
          <div class="modal-body"><a data-toggle="modal" href="#modal-notlong" style="position: absolute; top: 50%; right: 12px" class="btn btn-primary">Not So Long Modal</a><img style="height: 800px;" src="http://i.imgur.com/KwPYo.jpg"/></div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
          </div>
        </div>
      </div>
    </div>
    <div id="modal-notlong" tabindex="-1" data-replace="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4>Not That Long</h4>
          </div>
          <div class="modal-body"><img style="height: 300px;" src="http://i.imgur.com/KwPYo.jpg"/></div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Alert-->
    <div id="modal-alert" tabindex="-1" role="dialog" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">Hello world !</div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-primary">Ok</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Confirm-->
    <div id="modal-confirm" tabindex="-1" role="dialog" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">Are you sure?</div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
            <button type="button" data-dismiss="modal" class="btn btn-primary">Ok</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Custom Dialog-->
    <div id="modal-custom-dialog" tabindex="-1" role="dialog" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 class="modal-title">Custom Dialog</h4>
          </div>
          <div class="modal-body">I am a custom dialog</div>
          <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-success">Success !</button>
            <button type="button" data-dismiss="modal" class="btn btn-info">Information !</button>
            <button type="button" data-dismiss="modal" class="btn btn-danger">Danger !</button>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Login-->
    <div id="modal-login" tabindex="-1" role="dialog" aria-labelledby="modal-login-label" aria-hidden="true" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h4 id="modal-login-label" class="modal-title">Login</h4>
          </div>
          <div class="modal-body">
            <div class="form">
              <form class="form-horizontal">
                <div class="form-group">
                  <label for="username" class="control-label col-md-3">Username</label>
                  <div class="col-md-9">
                    <input id="username" type="text" class="form-control"/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="password" class="control-label col-md-3">Password</label>
                  <div class="col-md-9">
                    <input id="password" type="password" class="form-control"/>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-md-9 col-md-offset-3">
                    <button type="button" class="btn btn-primary">Login</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--Modal Prompt-->
  
    </div>
  </body>
</html>"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 06 11:05:07 UTC 2015
                    SOURCE: /home/jobby/app/views/saIndex.scala.html
                    HASH: 076a1e549da5111c3abe4a7cd60b20c00c57564a
                    MATRIX: 857->0|920->36|948->37|1042->105|1069->106|1642->643|1657->649|1778->747|1852->785|1867->791|1953->854|2027->892|2042->898|2121->955|2195->993|2210->999|2278->1045|2352->1083|2367->1089|2447->1147|2521->1185|2536->1191|2611->1244|2685->1282|2700->1288|2771->1337|2845->1375|2860->1381|2928->1427|3007->1470|3022->1476|3110->1541|3188->1583|3203->1589|3278->1642|3352->1680|3367->1686|3477->1773|3551->1811|3566->1817|3664->1892|3738->1930|3753->1936|3850->2010|3924->2048|3939->2054|4012->2105|4086->2143|4101->2149|4189->2214|4263->2252|4278->2258|4358->2316|4432->2354|4447->2360|4523->2414|4597->2452|4612->2458|4688->2512|4762->2550|4777->2556|4866->2622|4940->2660|4955->2666|5053->2741|5127->2779|5142->2785|5253->2873|5328->2912|5343->2918|5431->2983|5506->3022|5521->3028|5598->3083|5672->3121|5687->3127|5771->3188|5850->3231|5865->3237|5938->3288|6016->3330|6031->3336|6122->3404|6196->3442|6211->3448|6305->3519|6379->3557|6394->3563|6490->3636|6564->3674|6579->3680|6673->3751|6748->3790|6763->3796|6852->3862|6927->3901|6942->3907|7015->3958|7089->3996|7104->4002|7197->4072|7271->4110|7286->4116|7382->4189|7456->4227|7471->4233|7566->4305|7640->4343|7655->4349|7737->4408|7811->4446|7826->4452|7916->4519|7990->4557|8005->4563|8103->4638|8177->4676|8192->4682|8308->4775|8383->4814|8398->4820|8500->4899|8574->4937|8589->4943|8675->5006|8749->5044|8764->5050|8854->5117|8928->5155|8943->5161|8996->5192|9070->5230|9085->5236|9167->5295|9241->5333|9256->5339|9330->5391|9404->5429|9419->5435|9509->5502|9583->5540|9598->5546|9687->5612|9761->5650|9776->5656|9866->5723|9940->5761|9955->5767|10061->5850|10135->5888|10150->5894|10240->5961|10314->5999|10329->6005|10400->6054|10475->6093|10490->6099|10564->6151|10641->6192|10656->6198|10720->6240|10800->6284|10815->6290|10879->6332|10973->6390|10988->6396|11059->6444|11155->6504|11170->6510|11243->6560|11383->6664|11398->6670|11466->6716|11542->6756|11557->6762|11606->6789|11773->6920|11788->6926|11866->6982|11937->7017|11952->7023|12031->7080|12105->7118|12120->7124|12205->7187|12279->7225|12294->7231|12389->7303|12463->7341|12478->7347|12553->7400|12627->7438|12642->7444|12702->7482|12776->7520|12791->7526|12868->7581|12942->7619|12957->7625|13060->7705|13134->7743|13149->7749|13224->7802|13413->7955|13428->7961|13496->8007|13570->8045|13585->8051|13653->8097|13738->8146|13753->8152|13826->8203|13900->8241|13915->8247|13999->8309|14073->8347|14088->8353|14165->8408|14239->8446|14254->8452|14335->8511|14409->8549|14424->8555|14504->8613|14578->8651|14593->8657|14678->8720|14752->8758|14767->8764|14846->8821|14920->8859|14935->8865|15015->8923|15093->8965|15108->8971|15187->9028|15262->9066|15278->9072|15358->9129|15433->9167|15449->9173|15532->9233|15607->9271|15623->9277|15723->9353|15807->9400|15823->9406|15916->9475|15991->9513|16007->9519|16108->9596|16193->9644|16209->9650|16282->9700|16357->9738|16373->9744|16460->9808|16535->9846|16551->9852|16626->9904|16701->9942|16717->9948|16793->10001|16868->10039|16884->10045|16960->10098|17035->10136|17051->10142|17122->10190|17197->10228|17213->10234|17287->10285|17362->10323|17378->10329|17480->10407|17555->10445|17571->10451|17662->10518|17737->10556|17753->10562|17837->10623|17912->10661|17928->10667|18011->10727|18097->10776|18113->10782|18203->10848|18278->10886|18294->10892|18387->10961|18462->10999|18478->11005|18592->11095|18667->11133|18683->11139|18785->11217|18860->11255|18876->11261|18955->11317|19030->11355|19046->11361|19135->11427|19210->11465|19226->11471|19323->11544|19398->11582|19414->11588|19493->11644|19576->11690|19592->11696|19671->11752|19758->11802|19774->11808|19850->11861|19925->11899|19941->11905|20026->11967|20127->12031|20143->12037|20217->12088|20292->12126|20308->12132|20387->12188|20462->12226|20478->12232|20548->12279|20636->12330|20652->12336|20734->12394|20820->12443|20836->12449|20914->12504|20989->12542|21005->12548|21092->12612|21167->12650|21183->12656|21260->12710|21339->12752|21355->12758|21443->12822|21518->12860|21534->12866|21618->12927|21693->12965|21709->12971|21803->13041|21878->13079|21894->13085|21959->13127|22040->13171|22056->13177|22171->13268|22253->13313|22269->13319|22366->13392|22447->13436|22463->13442|22548->13503|22629->13547|22645->13553|22744->13628|22819->13666|22835->13672|22928->13741|23009->13785|23025->13791|23115->13857|23214->13919|23230->13925|23303->13974|23382->14016|23398->14022|23496->14096|23578->14141|23594->14147|23684->14213|23769->14261|23785->14267|23881->14339|23956->14377|23972->14383|24072->14459|24147->14497|24163->14503|24271->14587|24346->14625|24362->14631|24460->14705|24544->14752|24560->14758|24652->14826|24727->14864|24743->14870|24843->14946|24918->14984|24934->14990|25032->15064|25107->15102|25123->15108|25221->15182|25296->15220|25312->15226|25410->15300|25485->15338|25501->15344|25602->15421|25677->15459|25693->15465|25788->15536|25878->15589|25894->15595|25994->15671|26069->15709|26085->15715|26148->15755|26223->15793|26239->15799|26300->15837|26375->15875|26391->15881|26484->15950|26559->15988|26575->15994|26646->16042|26725->16084|26741->16090|26831->16156|26914->16202|26930->16208|27018->16272|27093->16310|27109->16316|27186->16370|27261->16408|27277->16414|27359->16473|27440->16517|27456->16523|27565->16608|27647->16653|27663->16659|27763->16735|27838->16773|27854->16779|27952->16853|28027->16891|28043->16897|28137->16967|28224->17017|28240->17023|28330->17089|28418->17140|28434->17146|28506->17195|28600->17252|28616->17258|28694->17313|28787->17369|28803->17375|28862->17411|28928->17440|28944->17446|29001->17480|29094->17536|29110->17542|29189->17598|29253->17625|29269->17631|29339->17678|29407->17709|29423->17715|29499->17768|29566->17798|29582->17804|29651->17850|29718->17880|29734->17886|29803->17932|29870->17962|29886->17968|29937->17996|30000->18022|30016->18028|30108->18097|30210->18162|30226->18168|30304->18223|30397->18279|30413->18285|30472->18321|30538->18350|30554->18356|30611->18390|30708->18450|30724->18456|30803->18512|30867->18539|30883->18545|30953->18592|31021->18623|31037->18629|31113->18682|31180->18712|31196->18718|31265->18764|31332->18794|31348->18800|31417->18846|31484->18876|31500->18882|31551->18910|31627->18949|31643->18955|31735->19024|33002->20261|33033->20262|33079->20278|33110->20279|33580->20720|33610->20721|33659->20740|33690->20741
                    LINES: 29->1|31->3|31->3|36->8|36->8|47->19|47->19|47->19|48->20|48->20|48->20|49->21|49->21|49->21|50->22|50->22|50->22|51->23|51->23|51->23|52->24|52->24|52->24|53->25|53->25|53->25|54->26|54->26|54->26|55->27|55->27|55->27|56->28|56->28|56->28|57->29|57->29|57->29|58->30|58->30|58->30|59->31|59->31|59->31|60->32|60->32|60->32|61->33|61->33|61->33|62->34|62->34|62->34|63->35|63->35|63->35|64->36|64->36|64->36|65->37|65->37|65->37|66->38|66->38|66->38|67->39|67->39|67->39|68->40|68->40|68->40|69->41|69->41|69->41|70->42|70->42|70->42|71->43|71->43|71->43|72->44|72->44|72->44|73->45|73->45|73->45|74->46|74->46|74->46|75->47|75->47|75->47|76->48|76->48|76->48|77->49|77->49|77->49|78->50|78->50|78->50|79->51|79->51|79->51|80->52|80->52|80->52|81->53|81->53|81->53|82->54|82->54|82->54|83->55|83->55|83->55|84->56|84->56|84->56|85->57|85->57|85->57|86->58|86->58|86->58|87->59|87->59|87->59|88->60|88->60|88->60|89->61|89->61|89->61|90->62|90->62|90->62|91->63|91->63|91->63|92->64|92->64|92->64|93->65|93->65|93->65|94->66|94->66|94->66|95->67|95->67|95->67|96->68|96->68|96->68|97->69|97->69|97->69|98->70|98->70|98->70|99->71|99->71|99->71|100->72|100->72|100->72|101->73|101->73|101->73|103->75|103->75|103->75|104->76|104->76|104->76|106->78|106->78|106->78|107->79|107->79|107->79|108->80|108->80|108->80|109->81|109->81|109->81|110->82|110->82|110->82|111->83|111->83|111->83|112->84|112->84|112->84|113->85|113->85|113->85|114->86|114->86|114->86|117->89|117->89|117->89|118->90|118->90|118->90|119->91|119->91|119->91|120->92|120->92|120->92|121->93|121->93|121->93|122->94|122->94|122->94|123->95|123->95|123->95|124->96|124->96|124->96|125->97|125->97|125->97|126->98|126->98|126->98|127->99|127->99|127->99|128->100|128->100|128->100|129->101|129->101|129->101|130->102|130->102|130->102|131->103|131->103|131->103|132->104|132->104|132->104|133->105|133->105|133->105|134->106|134->106|134->106|135->107|135->107|135->107|136->108|136->108|136->108|137->109|137->109|137->109|138->110|138->110|138->110|139->111|139->111|139->111|140->112|140->112|140->112|141->113|141->113|141->113|142->114|142->114|142->114|143->115|143->115|143->115|144->116|144->116|144->116|145->117|145->117|145->117|146->118|146->118|146->118|147->119|147->119|147->119|148->120|148->120|148->120|149->121|149->121|149->121|150->122|150->122|150->122|151->123|151->123|151->123|152->124|152->124|152->124|153->125|153->125|153->125|154->126|154->126|154->126|156->128|156->128|156->128|157->129|157->129|157->129|158->130|158->130|158->130|159->131|159->131|159->131|160->132|160->132|160->132|161->133|161->133|161->133|162->134|162->134|162->134|163->135|163->135|163->135|164->136|164->136|164->136|165->137|165->137|165->137|166->138|166->138|166->138|167->139|167->139|167->139|168->140|168->140|168->140|169->141|169->141|169->141|170->142|170->142|170->142|171->143|171->143|171->143|172->144|172->144|172->144|173->145|173->145|173->145|174->146|174->146|174->146|175->147|175->147|175->147|176->148|176->148|176->148|177->149|177->149|177->149|178->150|178->150|178->150|179->151|179->151|179->151|180->152|180->152|180->152|181->153|181->153|181->153|182->154|182->154|182->154|183->155|183->155|183->155|184->156|184->156|184->156|185->157|185->157|185->157|186->158|186->158|186->158|187->159|187->159|187->159|188->160|188->160|188->160|189->161|189->161|189->161|190->162|190->162|190->162|191->163|191->163|191->163|192->164|192->164|192->164|193->165|193->165|193->165|194->166|194->166|194->166|195->167|195->167|195->167|196->168|196->168|196->168|197->169|197->169|197->169|198->170|198->170|198->170|199->171|199->171|199->171|200->172|200->172|200->172|201->173|201->173|201->173|205->177|205->177|205->177|206->178|206->178|206->178|207->179|207->179|207->179|208->180|208->180|208->180|209->181|209->181|209->181|210->182|210->182|210->182|211->183|211->183|211->183|212->184|212->184|212->184|213->185|213->185|213->185|214->186|214->186|214->186|216->188|216->188|216->188|217->189|217->189|217->189|218->190|218->190|218->190|219->191|219->191|219->191|220->192|220->192|220->192|221->193|221->193|221->193|222->194|222->194|222->194|223->195|223->195|223->195|224->196|224->196|224->196|227->199|227->199|227->199|241->213|241->213|241->213|241->213|246->218|246->218|246->218|246->218
                    -- GENERATED --
                */
            
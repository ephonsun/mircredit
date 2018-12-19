// JavaScript Document

;(function($){
	$.fn.extend({
		"accordion": function(options){
			options = $.extend({
				moduledt: "dt",
				moduledd: "dd",
				dtcurrent: "dtcurrent",
				licurrent: "licurrent",
				speed: "normal",
				eventtype: "click"
			}, options);
			
			var $dt = this.find(options.moduledt);
			var $ul = this.find(options.moduledd).find("ul");
			
			$dt.bind(options.eventtype, function(){
				$(this).toggleClass(options.dtcurrent).next().slideToggle(options.speed);
			});
			$ul.find("li").bind("click", function(){
				if($(this).attr("class") == options.licurrent){
					return false;
				}
				$ul.find("li." + options.licurrent).removeAttr("class");
				$(this).addClass(options.licurrent);
			});
			
			return this;
		},
		"stretch": function(o){
			o = $.extend({
				button: "#switch"
			}, o);
			
			$(this).find(o.button).toggle(
				function(){
					$(this).parent().css("left", -$(this).parent().width()).next().css("marginLeft", 0);
				},
				function(){
					$(this).parent().css("left", 0).next().css({"marginLeft": $(this).parent().width() + $(this).width() + 1});
				}
			);
			
			return this;
		}
	});
})(jQuery);
jQuery(function($) {
  "use strict";

  $('a.page-scroll').bind('click', function(event) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top - 100)
    }, 1250, 'easeInOutExpo');
    event.preventDefault();
  });

});

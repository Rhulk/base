$(document).ready(function(){
  var zindex = 10;
  
  $("button.joinbtn").click(function(e){
  
  	location.href ='curso'+document.querySelector('.joinbtn').value;
  
  });
  
  $("div.card_curso").click(function(e){
    e.preventDefault();

    var isShowing = false;

    if ($(this).hasClass("show")) {
      isShowing = true
    }

    if ($("div.cards_cursos").hasClass("showing")) {
      // a card is already in view
      $("div.card_curso.show")
        .removeClass("show");

      if (isShowing) {
        // this card was showing - reset the grid
        $("div.cards_cursos")
          .removeClass("showing");
          console.log(' - Is Showing - ');
          //location.href ='cursoID' No mola
      } else {
        // this card_curso isn't showing - get in with it
        $(this)
          .css({zIndex: zindex})
          .addClass("show");
          console.log(' - No Showing - ');

      }

      zindex++;

    } else {
      // no cards in view
      $("div.cards_cursos")
        .addClass("showing");
      $(this)
        .css({zIndex:zindex})
        .addClass("show");
        console.log(' - No no Showing - ');
      zindex++;
    }
    
  });
});
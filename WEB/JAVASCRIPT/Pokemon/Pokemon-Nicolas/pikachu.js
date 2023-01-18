//1) saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

  var posX=0;
  var posY=0;
  var posYbefore = 0;
  var posXbefore = 0;
  var mvt = 20;

  var pokemon="pikachu";
  var img="Down.png";

  imgPikachu.setAttribute("src","assets/img/"+pokemon+img);

  btnStart.onclick=lancerAventure;


  inputName.onkeyup=function(event)
  {
    //console.log(event);
    if(inputName.value=="")
    {
      btnStart.disabled=true;
    }
    else
    {
        btnStart.disabled=false;
        if(event.key=="Enter"){
        lancerAventure();
      }
    }
  }



  function lancerAventure()
  {
    document.body.onkeydown=deplacement;
    var startBalise = document.getElementById("formStart");
    startBalise.style.setProperty("display","none");
    var grassBalise = document.getElementById("grass");
    grassBalise.style.setProperty("display","block");
    var testBalise = document.getElementById("test");
    testBalise.title = document.getElementById("inputName").value;
    var mainTheme = document.getElementById("mainTheme");
    mainTheme.play();
    mainTheme.style.setProperty("display","block");
    mainTheme.volume = 0.02;
    positionPierre();
  }

  function deplacement(event)
  {

    var grass = document.getElementById("grass");

    if(event.key=="ArrowDown" || event.key=="s" )
    {
      posYbefore = posY;
      posY +=mvt;
      img="Down.png";
    }
    else if(event.key=="ArrowRight" || event.key=="d" )
    {
      posXbefore = posX;
      posX +=mvt;
      img="Right.png";
    }

    else if(event.key=="ArrowLeft" || event.key=="q")
    {
      posXbefore = posX;
      posX -=mvt;
      img="Left.png";
    }

    else if(event.key=="ArrowUp" || event.key=="z")
    {
      posYbefore = posY;
      posY -=mvt;
      img="Up.png";
    }
    //console.log(pikachu.offsetWidth-4)
  /*
  if(posX >= 0 && posX + pikachu.offsetWidth-4 <= grass.offsetWidth && posY >= 0 && posY + pikachu.offsetHeight-4 <= grass.offsetHeight){
  //console.log("if x"+posX+"y"+posY)
  }

  else{
    posX = posXbefore;
    posY = posYbefore;
    //console.log("else x"+posX+"y"+posY)
  }*/

  if (posX + pikachu.offsetWidth-6 >= grass.offsetWidth){
    posX = 0;
  }
  else if (posY + pikachu.offsetHeight-6 >= grass.offsetHeight){
    posY = 0;
  }
  else if (posX < 0 ){
    posX = grass.offsetWidth-pikachu.offsetWidth;
  }
  else if (posY < 0){
    posY = grass.offsetHeight-pikachu.offsetHeight;
  }

  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon+img);
  testfonction();
  }

  function positionPierre(){
    var grass = document.getElementById("grass");
    var pierre = document.getElementById("pierre");

    var maxX = grass.offsetWidth - pierre.offsetWidth;
    var maxY = grass.offsetHeight - pierre.offsetHeight;

    pierre.style.left = Math.random() * maxX + "px";
    pierre.style.top = Math.random() * maxY + "px";
  }

  function testfonction()
  {

    var pikachuRect = pikachu.getBoundingClientRect();
    var pierreRect = pierre.getBoundingClientRect();

    //console.log("right"+pikachuRect.right+ " bottom "+pikachuRect.bottom );
    //console.log(pikachuRect);
    //console.log("left"+otherRect.left + " top "+otherRect.top);
    //console.log(otherRect);

    if (pikachuRect.right > pierreRect.left && pikachuRect.bottom > pierreRect.top && pikachuRect.right < pierreRect.left + 60 && pikachuRect.bottom < pierreRect.top + 60) {
    console.log(imgPikachu.src)
    console.log(imgPikachu.src == "assets/img/pikachuDown.png");
    pierre.style.display="none";
    mainTheme.setAttribute("src","assets/audio/evolution.mp3");
    mainTheme.volume = 0.02;
    mainTheme.play();
    var intervalId = setInterval(function(){
      imgPikachu.setAttribute("src","assets/img/pikachuDown.png");
      }, 500);
    var intervalId2 = setInterval(function(){
      imgPikachu.setAttribute("src","assets/img/evolve.png");
      }, 200);
    setTimeout(function(){
    clearInterval(intervalId);
    clearInterval(intervalId2);
      pokemon = "raichu";
      imgPikachu.setAttribute("src","assets/img/"+pokemon+img);
      mvt=50;
      mainTheme.setAttribute("src","assets/audio/theme.mp3");
      mainTheme.currentTime = 3.0;
      mainTheme.volume = 0.02;
      mainTheme.play();
    }, 5000);
  }
  }

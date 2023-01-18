//1) saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posX=-75;
var posY=0;

var pokemon="pikachu";
var img="Down.png";
var maxY=650;
var maxX=590;
var minY=0;
var minX=-75;
var isDrunk=false;

imgPikachu.setAttribute("src","assets/img/"+pokemon+img);


inputName.onkeyup=function(event){
  if(inputName.value=="")
  {
    btnStart.disabled=true;
  } else {
    btnStart.disabled=false;
  }
}

btnStart.onclick=lancerAventure;

function lancerAventure()
{
  formStart.style.display="none";
  grass.style.display="block";
  let name = document.getElementById("inputName").value;
  pikachu.setAttribute("title",name);
  // pokeSound.play();
  document.body.onkeydown=deplacement;
}

function deplacement(event)
{
  if (isDrunk==false){
    if(event.key=="ArrowDown" || event.key=="s" )
    {
      img="Down.png";
      posY+=5;
      if(event.ctrlKey){posY+=20;}
    } else if(event.key=="ArrowRight" || event.key=="d" )
    {
      posX+=5;
      if(event.ctrlKey){posX+=20;}
      img="Right.png";
    } else if(event.key=="ArrowLeft" || event.key=="q")
    {
      posX-=5;
      if(event.ctrlKey){posX-=20;}
      img="Left.png";
    } else if(event.key=="ArrowUp" || event.key=="z")
    {
      posY-=5;
      if(event.ctrlKey){posY-=20;}
      img="Up.png";
    }

    if(posX>maxX){
      posX=minX;
    } else if (posX<minX){
      posX=maxX;
    } else if (posY>maxY){
      posY=minY;
    } else if (posY<minY){
      posY=maxY;
    }

  } else if (isDrunk==true){
    if(event.key=="ArrowDown" || event.key=="s" )
    {
      img="Up.png";
      posY-=5;
      if(event.ctrlKey){posY-=20;}
    } else if(event.key=="ArrowRight" || event.key=="d" )
    {
      posX-=5;
      if(event.ctrlKey){posX-=20;}
      img="Left.png";
    } else if(event.key=="ArrowLeft" || event.key=="q")
    {
      posX+=5;
      if(event.ctrlKey){posX+=20;}
      img="Right.png";
    } else if(event.key=="ArrowUp" || event.key=="z")
    {
      posY+=5;
      if(event.ctrlKey){posY+=20;}
      img="Down.png";
    }

    if(posX>maxX){
      posX=minX;
    } else if (posX<minX){
      posX=maxX;
    } else if (posY>maxY){
      posY=minY;
    } else if (posY<minY){
      posY=maxY;
    }
  }

  if ((posY>=140 && posY<=155) && (posX>=115 && posX<=120)) {
    isDrunk=true;
  }

  if ((posY>=440 && posY<=450) && (posX>=545 && posX<=560)) {
    isDrunk=false;
  }

  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon+img);
}

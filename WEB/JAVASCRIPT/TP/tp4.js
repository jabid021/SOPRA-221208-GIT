
var continuer;
do{
  var element = prompt("Quel type d'element ? id / class / tag ?");
  var libelle = prompt("libelle de l'element "+element);
  var prop = prompt("Quelle prop pour l'element "+element+" - "+libelle);
  var value= prompt("Quelle value pour la prop : "+prop);



  if(element=="id")
  {
    let balise = document.getElementById(libelle);
    balise.style.setProperty(prop,value);
  }
  else if(element=="class")
  {
    let balises=document.getElementsByClassName(libelle);
    for(b of balises)
    {
      //b.style.prop=value;
      b.style.setProperty(prop,value);
    }
  }
  else if(element=="tag")
  {
    let balises=document.getElementsByTagName(libelle);
    for(b of balises)
    {
      b.style.setProperty(prop,value);
    }
  }
  else
  {
    alert("type d'element invalide");
  }

  continuer=prompt("Faire une autre modif ? y/n");

}while(continuer=="y");

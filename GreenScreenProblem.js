var fgImage = new SimpleImage("drewgreen.png");
var bgImage = new SimpleImage("dinos.png");
var height = fgImage.getHeight();
var width = fgImage.getWidth();
var output = new SimpleImage(width, height);

for (var px of fgImage.values()){
    var x = px.getX(); //find x position
    var y = px.getY();//find y position
    
    //define green: has more green than blue and red combined
    if(px.getGreen() > (px.getRed() + px.getBlue())){  
        //look at same position in bgImage
        var bgPx = bgImage.getPixel(x, y); // get same position in bgImage
        output.setPixel(x, y, bgPx);
    } else {
        output.setPixel(x, y, px); 
    }
}

print(output)

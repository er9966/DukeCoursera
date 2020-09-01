// write your code here
var img = new SimpleImage("smallpanda.png");
var result = setBlack(img, 10);
print(result);

function setBlack(img, thickness){
    for (px of img.values()){
        x=px.getX();
        y=px.getY();
        
        if(x < thickness || y < thickness 
           || y>img.getHeight()-thickness || x>img.getWidth()-thickness){
            px.setRed(0);
            px.setGreen(0);
            px.setBlue(0);
        }
    }
    return img
    
}

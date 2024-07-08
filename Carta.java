package ec.edu.espol.cartas;

public abstract class Carta{
    private Color color;

    public Carta(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
    
    
}

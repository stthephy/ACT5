package Figures;

public class Pyramid extends GtricFigure {
    private double base;
    private double altura;
    private double apotema;
    
    public Pyramid(double base, double altura, double apotema) {
        this.base = base;
        this.altura = altura;
        this.apotema = apotema;
        this.setVolumen(calcularVolumen());
        this.setSuperficie(calcularSuperficie());
    }
    
    public double calcularVolumen() {
        double volumen = (Math.pow(base, 2.0) * altura) / 3.0;
        return volumen;
    }
    
    public double calcularSuperficie() {
        double áreaBase = Math.pow(base, 2.0);
        double áreaLado = 2.0 * base * apotema;
        return áreaBase + áreaLado;
    }
}

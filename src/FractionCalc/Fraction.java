package FractionCalc;

/**
 * Created by Abdullah A on 2017-08-12.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    //CONSTRUCTORS

    public Fraction(int numerator, int denominator) {

        if(denominator==0)
            throw new IllegalArgumentException("The denominator cannot be 0!");

        if(denominator < 0) {                                       //Making the numerator negative instead of denominator
            denominator = Math.abs(denominator);
            numerator = numerator * -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public Fraction() {
        this(0, 1);
    }

    //METHODS OR BEHAVIOUR

    public int getNumerator(){
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }

    public String toString(){
        return numerator + "/" + denominator;
    }

    public double toDouble(){
        return numerator/denominator;
    }

    public Fraction add(Fraction other){
        int Numerator;
        int Denominator;

        if(this.denominator!=other.denominator){
            Numerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);           //Making everything have a common denominator
            Denominator = this.denominator * other.denominator;
        }

        else{
            Numerator = this.numerator+other.numerator;
            Denominator = other.denominator;
        }

        Fraction add = new Fraction(Numerator, Denominator);
        add.toLowestTerms();
        return add;
    }

    public Fraction subtract(Fraction other){
        int Numerator;
        int Denominator;

        if(this.denominator!=other.denominator){
            Numerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);    //Making everything have a common denominator, NOT in lowest terms
            Denominator = this.denominator * other.denominator;
        }

        else{
            Numerator = this.numerator-other.numerator;
            Denominator = other.denominator;
        }

        Fraction subtract = new Fraction(Numerator, Denominator);
        subtract.toLowestTerms();
        return subtract;
    }

    public Fraction multiply(Fraction other){
        int Numerator = this.numerator * other.numerator;
        int Denominator = this.denominator * other.denominator;

        Fraction multiply = new Fraction(Numerator, Denominator);
        multiply.toLowestTerms();
        return multiply;
    }

    public Fraction divide(Fraction other){
        int Numerator = this.numerator * other.denominator;           //In division, you flip the second fraction and multiply
        int Denominator = this.denominator * other.numerator;

        Fraction divide = new Fraction(Numerator, Denominator);
        divide.toLowestTerms();
        return divide;
    }

    public boolean equals(Object other){
        if(other instanceof Fraction) {                         //if parameter is a fraction (part of the fraction class)

            Fraction otherFrac = (Fraction) other;              //casting it into a fraction
            otherFrac.toLowestTerms();

            if(otherFrac.numerator == this.numerator && otherFrac.denominator == this.denominator)
                return true;
            else
                return false;
        }

        else{
            throw new IllegalArgumentException("Parameter is not a fraction!");
        }
    }

    public void toLowestTerms() {
        int gcd = gcd(numerator, denominator);
        if(gcd != 0) {
            numerator = numerator / gcd;
            denominator = denominator / gcd;
        }
    }

    public static int gcd(int num, int denom) {
        while(num!=0 && denom!=0){                        //Euclidean algorithm
            int remainder = num % denom;
            num = denom;
            denom = remainder;
        }
        return num;
    }
}

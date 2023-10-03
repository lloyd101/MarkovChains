public class Vector extends Matrix {
    // constructor with number of columns as parameter
    public Vector(int c) {
        super(1, c);
    }
    // constructor with number of columns and linear array as parameters
    public Vector(int c, double[] linArr) {
        super(1, c, linArr);
    }
    // method to get element at column c
    public double getElement(int c) {
        return this.getElement(0, c);
    }
}
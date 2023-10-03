
class Matrix {
    //private instance variables
    private int numRows;
    private int numCols;
    private double[][] data;

    //constructor
    public Matrix(int r, int c) {
        numRows = r;
        numCols = c;
        data = new double[r][c];
    }

    //  2-dimensional array data constructor
    public Matrix(int r, int c, double[] linArr) {
        numRows = r;
        numCols = c;
        data = new double[r][c];
        int a = 0;
        for (int b = 0; b < r; b++) {
            for (int d = 0; d < c; d++) {
                data[b][d] = linArr[a++];
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public double[][] getData() {
        return data;
    }

    public double getElement(int r, int c) {
        return data[r][c];
    }

    public void setElement(int r, int c, double value) {
        data[r][c] = value;
    }

    public void transpose() {
        double[][] transposedData = new double[numCols][numRows];
        // Transpose the matrix
        for (int e = 0; e < numRows; e++) {
            for (int f = 0; f < numCols; f++) {
                transposedData[f][e] = this.data[e][f];
            }
        }
        // Update the instance variables, so that the number of rows and columns in the matrix is swapped
        this.data = transposedData;
        int swap = numRows;
        numRows = numCols;
        numCols = swap;
    }

    public Matrix multiply(double scalar) {
        Matrix result = new Matrix(numRows, numCols);
        for (int g = 0; g < numRows; g++) {
            for (int h = 0; h < numCols; h++) {
                result.data[g][h] = this.data[g][h] * scalar;
            }
        }
        return result;
    }
    public Matrix multiply(Matrix other) {
        if (other.numRows!= this.numCols) {
            return null;
        }

        //creating a new Matrix object
        double[][] newData = new double[this.numRows][other.numCols];

        Matrix result = new Matrix(this.numRows, other.numCols, new double[this.numRows * other.numCols]);

        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < other.numCols; j++) {
                for (int k = 0; k < this.numCols; k++) {
                    result.data[i][j] +=  this.data[i][k] * other.data[k][j];
                }
            }
        }
        return result;
    }

    public String toString(){
        //check if the data array is empty
        if (data.length == 0) {
            return "Empty matrix";
        }
        //building a string containing the entire matrix
        String result = "";
        //for loop to loop through each row and column of the matrix
        for (int l = 0; l < this.numRows; l++) {
            for (int m = 0; m < this.numCols; m++) {
                //concatenating all the string so that there is three decimal spaces and only 8 character in a line
                result += String.format("%8.3f", data[l][m]);
            }
            // after there is 8 character on the line it would create a new line
            result += "\n";
        }
        // returning the result
        return result;
    }
}


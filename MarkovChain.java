
import java.util.Arrays;

public class MarkovChain {
    // A variable to store the state vector.
    private Vector stateVector;
    // A variable to store the transition matrix.
    private Matrix transitionMatrix;

    // A variable to store the result of the computation.
    private Matrix result = transitionMatrix;

    // A constructor to initialize the MarkovChain object with a state vector and a transition matrix.
    public MarkovChain(Vector sVector, Matrix tMatrix) {
        this.stateVector = sVector;
        this.transitionMatrix = tMatrix;
    }

    // A method to check if the Markov chain is valid.
    public boolean isValid() {
        // Get the number of columns in the state vector.
        int stateVectorCols = stateVector.getNumCols();
        // Get the number of rows and columns in the transition matrix.
        int transitionMatrixRows = transitionMatrix.getNumRows();
        int transitionMatrixCols = transitionMatrix.getNumCols();

        // Check if the number of rows and columns in the transition matrix are equal,
        // and if the number of columns in the state vector is equal to the number of columns in the transition matrix.
        // If either of these conditions is not met, return false.
        if (transitionMatrixRows != transitionMatrixCols || transitionMatrixCols != stateVectorCols) {
            return false;
        }

        // Calculate the sum of all elements in the state vector.
        double stateVectorSum = Arrays.stream(stateVector.getData()[0]).sum();
        // Check if the sum of all elements in the state vector is close to 1.0.
        // If it is not, return false.
        if (Math.abs(stateVectorSum - 1.0) > 0.01) {
            return false;
        }

        // Loop through each row of the transition matrix.
        for (int i = 0; i < transitionMatrixRows; i++) {
            // Calculate the sum of all elements in the current row.
            double rowSum = Arrays.stream(transitionMatrix.getData()[i]).sum();
            // Check if the sum of all elements in the current row is close to 1.0.
            // If it is not, return false.
            if (Math.abs(rowSum - 1.0) > 0.01) {
                return false;
            }
        }

        // If all conditions are met, return true.
        return true;
    }

    // A method to compute the probability matrix after a specified number of steps.
    public Matrix computeProbabilityMatrix(int numSteps) {
        // Check if the Markov chain is valid.
        if (!isValid()) {
            // If it is not valid, return null.
            return null;
        }

        // Initialize a new matrix with the current transition matrix.
        Matrix newMatrix = transitionMatrix;
        // Loop through the number of steps specified.
        for (int i = 0; i < numSteps - 1; i++) {
            // Multiply the new matrix with the transition matrix.
            newMatrix = newMatrix.multiply(transitionMatrix);
        }
        // Multiply the state vector with the new matrix
        newMatrix = stateVector.multiply(newMatrix);

        return newMatrix;
    }
}




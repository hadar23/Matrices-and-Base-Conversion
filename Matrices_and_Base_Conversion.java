
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Matrices_and_Base_Conversion {

	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null, "Part 1, building and multiplication of 2 matrices");

		int num1, num2, num3, num4;
		// getting information from the user for building our arrays
		JOptionPane.showMessageDialog(null,
				"Please enter 4 numbers to create two matrices: \n (first matrix [1][2], second matrix [3][4]");
		num1 = Integer.parseInt(JOptionPane.showInputDialog("Enter number [1] - rows of matrix A"));
		num2 = Integer.parseInt(JOptionPane.showInputDialog("Enter number [2] - cols of matrix A"));
		num3 = Integer.parseInt(JOptionPane.showInputDialog("Enter number [3] - rows of matrix B"));
		num4 = Integer.parseInt(JOptionPane.showInputDialog("Enter number [4] - cols of matrix B"));
		int[][] metrix1 = matrixmaker(num1, num2);// NEW matrix1
		int[][] metrix2 = matrixmaker(num3, num4);// NEW matrix2
		int[][] metrixmulti = metrixmulti(metrix1, metrix2);// NEW matrix
		// =multiply between
		// matrix1 matrix2

		if (metrixmulti == null)
			JOptionPane.showMessageDialog(null, "Its not valid to multiply the matrices");
		else // if its possible to multiply
		{
			JOptionPane.showMessageDialog(null, new JTextArea(matrixtoString(metrixmulti)),
					"The multiplication of the matrices", JOptionPane.INFORMATION_MESSAGE);
		}

		JOptionPane.showMessageDialog(null,
				"Part 2, printing two different random numbers\nRandom BIN number and random HEX number ");
		int x = (int) (Math.random() * 128);// random decimal number
		// -----------------------------------------
		char[] BASE16 = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		// making new random NUM in base 16
		int sizeOfHex = (int) (Math.random() * 2 + 3);
		char[] hexRand = new char[sizeOfHex];// string to CharArray

		for (int i = 0; i < sizeOfHex; i++) {
			hexRand[i] = BASE16[(int) (Math.random() * 16)];
		}
		String hexRandStr = new String(hexRand);// array to string by
		// constructor

		JOptionPane.showMessageDialog(null, "(BIN)" + decimaltobinary(x) + "=(DEC)" + x + "\n" + "(HEX)" + hexRandStr
				+ "=(DEC)" + base16todecimal(hexRand));
		// -----------------------------------------
		
		JOptionPane.showMessageDialog(null,
				"Part 3, array printing. looking for a number, and printing in different bases ");
		int size = Integer.parseInt(JOptionPane.showInputDialog("Please enter the size of the array:"));
		int[] arrayuser = (arraymake(size));// making new array with random
		// number
		arrayuser = sortarray(arrayuser);
		arrayuser = reversarray(arrayuser);
		// print the array in a massage box
		JOptionPane.showMessageDialog(null, new JTextArea(arraytoString(arrayuser)), "The array is",
				JOptionPane.INFORMATION_MESSAGE);

		int numchoose = Integer.parseInt(JOptionPane.showInputDialog("Enter a simple number between 1 to 127:"));
		int index = binarySearch(arrayuser, numchoose);// searching the number that the user chose in the array

		if (index != -1) {// if we find the number, so printing
			JOptionPane.showMessageDialog(null, "The place of your number in the array is " + index + "\n" + numchoose
					+ "(DEC)= " + decimaltobinary(numchoose) + "(BIN)= " + decimaltobase16(numchoose) + "(HEX)");
		}

		else// if the number that we chose does not exist in the array
			JOptionPane.showMessageDialog(null, "The number you chose does not ex" + "ist in the array");

		// -----------------------------------------
		// char[] starray9 = { 'E', 5, 5 };

	}

	public static int[] arraymake(int x) { // make an array with random numbers
		int[] array = new int[x];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 128);
		}
		return array;
	}

	public static int[][] matrixmaker(int a, int b) { // matrix maker using
		// array make method
		int matrix[][] = new int[a][b];

		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = arraymake(b);
		}
		return matrix;
	}

	public static int[][] metrixmulti(int[][] matrixA, int[][] matrixB) {// matrix multiplication

		if (matrixA[0].length != matrixB.length)
			return null;

		int[][] result = new int[matrixA.length][matrixB[0].length];
		for (int i = 0; i < matrixA.length; i++) // over rows matrix 1
			for (int j = 0; j < matrixB[0].length; j++) // over cols matrix 2
				for (int k = 0; k < matrixA[0].length; k++) //
					result[i][j] += matrixA[i][k] * matrixB[k][j];// multiply
		return result;
	}

	public static int[][] transportmatrix(int[][] m1) {// transposed matrix
		int[][] tmatrix = new int[m1[0].length][m1.length];
		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1[0].length; j++)
				tmatrix[j][i] = m1[i][j];
		return tmatrix;
	}

	public static int decimaltobinary(int a) {// decimal to binary
		String num = "";
		// making string , do all the math necessary and convert to int
		while (a != 0) {
			if (a % 2 == 0) {
				num += 0;
				a = a / 2;
			} else {
				num += 1;
				a = a / 2;
			}
		}
		String[] s;
		s = Stringtoarray(num);
		s = reversarray(s);
		num = arraytoString(s);
		return Integer.parseInt(num);
	}

	public static int binarytodecimal(int a) {// binary to decimal
		String s = "";
		while (a != 0) { // int to string
			s += a % 10;
			a = a / 10;
		}
		String[] array = Stringtoarray(s);// String to array
		s = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals("1")) {// in array of strings, if you want to get to the
				// value, use ".equals"
				a += (int) Math.pow(2, i);
			}
		}
		return (a);
	}

	public static String decimaltobase16(int a) {// decimal to base 16
		String s = "";
		while (a != 0) {
			if (a % 16 != 0) {
				if (a % 16 > 9)
					s += (char) (a % 16 - 10 + 'A');
				else
					s += a % 16;
				a = a / 16;
			} else
				s += a / 16;
		}
		String[] array = Stringtoarray(s);

		array = reversarray(array);
		s = arraytoString(array);

		return s;

	}

	public static int base16todecimal(char[] array) {// base 16 to decimal

		char num;
		int X = 0;
		array = reversarray(array);
		for (int i = 0; i < array.length; i++) {
			if (array[i] - '0' < 10) { // if it is an int value
				num = array[i];
				X += (num - '0') * Math.pow(16, i);// num - '0' this make the
				// string value to int by
				// using the ASCII table.
			} else { // if its char value
				char ch = array[i];
				X += (ch - 'A' + 10) * Math.pow(16, i);
			}
		}
		return X;
	}

	public static char[] reversarray(char[] array) {// reversed array
		char temp;
		for (int i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
		return array;
	}

	public static String[] reversarray(String[] array) {// reversed array
		String temp;
		for (int i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
		return array;
	}

	public static int[] reversarray(int[] array) {// reversed array
		int temp;
		for (int i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
		return array;
	}

	public static String[] Stringtoarray(String s) {// String to array
		String[] array = new String[s.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = String.valueOf(s.charAt(i));
		}
		return array;

	}

	public static String arraytoString(char[] array) {// String to array
		String arrays = "";
		for (int i = 0; i < array.length; i++) {
			arrays += array[i];
		}
		return arrays;

	}

	public static String arraytoString(String[] array) {// array to String
		String arrays = "";
		for (int i = 0; i < array.length; i++) {
			arrays += array[i];
		}
		return arrays;

	}

	public static int[] sortarray(int[] array) {// sort array
		for (int i = 0; i < array.length; i++)
			for (int J = 0; J < array.length; J++) {

				if (array[i] > array[J]) {
					int temp = array[i];
					array[i] = array[J];
					array[J] = temp;
				}
			}
		return array;
	}

	public static int binarySearch(int[] list, int key) {// binary Search
		int low = 0;
		int high = list.length - 1;

		while (high >= low) {
			int mid = (low + high) / 2;
			if (key < list[mid])
				high = mid - 1;
			else if (key == list[mid])
				return mid;
			else
				low = mid + 1;
		}
		return -1; // Now high < low
	}

	public static String arraytoString(int[] array) {// array to String
		String arrays = "";
		for (int i = 0; i < array.length; i++) {
			arrays += array[i] + "   ";
		}
		return arrays;
	}

	public static String matrixtoString(int[][] matrix) {// matrix to String
		String matrixs = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrixs += matrix[i][j] + "\t";
			}
			matrixs += "\n";
		}
		return matrixs;

	}

}
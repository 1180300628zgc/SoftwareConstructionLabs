package P1;

import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MagicSquare {
	public static void main(String[] args) {
		System.out.println(isLegalMagicSquare("1.txt"));
		System.out.println(isLegalMagicSquare("2.txt"));
		System.out.println(isLegalMagicSquare("3.txt"));
		System.out.println(isLegalMagicSquare("4.txt"));
		System.out.println(isLegalMagicSquare("5.txt"));
		// 产生 Magic Square
		generateMagicSquare(4);
		System.out.println(isLegalMagicSquare("6.txt"));
	}

	public static boolean isLegalMagicSquare(String fileName) {
		int column = 0; // 列数
		int row = 0; // 行数
		int sum = 0; // 行总和
		int otherSum = 0; // 其它行总和
		int i, j = 0;
		int[][] matrix = new int[1000][1000]; // 存储 Square
		boolean skip = false;
		boolean flag = true;

		String filename = "src/P1/txt/" + fileName; // 文件路径
		File file = new File(filename);
		Long fileLength = file.length(); // 保存文件字符串长度
		try {
			InputStreamReader inputContent = new InputStreamReader(new FileInputStream(filename)); // 建立一个流输入对象
			BufferedReader br = new BufferedReader(inputContent);
			String line = "";
			while (line != null) {
				try {
					line = br.readLine(); // 分割为每行的字符串保存
					if (line != null) { // 防止空指针
						row++; // 行数在每次读完一行之后 +1
						String[] lineCharacter = line.split("\t"); // 以 tab 分割字符
						column = lineCharacter.length; // 保存列数
						for (i = 0; i < column; i++) {
							try {
								matrix[j][i] = Integer.valueOf(lineCharacter[i]);
								if(matrix[i][j] < 0) {
									System.out.println("存在负整数输入 ");
									return false;
								}
							} catch (Exception e) {
								System.out.print("数字之间并非使用 \\t 分割 ");
								return false; // Integer.valueOf(lineCharacter[i]) 可能捕捉到带空格的数字，即不是以 /t 分隔
							}
						}

						j++;
						if (!skip) {
							for (i = 0; i < column; i++)
								sum += matrix[j - 1][i];
							// sum += Integer.valueOf(lineCharacter[i]);
							skip = true;
						} else {
							for (i = 0; i < column; i++)
								otherSum += matrix[j - 1][i];
							// otherSum += Integer.valueOf(lineCharacter[i]);
							// System.out.println(otherSum);
							if (otherSum != sum) {
								System.out.print("行、列、对角线数字和并不完全相等 ");
								return false;
							}
							otherSum = 0;
						}

						if (j == column) {
							for (i = 0; i < column; i++) {
								for (j = 0; j < column; j++) {
									otherSum += matrix[i][j];
								}
								if (otherSum != sum) {
									// System.out.println("for col: " + otherSum);
									System.out.print("行、列、对角线数字和并不完全相等 ");
									return false;
								}
								otherSum = 0;
							}

							for (i = 0; i < column; i++) {
								otherSum += matrix[i][i];
							}
							if (otherSum != sum) {
								// System.out.println("for row: " + otherSum);
								System.out.print("行、列、对角线数字和并不完全相等 ");
								return false;
							}
							otherSum = 0;
						}

					}
					// System.out.println(row); 测试
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.print("文件输入错误，打印错误信息： ");
					e.printStackTrace();
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.print("文件未找到 ");
			return false;
		}

		if (row != column) {
			System.out.print("行数和列数不相等 ");
			return false;
		}
		return true;
	}

	public static boolean generateMagicSquare(int n) {
		// 如果 n 是负数，根本无法产生 magic 数组，所以会产生 java.lang.NegativeArraySizeException
		try {
			int magic[][] = new int[n][n]; // 用 magic 二维数组存储 magic square
			int row = 0, col = n / 2, i, j, square = n * n;

			for (i = 1; i <= square; i++) {
				magic[row][col] = i;
				// col 从 n / 2 开始，超出界限后换行
				if (i % n == 0) {
					// 原来的情况是在最后一行的情况下同时 row ++ 会越界，所以产生
					// java.lang.ArrayIndexOutOfBoundsException
					row++;
				}

				else {
					// 行数如果为 0，立刻变为最后一行，否则向 0 行移动
					if (row == 0)
						row = n - 1;
					else
						row--;
					// 列数如果为最后一列，立刻变为第 0 列，否则向最后一列移动
					if (col == (n - 1))
						col = 0;
					else
						col++;
				}
			}

			File writename = new File("src/P1/txt/6.txt"); // 将生成的 Magic Square 写入 6.txt
			try {
				writename.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++) {
						System.out.print(magic[i][j] + "\t");
						out.write(magic[i][j] + "\t");
					}
					if (i != n - 1)
						out.write("\n");
					System.out.println();
				}

				out.flush();
				out.close();
			} catch (IOException e) { // 如果输入非法，依题意返回 false
				// TODO Auto-generated catch block
				System.out.println("输入不合法，打印错误信息：");
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			System.out.println("输入不合法，打印错误信息：");
			e.printStackTrace();
			return false;
		}
		// 打印 magic square
		return true;
	}
}

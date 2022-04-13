package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Products;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Products> list = new ArrayList<>();

		System.out.println("Enter file path: ");
		String strPath = sc.nextLine();

		File path = new File(strPath);
		String strFolder = path.getParent();

		boolean success = new File(strFolder + "\\out").mkdir();

		String targetFile = strFolder + "\\out\\sumary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {

			String line = br.readLine();
			while (line != null) {

				String [] lines = line.split(",");
				String name = lines[0];
				double price = Double.parseDouble(lines[1]);
				int quantity = Integer.parseInt(lines[2]);

				list.add(new Products(name, price, quantity));

				line = br.readLine();

			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {

				for (Products item : list) {

					bw.write(item.getName() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}
				
				System.out.println(targetFile + " CREATED!");
				
			} catch (IOException e) {
				System.out.println("Error reding file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reding file: " + e.getMessage());
		}

		sc.close();
	}

}

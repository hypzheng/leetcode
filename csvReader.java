import java.io.*;
import java.util.*;

/**
 * Author: Hao Zheng
 * This code is used for processing COVID-19 metadata csv file.
 */
public class csvReader
{
	private static ArrayList<ArrayList<String>> cols;
	private static ArrayList<String[]> rows;

	/* 主方法 */
	public static void main(String[] args)
	{
		//初始化变量
		String path = "/Users/zhenghao/Desktop/usra2021/test.csv";
		cols = new ArrayList<>();
		rows = new ArrayList<>();
		int colWeek = 2;
		int colYear = 4;
		int colGender = 5;
		int colAge = 6;
		int colIcu = 11;

		//读取文件
		readCSV(path);

		//输出分析结果
		System.out.println();

		//筛选出2020和2021年满足要求的entry的数量
		ArrayList<String[]> year20 = getEachYear(colYear, colWeek, 20, 8);
		System.out.println("#entries from week 8, year 2020 to current: " + year20.size() + "\n");
		ArrayList<String[]> year21 = getEachYear(colYear, colWeek, 21, 0);
		System.out.println("#entries of year 2021: " + year21.size() + "\n");

		//找出每年最多的week数量 (i.e. 52 or 53?)
		int maxWeek20 = 53;
		int maxWeek21 = 53;


		ArrayList<ArrayList<String[]>> weeks20 = new ArrayList<>();

		ArrayList<String[]> wk8yr20 = getEntry(year20, colWeek, 46);
//		System.out.println("#entries of week 8, year 2020: " + wk8yr20.size());

//		weeks20.add(wk8yr20);
		weeks20.add(getEntry(year20, colWeek, 46));

		for (int i = 0; i < weeks20.size(); i++) {
			for (int j = 0; j < weeks20.get(i).size(); j++) {
				System.out.println(Arrays.toString(weeks20.get(i).get(j)));
			}
		}


//		System.out.println("#entries from week 8, 2020 to current: ");
//		System.out.println("#entries that was hospitalized and ICU: " + getEntry(rows, icu, 3).size());


	}

	/* 将所有entry按年份分开存入ArrayList */
	public static ArrayList<String[]> getEachYear(int yearCol, int weekCol, int year, int week)
	{
		ArrayList<String[]> list = new ArrayList<>();
		for (String[] entry : getEntry(rows, yearCol, year)) {
			if (Integer.parseInt(entry[weekCol]) >= week) {
				list.add(entry);
			}
		}
		return list;
	}

	/* 进行单个元素搜索, 获取符合条件的每行数据 */
	public static ArrayList<String[]> getEntry(ArrayList<String[]> table, int col, int key)
	{
		ArrayList<String[]> list = new ArrayList<>();
		for (String[] entry : table) {
			if (entry[col].equals(Integer.toString(key))) {
				list.add(entry);
//				System.out.println(Arrays.toString(entry));
			}
		}
		return list;
	}

	/* 读取目录下的csv文件 */
	public static void readCSV(String path)
	{
		//打开表格
		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//读取表格每行的内容
		String line;
		try {
			if (br != null) {
				while ((line = br.readLine()) != null) {
					rows.add(line.split(","));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//逆时针逆转表格并存入cols
		for (int i = 0; i < rows.get(0).length; i++) {
			ArrayList<String> temp = new ArrayList<>();
			for (String[] row : rows) {
				temp.add(row[i]);
			}
			cols.add(temp);
		}

		/*
		//打印每行
		System.out.println("打印每行:");
		for (String[] row : rows) {
			System.out.println(Arrays.toString(row));
		}

		//打印每列
		System.out.println("打印每列:");
		for (ArrayList<String> col : cols) {
			System.out.println(col);
		}

		System.out.println("表格中所有行数：" + rows.size());
		System.out.println("表格中所有列数：" + cols.size());
		*/
	}
}

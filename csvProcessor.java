import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Author: Hao Zheng
 * This code is used for processing COVID-19 metadata csv file.
 */
public class csvProcessor
{
//	private static ArrayList<ArrayList<String>> cols;
	private static ArrayList<String[]> rows;
	private static final int WEEKS = 52;	//csv源文件中week最大值为52 (忽略99)
	private static String nameYear;
	private static int startWeek;

	/* 主方法 */
	public static void main(String[] args)
	{
		/* 初始化变量 */
		String filePath = "/Users/zhenghao/usra2021/";
		String fileName = "COVID19-eng.csv";
//		cols = new ArrayList<>();
		rows = new ArrayList<>();
		int colRegion = 1;
		int colWeek = 2;
		int colYear = 4;
		int colIcu = 11;

		/* 读取文件 */
		readCSV(filePath + fileName);

		/* 第1次筛选: 筛选出2020和2021年满足ICU分类的entry的数量 */
		ArrayList<String[]> hp_icu = getEntry(rows, colIcu, 1);
		ArrayList<String[]> hp_non_icu = getEntry(rows, colIcu, 2);
		ArrayList<String[]> non_hp = getEntry(rows, colIcu, 3);

		/* 第2次筛选: 在第一次筛选的基础上, 筛选出2020和2021年满足地域要求的entry的数量 */
		//当 COV_HSP = 1 时
		ArrayList<String[]> AC1 = getEntry(hp_icu, colRegion, 1);	//Atlantic
		ArrayList<String[]> QC1 = getEntry(hp_icu, colRegion, 2);	//Quebec
		ArrayList<String[]> ON1 = getEntry(hp_icu, colRegion, 3);	//Ontario and Nunavut
		ArrayList<String[]> PS1 = getEntry(hp_icu, colRegion, 4);	//Prairies
		ArrayList<String[]> BY1 = getEntry(hp_icu, colRegion, 5);	//British Columbia and Yukon

		//当 COV_HSP = 2 时
		ArrayList<String[]> AC2 = getEntry(hp_non_icu, colRegion, 1);
		ArrayList<String[]> QC2 = getEntry(hp_non_icu, colRegion, 2);
		ArrayList<String[]> ON2 = getEntry(hp_non_icu, colRegion, 3);
		ArrayList<String[]> PS2 = getEntry(hp_non_icu, colRegion, 4);
		ArrayList<String[]> BY2 = getEntry(hp_non_icu, colRegion, 5);

		//当 COV_HSP = 3 时
		ArrayList<String[]> AC3 = getEntry(non_hp, colRegion, 1);
		ArrayList<String[]> QC3 = getEntry(non_hp, colRegion, 2);
		ArrayList<String[]> ON3 = getEntry(non_hp, colRegion, 3);
		ArrayList<String[]> PS3 = getEntry(non_hp, colRegion, 4);
		ArrayList<String[]> BY3 = getEntry(non_hp, colRegion, 5);

		/* 生成新文件 */
		String[] dirHpstat = {"hp_status/hp_icu/", "hp_status/hp_non_icu/", "hp_status/non_hp/"};
		String[] dirRegion = {"Atlantic/", "Quebec/", "Ontario & Nunavut/", "Prairies/", "British Columbia & Yukon/"};

		//Canada: csv 按时间段 week, 5 weeks, 13 weeks (quarter) 分类
		outputCSV(filePath, dirHpstat[0] + "Canada/", getPeriod(hp_icu, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[1] + "Canada/", getPeriod(hp_non_icu, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[2] + "Canada/", getPeriod(non_hp, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[0] + "Canada/", getPeriod(hp_icu, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[1] + "Canada/", getPeriod(hp_non_icu, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[2] + "Canada/", getPeriod(non_hp, colYear, colWeek, 21), colWeek);

		//Atlantic: csv 按时间段 week, 5 weeks, 13 weeks (quarter) 分类
		outputCSV(filePath, dirHpstat[0] + dirRegion[0], getPeriod(AC1, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[0], getPeriod(AC2, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[0], getPeriod(AC3, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[0] + dirRegion[0], getPeriod(AC1, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[0], getPeriod(AC2, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[0], getPeriod(AC3, colYear, colWeek, 21), colWeek);

		//Quebec: csv 按时间段 week, 5 weeks, 13 weeks (quarter) 分类
		outputCSV(filePath, dirHpstat[0] + dirRegion[1], getPeriod(QC1, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[1], getPeriod(QC2, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[1], getPeriod(QC3, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[0] + dirRegion[1], getPeriod(QC1, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[1], getPeriod(QC2, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[1], getPeriod(QC3, colYear, colWeek, 21), colWeek);

		//Ontario & Nunavut: csv 按时间段 week, 5 weeks, 13 weeks (quarter) 分类
		outputCSV(filePath, dirHpstat[0] + dirRegion[2], getPeriod(ON1, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[2], getPeriod(ON2, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[2], getPeriod(ON3, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[0] + dirRegion[2], getPeriod(ON1, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[2], getPeriod(ON2, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[2], getPeriod(ON3, colYear, colWeek, 21), colWeek);

		//Prairies: csv 按时间段 week, 5 weeks, 13 weeks (quarter) 分类
		outputCSV(filePath, dirHpstat[0] + dirRegion[3], getPeriod(PS1, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[3], getPeriod(PS2, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[3], getPeriod(PS3, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[0] + dirRegion[3], getPeriod(PS1, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[3], getPeriod(PS2, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[3], getPeriod(PS3, colYear, colWeek, 21), colWeek);

		//British Columbia & Yukon: csv 按时间段 week, 5 weeks, 13 weeks (quarter) 分类
		outputCSV(filePath, dirHpstat[0] + dirRegion[4], getPeriod(BY1, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[4], getPeriod(BY2, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[4], getPeriod(BY3, colYear, colWeek, 20), colWeek);
		outputCSV(filePath, dirHpstat[0] + dirRegion[4], getPeriod(BY1, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[1] + dirRegion[4], getPeriod(BY2, colYear, colWeek, 21), colWeek);
		outputCSV(filePath, dirHpstat[2] + dirRegion[4], getPeriod(BY3, colYear, colWeek, 21), colWeek);

		/* 输出统计 */
		//todo
	}

	/* 生成并命名文件夹和文件 */
	private static void outputCSV(String path, String ctg, ArrayList<String[]> table, int wkCol)
	{
		//按时间段为 week 生成csv
		for (int i = startWeek; i <= WEEKS; i++) {
			String name = "outputs/" + ctg + "perWeek/" + nameYear + "week_" + i + ".csv";
			ArrayList<String[]> temp = getEntry(table, wkCol, i);
			//避免生成空白csv
			if (temp.size() > 0) {
				generateCSV(path + name, temp);
			}
		}

		//按时间段为 5weeks 生成csv
		for (int i = startWeek; i <= WEEKS; i += 5) {
			String name = "outputs/" + ctg + "per5Week/" + nameYear + "weeks_" + i + "-" + (i + 4) + ".csv";
			ArrayList<String[]> temp = getEntry(table, wkCol, i);
			for (int j = i + 1; j <= i + 4; j++) {
				temp.addAll(getEntry(table, wkCol, j));
			}
			//避免生成空白csv
			if (temp.size() > 0) {
				generateCSV(path + name, temp);
			}
		}

		//按时间段为 13weeks 生成csv
		for (int i = startWeek; i <= WEEKS; i += 13) {
			String name = "outputs/" + ctg + "perQuarter/" + nameYear + "quarter_" + (i + 12)/13 + ".csv";
			ArrayList<String[]> temp = getEntry(table, wkCol, i);
			for (int j = i + 1; j <= i + 12; j++) {
				temp.addAll(getEntry(table, wkCol, j));
			}
			//避免生成空白csv
			if (temp.size() > 0) {
				generateCSV(path + name, temp);
			}
		}
	}

	/* 将所有entry按时间段分开存入ArrayList */
	private static ArrayList<String[]> getPeriod(ArrayList<String[]> table, int yrCol, int wkCol, int year)
	{
		//命名生成的csv的年份
		if (year == 20) {
			nameYear = "2020_";
			startWeek = 8;
		} else if (year == 21) {
			nameYear = "2021_";
			startWeek = 1;
		}

		//遍历传入的表格, 筛选后存入ArrayList
		ArrayList<String[]> list = new ArrayList<>();
		for (String[] entry : getEntry(table, yrCol, year)) {
			if (Integer.parseInt(entry[wkCol]) >= 1 && Integer.parseInt(entry[wkCol]) <= WEEKS) {
				list.add(entry);
			}
		}
		return list;
	}

	/* 进行单个元素搜索, 获取符合条件的每行数据 */
	private static ArrayList<String[]> getEntry(ArrayList<String[]> table, int col, int key)
	{
		ArrayList<String[]> list = new ArrayList<>();
		for (String[] entry : table) {
			if (entry[col].equals(Integer.toString(key))) {
				list.add(entry);
//				System.out.println(Arrays.toString(entry));	//打印结果到控制台
			}
		}
		return list;
	}

	/* 在源文件目录下生成新的csv文件 */
	private static void generateCSV(String path, ArrayList<String[]> table)
	{
		String token1 = "[", token2 = "]", token3 = "";
		BufferedWriter bw = null;
		File file = new File(path);
		File fileParent = file.getParentFile();

		//生成文件夹
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}

		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//忽略Arrays.toString()打印数据后自带的中括号
		try {
			if (bw != null) {
				//所有表格第一行都与源文件一致
				bw.write(Arrays.toString(rows.get(0)).replace(token1, token3).replace(token2, token3));
				for (String[] entry : table) {
					bw.newLine();
					bw.write(Arrays.toString(entry).replace(token1, token3).replace(token2, token3));
				}
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 读取目录下的csv文件 */
	private static void readCSV(String path)
	{
		//打开表格
		BufferedReader br = null;
		File file = new File(path);
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
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

//		//逆时针90度旋转表格并存入cols
//		for (int i = 0; i < rows.get(0).length; i++) {
//			ArrayList<String> temp = new ArrayList<>();
//			for (String[] row : rows) {
//				temp.add(row[i]);
//			}
//			cols.add(temp);
//		}
//
//		//打印每行
//		System.out.println("打印每行:");
//		for (String[] row : rows) {
//			System.out.println(Arrays.toString(row));
//		}
//		//打印每列
//		System.out.println("打印每列:");
//		for (ArrayList<String> col : cols) {
//			System.out.println(col);
//		}
//		System.out.println("表格中所有行数：" + rows.size());
//		System.out.println("表格中所有列数：" + cols.size());
	}
}

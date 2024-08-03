import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/*
 *   WorkingResult.csv（一か月分の勤務実績ファイル）を読み込み、各日の労働時間を計算して出力する処理です。
 *   このサンプルではWorkingResult.csvを「C:\WorkSpace」直下に配置して実行する前提になっています。
 *   ディレクトリの名前や配置場所が違う場合は WORKING_RESULT_FILE_PATH の値を適切なものに変更してください。
 */

public class ReadFileSample {
	public static void main(String[] args) {
		
		//  WorkingResult.csvのパス ※「C:\WorkSpace」直下に配置していない場合は適宜変更してください。
		final String WORKING_RESULT_FILE_PATH = "Resources/WorkingResult.csv";
		// コンマ
		final String COMMA = ","; 
		
		// 計算用の数値を定数で用意
		final long ONE_HOUR_BY_MILLI_SEC = 1000 * 60 * 60; // 1時間のミリ秒換算
		final long ONE_MIN_BY_MILLI_SEC  = 1000 * 60;      // 1分のミリ秒換算
		final int  ONE_HOUR_BY_MIN       = 60;             // 1時間の分換算
		final int  ZIKYU                 = 900;
		final double  BORDER_HOURS_OF_1HBREAK = 8.0;
		final double  BORDER_HOURS_OF_45MBREAK = 6.0;
		final double  ADITTIONAL_TO_SALARY  = 1.25;
		double        SALARY                = 0;
		double        workingTimeForCalcSalary = 0.0;
		int			  SalaryForMonth          =0;
		
		List<String> workingResults = new ArrayList<String>(); //ファイルから読み込んだデータの格納用
		
		//  WorkingResult.csvを読み込む
		try {
			// WorkingResult.csvの読み込み準備
			File workingResultFile = new File(WORKING_RESULT_FILE_PATH);
			BufferedReader br = new BufferedReader(new FileReader(workingResultFile));
			
			// WorkingResult.csvを1行ずつ読み込んでArrayListに格納する
			String recode = br.readLine();
			while (recode != null) {
				workingResults.add(recode);
				recode = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		// ArrayListから1行ずつ取り出して日付/出勤時間/退勤時間に振り分け
		for (int i = 0; i < workingResults.size() ; i++) {
			
			String workingRecode    = workingResults.get(i);      // 1行ずつ文字列を取り出す
			String[] forSplitRecode = workingRecode.split(COMMA); // splitメソッドを用いてカンマ区切りで文字列を分解＆配列にそれぞれ格納
			
			Date workingDate = Date.valueOf(forSplitRecode[0]); // 出勤日
			String startTime   = String.valueOf(forSplitRecode[1]); // 出勤時間
			String finishTime  = String.valueOf(forSplitRecode[2]); // 退勤時間
			
			SalaryForMonth = SalaryForMonth + CalcWrokingForOneDay(startTime, finishTime);


			// 出力
	//		System.out.println( "【日付】"     + workingDate + " / " +
	//		                    "【勤務時間】" + startTime + "～" + finishTime + " / " +
	//		                    "【労働時間】" + workingHour + "時間" + workingMin + "分" + " / " +
	//		                    " 【日給】" + SalaryInt + "円"
	//		                  );
		}
		System.out.println("1ヶ月分の給与：" + SalaryForMonth + "円");
	}


	public static int CalcWrokingForOneDay(String st, String ed) {

		// 計算用の数値を定数で用意
		final long ONE_HOUR_BY_MILLI_SEC = 1000 * 60 * 60; // 1時間のミリ秒換算
		final long ONE_MIN_BY_MILLI_SEC  = 1000 * 60;      // 1分のミリ秒換算
		final int  ONE_HOUR_BY_MIN       = 60;             // 1時間の分換算
		final int  ZIKYU                 = 900;
		final double  BORDER_HOURS_OF_1HBREAK = 8.0;
		final double  BORDER_HOURS_OF_45MBREAK = 6.0;
		final double  ADITTIONAL_TO_SALARY  = 1.25;
		double        SALARY                = 0;
		double        workingTimeForCalcSalary = 0.0;


		// バイトの開始時間と終了時間をコマンドライン引数から受け取る
		Time startTime  = Time.valueOf(st);
		Time finishTime = Time.valueOf(ed);

		// getTimeメソッドを使って労働時間をミリ秒（0.001秒単位）で取得する
		// ※getTime()メソッドの戻り値はlong型であることに注意
		long workingTime = finishTime.getTime() - startTime.getTime();

		// ミリ秒で取得した労働時間を○時間△分の形式に直す
		int workingHour = (int)( workingTime / ONE_HOUR_BY_MILLI_SEC );                      // 時間に換算
		int workingMin  = (int)( ( workingTime / ONE_MIN_BY_MILLI_SEC ) % ONE_HOUR_BY_MIN ); // 分に換算
		double workingTimeHour = (double)( workingHour + (workingMin / 60.0 )  );
				
		if( workingTimeHour < BORDER_HOURS_OF_45MBREAK ) {
			workingTimeForCalcSalary =  workingTimeHour;
			SALARY = workingTimeForCalcSalary * ZIKYU;			
			
		}else if ( workingTimeHour < BORDER_HOURS_OF_1HBREAK) {
			workingTimeForCalcSalary =  workingTimeHour - 0.75;
			SALARY = workingTimeForCalcSalary * ZIKYU;			
		
		}else if ( workingTimeHour > BORDER_HOURS_OF_1HBREAK ){
			workingTimeForCalcSalary =  workingTimeHour - 1.0;
			if (workingTimeForCalcSalary > 8.0 ) {
				SALARY = ( workingTimeForCalcSalary + ( workingTimeForCalcSalary - 8.0 ) * ADITTIONAL_TO_SALARY ) * ZIKYU;		
			}else {
				SALARY = workingTimeForCalcSalary * ZIKYU;					
			}
		}
		
		int SalaryInt = ( int )( SALARY );
		
		return SalaryInt;
		
		// 出力
	//	System.out.println("本日の労働時間は" + workingHour + "時間" + workingMin + "分です。");
	//	System.out.println("本日の実質労働時間は" + workingTimeForCalcSalary + "時間です。");
	//	
	//	System.out.println("本日の給与は" + SalaryInt + "円です。");
		
	}
}



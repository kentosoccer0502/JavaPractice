import java.sql.Time;

public class CalcWorkingTimeAndSalary {

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

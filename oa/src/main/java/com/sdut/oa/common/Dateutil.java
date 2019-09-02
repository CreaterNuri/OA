package com.sdut.oa.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间 公共类
 * @author Nuri
 *
 */
public class Dateutil {
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 计算除周六周天外的所有天数
	 * @param year
	 * @param month
	 * @return
	 */
	public int  listUnWeekend(int year,int month){  
			int day = getDay(year,month);
			int count=0;
			for(int i = 1; i <= day; i++){
				if(!getIsWeekend(year,month,i)){
					count++;
				}
			}
			return count;
		}


		public boolean getIsWeekend(int year,int month,int day){
				Calendar c = Calendar.getInstance();
				c.set(year, month - 1, day); //设置日期
				int week = c.get(Calendar.DAY_OF_WEEK);  //获取当前日期星期，英国那边的算法(周日算一周第一天)
				if(week == 7 || week == 1){   //如果是周六或周日就返回true
					return true;
				}
				return false;
		}
		
		/**
		 * 判断该年该月的天数
		 * @param year
		 * @param month
		 * @return
		 */
		
		public int getDay(int year,int month){  
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
				return 31;
			}else if(month != 2){
				return 30;
			}else{
				if(0==year%4 && ((year%100!=0)||(year%400==0))){  //判断当年是否是闰年
					return 29;
				}else{
					return 28;
				}
			}
		}
		
		
		/**
		 * 计算除周六周日外工作日
		 * @param strStartDate
		 * @param strEndDate
		 * @return
		 */
		public static int getDutyDays(String strStartDate,String strEndDate) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate=null;
			Date endDate = null;
			try {
			startDate=df.parse(strStartDate);
			endDate = df.parse(strEndDate);
			} catch (Exception e) {
			System.out.println("非法的日期格式,无法进行转换");
			e.printStackTrace();
			}
			int result = 0;
			while (startDate.compareTo(endDate) <= 0) {
			if (startDate.getDay() != 6 && startDate.getDay() != 0)
				result++;
				startDate.setDate(startDate.getDate() + 1);
			}
			return result;
			}
		
		/**
		* 获取日期年份
		* @param date
		* @return
		* @throws ParseException
		*/
		public static int getYear(String date) throws Exception{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			return calendar.get(Calendar.YEAR);
		}
		
		/**
		* 获取日期月份
		* @param date
		* @return
		* @throws ParseException
		*/
		public static int getMonth(String date) throws Exception{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			return (calendar.get(Calendar.MONTH) + 1);
		}
		
		/**
		* 获取日期号
		* @param date
		* @return
		* @throws ParseException
		*/
		public static int getDay(String date) throws Exception{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			return calendar.get(Calendar.DAY_OF_MONTH);
		}
		/**
		* 获取月份起始日期
		* @param date
		* @return
		* @throws ParseException
		*/
		public static String getMinMonthDate(String date) throws Exception{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		}
		
		/**
		* 获取月份最后日期
		* @param date
		* @return
		* @throws ParseException
		*/
		public static String getMaxMonthDate(String date) throws Exception{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		}
		
		
}

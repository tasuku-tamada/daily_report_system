package models.validators;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import models.Attendance;
import models.Employee;
import utils.DBUtil;

public class AttendanceValidator {
    public static List<String> validate(Attendance a,Employee employee,boolean flag) {
        List<String> errors = new ArrayList<String>();

        String date_error = _validateDate(a.getDate(),employee,flag);
        if(!date_error.equals("")) {
            errors.add(date_error);
        }

        String attendance_time_error = _validateTime(a.getAttendance_time(),"出勤時刻");
        if(!attendance_time_error.equals("")) {
            errors.add(attendance_time_error);
        }

        String leave_time_error = _validateTime(a.getLeave_time(),"退勤時刻" );
        if(!leave_time_error.equals("")) {
            errors.add(leave_time_error);
        }

        return errors;
    }

    // 日付
    private static String _validateDate(Date date,Employee employee,boolean flag) {
        // 必須入力チェック
        if(date == null || date.equals("")) {
            return "日付が未入力です";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateBegin;
        try{
            dateBegin = sdf.parse(date.toString());
        }catch(ParseException e){
            dateBegin = new java.util.Date();
        }
        //翌日をのDateを取得する
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateBegin);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        java.util.Date dateEnd = calendar.getTime();

        if(flag){
            // すでに登録されている日付との重複チェック
            EntityManager em = DBUtil.createEntityManager();
            long attendances_count = (long)em.createNamedQuery("checkAttendanceDate", Long.class)
                    .setParameter("employee", employee)
                    .setParameter("dateBegin",dateBegin)
                    .getSingleResult();
            em.close();

            if(attendances_count > 0) {
                return "入力された日付の情報はすでに存在しています。";
            }
        }
        return "";
    }

    // 時間の必須入力チェック
    private static String _validateTime(String time,String column) {
        if(time == null || time.equals("")) {
            return column +"を入力してください。";
        }

        return "";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import model.AcademicRecord;
import model.Assessment;
import model.AverageMark;
import model.Registration;

/**
 *
 * @author Nguyen Kim Duong
 */
public class CalculatorAverageMark {

    public ArrayList<AverageMark> list(ArrayList<AcademicRecord> academicRecords, ArrayList<Registration> registrations) {
        ArrayList<AverageMark> list = new ArrayList<>();
        for (Registration r : registrations) {
            int flag = 0;
            double avg = 0;
            for (AcademicRecord a : academicRecords) {
                if (r.getId().equals(a.getRegistration().getId())) {
                    flag = 1;
                    Assessment ass = a.getGrade().getExam().getAssessment();
                    double weight = (double)ass.getWeight()/100;
                    avg += weight * a.getGrade().getScore();
                    System.out.println("weight: "+ ass.getWeight());
                }
            }
            
            if (flag == 1) {
                DecimalFormat format = new DecimalFormat("#.##");
                avg = Double.parseDouble(format.format(avg));
                AverageMark averageMark = new AverageMark(r, avg);
                list.add(averageMark);
            }
        }

        return list;
    }
}

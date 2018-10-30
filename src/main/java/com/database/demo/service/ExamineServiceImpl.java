package com.database.demo.service;

import com.database.demo.dao.*;
import com.database.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */

@Service
public class ExamineServiceImpl implements ExamineService {

    private ComboRecordRepository comboRecordRepository;

    private ComboRepository comboRepository;

    private CallRecordRepository callRecordRepository;

    private MessageRecordRepository messageRecordRepository;

    private DataRecordRepository dataRecordRepository;

    private UserRepository userRepository;

    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String lineSplit = "---------";

    private long startTime;

    private long endTime;

    @Autowired
    public void setComboRecordRepository(ComboRecordRepository comboRecordRepository){
        this.comboRecordRepository = comboRecordRepository;
    }

    @Autowired
    public void setComboRepository(ComboRepository comboRepository){
        this.comboRepository = comboRepository;
    }

    @Autowired
    public void setCallRecordRepository(CallRecordRepository callRecordRepository){
        this.callRecordRepository = callRecordRepository;
    }

    @Autowired
    public void setMessageRecordRepository(MessageRecordRepository messageRecordRepository){
        this.messageRecordRepository = messageRecordRepository;
    }

    @Autowired
    public void setDataRecordRepository(DataRecordRepository dataRecordRepository){
        this.dataRecordRepository = dataRecordRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<ComboUsedRecord> getAllCombos(String phoneNumber) {

        startTime = System.currentTimeMillis(); // 获取开始时间

        User user = getValidUser(phoneNumber);

        List<ComboUsedRecord> comboUsedRecords = comboRecordRepository.findAllByUser(user);

        endTime = System.currentTimeMillis(); // 获取结束时间

        System.out.println();
        System.out.println(lineSplit+phoneNumber+"用户历史套餐查询"+lineSplit);
        for (int i=0;i<comboUsedRecords.size();i++){
            System.out.println("套餐名："+comboUsedRecords.get(i).getCombo().getComboName());
            System.out.println("开始时间："+df.format(comboUsedRecords.get(i).getStartDateTime()));
            System.out.println("结束时间："+df.format(comboUsedRecords.get(i).getEndDateTime()));
            System.out.println();
        }

       printRunTime();

        return comboUsedRecords;
    }


    @Override
    public void setNewCombo(String phoneNumber, Integer comboId, int months) {
        LocalDateTime nowDateTime = LocalDateTime.now();

        startTime = System.currentTimeMillis(); // 获取开始时间
        User user = getValidUser(phoneNumber);
        Combo combo = getValidCombo(comboId);
        List<ComboUsedRecord> comboUsedRecords = comboRecordRepository.findAllByUserAndCombo(user,combo);


        for(int i=0;i<comboUsedRecords.size();i++){
            if(comboUsedRecords.get(i).getEndDateTime().isAfter(nowDateTime)){
                //当前已订购该套餐且该套餐未结束
                LocalDateTime newEndDateTime = comboUsedRecords.get(i).getEndDateTime().plusMonths(months);
                comboUsedRecords.get(i).setEndDateTime(newEndDateTime);
                comboRecordRepository.save(comboUsedRecords.get(i));
                endTime = System.currentTimeMillis(); // 获取结束时间

                System.out.println();
                System.out.println("用户"+phoneNumber+"已成功延时"+comboRepository.findByComboId(comboId).get().getComboName());
                System.out.println();
                printRunTime();
                return;
            }
        }

        // 次月生效
        LocalDateTime newStartDateTime = nowDateTime.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        ComboUsedRecord record = new ComboUsedRecord(user,combo,newStartDateTime,newStartDateTime.plusMonths(months));
        comboRecordRepository.save(record);
        endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println();
        System.out.println("用户"+phoneNumber+"已成功订阅"+comboRepository.findByComboId(comboId).get().getComboName());
        System.out.println();
        printRunTime();
    }

    @Override
    public void cancelOneCombo(String phoneNumber, Integer comboId, boolean nextMonth) {

        User user = getValidUser(phoneNumber);
        Combo combo = getValidCombo(comboId);
        List<ComboUsedRecord> comboUsedRecords = comboRecordRepository.findAllByUserAndCombo(user,combo);

        LocalDateTime nowDateTime = LocalDateTime.now();
        for(int i=0;i<comboUsedRecords.size();i++){
            if(comboUsedRecords.get(i).getEndDateTime().isAfter(nowDateTime)){
                if(nextMonth){
                    cancelNextMonth(comboUsedRecords.get(i));
                }else {
                    cancelCurrentMonth(comboUsedRecords.get(i));
                }

                printRunTime();
                return;
            }
        }

    }

    @Override
    public double getExpenseOnCallCombo(String phoneNumber) {

        startTime = System.currentTimeMillis();
        User user = getValidUser(phoneNumber);
        List<CallRecord> callRecords = callRecordRepository.findAllByUser(user);
        endTime = System.currentTimeMillis();
        System.out.println(lineSplit + phoneNumber +"用户通话资费" + lineSplit);
        for(int i = 0; i < callRecords.size(); i++){
            System.out.println("费用："+callRecords.get(i).getExpense()+"元");
            System.out.println("开始时间："+ df.format(callRecords.get(i).getStartDateTime()));
            System.out.println("持续时间："+callRecords.get(i).getMinutes()+"分钟");
            System.out.println(lineSplit);
        }

        printRunTime();

        return 0;
    }

    @Override
    public double getExpenseOnDataCombo(String phoneNumber) {

        startTime = System.currentTimeMillis();
        User user = getValidUser(phoneNumber);
        List<DataRecord> localDataRecords = dataRecordRepository.findByUserAndDataType(user, DataType.LOCAL_DATA);
        List<DataRecord> domesticDataRecords = dataRecordRepository.findByUserAndDataType(user, DataType.DOMESTIC_DATA);
        endTime = System.currentTimeMillis();

        System.out.println(lineSplit + phoneNumber + "用户流量资费查询" + lineSplit);
        System.out.println();
        System.out.println("本地流量：");
        for (int i = 0; i < localDataRecords.size(); i++){
            System.out.println("费用：" + localDataRecords.get(i).getExpense() + "元");
            System.out.println("使用时间:" + df.format( localDataRecords.get(i).getStartDateTime() ) );
            System.out.println("流量使用数量：" + localDataRecords.get(i).getAmount());
            System.out.println(lineSplit);
        }

        System.out.println();
        System.out.println("国内流量");
        for (int i = 0; i < domesticDataRecords.size(); i++){
            System.out.println("费用：" + domesticDataRecords.get(i).getExpense() + "元");
            System.out.println("使用时间:" + df.format( domesticDataRecords.get(i).getStartDateTime() ) );
            System.out.println("流量使用数量: " + domesticDataRecords.get(i).getAmount());
            System.out.println(lineSplit);
        }

        printRunTime();

        return 0;
    }

    @Override
    public void getMonthBill(String phoneNumber) {

        User user = getValidUser(phoneNumber);
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime[] result = getFirstAndLastDay(nowDateTime);

        LocalDateTime first = result[0];
        LocalDateTime last = result[1];

        startTime = System.currentTimeMillis();

        Double callExpense = (callRecordRepository.getSumOfExpense(phoneNumber, first, last) != null)
                ? (callRecordRepository.getSumOfExpense(phoneNumber, first, last)) : 0;
        Double messageExpense = ( messageRecordRepository.getSumOfExpense(phoneNumber, first, last) != null )
                ? (messageRecordRepository.getSumOfExpense(phoneNumber, first, last)) : 0;
        Double localDataExpense = ( dataRecordRepository.getSumOfDataExpense(phoneNumber, first, last, DataType.LOCAL_DATA.getStr()) != null)
                ? (dataRecordRepository.getSumOfDataExpense(phoneNumber, first, last, DataType.LOCAL_DATA.getStr())) : 0;
        Double domesticDataExpense = ( dataRecordRepository.getSumOfDataExpense(phoneNumber, first, last, DataType.DOMESTIC_DATA.getStr()) != null)
                ? (dataRecordRepository.getSumOfDataExpense(phoneNumber, first, last, DataType.DOMESTIC_DATA.getStr())) : 0;
        Double monthExpense = (comboRecordRepository.getSumOfExpense(phoneNumber, nowDateTime) != null)
                ? (comboRecordRepository.getSumOfExpense(phoneNumber, nowDateTime)) : 0;

        endTime = System.currentTimeMillis();

        System.out.println(lineSplit + phoneNumber + "用户月账单查询" + lineSplit);
        System.out.println("月功能费：" + monthExpense + "元");
        System.out.println("通话费用：" + callExpense + "元");
        System.out.println("短信费用：" + messageExpense + "元" );
        System.out.println("本地流量费用：" + localDataExpense + "元");
        System.out.println("国内流量费用：" + domesticDataExpense + "元");

        printRunTime();
    }

    private User getValidUser(String phoneNumber){
        User user;
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            user = userRepository.findByPhoneNumber(phoneNumber).get();
            return user;
        }else {
            System.out.println(phoneNumber+"用户不存在");
            return null;
        }
    }

    private Combo getValidCombo(Integer comboId){
        Combo combo;
        if(comboRepository.existsByComboId(comboId)){
            combo = comboRepository.findByComboId(comboId).get();
            return combo;
        }else {
            System.out.println("套餐ID"+comboId+"不存在");
            return null;
        }
    }

    private void printRunTime(){
        System.out.println("run time:" + ( endTime - startTime) + "ms");
        System.out.println();
    }

    private void cancelNextMonth(ComboUsedRecord record){
        //退订次月生效
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime newEndDateTime = nowDateTime.with(TemporalAdjusters.lastDayOfMonth());
        record.setEndDateTime(newEndDateTime);

        comboRecordRepository.save(record);
        endTime = System.currentTimeMillis(); // 获取结束时间

        System.out.println("用户"+record.getUser().getPhoneNumber()+"已成功退订"+comboRepository.findByComboId(record.getId()).get().getComboName()+"(次月生效)");
        System.out.println();

    }

    private void cancelCurrentMonth(ComboUsedRecord record){
        //退订当月生效
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime[] result = getFirstAndLastDay(nowDateTime);

        LocalDateTime first = result[0];
        LocalDateTime last = result[1];

        startTime = System.currentTimeMillis();

        record.setEndDateTime(nowDateTime.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
        comboRecordRepository.save(record);

        int comboId= record.getCombo().getId();
        User user = record.getUser();
        List<CallRecord> callRecords = callRecordRepository.findByUserAndStartDateTimeBetween(user, first, last);
        List<MessageRecord> messageRecords = messageRecordRepository.findByUserAndStartDateTimeBetween(user, first, last);
        List<DataRecord> localDataRecords = dataRecordRepository.findByUserAndDataTypeAndStartDateTimeBetween(user, DataType.LOCAL_DATA, first, last);
        List<DataRecord> domesticDataRecords = dataRecordRepository.findByUserAndDataTypeAndStartDateTimeBetween(user, DataType.DOMESTIC_DATA, first, last);

        for(int i = 0; i < callRecords.size(); i++){
            setOneCallExpense(user, callRecords.get(i).getMinutes(), callRecords.get(i));
        }

        for(int i = 0; i < messageRecords.size(); i++){
            setOneMessageExpense(user, messageRecords.get(i));
        }

        for(int i = 0; i < localDataRecords.size(); i++){
            setOneTimeDataExpense(user, localDataRecords.get(i).getAmount(), DataType.LOCAL_DATA, localDataRecords.get(i));
        }

        for(int i = 0; i < domesticDataRecords.size(); i++){
            setOneTimeDataExpense(user, domesticDataRecords.get(i).getAmount(), DataType.DOMESTIC_DATA, domesticDataRecords.get(i));
        }

        System.out.println("用户"+record.getUser().getPhoneNumber()+"已成功退订"+record.getCombo().getComboName()+"(当月生效)");
        System.out.println();

        endTime = System.currentTimeMillis();

    }

    public void setCallRecord(String phoneNumber, int calls, int minute){
        for(int i = 0; i< calls; i++){
            LocalDateTime nowDateTime = LocalDateTime.now();
            User user = getValidUser(phoneNumber);
            CallRecord record = new CallRecord(user, nowDateTime, minute, null);
            callRecordRepository.save(record);
            setOneCallExpense(user, minute, record);
        }
    }

    public void setMessageRecord(String phoneNumber, int num){
        for(int i=0;i<num;i++){
            LocalDateTime nowDateTime = LocalDateTime.now();
            User user = getValidUser(phoneNumber);
            MessageRecord record = new MessageRecord(user,nowDateTime,null);
            messageRecordRepository.save(record);
            setOneMessageExpense(user, record);
        }
    }

    public void setDataRecord(String phoneNumber, int num, double amount, DataType type){
        for(int i = 0; i < num; i++){
            LocalDateTime nowDateTime = LocalDateTime.now();
            User user = getValidUser(phoneNumber);
            DataRecord record = new DataRecord(user, nowDateTime, amount ,type, null);
            dataRecordRepository.save(record);
            setOneTimeDataExpense(user, amount, type, record);
        }
    }

    private void setOneMessageExpense(User user, MessageRecord record){

        int max = 200;

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime[] result = getFirstAndLastDay(nowDateTime);
        LocalDateTime currentMonthFirstDay = result[0];
        LocalDateTime currentMonthLastDay = result[1];

        Combo combo6 = getValidCombo(6);
        Boolean haveCombined = isHaveTheCombo(user, combo6, nowDateTime);
        if (haveCombined){
            max += combo6.getNumOfMessage();
        }

        List<MessageRecord> messageRecords = messageRecordRepository.findByUserAndStartDateTimeBetween(user,currentMonthFirstDay,currentMonthLastDay);
        int count = messageRecords.size(); //当月已发短信条数

        int messageComboId = 3;
        Combo combo3 = getValidCombo(messageComboId);

        Boolean haveCombo = isHaveTheCombo(user,combo3,nowDateTime);
        if(haveCombo){
            // 有短信套餐
            if(count <= max){
                record.setExpense(0.0);
            }else{
                record.setExpense(0.1);
            }
        }else{
            //没有短信套餐
            record.setExpense(0.1);
        }
        messageRecordRepository.save(record);
    }

    private void setOneCallExpense(User user, int minute, CallRecord record){
        int max = 100;
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime[] result = getFirstAndLastDay(nowDateTime);

        LocalDateTime first = result[0];
        LocalDateTime last = result[1];

        Combo combo6 = getValidCombo(6);
        Boolean haveCombined = isHaveTheCombo(user, combo6, nowDateTime);
        if (haveCombined){
            max += combo6.getMinuteOfCall();
        }

        List<CallRecord> callRecords = callRecordRepository.findByUserAndStartDateTimeBetween(user,first,last);
        int countMinute = 0; // 当月已打电话分钟数
        for(int i = 0; i < callRecords.size(); i++){
            countMinute += callRecords.get(i).getMinutes();
        }

        int callComboId = 2;
        Combo combo2 = getValidCombo(callComboId);

        Boolean haveCombo = isHaveTheCombo(user, combo2, nowDateTime);

        if(haveCombo){
            if(countMinute - minute >= max){
                record.setExpense(minute * 0.5);
            }else if(countMinute <= max){
                record.setExpense(0.0);
            }else{
                int a = max - (countMinute - minute);
                int b = minute - a;
                record.setExpense(b * 0.5);
            }
        }else{
            record.setExpense(minute * 0.5);
        }

        callRecordRepository.save(record);
    }

    private void setOneTimeDataExpense(User user,double amount, DataType type, DataRecord record){

        int max1 = 2 * 1024, max2 = 3 * 1024;

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime[] result = getFirstAndLastDay(nowDateTime);

        LocalDateTime first = result[0];
        LocalDateTime last = result[1];

        Combo combo6 = getValidCombo(6);
        Boolean haveCombined = isHaveTheCombo(user, combo6, nowDateTime);
        if (haveCombined){
            max1 += combo6.getNumOfLocalData();
            max2 += combo6.getNumOfDomesticData();
        }

        List<DataRecord> localDataRecords = dataRecordRepository.findByUserAndDataTypeAndStartDateTimeBetween(user,DataType.LOCAL_DATA,first,last);
        double amountOfAll1 = 0; // 已用本地流量
        for(int i = 0; i < localDataRecords.size(); i++){
            amountOfAll1 += localDataRecords.get(i).getAmount();
        }

        List<DataRecord> domesticDataRecords = dataRecordRepository.findByUserAndDataTypeAndStartDateTimeBetween(user,DataType.DOMESTIC_DATA,first,last);
        double amountOfAll2 = 0; // 已用全国流量
        for(int i = 0; i < domesticDataRecords.size(); i++){
            amountOfAll2 += domesticDataRecords.get(i).getAmount();
        }

        int dataComboId1 = 4;
        int dataComboId2 = 5;

        Combo combo4 = getValidCombo(dataComboId1);
        Combo combo5 = getValidCombo(dataComboId2);

        Boolean haveLocalData = isHaveTheCombo(user, combo4, nowDateTime);
        Boolean haveDomesticData = isHaveTheCombo(user, combo5, nowDateTime);
        if(type.equals(DataType.LOCAL_DATA)){
            if(haveLocalData){
                //有本地流量套餐
                if(amountOfAll1 - amount >= max1){
                    if(haveDomesticData){
                        // 且有全国流量套餐
                        if(amountOfAll2 >= max2){
                            record.setExpense(amount * 2);
                        }else if(amountOfAll2 + amount <= max2){
                            //将使用的全国流量套餐记录到数据库中
                            DataRecord newDomesticRecord = new DataRecord(user, nowDateTime, amount, DataType.DOMESTIC_DATA, 0.0);
                            dataRecordRepository.save(newDomesticRecord);
                            record.setExpense(0.0);
                            record.setAmount(0.0);
                        }else{
                            double a = max2 - amountOfAll2;
                            double b = amount - a;

                            //将使用的全国流量套餐记录到数据库中
                            DataRecord newDomesticRecord = new DataRecord(user, nowDateTime, a, DataType.DOMESTIC_DATA, 0.0);
                            dataRecordRepository.save(newDomesticRecord);

                            record.setExpense(b * 2);
                            record.setAmount(b);
                        }
                    }else{
                        record.setExpense(amount * 2);
                    }
                }else if(amountOfAll1 <= max1){
                    record.setExpense(0.0);
                }else{
                    double a = max1 - (amountOfAll1 - amount);
                    double b = amount - a;

                    if(haveDomesticData){
                        if(amountOfAll2 > max2){
                            record.setExpense(b * 2);
                        }else if(amountOfAll2 + b <= max2){
                            //将使用的全国流量套餐记录到数据库中
                            DataRecord newDomesticRecord = new DataRecord(user, nowDateTime, b, DataType.DOMESTIC_DATA, 0.0);
                            dataRecordRepository.save(newDomesticRecord);
                            record.setExpense(0.0);
                            record.setAmount(a);
                        }else{
                            double c = max2 - amountOfAll2;
                            double d = b - c;

                            //将使用的全国流量套餐记录到数据库中
                            DataRecord newDomesticRecord = new DataRecord(user, nowDateTime, c, DataType.DOMESTIC_DATA, 0.0);
                            dataRecordRepository.save(newDomesticRecord);

                            record.setExpense(d * 2);
                            record.setAmount(d);
                        }
                    }else{
                        record.setExpense(b * 2);
                    }

                }
            }else{
                record.setExpense(amount * 2);
            }
        }else if(type.equals(DataType.DOMESTIC_DATA)){
            if(haveDomesticData){
                if(amountOfAll2 - amount > max2){
                    record.setExpense(amount * 5);
                }else if(amountOfAll2 <= max2){
                    record.setExpense(0.0);
                }else {
                    double a = max2 - (amountOfAll2 - amount);
                    double b = amount - a;
                    record.setExpense(b * 5);
                }
            }else {
                record.setExpense(amount * 5);
            }
        }

        dataRecordRepository.save(record);
    }

    private LocalDateTime[] getFirstAndLastDay(LocalDateTime nowDate){

        LocalDateTime firstDayDate = nowDate.withDayOfMonth(1);
        LocalDateTime lastDayDate = nowDate.with(TemporalAdjusters.lastDayOfMonth());

        int year = firstDayDate.getYear();
        int month = firstDayDate.getMonthValue();
        int firstDay = firstDayDate.getDayOfMonth();
        int lastDay = lastDayDate.getDayOfMonth();

        LocalDateTime first = LocalDateTime.of(year,month,firstDay,0,0,0);
        LocalDateTime last = LocalDateTime.of(year,month,lastDay,23,59,59);

        LocalDateTime[] result = new LocalDateTime[2];
        result[0] = first;
        result[1] = last;

        return result;
    }

    private boolean isHaveTheCombo(User user, Combo combo, LocalDateTime nowDateTime){
        List<ComboUsedRecord> comboRecords = comboRecordRepository.findAllByUserAndCombo(user,combo);

        for(int i = 0; i < comboRecords.size(); i++){
            ComboUsedRecord record = comboRecords.get(i);
            if(nowDateTime.isAfter(record.getStartDateTime()) && nowDateTime.isBefore(record.getEndDateTime())){
                // 有套餐
                return true;
            }
        }
        return false;
    }

}

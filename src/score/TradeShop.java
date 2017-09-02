package score;
import clients.Clients;
import com.alibaba.fastjson.JSON;
import org.omg.CORBA.FREE_MEM;

import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 24.08.2017.
 */
public class TradeShop {
   private List<Fruts> fructs = new ArrayList<>();
   private List<Clients>clients = new ArrayList<>();
   double moneyBalance;

   public TradeShop(List<Fruts> fructs){
       this.fructs = fructs;
   }
   public TradeShop(){
   }

        //принимаем поставки фруктов и дополняем базу данных склада лавки новой информацией
    public void addFruits(String pathToJsonFile) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(pathToJsonFile));
        String json = r.readLine();
        fructs.addAll(JSON.parseArray(json, Fruts.class));
    }
    //сохраняем всю информацию со склада лавки в json файл
    public void save(String pathToJsonFile) throws IOException{
        String json = JSON.toJSONString(fructs);
        for(int i = 0; i < fructs.size(); i++){
            moneyBalance+=fructs.get(i).getPrice();
        }
        String json1 = JSON.toJSONString(moneyBalance);
        FileWriter writer = new FileWriter(pathToJsonFile);
        writer.write(json + json1);
        writer.flush();
        writer.close();
    }
    //удаляюем текущую информацию из коллекции и загружаем новую из сохраненной версии
    public void load(String pathToJsonFile)throws IOException{
        fructs.clear();
        addFruits(pathToJsonFile);
    }
    //метод способный сказать какие продукты испортятся к заданной дате
    public List<Fruts> getSpoiledFruits(Date date) throws ParseException {
        List<Fruts> spoiled = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        for(int i = 0; i < fructs.size(); i++){
            Date newDate = format.parse(fructs.get(i).getDate());
            calendar.setTime(newDate);
            calendar.add(Calendar.DAY_OF_MONTH,fructs.get(i).getShelfLife());
            if(calendar.getTime().before(calendar1.getTime())){
                spoiled.add(fructs.get(i));
            }
        }
        return spoiled;
    }
    // метод который возвращает список готовых к продаже продуктов
    public List<Fruts> getAvailableFruits(Date date) throws ParseException {
        List<Fruts> available = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        for(int i = 0; i < fructs.size(); i++){
            Date newDate = format.parse(fructs.get(i).getDate());
            calendar.setTime(newDate);
            calendar.add(Calendar.DAY_OF_MONTH,fructs.get(i).getShelfLife());
            if(calendar.getTime().after(calendar1.getTime())){
                available.add(fructs.get(i));
            }
        }
        return available;
    }
    //перегружаем метод на прием еще одного параметра - вид фрукта
    public List<Fruts> getSpoiledFruits(Date date, Fruts.Type type) throws ParseException {
        List<Fruts> spoiled = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        for(int i = 0; i < fructs.size(); i++){
            Date newDate = format.parse(fructs.get(i).getDate());
            calendar.setTime(newDate);
            calendar.add(Calendar.DAY_OF_MONTH,fructs.get(i).getShelfLife());
            if(fructs.get(i).getType().equals(type) && calendar.getTime().before(calendar1.getTime())){
                spoiled.add(fructs.get(i));
            }
        }
        return spoiled;
    }
    //перегружаем метод на прием еще одного параметра - вид фрукта
    public List<Fruts> getAvailableFruits(Date date, Fruts.Type type) throws ParseException{
        List<Fruts> available = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        for(int i = 0; i < fructs.size(); i++){
            Date newDate = format.parse(fructs.get(i).getDate());
            calendar.setTime(newDate);
            calendar.add(Calendar.DAY_OF_MONTH,fructs.get(i).getShelfLife());
            if(fructs.get(i).getType().equals(type) && calendar.getTime().after(calendar1.getTime())){
                available.add(fructs.get(i));
            }
        }
        return available;
    }
    //метод который возвращает продукты которые были доставлены в заданную дату
    public List<Fruts> getAddedFruits(Date date) throws ParseException {
        List<Fruts> added = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        for(int i = 0; i < fructs.size(); i++){
            Date newDate = format.parse(fructs.get(i).getDate());
            if(newDate.getTime()== date.getTime()){
                added.add(fructs.get(i));
            }
        }
        return added;
    }
    //перегружаем метод на прием еще одного параметра - вид фрукта
    public List<Fruts> getAddedFruits(Date date, Fruts.Type type) throws ParseException {
        List<Fruts> added = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        for(int i = 0; i < fructs.size(); i++){
            Date newDate = format.parse(fructs.get(i).getDate());
            if(fructs.get(i).getType().equals(type) && newDate.getTime()== date.getTime()){
                added.add(fructs.get(i));
            }
        }
        return added;
    }
    //метод продажи продуктов клиентам
    public void sell(String pathToJsonFile) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(pathToJsonFile));
        String json = r.readLine();
        int count;
        clients.addAll(JSON.parseArray(json, Clients.class));
        for(int i = 0; i < clients.size(); i++){
            count = 0;
            for(int j = 0; j < fructs.size(); j++){
                if(fructs.get(j).getType().equals(clients.get(i).getType())){
                    count++;
                }
            }if(count <=clients.get(i).getCount()){sellOk(clients.get(i).getType());}
        }
    }
    //медод удаления родуктов со склада + пополнение кошелька
        private void sellOk(Clients.Type type){
            for(int i = 0; i < clients.size(); i++){
                for(int j = 0; j < fructs.size(); j++){
                    if(fructs.get(j).getType().equals(type)){
                        moneyBalance +=fructs.get(j).getPrice();
                        fructs.remove(j);
                    }
                }
            }
        }

    //вывод в консоль содержимого склада
    public void showStock(){
       for(int i = 0; i < fructs.size(); i++){
           System.out.println("Type - " + fructs.get(i).getType() + "  " + "\n" + "Shelflife - " + fructs.get(i).getShelfLife()
                   + "  " + "\n" + "Date - " + fructs.get(i).getDate() + "  " + "\n" + "Price - " + fructs.get(i).getPrice());
           System.out.println();
       }
    }
    //вывод в консоль коллекции с фруктами
    public void showFruit(List<Fruts> fruts){
        for(int i = 0; i < fruts.size(); i++){
            System.out.println("Type - " + fruts.get(i).getType() + "  " + "\n" + "Shelflife - " + fruts.get(i).getShelfLife()
                    + "  " + "\n" + "Date - " + fruts.get(i).getDate() + "  " + "\n" + "Price - " + fruts.get(i).getPrice());
            System.out.println();
        }
    }

    public double getMoneyBalance() {
        return moneyBalance;
    }
}

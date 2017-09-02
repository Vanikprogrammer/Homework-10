
import score.Fruts;
import score.TradeShop;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 25.08.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        TradeShop shop = new TradeShop();
        String baze = "bazeFruits/Stock.txt";
        String baze1 = "bazeFruits/Stock1.txt";
        String baze2 = "bazeFruits/Stock2.txt";
        String save = "bazeFruits/AllStockInfo.txt";
        //очищаем склад и добавляем новые данные
        shop.load(baze);
        //добавляем новые данные в склад
        shop.addFruits(baze1);
        //делаем еще поставку фруктов и выводим в консоль
        shop.addFruits(baze2);
        shop.showStock();
        //сохраняем всю информацию со склада в файл
        shop.save(save);


        //выясняем какие продукты испортятся к заданной дате
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = format.parse("25/07/2017");
        //System.out.println("К заданной дате испортятся");
        System.out.println();

        //List<Fruts>fruits = shop.getSpoiledFruits(date);
        //shop.showFruit(fruits);

        // готовые к продаже фрукты
        System.out.println("К продаже готовы");
        //List<Fruts>fruts1 = shop.getAvailableFruits(date);
        //shop.showFruit(fruts1);
        //System.out.println("");
        //перегружаемый метод испортившихся вруктов + тип
        Fruts.Type type = Fruts.Type.Banana;
        //List<Fruts>fruts2 = shop.getSpoiledFruits(date, type );
        //shop.showFruit(fruts2);
        //перегружаемый метод готовых к продаже вруктов + тип
        //List<Fruts>fruts3 = shop.getAvailableFruits(date,type);
        //shop.showFruit(fruts3);
        //List<Fruts>fruts4 = shop.getAddedFruits(date);
        //shop.showFruit(fruts4);
        //List<Fruts>fruts5 = shop.getAddedFruits(date,type);
        //shop.showFruit(fruts5);









        
  }
}

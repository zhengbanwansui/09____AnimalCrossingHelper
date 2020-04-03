package starter;

import entity.TwoVal;
import util.Drag;
import util.ReadColor;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws AWTException, InterruptedException {

        int boxXLimit = 1222;
        int boxYLimit = 280;
        int bagXLimit = 1148;
        int bagYLimit = 573;
        // 初始化柜子
        ArrayList<TwoVal> box = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                box.add(new TwoVal(boxXLimit + j * 123, boxYLimit + i * 113));
            }
        }
        // 初始化背包
        ArrayList<TwoVal> bag = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (!(i == 0 && j == 2) && !(i == 0 && j == 3)) {
                    bag.add(new TwoVal(bagXLimit + j * 135, bagYLimit + i * 111));
                }
            }
        }

        ReadColor readColor = new ReadColor();
        int intRGB,r,g,b;

        // 有芜菁的柜子
        Thread.sleep(3000);
        ArrayList<TwoVal> FullBox = new ArrayList<>();
        for (TwoVal twoVal : box) {
            intRGB = readColor.getScreenPixel(twoVal.getA(), twoVal.getB());
            r = (intRGB & 0xff0000) >> 16;
            g = (intRGB & 0xff00) >> 8;
            b = (intRGB & 0xff);
            System.out.println("box" + r + " " + g + " " + b);
            if (r > 230 && g > 230 && b > 230) {
                FullBox.add(new TwoVal(twoVal.getA(), twoVal.getB()));
            }
        }
        // 没有芜菁的背包
        ArrayList<TwoVal> EmptyBag = new ArrayList<>();
        for (TwoVal twoVal : bag) {
            intRGB = readColor.getScreenPixel(twoVal.getA(), twoVal.getB());
            r = (intRGB & 0xff0000) >> 16;
            g = (intRGB & 0xff00) >> 8;
            b = (intRGB & 0xff);
            System.out.println("bag" + r + " " + g + " " + b);
            if (!(r > 230 && g > 230 && b > 230)) {
                EmptyBag.add(new TwoVal(twoVal.getA(), twoVal.getB()));
            }
        }
        // 开始移动物品
        System.out.println(FullBox.toString());
        System.out.println(EmptyBag.toString());
        Thread.sleep(3000);
        int count = EmptyBag.size() < FullBox.size() ? EmptyBag.size() : FullBox.size();
        for (int i = 0; i < count; i++) {
            TwoVal twoBox = FullBox.get(i);
            TwoVal twoBag = EmptyBag.get(i);
            Drag.drag(twoBox.getA(), twoBox.getB(), twoBag.getA(), twoBag.getB());
        }
    }
}

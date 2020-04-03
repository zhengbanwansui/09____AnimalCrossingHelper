package helper;

import entity.TwoVal;
import util.Drag;
import util.ReadColor;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.event.InputEvent.BUTTON1_MASK;

public class MoveFromBox {

    private int boxXLimit = 1222;
    private int boxYLimit = 280;
    private int bagXLimit = 1148;
    private int bagYLimit = 573;
    private int saleXLimit = 1156;
    private int saleYLimit = 494;
    ArrayList<TwoVal> box = new ArrayList<>();
    ArrayList<TwoVal> bag = new ArrayList<>();
    ArrayList<TwoVal> FullBox = new ArrayList<>();
    ArrayList<TwoVal> EmptyBox = new ArrayList<>();
    ArrayList<TwoVal> FullBag = new ArrayList<>();
    ArrayList<TwoVal> EmptyBag = new ArrayList<>();

    public void initBagAndBox() {
        // 初始化柜子
        box.clear();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                box.add(new TwoVal(boxXLimit + j * 123, boxYLimit + i * 113));
            }
        }
        // 初始化背包
        bag.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (!(i == 0 && j == 2) && !(i == 0 && j == 3)) {
                    bag.add(new TwoVal(bagXLimit + j * 135, bagYLimit + i * 111));
                }
            }
        }
    }

    private void initFullAndEmpty() throws AWTException {

        ReadColor readColor = new ReadColor();
        int intRGB,r,g,b;
        // 分类柜子
        FullBox.clear();
        EmptyBox.clear();
        for (TwoVal twoVal : box) {
            intRGB = readColor.getScreenPixel(twoVal.getA(), twoVal.getB());
            r = (intRGB & 0xff0000) >> 16;
            g = (intRGB & 0xff00) >> 8;
            b = (intRGB & 0xff);
            System.out.println("box" + r + " " + g + " " + b);
            if (r > 230 && g > 230 && b > 230) {
                FullBox.add(new TwoVal(twoVal.getA(), twoVal.getB()));
            } else {
                EmptyBox.add(new TwoVal(twoVal.getA(), twoVal.getB()));
            }
        }
        // 分类背包
        EmptyBag.clear();
        FullBag.clear();
        for (TwoVal twoVal : bag) {
            intRGB = readColor.getScreenPixel(twoVal.getA(), twoVal.getB());
            r = (intRGB & 0xff0000) >> 16;
            g = (intRGB & 0xff00) >> 8;
            b = (intRGB & 0xff);
            System.out.println("bag" + r + " " + g + " " + b);
            if (!(r > 230 && g > 230 && b > 230)) {
                EmptyBag.add(new TwoVal(twoVal.getA(), twoVal.getB()));
            } else {
                FullBag.add(new TwoVal(twoVal.getA(), twoVal.getB()));
            }
        }
        System.out.println("箱子芜菁" + FullBox.toString());
        System.out.println("箱子空位" + EmptyBox.toString());
        System.out.println("背包芜菁" + FullBag.toString());
        System.out.println("背包空位" + EmptyBag.toString());
    }

    public void moveFromBoxToBag() throws InterruptedException, AWTException {

        initFullAndEmpty();
        int count = EmptyBag.size() < FullBox.size() ? EmptyBag.size() : FullBox.size();
        for (int i = 0; i < count; i++) {
            TwoVal twoBox = FullBox.get(i);
            TwoVal twoBag = EmptyBag.get(i);
            Drag.drag(twoBox.getA(), twoBox.getB(), twoBag.getA(), twoBag.getB());
        }
    }

    public void moveFromBagToBox() throws InterruptedException, AWTException {

        initFullAndEmpty();
        int count = FullBag.size() < EmptyBox.size() ? FullBag.size() : EmptyBox.size();
        for (int i = 0; i < count; i++) {
            TwoVal twoBox = EmptyBox.get(i);
            TwoVal twoBag = FullBag.get(i);
            Drag.drag(twoBag.getA(), twoBag.getB(), twoBox.getA(), twoBox.getB());
        }
    }

    public void saleInShop() throws AWTException, InterruptedException {
        // 背包向上移动31像素
        Robot robot = new Robot();
        for (TwoVal twoVal : bag) {
            robot.mouseMove(twoVal.getA(), twoVal.getB() - 83);
            //Thread.sleep(500);
            robot.mousePress(BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(BUTTON1_MASK);
            Thread.sleep(300);
        }
    }
}

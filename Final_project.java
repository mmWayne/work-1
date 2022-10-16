/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 期末專題;

import java.util.*;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
//此程式執行抽鬼牌遊戲，由一名玩家對上三個電腦，先沒牌的獲勝。
public class Final_project {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        out.print("歡迎遊玩抽鬼牌遊戲，您的對手是阿東、揚哥和小杰(開始遊玩請輸入1)：");
        int n = input.nextInt();//確認是否要玩此遊戲
        display(n);
    }

    public static void display(int n) { //顯示程式進行的畫面
        List<Integer> list_a = new ArrayList<>(); //一副牌
        List<Integer> list_b = new ArrayList<>(); //記錄發過的牌
        List<String> player = new ArrayList<>(); //玩家手牌
        List<String> com1 = new ArrayList<>(); //電腦1手牌
        List<String> com2 = new ArrayList<>(); //電腦2手牌
        List<String> com3 = new ArrayList<>(); //電腦3手牌
        List<String> playerpai_no = new ArrayList<>();//玩家牌值
        List<String> com1pai_no = new ArrayList<>();//電腦1牌值
        List<String> com2pai_no = new ArrayList<>();//電腦2牌值
        List<String> com3pai_no = new ArrayList<>();//電腦3牌值
        String flower = ""; //宣告並存放花色
        String paiarr[] = {"Joker", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};//建立牌的點數
        String pai_no = "";//存放牌的點數
        for (int i = 0; i < 52; i++) {
            list_a.add(i + 1);
        }
        for (int i = 0; i < 52; i++) {
            int randnum = (int) (Math.random() * list_a.size());//隨機生成0~51的數字
            list_b.add(list_a.get(randnum));
            list_a.remove(randnum);
        }
        for (int i = 0; i < list_b.size(); i++) {
            int pai = list_b.get(i);//為第幾張牌
            if (pai >= 1 && pai <= 13) {
                flower = "♠";
                pai_no = paiarr[pai];
            }
            if (pai >= 14 && pai <= 26) {
                flower = "♣";
                pai_no = paiarr[pai - 13];
            }
            if (pai >= 27 && pai <= 39) {
                flower = "♥";
                pai_no = paiarr[pai - 26];
            }
            if (pai >= 40 && pai <= 52) {
                flower = "♦";
                pai_no = paiarr[pai - 39];
            }
            if (i < 13) {
                player.add(flower + pai_no);
                playerpai_no.add(pai_no);
                if (i == 13) {
                    out.print("\n");
                }
                continue;
            }
            if (i < 26) {
                com1.add(flower + pai_no);
                com1pai_no.add(pai_no);
                if (i == 26) {
                    out.print("\n");
                }
                continue;
            }
            if (i < 39) {
                com2.add(flower + pai_no);
                com2pai_no.add(pai_no);
                if (i == 39) {
                    out.print("\n");
                }
                continue;
            }
            if (i < 52) {
                com3.add(flower + pai_no);
                com3pai_no.add(pai_no);
                if (i == 52) {
                    out.print("\n");
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            int ghostnum = (int) (Math.random() * 4);//看鬼牌分給誰
            int index = (int) (Math.random() * player.size());//隨機生成鬼牌在手牌中的位置
            switch (ghostnum) {
                case 0:
                    player.add(index, paiarr[0]);
                    playerpai_no.add(index, paiarr[0]);
                    break;
                case 1:
                    com1.add(index, paiarr[0]);
                    com1pai_no.add(index, paiarr[0]);
                    break;
                case 2:
                    com2.add(index, paiarr[0]);
                    com2pai_no.add(index, paiarr[0]);
                    break;
                case 3:
                    com3.add(index, paiarr[0]);
                    com3pai_no.add(index, paiarr[0]);
                    break;
                default:
                    break;
            }
        }
        out.print("玩家手牌：\t");
        discard(player, playerpai_no);
        for (int i = 0; i < player.size(); i++) {
            out.print(player.get(i) + "\t");
        }
        discard(com1, com1pai_no);
        discard(com2, com2pai_no);
        discard(com3, com3pai_no);
        while (true) {                                           //while內是遊戲過程且判斷是誰贏了
            if (player.isEmpty()) {
                out.print("恭喜你贏了！");
                break;
            } else if (com1.isEmpty()) {
                out.print("可惜，阿東比您幸運一些，已取得勝利！");
                break;
            } else if (com2.isEmpty()) {
                out.print("哎呀，揚哥實力堅強，已經獲勝了！");
                break;
            } else if (com3.isEmpty()) {
                out.print("阿杰運氣好了那麼一些，恭喜她獲勝囉。");
                break;
            }
            changecard(player, com1, playerpai_no, com1pai_no);
            discard(player, playerpai_no);
            botchangecard(com1, com2, com1pai_no, com2pai_no);
            discard(com1, com1pai_no);
            bot1changecard(com2, com3, com2pai_no, com3pai_no);
            discard(com2, com2pai_no);
            bot2changecard(com3, player, com3pai_no, playerpai_no);
            discard(com3, com3pai_no);
            if (player.isEmpty()) {
                out.print("WOW，你真幸運，恭喜你贏了！");
                break;
            } else if (com1.isEmpty()) {
                out.print("可惜，阿東比您幸運一些，得到了勝利！");
                break;
            } else if (com2.isEmpty()) {
                out.print("哎呀，揚哥實力堅強，已經獲勝了！");
                break;
            } else if (com3.isEmpty()) {
                out.print("阿杰運氣好了那麼一些，他贏了！");
                break;
            }
            out.print("玩家手牌:\t");
            out.println();
            for (int i = 0; i < player.size(); i++) {
                out.print(player.get(i) + "\t");
            }
        }
    }

//有兩張一樣的牌就刪掉,用點數是否一樣判斷,然後讓手牌跟點數一起刪掉
    public static void discard(List<String> card, List<String> pai_no) {
        for (int i = 0; i < pai_no.size(); i++) {
            for (int j = 0; j < pai_no.size() && i != j; j++) {
                if (pai_no.get(i).equals(pai_no.get(j)) && !pai_no.get(i).equals("Joker")) {
                    pai_no.set(i, "0");
                    pai_no.set(j, "0");
                }
            }
        }
        for (int i = 0; i < pai_no.size(); i++) {
            if ("0".equals(pai_no.get(i))) {
                pai_no.remove(i);
                card.remove(i);
                i = -1;
            }
        }
    }

//抽對方的牌,手牌跟點數都會加上抽到的,被抽的就刪掉
    public static void changecard(List<String> player, List<String> com, List<String> playerpai_no, List<String> compai_no) {
        Scanner input = new Scanner(System.in);
        out.println();
        out.println("對方剩" + com.size() + "張牌");
        out.print("請問要抽哪張牌：");
        int x = input.nextInt();//抽第幾張牌
        while (true) {
            if (x <= com.size()) {
                out.print("請問要放在哪個位置：");
                break;
            } else {
                out.print("對方沒那麼多牌，請再輸入一次：");
                x=input.nextInt();
                out.print("請問要放在哪個位置：");
                break;
            }
        }
        int index = input.nextInt();//放在哪個位置
        if (!com.isEmpty()) {
            player.add(index - 1, com.get(x - 1));
            playerpai_no.add(index - 1, compai_no.get(x - 1));
            com.remove(x - 1);
            compai_no.remove(x - 1);
        }
    }

//以下都是電腦抽牌,手牌跟點數都會加上抽到的,被抽的就刪掉
    public static void botchangecard(List<String> a, List<String> b, List<String> apai_no, List<String> bpai_no) {
        int x = (int) (Math.random() * b.size());//抽第幾張牌
        int index = (int) (Math.random() * a.size());//放在一個隨機的位置
        if (!b.isEmpty()) {
            a.add(index, b.get(x));
            apai_no.add(index, bpai_no.get(x));
            b.remove(x);
            bpai_no.remove(x);
            out.println("阿東抽了第" + (x + 1) + "張");
        }
    }

    public static void bot1changecard(List<String> a, List<String> b, List<String> apai_no, List<String> bpai_no) {
        int x = (int) (Math.random() * b.size());//抽第幾張牌
        int index = (int) (Math.random() * a.size());//放在一個隨機的位置
        if (!b.isEmpty()) {
            a.add(index, b.get(x));
            apai_no.add(index, bpai_no.get(x));
            b.remove(x);
            bpai_no.remove(x);
            out.println("揚哥抽了第" + (x + 1) + "張");
        }
    }

    public static void bot2changecard(List<String> a, List<String> b, List<String> apai_no, List<String> bpai_no) {
        int x = (int) (Math.random() * b.size());//抽第幾張牌\
        int index = (int) (Math.random() * a.size());//放在一個隨機的位置
        if (!b.isEmpty()) {
            a.add(index, b.get(x));
            apai_no.add(index, bpai_no.get(x));
            b.remove(x);
            bpai_no.remove(x);
            out.println("小杰抽了第" + (x + 1) + "張");
        }
    }
}

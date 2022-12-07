package com.java.sparsearray;

/**
 * @Author: code muxiaoming
 * @DateCreatedIn: 2022/12/1 20:43
 * @Description: 稀疏数组存储五子棋局
 * 创建一个原始的二维数组11*11
 * 0:表示没有棋子,1表示黑子,2表示白子
 */
public class SparseArray {
    public static void main(String[] args) {
        //棋局(原始稀疏数组)
        int[][] oriChessArray = new int[11][11];
        oriChessArray[1][2] = 1;//黑子落子处
        oriChessArray[2][3] = 2;//白子落子处
        //输出原始棋局
        System.out.println("------------------原始棋局-----------------");
        for (int[] row : oriChessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);//%d应该是占位符,\t是制表符(起空格的作用)
            }
            System.out.println();
        }
        //二维数组本质上是在一维数组中存了个数组(一维数组取代原来用来存放元素的位置,可以这么理解)
        //注意,二维数组各列长度可以不等,如oriChessArray[0].length就是第一列的长度
        //所以oriChessArray.length的长度就是行的长度
        //和上面的效果一样只是写法不同
        /*for (int i = 0; i < oriChessArray.length; i++) {
            for (int j = 0; j < oriChessArray[0].length; j++){
                System.out.printf("%d\t",oriChessArray[i][j]);
            }
            System.out.println();
        }*/

        //将二维数组转成稀疏数组
        //1. 取到棋局中棋子的个数(创建稀疏数组需要)
        int counter = 0;
        for (int i = 0; i < oriChessArray.length; i++) {
            for (int j = 0; j < oriChessArray[0].length; j++) {
                if (oriChessArray[i][j] != 0) {
                    counter++;
                }
            }
        }
        //第二部,创建稀疏数组
        //数组第一行为:           原数组的行,原数组的列,棋子个数
        //第二行及counter+1行为:棋子的横坐标,棋子的纵坐标,棋子颜色(黑白双方)
        int[][] sparse = new int[counter + 1][3];
        sparse[0][0] = oriChessArray.length;
        //因为棋盘是正方形,所以使用oriChessArray.length也行,上方同理
        sparse[0][1] = oriChessArray[0].length;
        sparse[0][2] = counter;
        int row = 1;
        for (int i = 0; i < oriChessArray.length; i++) {
            for (int j = 0; j < oriChessArray[0].length; j++) {
                if (oriChessArray[i][j] != 0) {
                    sparse[row][0] = i;
                    sparse[row][1] = j;
                    sparse[row][2] = oriChessArray[i][j];
                    row++;
                }
            }
        }
        System.out.println("------------------稀疏数组-----------------");
        //稀疏数组只有3列,枚举,单循环足矣
        /*for (int[] row1 : sparse) {
            for (int data : row1) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }*/
        for (int i = 0; i < sparse.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparse[i][0],sparse[i][1],sparse[i][2]);
        }
        //还原数组,稀疏数组的第一行的第一二个值是数组的行和列
        int[][] restoreArray = new int[sparse[0][0]][sparse[0][1]];

        //这种方法有点愚蠢,肯定要循环小数组啊!!!!!!!
        /*for (int i = 0; i < restoreArray.length; i++) {
            for (int j = 0; j < restoreArray[0].length; j++) {
                if (sparse[count][0] == i && sparse[count][1] == j) {
                   restoreArray[i][j] = sparse[count][2];
                   count++;
                }
            }
        }*/
        //循环稀疏数组只要单重循环
        for (int i = 1; i < sparse.length; i++) {
            restoreArray[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }
        System.out.println("------------------还原数组-----------------");
        for (int[] row1 : oriChessArray) {
            for (int data : row1) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}

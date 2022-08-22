package com.yeongbin.stock.algorithmproblem;

import java.util.Scanner;

/**
 * TC 0번 확인
 * 3 3
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * TC 1번 확인
 * 4 2
 * 1	2
 * 3	4
 * 5	6
 * 7	8
 *
 *TC 2번 확인
 * 4 1
 * 1
 * 2
 * 3
 * 4
 *
 * TC 3번 확인
 * 2 4
 * 1	2	3	4
 * 5	6	7	8
 *
 * TC 4번 확인
 * 2 1
 * 1
 * 2
 * TC 5번 확인
 * 1 2
 * 1	2
 *
 * TC 6번 확인
 * 1 1
 * 1
 */
public class problem1 {
    public static void main(String[] args){
        // scanner 선언
        Scanner scanner = new Scanner(System.in);
        // 사용자 입력
        System.out.print("가로 열 : ");
        int row = scanner.nextInt();

        System.out.print("세로 열 : ");
        int col = scanner.nextInt();
        //enter consume 처리
        scanner.nextLine();
        System.out.print("배열 데이터 ,로 구분 : ");
        String str = scanner.nextLine();
        String[] strArr = str.split(",");

        int[][] intArr = new int[row][col];

        int idx = 0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                intArr[i][j] = Integer.parseInt(strArr[idx++]);
                //System.out.println(intArr[i][j]);
            }
        }

        // 위는 데이터 입력 및 초기화 부분
        //////////////////////////////////
        //////////////////////////////////
        // 아래부터 처리 로직

        int[] startPos = {0,0};
        int[][] rightUp = {{-1,1},{0,1},{1,0}};
        int[][] leftDown = {{1,-1},{1,0},{0,1}};
        boolean dirFlag = true;

        System.out.print(intArr[startPos[0]][startPos[1]]);
        do{
            if(row==1 && col == 1) break;
            if(dirFlag){
                int dx = rightUp[0][0];
                int dy = rightUp[0][1];
                int nextXPos = startPos[0] + dx;
                int nextYPos = startPos[1] + dy;
                //System.out.println("current pos x,y" + startPos[0] + ", " + startPos[1]);

                if(nextXPos < 0 && nextYPos  < col ){
                    nextXPos = startPos[0] + rightUp[1][0];
                    nextYPos = startPos[1] + rightUp[1][1];
                    dirFlag = !dirFlag;
                }
                else if(nextYPos  >= col){
                    nextXPos = startPos[0] + rightUp[2][0];
                    nextYPos = startPos[1] + rightUp[2][1];
                    dirFlag = !dirFlag;
                }

                startPos[0] = nextXPos;
                startPos[1] = nextYPos;
            }
            else{
                int dx = leftDown[0][0];
                int dy = leftDown[0][1];
                int nextXPos = startPos[0] + dx;
                int nextYPos = startPos[1] + dy;
                //System.out.println("current pos x,y" + startPos[0] + ", " + startPos[1]);

                if(nextXPos < row && nextYPos  < 0){
                    nextXPos = startPos[0] + leftDown[1][0];
                    nextYPos = startPos[1] + leftDown[1][1];
                    dirFlag = !dirFlag;
                }
                else if(nextXPos >= row){
                    nextXPos = startPos[0] + leftDown[2][0];
                    nextYPos = startPos[1] + leftDown[2][1];
                    dirFlag = !dirFlag;
                }

                startPos[0] = nextXPos;
                startPos[1] = nextYPos;
            }
            //System.out.println("next current pos x,y" + startPos[0] + ", " + startPos[1] +"\n");
            System.out.print(", " + intArr[startPos[0]][startPos[1]]);
        }while( !(startPos[0] == row-1 && startPos[1] == col-1) );

        // close scanner
        scanner.close();

    }
}

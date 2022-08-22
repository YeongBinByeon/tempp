package com.yeongbin.stock.algorithmproblem;
import java.util.Scanner;

public class problem2 {
    public static void main(String[] args) {
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
        for(int i=0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                intArr[i][j] = Integer.parseInt(strArr[idx++]);
                System.out.println(intArr[i][j]);
            }
        }


        System.out.print("명령 공백 없이 연속 입력 : 우측 90 / R, 좌측 90 / L, 좌우 반전 / T : ");
        String[] commandArr = scanner.nextLine().split("");
        // 위는 데이터 입력 및 초기화 부분
        //////////////////////////////////
        //////////////////////////////////
        // 아래부터 처리 로직

        int[][] rotateArr = intArr;
        for(int i=0; i<commandArr.length; i++){
            switch(commandArr[i]){
                case "R":
                    rotateArr = rightRotate(rotateArr.length,rotateArr[0].length,rotateArr); //(ROW, COL, ARR)
                    break;
                case "L":
                    rotateArr = leftRotate(rotateArr.length,rotateArr[0].length,rotateArr);
                    break;
                case "T":
                    rotateArr = reverseLeftRight(rotateArr.length,rotateArr[0].length,rotateArr);
                    break;

            }
        }
        //위 배열에서 가로(1) 세로(3) ->(0,2) 위치의 숫자 1을 출력
        // 입력받는 배열 크기에 따라서 실행 시 예외 터질 수 있겠지만, 문서에 해당 좌표로 적혀있음
        System.out.println("위 배열에서 가로(1) 세로(3) ->(0,2) 위치의 숫자 1을 출력 : " + rotateArr[2][0]);

    }

    // 시게방향으로 90도 회전, 오른쪽으로 90도 회전
    public static int[][] rightRotate(int row, int col, int[][] arr) {
        int[][] res = new int[col][row];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                //res[i][j] = arr[col -j-1][i];
                res[j][row-1-i] = arr[i][j];
            }
        }
        print(col,row,res);
        return res;
    }

    // 반시계 방향으로 90도 회전, 270도, -90도, 왼쪽으로 90도
    public static int[][] leftRotate(int row, int col, int[][] arr) {
        int[][] res = new int[col][row];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                res[col-1-j][i] = arr[i][j];
            }
        }
        print(col,row,res);
        return res;
    }

    // 좌우로 뒤집기 회전
    public static int[][] reverseLeftRight(int row, int col, int[][] arr) {
        int[][] res = new int[row][col];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                res[i][col-j-1] = arr[i][j];
            }
        }
        print(row,col,res);
        return res;
    }

    public static void print(int row, int col, int[][] array){
        // print
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

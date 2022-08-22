package com.yeongbin.stock.algorithmproblem;

import java.util.*;

public class problem3{
    public static void main(String[] args){
        // scanner 선언
        Scanner scanner = new Scanner(System.in);
        // 사용자 입력
        System.out.print("사람 수 : ");
        int manNum = scanner.nextInt();

        System.out.print("이동할 거리 : ");
        int distance = scanner.nextInt();

        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<=manNum; i++){
            q.add(i);
        }

       StringBuilder sb = new StringBuilder();
        sb.append("<");
        while(q.size() > 1){
            for (int i=0; i<distance-1; i++){
                int num = q.poll();
                q.offer(num);
            }
            sb.append(q.poll()).append(", ");
        }
        int lastMan = q.poll();
        sb.append(lastMan).append(">");
        System.out.println("제거되는 값 순서 : " + sb);
        System.out.println("마지막으로 남는 사람 : " + lastMan);
    }
}


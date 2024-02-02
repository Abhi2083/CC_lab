import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        int n;
        float totalTAT = 0, totalWT = 0;
        int[] AT = new int[10];
        int[] BT = new int[10];
        int[] CT = new int[10];
        int[] TAT = new int[10];
        int[] WT = new int[10];

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the no of process:- ");
        n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process[" + (i + 1) + "]:-");
            AT[i] = scanner.nextInt();

            System.out.print("Enter burst time of process[" + (i + 1) + "]:-");
            BT[i] = scanner.nextInt();

            System.out.println("\n\n");
        }
        scanner.close();
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n - 1; j++) {
                if (AT[i] > AT[j + 1]) {
                    temp = AT[i];
                    AT[i] = AT[j + 1];
                    AT[j + 1] = temp;
                }
            }
        }

        int sum = AT[0];
        for (int i = 0; i < n; i++) {
            sum = sum + BT[i];
            CT[i] = sum;
        } 

        for (int i = 0; i < n; i++) {
            TAT[i] = CT[i] - AT[i];
            totalTAT = totalTAT + TAT[i];
        }

        for (int i = 0; i < n; i++) {
            WT[i] = TAT[i] - BT[i];
            totalWT = totalWT + WT[i];
        }

        System.out.println("Solution:- ");
        System.out.println("Pno \t At \t Bt \t Ct \t TAT \t Wt ");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " \t " + AT[i] + " \t " + BT[i] + " \t " + CT[i] + " \t " + TAT[i] + " \t " + WT[i]);
        }
        System.out.println("Avg TAT = " + totalTAT / n);
        System.out.println("Avg WT = " + totalWT / n);
    }
}

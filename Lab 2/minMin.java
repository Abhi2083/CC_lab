import java.util.Scanner;

public class minMin {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of machines and tasks:");
            int nM = sc.nextInt();
            int nT = sc.nextInt();

            int[][] executionTimes = new int[nM][nT];

            System.out.println("\nFill Data:");
            for (int i = 0; i < nM; i++)
                for (int j = 0; j < nT; j++)
                    executionTimes[i][j] = sc.nextInt();

            System.out.println("\nOriginal Data:");
            for (int[] row : executionTimes) {
                for (int value : row)
                    System.out.print(value + " ");
                System.out.println();
            }

            int[] resultTask = new int[nT];
            int[] resultMachine = new int[nT];
            int[] resultTime = new int[nT];

            for (int ptr = 0; ptr < nT; ptr++) {
                int minTime = Integer.MAX_VALUE, task = -1, machine = -1;

                for (int j = 0; j < nT; j++)
                    for (int i = 0; i < nM; i++)
                        if (executionTimes[i][j] != -1 && executionTimes[i][j] < minTime) {
                            minTime = executionTimes[i][j];
                            task = j;
                            machine = i;
                        }

                resultTask[ptr] = task;
                resultMachine[ptr] = machine;
                resultTime[ptr] = minTime;

                for (int i = 0; i < nM; i++)
                    executionTimes[i][task] = -1;
            }

            System.out.println("\nScheduled Tasks are:");
            for (int i = 0; i < nT; i++)
                System.out.printf("\nTask %d Runs on Machine %d with Time %d units\n", resultTask[i] + 1,
                        resultMachine[i] + 1, resultTime[i]);
        }
    }
}

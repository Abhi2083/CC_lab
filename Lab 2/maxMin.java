import java.util.Scanner;

public class maxMin {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of machines and tasks:");
            int nM = sc.nextInt();
            int nT = sc.nextInt();

            int[][] maxMin = new int[nM][nT];
            int[][] tmp = new int[nM][nT];

            System.out.println("\nFill Data:");
            for (int i = 0; i < nM; i++)
                for (int j = 0; j < nT; j++)
                    tmp[i][j] = maxMin[i][j] = sc.nextInt();

            System.out.println("\nOriginal Data:");
            for (int[] row : maxMin) {
                for (int value : row)
                    System.out.print(value + " ");
                System.out.println();
            }

            int[] resultTask = new int[nT];
            int[] resultMachine = new int[nT];
            int[] resultTime = new int[nT];
            int makespan = 0;

            for (int ptr = 0; ptr < nT; ptr++) {
                int[] time = new int[nT];
                int[] machine = new int[nT];

                for (int j = 0; j < nT; j++) {
                    int[] column = maxMin[0];
                    int pos = 0;
                    for (int i = 1; i < nM; i++)
                        if (maxMin[i][j] < column[j]) {
                            column = maxMin[i];
                            pos = i;
                        }
                    time[j] = column[j];
                    machine[j] = pos;
                }

                int pos = 0;
                for (int j = 0; j < nT; j++)
                    if (time[j] > time[pos])
                        pos = j;

                resultTask[ptr] = pos;
                resultMachine[ptr] = machine[pos];
                resultTime[ptr] = tmp[resultMachine[ptr]][resultTask[ptr]];

                makespan += resultTime[ptr];

                for (int i = 0; i < nM; i++)
                    for (int j = 0; j < nT; j++)
                        if (j == resultTask[ptr])
                            maxMin[i][j] = Integer.MAX_VALUE;
                        else if (i == resultMachine[ptr] && maxMin[i][j] != Integer.MAX_VALUE)
                            maxMin[i][j] += time[pos];
            }

            System.out.println("\nScheduled Tasks are:");
            for (int i = 0; i < nT; i++)
                System.out.printf("\nTask %d Runs on Machine %d with Time %d units\n", resultTask[i] + 1,
                        resultMachine[i] + 1, resultTime[i]);

            System.out.printf("\nTotal elapsed time: %d units\n", makespan);
        }
    }
}

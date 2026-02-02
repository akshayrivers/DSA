import java.util.Arrays;

public class ServerMigration {

    // Function to calculate the minimum cost to achieve finalMachineCount in any 3 regions
    public static int minCost(int[] machineCount, int[] finalMachineCount, int shiftingCost) {
        int n = machineCount.length;
        int minCost = Integer.MAX_VALUE;

        // Sort finalMachineCount for easier comparison
        Arrays.sort(finalMachineCount);

        // Generate all combinations of choosing 3 regions out of n regions
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Get the regions we're considering
                    int[] current = {machineCount[i], machineCount[j], machineCount[k]};
                    Arrays.sort(current);

                    // Calculate the cost to make these 3 regions match finalMachineCount
                    int addRemoveCost = Math.abs(current[0] - finalMachineCount[0]) 
                                      + Math.abs(current[1] - finalMachineCount[1])
                                      + Math.abs(current[2] - finalMachineCount[2]);

                    // Consider shifting the remaining regions to these 3 regions
                    int shiftCost = 0;
                    for (int x = 0; x < n; x++) {
                        if (x != i && x != j && x != k) {
                            shiftCost += shiftingCost;
                        }
                    }

                    // Total cost for this combination
                    int totalCost = addRemoveCost + shiftCost;

                    // Update minimum cost found
                    minCost = Math.min(minCost, totalCost);
                }
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        int[] machineCount = {2,3, 5, 7};
        int[] finalMachineCount = {5, 10, 5};
        int shiftingCost = 2;

        int result = minCost(machineCount, finalMachineCount, shiftingCost);
        System.out.println("Minimum cost to achieve final machine counts: " + result);
    }
}

import java.util.*;

public class Scouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many matches?");
        int m = sc.nextInt();
        int[][] matches =  new int[8][m];
        String temp = "";
        temp = sc.nextLine();
        System.out.println("Copy paste the excel spreadsheet");
        for(int i = 0; i < m; i++) {
            temp = sc.nextLine();
            for(int j = 0; j < 8; j++){
                matches[j][i] = Integer.valueOf(temp.substring(j * 4, j * 4 + 4));
            }
        }
        ArrayList<Integer> teams = new ArrayList<Integer>();
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < matches[0].length; j++) {
                if(!teams.contains(matches[i][j])) {
                    teams.add(matches[i][j]);
                }
            }
        }
        System.out.println(teams);
        double[][] input = new double[teams.size()][teams.size() + 2];
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                input[i][j] = 0;
            }
        }
        int temp1;
        int temp2;
        for(int i = 0; i < teams.size(); i++) {
            temp1 = teams.get(i);
            for(int j = 0; j < teams.size(); j++) {
                temp2 = teams.get(j);
                for(int k = 0; k < 6; k++) {
                    for(int l = 0; l < matches[0].length; l++) {
                       if(matches[k][l] == temp1) {
                           for(int n = 0; n < 6; n++) {
                               if(matches[n][l] == temp2) {
                                   if(Math.abs(k / 3 - n / 3) == 1) {
                                       input[i][j]--;
                                   } else {
                                       input[i][j]++;
                                   }
                               }
                           }
                       }
                    }
                }
            }
        }
        int sTemp = 0;
        int win = 1;
        for(int i = 0; i < matches[0].length; i++) {
            sTemp = matches[6][i] - matches[7][i];
            if(sTemp > 0)
                win = 1;
            else
                win = -1;
            for(int j = 0; j < 6; j++) {
		//please fix this future me, this is by far the dumbest piece of code I've ever written.
                input[teams.indexOf(matches[j][i])][teams.size()] += win * (j / 3 * -2 + 1);
                input[teams.indexOf(matches[j][i])][teams.size() + 1] += sTemp * (j / 3 * -2 + 1);
            }
        }
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j] + ", ");
            }
            System.out.println("");
        }
	double[][] output = new double[input.length][input[0].length];
        output = RREF.rref(RREF.rref(input));
	for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[0].length; j++) {
                System.out.print(output[i][j] + ", ");
            }
            System.out.println("");
        }
	System.out.println("Teams\tWin Score\t\tPoints Score");
	for(int i = 0; i < teams.size(); i++) {
	    System.out.println(teams.get(i) + "\t" + output[i][teams.size()] + "\t" + output[i][teams.size() + 1]);
	}
    }
}
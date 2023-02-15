package ufrpe.flyfood;

import ufrpe.flyfood.plot.GraficosFlyFood;

public class Main {
    public static void main(String[] args) {
        String[][] caminho = {
                {"0", "0", "V", "0", "E", "W", "0"},
                {"0", "0", "0", "0", "0", "0", "Q"},
                {"A", "R", "Z", "0", "0", "D", "0"},
                {"0", "0", "0", "0", "0", "K", "N"},
                {"0", "0", "F", "0", "C", "0", "0"},
                {"0", "O", "0", "0", "0", "L", "0"},
                {"0", "0", "0", "0", "U", "0", "0"},
        };
        FlyFood flyFood = new FlyFood(caminho);
        GraficosFlyFood example = new GraficosFlyFood();
        example.mostrarPontos(flyFood.pontos);
        example.melhorAlgoritmo();
        example.melhorPermutacao();

        System.out.printf("Quantidade de pontos: %s%n", flyFood.pontos.size()-1);
        long startTime = System.nanoTime();
        FlyFoodSolucaoDto flyFoodSolucao = flyFood.buscarMelhorCaminho();
        long endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime);
        System.out.println("|| === --- === --- === MENOR ROTA === --- === --- === ||");
        System.out.printf("Rota: %s%nCusto: %s%n", flyFoodSolucao.getMelhorRota(), flyFoodSolucao.getCusto());
        System.out.printf("Total de permutações: %s%n", flyFoodSolucao.getPermutacoes());
        System.out.println("|| === --- === --- === --- === --- === --- === --- === ||");
        System.out.printf("Finished time in seconds: %.4f s%n", (elapsedTime / 1000000000 ));

    }
}